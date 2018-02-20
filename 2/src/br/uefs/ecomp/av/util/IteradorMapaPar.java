package br.uefs.ecomp.av.util;

/**
 * A Classe (extra) IteradorMapaPar, implementada (somente por conveniência) a
 * partir dos métodos da interface Iterador, permite ser utilizada para manipular
 * um mapa sem fazer algum tipo de alteração, seja elementar ou estrutural.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class IteradorMapaPar implements Iterador {
    
    private final Object vetor[];
    private int posicao = 0;

    /** Construtor da classe, recebe um vetor e faz uma cópia num objeto de mesmo tipo.
     * 
     * @param vetor
     */
    public IteradorMapaPar(Object vetor[]) {
      this.vetor = vetor;
    }

    /** Método que verifica se ainda há mais posições no vetor atual, retornando
     * false para não e true caso sim.
     * 
     * @return boolean (true ou false)
     */
    @Override
    public boolean temProximo() {
        return (posicao < vetor.length);
    }

    /** Método que retorna o par desejado, após verificação de existência de
     * um próximo elemento (método anterior), antes do retorno uma posição é
     * avançada, e assim sucessivamente, até não haver próximo par para retornar.
     * 
     * @return Comparable chave
     */
    @Override
      public Comparable obterProximo() {

        if (temProximo()) {

            while (vetor[posicao] == null) {
                posicao++;
            }
            return (Comparable) (vetor[posicao++]);
        }
        return null;
    }
}