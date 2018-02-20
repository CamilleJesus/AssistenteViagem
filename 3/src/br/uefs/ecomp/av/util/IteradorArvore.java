package br.uefs.ecomp.av.util;


/**
 * A Classe IteradorArvore, implementada a partir dos métodos da interface Iterador,
 * permite ser utilizada para manipular uma árvore sem fazer algum tipo de alteração,
 * seja elementar ou estrutural.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class IteradorArvore implements Iterador {

    private CelulaArvore aux;
    private final ILista pilha = new Lista();

    /** Construtor da classe, recebe a raiz da árvore e a insere no início de uma
     * pilha.
     * 
     * @param raiz
     */
    public IteradorArvore(CelulaArvore raiz) {
        
        if (raiz != null) {
            pilha.inserirInicio((Comparable) raiz);
            aux = raiz.getEsquerda();
        }
    }
    
    /** Método que verifica se a lista está vazia, enquanto não estiver, haverá
     * uma próxima célula na árvore.
     * @return true or falsse
     */
    @Override
    public boolean temProximo() {
        return (!(pilha.estaVazia()));
    }
    
    /** Método que retorna o próximo elemento da pilha, até o final.
     * 
     * @return Comparable aux2
     */
    @Override
    public Comparable obterProximo() {

        while (aux != null) {
            pilha.inserirInicio((Comparable) aux);
            aux = aux.getEsquerda();
        }
        aux = ((CelulaArvore) pilha.removerInicio());
        CelulaArvore aux2 = aux;

        if (aux.getDireita() != null) {
            pilha.inserirInicio((Comparable) aux.getDireita());
            aux = aux.getDireita();
            aux = aux.getEsquerda();
        } else {
            aux = null;
        }
        return aux2.getObjeto();
    }
}