package br.uefs.ecomp.av.model.exception;


/**
 * Classe de Exceção de dado inexistente.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class DadoInexistenteException extends Exception {
    
    public DadoInexistenteException() {
        super();
    }

    public DadoInexistenteException(String str) {
        super(str);
    }

    public DadoInexistenteException(Throwable exception) {
        super(exception);
    }
}
