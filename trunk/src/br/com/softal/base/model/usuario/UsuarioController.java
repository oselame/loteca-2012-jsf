package br.com.softal.base.model.usuario;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import br.com.softal.base.service.ServiceException;
import br.com.softal.loteca.LtcServiceLocator;


@ManagedBean(name = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {
 
    private Usuario usuario;
 
    public UsuarioController() {
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
 
    public Usuario getUsuario() {
        return usuario;
    }
 
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String login() {
    	return "/templates/template.xhtml";
    }
 
}
