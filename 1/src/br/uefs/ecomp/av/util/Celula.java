package br.uefs.ecomp.av.util;


/**
 * Classe Célula, responsável pela criação de cada elemento da lista encadeada,
 * recebe um objeto para criação individual do elemento.
 * 
 * @author Camille Jesus
 */
public class Celula {
    
    private Object objeto;
    private Celula proximo;
    
    /** Construtor da classe, responsável pela criação de uma nova célula, recebe
     * um objeto e cria uma célula específica para ele.
     * 
     * @param objeto
     */
    public Celula(Object objeto) {
        this.objeto = objeto;
        this.proximo = null;
    }

    /** Método que retorna um objeto da célula.
     * 
     * @return Object objeto
     */
    public Object getObjeto() {
        return objeto;
    }
    
    /** Método que retorna o campo próximo de uma célula, uma referência para a
     * célula seguinte à esta.
     * 
     * @return Celula proximo
     */
    public Celula getProximo() {
        return proximo;
    }    
    
    /** Método que recebe um objeto como parâmetro e altera seu valor:
     * 
     * @param objeto
     */
    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }

    /** Método que recebe uma referência para a célula seguinte e altera seu valor:
     * 
     * @param proximo
     */
    public void setProximo(Celula proximo) {
        this.proximo = proximo;
    }
}