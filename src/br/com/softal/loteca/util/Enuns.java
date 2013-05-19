package br.com.softal.loteca.util;

public class Enuns {
    public enum SituacaoLoteca {
        CADASTRAMENTO(1l),
        ANDAMENTO(2l),
        CONCLUIDA(3l);
        
        private Long valor;
        
        private SituacaoLoteca(Long valor) {
            this.valor = valor;
        }
        
        public long longValue() {
        	return this.valor;
        }
    };
    
    public enum SituacaoData {
    	 CADASTRAMENTO(1l),
         ANDAMENTO(2l),
         PROCESSAMENTO(3L),
         CONCLUIDA(4l);
    	 
    	 private Long valor;
         
         private SituacaoData(Long valor) {
             this.valor = valor;
         }
         
         public long longValue() {
         	return this.valor;
         }
    }
    
    public enum TipoResultado {
    	EMPATE(0l),
    	COLUNA1(1l),
    	COLUNA2(2L);
    	
    	private Long valor;
    	
    	private TipoResultado(Long valor) {
    		this.valor = valor;
    	}
    	
    	public long longValue() {
    		return this.valor;
    	}
    }
    
    public enum TipoJogo {
    	SIMPLES(0l),
    	DUPLO(1l),
    	TRIPLO(2L);
    	
    	private Long valor;
    	
    	private TipoJogo(Long valor) {
    		this.valor = valor;
    	}
    	
    	public long longValue() {
    		return this.valor;
    	}
    }
    
}
