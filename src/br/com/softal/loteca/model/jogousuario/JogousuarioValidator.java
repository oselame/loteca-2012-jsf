package br.com.softal.loteca.model.jogousuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.softal.base.service.ServiceException;
import br.com.softal.loteca.model.jogo.Jogo;
import br.com.softal.loteca.util.Enuns.TipoJogo;


public class JogousuarioValidator {
	
	public static void gerarJogousuarioAleatorio(List<Jogousuario> jogos) throws ServiceException {
		Random random = new Random(System.currentTimeMillis());
	    int nNinhaDuplo = random.nextInt(14);
	    int posi = 0;
	    for (Jogousuario j : jogos) {
			j.setFlColuna1(0l);
			j.setFlEmpate(0l);
			j.setFlColuna2(0l);
			
			int col = random.nextInt(3);
			if (col == 0) {
				j.setFlColuna1(1l);
			} else if (col == 1) {
				j.setFlEmpate(1l);
			} else {
				j.setFlColuna2(1l);					
			}
			
			if (posi == nNinhaDuplo) {
				if (j.getFlColuna1().intValue() == 1) {
					if (random.nextInt(2) == 0) {
						j.setFlEmpate(1l);
					} else {
						j.setFlColuna2(1l);
					}
				} else if (j.getFlEmpate().intValue() == 1) {
					if (random.nextInt(2) == 0) {
						j.setFlColuna1(1l);
					} else {
						j.setFlColuna2(1l);
					}
				} else {
					if (random.nextInt(2) == 0) {
						j.setFlColuna1(1l);
					} else {
						j.setFlEmpate(1l);
					}						
				}
			}
			
			posi++;
	    }
	}

	public static void validarJogousuario(List<Jogousuario> jogos) throws ServiceException {
		int nuDuplos = 0;
		int nuTriplos = 0;
		for (Jogousuario ju : jogos) {
			ju.setTpJogo(0l);
			if (ju.getFlColuna1() == 0 && ju.getFlEmpate() == 0 && ju.getFlColuna2() == 0) {
				throw new ServiceException("warning_jogo_nao_cadastrado");
			}
			
			if (ju.getFlColuna1() == 1 && ju.getFlEmpate() == 1 && ju.getFlColuna2() == 1) {
				nuTriplos++;
			}
			
			if (ju.getFlColuna1() == 1 && ju.getFlEmpate() == 1 && ju.getFlColuna2() == 0) {
				nuDuplos++;
				ju.setTpJogo(1l);
			} else if (ju.getFlColuna1() == 1 && ju.getFlEmpate() == 0 && ju.getFlColuna2() == 1) {
				nuDuplos++;
				ju.setTpJogo(1l);
			} else if (ju.getFlColuna1() == 0 && ju.getFlEmpate() == 1 && ju.getFlColuna2() == 1) {
				nuDuplos++;
				ju.setTpJogo(1l);
			}
		}
		
		if (nuTriplos > 0) {
			throw new ServiceException("warning_jogo_triplo_nao_permitido");
		}
		if (nuDuplos == 0) {
			throw new ServiceException("warning_jogo_duplo_nao_cadastrado");
		} else if (nuDuplos > 1) {
			throw new ServiceException("warning_exite_mais_de_um_jogo_duplo_cadastrado");
		}
	}
	
	public static String geraBytesJogos(List<Jogousuario> jogos) throws ServiceException {
		String deBytes = "";
		for (Jogousuario ju : jogos) {
			deBytes += ju.getFlColuna1().toString() + ju.getFlEmpate().toString() + ju.getFlColuna2().toString();
		}
		return deBytes;
	}
	
	public static List<Jogousuario> getJogousuario(List<Jogo> jogos, String historico) {
		List<Jogousuario> lista = new ArrayList<Jogousuario>();
		int j = 0;
		for (int i = 0; i < 14;i++) {
			String sub = historico.substring(j, j+3);
			
			j = j + 3;		
			
			Jogousuario ju = new Jogousuario();
			ju.setJogo( jogos.get(i) );
			ju.setFlColuna1( Long.valueOf(sub.substring(0,1)) );
			ju.setFlEmpate(  Long.valueOf(sub.substring(1,2)) );
			ju.setFlColuna2( Long.valueOf(sub.substring(2,3)) );
			
			ju.setTpJogo( TipoJogo.SIMPLES.longValue() );
			if (ju.getFlColuna1() == 1 && ju.getFlEmpate() == 1) {
				ju.setTpJogo( TipoJogo.DUPLO.longValue() );
			} 
			if (ju.getFlColuna1() == 1 && ju.getFlColuna2() == 1) {
				ju.setTpJogo( TipoJogo.DUPLO.longValue() );
			}
			if (ju.getFlEmpate() == 1 && ju.getFlColuna2() == 1) {
				ju.setTpJogo( TipoJogo.DUPLO.longValue() );
			}
			lista.add( ju );
		}
		return lista;
	}
}
