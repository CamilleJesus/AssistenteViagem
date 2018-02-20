package br.uefs.ecomp.av.util;


/** 
 * Classe Fila, implementada a partir dos métodos da interface IFila.
 * 
 * @author Camille Jesus
 */
public class Fila implements IFila {
    
    private ILista fila;   //Declara um objeto do tipo ILista, para usar métodos da lista.

    /** Construtor da classe, instancia o objeto tipo lista "fila", que aponta
     * para nulo.
     */
    public Fila() {
        fila = new Lista();
    }
    
    /** Método que verifica se a fila está vazia.
     * 
     * @return boolean (true ou false)
     */
    @Override
    public boolean estaVazia() {
        return (fila.estaVazia());
    }

    /** Método que verifica o tamanho da fila em tempo de execução.
     * 
     * @return int tamanho
     */
    @Override
    public int obterTamanho() {
        return (fila.obterTamanho());
    }

    /** Método que recebe um objeto para inserir no final da fila.
     * 
     * @param o
     */
    @Override
    public void inserirFinal(Object o) {
        fila.inserirFinal(o);
    }

    /** Método que remove o primeiro elemento da fila.
     * 
     * @return Object objeto
     */
    @Override
    public Object removerInicio() {
        return (fila.removerInicio());
    }

    /** Método que recupera o primeiro elemento da fila.
     * 
     * @return Object objeto
     */
    @Override
    public Object recuperarInicio() {
        return (fila.recuperar(0));
    }

    /** Método que percorre a fila e retorna cada objeto sequencialmente.
     * 
     * @return Iterador iterador
     */
    @Override
    public Iterador iterador() {
        return (fila.iterador());
    }    
}