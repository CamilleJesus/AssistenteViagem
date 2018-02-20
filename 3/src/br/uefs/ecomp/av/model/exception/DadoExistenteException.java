package br.uefs.ecomp.av.model.exception;


/**
 * Classe de Exceção de dado existente.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class DadoExistenteException extends Exception {
    
    public DadoExistenteException() {
        super();
    }

    public DadoExistenteException(String str) {
        super(str);
    }

    public DadoExistenteException(Throwable exception) {
        super(exception);
    }
}