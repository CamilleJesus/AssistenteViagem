package br.uefs.ecomp.av.util;


import java.io.Serializable;

/**
 * Classe CélulaLista, responsável pela criação de cada elemento da lista encadeada,
 * recebe um objeto para criação individual do elemento.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class CelulaLista implements Serializable {
    
    private Object objeto;
    private CelulaLista proximo;
    
    /** Construtor da classe, responsável pela criação de uma nova célula, recebe
     * um objeto e cria uma célula específica para ele.
     * 
     * @param objeto
     */
    public CelulaLista(Object objeto) {
        this.objeto = objeto;
        this.proximo = null;
    }

    /** Método que retorna o objeto de uma célula.
     * 
     * @return Object objeto
     */
    public Object getObjeto() {
        return objeto;
    }
    
    /** Método que recebe um objeto como parâmetro e altera seu valor:
     * 
     * @param objeto
     */
    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }
    
    /** Método que retorna o campo próximo de uma célula, uma referência para a
     * célula seguinte à esta.
     * 
     * @return CelulaLista proximo
     */
    public CelulaLista getProximo() {
        return proximo;
    }    
    
    /** Método que recebe a referência para uma célula seguinte e altera seu valor:
     * 
     * @param proximo
     */
    public void setProximo(CelulaLista proximo) {
        this.proximo = proximo;
    }
}