package br.uefs.ecomp.av.model.exception;


/**
 * Classe de Exceção de campo vazio.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class CampoVazioException extends Exception {
    
    public CampoVazioException() {
        super();
    }

    public CampoVazioException(String str) {
        super(str);
    }

    public CampoVazioException(Throwable exception) {
        super(exception);
    }
}