package br.com.softal.base.bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import br.com.softal.base.model.usuario.Usuario;
import br.com.softal.base.service.ServiceException;
import br.com.softal.loteca.LtcServiceLocator;

@ManagedBean(name="authenticationBean")
@SessionScoped
public class AuthenticationBean {
	
	private Usuario usuario; 
	
	public String doLogin() throws IOException, ServletException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/j_spring_security_check");
        dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
        x();
        FacesContext.getCurrentInstance().responseComplete();
        return null;
    }
	
	public void x() {
		usuario = new Usuario();
        SecurityContext context = SecurityContextHolder.getContext();
        if (context instanceof SecurityContext){
            Authentication authentication = context.getAuthentication();
            if (authentication instanceof Authentication){
                //usuario.setDeLogin(((Usuario)authentication.getPrincipal()).getDeLogin());
            	try {
            		if (authentication.getPrincipal().toString().equals("anonymousUser")) {
            			usuario.setDeLogin("");
            		} else {
            			String deLogin = ((User) authentication.getPrincipal()).getUsername();
            			usuario = LtcServiceLocator.getInstance().getLotecaService().findUsuarioByLogin(deLogin);
            		}
				} catch (ServiceException e) {
					e.printStackTrace();
				}
            }
        }
	}
	
	public boolean isLogado() {
		// System.out.println( SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() );
		return !SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser");
	}

    public String doLogout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/logout.xhtml";
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
 
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
