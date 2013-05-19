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
        
    };
    
}
