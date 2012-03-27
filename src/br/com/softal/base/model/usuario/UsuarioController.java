package br.com.softal.base.model.usuario;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.softal.loteca.model.usuario.Usuario;

@ManagedBean
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
                usuario.setCdUsuario(((Usuario)authentication.getPrincipal()).getCdUsuario());
            }
        }
    }
 
    public Usuario getUsuario() {
        return usuario;
    }
 
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
 
}
