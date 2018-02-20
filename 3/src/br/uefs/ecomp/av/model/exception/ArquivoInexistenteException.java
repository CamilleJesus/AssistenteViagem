package br.uefs.ecomp.av.model.exception;


/**
 * Classe de Exceção de arquivo inexistente.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class ArquivoInexistenteException extends Exception {
   
    public ArquivoInexistenteException() {
        super();
    }

    public ArquivoInexistenteException(String str) {
        super(str);
    }
    
    public ArquivoInexistenteException(Throwable exception) {
        super(exception);
    }
}