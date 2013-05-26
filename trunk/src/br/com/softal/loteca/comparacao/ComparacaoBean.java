package br.com.softal.loteca.comparacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.softal.base.bean.AbstractManegedBean;
import br.com.softal.base.model.usuario.Usuario;
import br.com.softal.loteca.dto.ComparacaoDto;
import br.com.softal.loteca.model.data.Data;
import br.com.softal.loteca.model.jogousuario.Jogousuario;
import br.com.softal.loteca.model.loteca.Loteca;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;
import br.com.softal.loteca.model.usuariodata.AproveitamentoDTO;
import br.com.softal.loteca.model.usuariodata.Usuariodata;

@SuppressWarnings("serial")
@ManagedBean(name="comparacaoBean")
@RequestScoped
public class ComparacaoBean extends AbstractManegedBean implements Serializable {
	
	private Long cdUsuario;
	private Long cdData;
	private String deResultado;
	
	private List<ComparacaoDto> jogos;
	private List<Lotecausuario> lotecausuarios;
	private List<Data> datas;
	private Usuario usuariologado;
	private Loteca loteca;
	
	public String getDeResultado() {
		return deResultado;
	}
	
	public Long getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUsuario(Long cdUsuario) {
		this.cdUsuario = cdUsuario;
	}
	
	public List<Lotecausuario> getLotecausuarios() {
		return lotecausuarios;
	}
	
	public List<Data> getDatas() {
		return datas;
	}
	
	public Long getCdData() {
		return cdData;
	}

	public void setCdData(Long cdData) {
		this.cdData = cdData;
	}
	
	private void ordenaLotecaUsuarios() {
		Collections.sort(lotecausuarios, new Comparator<Lotecausuario>() {
			@Override
			public int compare(Lotecausuario o1, Lotecausuario o2) {
				return o1.getUsuario().getNmUsuario().compareTo(o2.getUsuario().getNmUsuario());
			}
		});
	}
	
	private void removeUsuarioLogadodaLista() {
		for (Lotecausuario lu : this.lotecausuarios) {
			if (lu.getUsuario().getCdUsuario().equals(this.usuariologado.getCdUsuario())) {
				this.lotecausuarios.remove(lu);
				break;
			}
		}
	}
	
	public List<ComparacaoDto> getJogos() {
		return jogos;
	}
	
	private void carregaResultados(Data data, Usuario usuariologado, Usuario adversario) {
		try {
			List<Usuariodata> datas = super.getLotecaService().findAllUsuariodata(this.getLotecaativa(), data);
			Map<Long, Usuariodata> mapUsuarios = new HashMap<Long, Usuariodata>();
			for (Usuariodata aprov : datas) {
				mapUsuarios.put( aprov.getLotecausuario().getUsuario().getCdUsuario() , aprov);
			}
			
			Usuariodata usuLogado = mapUsuarios.get( usuariologado.getCdUsuario() );
			Usuariodata usuAdver = mapUsuarios.get( adversario.getCdUsuario() );
			
			this.deResultado = usuariologado.getNmUsuario() + "  " + usuLogado.getNuPontoscartao() + " x " + usuAdver.getNuPontoscartao() + "  " + adversario.getNmUsuario();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void init() {
		try {
			usuariologado = super.getUsuariologado();
			loteca = super.getLotecaativa();
			lotecausuarios = super.getLotecaService().findAllLotecausuarioAtivoByLoteca(loteca);
			datas = super.getLotecaService().findAllDatasEncerradas(loteca);
			jogos = new ArrayList<ComparacaoDto>();
			
			removeUsuarioLogadodaLista();
			this.ordenaLotecaUsuarios();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initializeEntity() {
	}
	
	public String abrirComparacaoApostas() {
		
		return "/pages/user/comparacao/eltcConComparacaoApostas.xhtml";
	}
	
	public String carregaJogousuario() {
		try {
			Data data = super.getLotecaService().findData(this.cdData);
			
			
			Lotecausuario lotecausuariologado = super.getLotecaService().findLotecausuarioAtivo(usuariologado);
			List<Jogousuario> jogosusuariologado = super.getLotecaService().findAllJogoUsuario(data, lotecausuariologado);
			Map<Long, Jogousuario> mapUsuarioLogado = new HashMap<Long, Jogousuario>();
			for (Jogousuario jo : jogosusuariologado) {
				mapUsuarioLogado.put(jo.getJogo().getCdJogo(), jo);
			}
			
			Usuario usuario = new Usuario();
			usuario.setCdUsuario(this.cdUsuario);
			Lotecausuario lotecausuario = super.getLotecaService().findLotecausuarioAtivo(usuario);
			List<Jogousuario> jogosusuario = super.getLotecaService().findAllJogoUsuario(data, lotecausuario);
			Map<Long, Jogousuario> mapAdversario = new HashMap<Long, Jogousuario>();
			for (Jogousuario jo : jogosusuario) {
				mapAdversario.put(jo.getJogo().getCdJogo(), jo);
			}
			
			jogos.clear();
			for (long l = 1; l<=14;l++) {
				Jogousuario jogoUsuarioLogado = mapUsuarioLogado.get( l );
				Jogousuario jogoAdversario = mapAdversario.get( l );
				ComparacaoDto dtoUsuarioLogado = new ComparacaoDto( usuariologado.getNmUsuario(), jogoUsuarioLogado.getTpJogo(), jogoUsuarioLogado.getFlColuna1(), jogoUsuarioLogado.getFlEmpate(), jogoUsuarioLogado.getFlColuna2());
				ComparacaoDto dtoAdversario = new ComparacaoDto( lotecausuario.getUsuario().getNmUsuario(), jogoAdversario.getTpJogo(), jogoAdversario.getFlColuna1(), jogoAdversario.getFlEmpate(), jogoAdversario.getFlColuna2());
				
				ComparacaoDto dto = new ComparacaoDto(l, jogoUsuarioLogado.getJogo().getDeJogo(), jogoUsuarioLogado.getJogo().getTpResultadofinal());
				dto.setJogos(new ArrayList<ComparacaoDto>());
				dto.getJogos().add(dtoUsuarioLogado);
				dto.getJogos().add(dtoAdversario);
				
				jogos.add(dto);
			}
			
			this.carregaResultados( data, this.usuariologado, lotecausuario.getUsuario());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/pages/user/comparacao/eltcConComparacaoApostas.xhtml";
	}

}
