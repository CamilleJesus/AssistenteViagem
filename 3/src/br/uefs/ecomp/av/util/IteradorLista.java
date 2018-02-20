package br.uefs.ecomp.av.util;


/**
 * A Classe IteradorLista, implementada a partir dos métodos da interface Iterador,
 permite ser utilizada para manipular uma lista encadeada sem fazer algum
 tipo de alteração, seja elementar ou estrutural.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class IteradorLista implements Iterador {
    
    private CelulaLista no;
    
    /** Construtor da classe, recebe o primeiro elemento da lista e faz uma cópia
     * num objeto de mesmo tipo.
     * 
     * @param primeiro
     */
    public IteradorLista(CelulaLista primeiro) {
        no = primeiro;
    }
            
    /** Método que verifica se há um objeto no campo próximo da célula atual,
     * retornando false para vazia e true caso haja elementos nela.
     * 
     * @return boolean (true ou false)
     */
    @Override
    public boolean temProximo() {
        return (no != null);
    }

    /** Método que retorna o objeto desejado, após verificação de existência de
     * um próximo elemento (método anterior), antes do retorno o auxiliar avança
     * uma posição, e assim sucessivamente, até não haver próximo para retornar.
     * 
     * @return Object o
     */
    @Override
    public Comparable obterProximo() {
        
        if (this.temProximo()) {
            Object o = no.getObjeto();
            no = no.getProximo();
            return ((Comparable) o);
        }
        return null;
    }    
}