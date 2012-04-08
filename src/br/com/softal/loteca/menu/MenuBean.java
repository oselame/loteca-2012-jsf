package br.com.softal.loteca.menu;

import java.io.Serializable;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import br.com.softal.base.message.MessagesWeb;
import br.com.softal.base.model.usuario.Usuario;
import br.com.softal.base.model.usuariogrupo.Usuariogrupo;
import br.com.softal.loteca.model.jogousuario.JogousuarioBean;
import br.com.softal.loteca.util.Constantes;


@SuppressWarnings("serial")
@ManagedBean(name="menuBean")
@SessionScoped
public class MenuBean implements Serializable {
	
	private MenuModel model;
	
	public MenuModel getModel() {  
        return model;  
    } 
	
	public MenuBean() {
		this.geraMenu();
	}
	
	private void geraMenu() {
		model = new DefaultMenuModel();
		MessagesWeb msg = new MessagesWeb();
		Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(Constantes.USUARIO_LOGADO);
		
		FacesContext facesCtx = FacesContext.getCurrentInstance();
		ELContext elCtx = facesCtx.getELContext();
		ExpressionFactory expFact = facesCtx.getApplication().getExpressionFactory();
		
		for (Usuariogrupo usuariogrupo : usuario.getUsuariogrupos()) {
			if (usuariogrupo.getGrupo().getNmGrupo().equals(Constantes.ROLE_ADMIN)) {
				Submenu smSeguranca = new Submenu(); 
				smSeguranca.setLabel(msg.getMessage("menu_seguranca"));
				
				//-- Grupo
				MenuItem miGrupo = new MenuItem();
				miGrupo.setValue(msg.getMessage("menu_grupos"));
				miGrupo.setUrl("/pages/adm/grupo/eltcConGrupo.xhtml");
				smSeguranca.getChildren().add(miGrupo);
				
				//-- Action
				MenuItem miAction = new MenuItem();
				miAction.setValue(msg.getMessage("menu_actions"));
				miAction.setUrl("/pages/adm/action/eltcConAction.xhtml");
				smSeguranca.getChildren().add(miAction);
				
				//-- GrupoAction
				MenuItem miGrupoaction = new MenuItem();
				miGrupoaction.setValue(msg.getMessage("menu_grupo_actions"));
				miGrupoaction.setUrl("/pages/adm/grupoaction/eltcConGrupoaction.xhtml");
				smSeguranca.getChildren().add(miGrupoaction);
				
				//-- projeto
				MenuItem miProjeto = new MenuItem();
				miProjeto.setValue(msg.getMessage("menu_projetos"));
				miProjeto.setUrl("/pages/adm/projeto/eltcConProjeto.xhtml");
				smSeguranca.getChildren().add(miProjeto);
				
				//-- usuarios
				MenuItem miUsuarios = new MenuItem();
				miUsuarios.setValue(msg.getMessage("menu_usuarios"));
				miUsuarios.setUrl("/pages/adm/usuario/eltcConUsuario.xhtml");
				smSeguranca.getChildren().add(miUsuarios);
				
				//-- usuarios
				MenuItem miUsuariogrupo = new MenuItem();
				miUsuariogrupo.setValue(msg.getMessage("menu_loteca_usuario_grupo"));
				miUsuariogrupo.setUrl("/pages/adm/usuariogrupo/eltcConUsuariogrupo.xhtml");
				smSeguranca.getChildren().add(miUsuariogrupo);
				
				model.addSubmenu(smSeguranca); 
				
				/*********************************************************************************/
				/*********************************************************************************/
				/*********************************************************************************/
				Submenu smAdministracao = new Submenu(); 
				smAdministracao.setLabel(msg.getMessage("menu_seguranca"));
				
				//-- clubes
				MenuItem miClubes = new MenuItem();
				miClubes.setValue(msg.getMessage("menu_times"));
				miClubes.setUrl("/pages/adm/clube/eltcConClube.xhtml");
				smAdministracao.getChildren().add(miClubes);
				
				//-- lotecausuario
				MenuItem miLotecausuario = new MenuItem();
				miLotecausuario.setValue(msg.getMessage("menu_loteca_usuario"));
				miLotecausuario.setUrl("/pages/adm/lotecausuario/eltcConLotecausuario.xhtml");
				smAdministracao.getChildren().add(miLotecausuario);
				
				//-- data
				MenuItem miData = new MenuItem();
				miData.setValue(msg.getMessage("menu_data"));
				miData.setUrl("/pages/adm/data/eltcConData.xhtml");
				smAdministracao.getChildren().add(miData);
				
				model.addSubmenu(smAdministracao);
			} else if (usuariogrupo.getGrupo().getNmGrupo().equals(Constantes.ROLE_USER)) {
				Submenu smLoteca = new Submenu(); 
				smLoteca.setLabel(msg.getMessage("menu_loteca"));
				
				//-- aposta
				MenuItem miJogousuario = new MenuItem();
				miJogousuario.setValue(msg.getMessage("menu_jogo_usuario"));
				miJogousuario.setActionExpression(expFact.createMethodExpression(elCtx, "#{jogousuarioBean.abrirCadJogousuario}", JogousuarioBean.class, new Class[0])); 
				miJogousuario.setAjax(false);
				miJogousuario.setUrl("#");
				smLoteca.getChildren().add(miJogousuario);
				
				//-- resultado
				MenuItem miResultado = new MenuItem();
				miResultado.setValue(msg.getMessage("menu_jogo_usuario"));
				miResultado.setActionExpression(expFact.createMethodExpression(elCtx, "#{jogousuarioBean.abrirCadJogousuario}", JogousuarioBean.class, new Class[0])); 
				miResultado.setAjax(false);
				miResultado.setUrl("#");
				smLoteca.getChildren().add(miResultado);
				
				model.addSubmenu(smLoteca);
			}
		}
	}

}
