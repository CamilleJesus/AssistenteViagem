package br.uefs.ecomp.av.util;


import java.io.Serializable;

/**
 * Classe CélulaMapa, responsável pela criação de cada par chave-valor do mapa,
 * recebe esses dois objetos para criação individual do elemento.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class CelulaMapa implements Comparable<CelulaMapa>, Serializable {
    
    private Object chave;
    private Object valor;
    
    /** Construtor da classe, responsável pela criação de uma nova célula, recebe
     * dois objetos e cria uma célula específica para eles.
     * 
     * @param chave
     * @param valor
     */
    public CelulaMapa(Object chave, Object valor){
        this.chave = chave;
        this.valor = valor;
    }
    
    /** Método que retorna a chave de uma célula.
     * 
     * @return Object chave
     */
    public Object getChave() {
        return chave;
    }

    /** Método que recebe uma chave como parâmetro e altera seu valor:
     * 
     * @param chave
     */
    public void setChave(Object chave) {
        this.chave = chave;
    }

    /** Método que retorna o valor de uma célula.
     * 
     * @return Object valor
     */
    public Object getValor() {
        return valor;
    }
    
    /** Método que recebe um valor como parâmetro e o altera:
     * 
     * @param valor
     */
    public void setValor(Object valor) {
        this.valor = valor;
    }
    
    /** Método que recebe uma célula como parâmetro e o compara com a célula atual,
     * o critério é a chave da mesma. Se a chave atual vier antes, retorna -1, se
     * vier depois, retorna 1 e por fim, se for igual retorna 0.
     * 
     * @param cm
     * @return int
     */
    @Override
    public int compareTo(CelulaMapa cm) {
        return chave.toString().compareTo(cm.toString());
    }
}