package br.com.softal.base.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import br.com.softal.base.model.usuario.Usuario;
import br.com.softal.base.service.ServiceException;
import br.com.softal.loteca.LtcServiceLocator;
import br.com.softal.loteca.util.Constantes;

@SuppressWarnings("serial")
@ManagedBean(name="authenticationBean")
@SessionScoped
public class AuthenticationBean extends AbstractManegedBean<Usuario> implements Serializable {
	
	public String doLogin() throws IOException, ServletException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/j_spring_security_check");
        dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
        carregaUsuarioLogado();
        FacesContext.getCurrentInstance().responseComplete();
        return null;
    }
	
	@PostConstruct  
    @SuppressWarnings("unused")  
    private void handleErrorMessage()  {  
        Exception e = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY);  
        if (e instanceof BadCredentialsException)  {  
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY, null);  
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username or password not valid.", null));  
        }  
    } 
	
	public void carregaUsuarioLogado() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context instanceof SecurityContext){
            Authentication authentication = context.getAuthentication();
            if (authentication instanceof Authentication){
            	try {
            		if (authentication.getPrincipal().toString().equals("anonymousUser")) {
            			super.getUsuariologado().setDeLogin("");
            		} else {
            			String deLogin = ((User) authentication.getPrincipal()).getUsername();
            			Usuario usuario = LtcServiceLocator.getInstance().getLotecaService().findUsuarioByLogin(deLogin);
            			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Constantes.USUARIO_LOGADO, usuario);
            		}
				} catch (ServiceException e) {
					e.printStackTrace();
				}
            } else {
            	super.getMessages().addSucessMessage("msg_erro_usuario_senha_invalido");
            }
        } else {
        	super.getMessages().addSucessMessage("msg_erro_usuario_senha_invalido");
        }
	}
	
	public boolean isLogado() {
		try {
			return !SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser");
		} catch (Exception e) {
			return false;
		}
	}

    public String doLogout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/logout.xhtml";
    }
    
	@Override
	protected void initializeEntity() {
		setEntity(new Usuario());
	}
}
