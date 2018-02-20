package br.uefs.ecomp.av.util;


import br.uefs.ecomp.av.model.exception.DadoExistenteException;

import java.io.Serializable;

/**
 * Classe Arvore, implementada a partir dos métodos da interface IArvore.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class Arvore implements IArvore, Serializable {

    private CelulaArvore raiz;
    private int tamanho;
    
    /** Construtor da classe, faz o objeto célula "raiz" apontar para nulo e inicializa
     * o tamanho.
     */
    public Arvore() {
        raiz = null;
        tamanho = 0;
    }

    /** Método quee retorna o elemento raiz da árvore.
     *
     * @return CelulaArvore primeiro
     */ 
    public CelulaArvore getRaiz() {
        return raiz;
    }

    /** Método que verifica se a árvore está vazia, a condição já é feita no retorno;
     * se a raiz é nula, retorna "true" (valor lógico, verdadeiro) portanto a árvore
     * está vazia, caso não, retorna "false" e há pelo menos um elemento nela.
     * 
     * @return boolean (true ou false)
     */
    @Override
    public boolean estaVazia() {
        return (raiz == null);
    }

    /** Método que verifica o tamanho da árvore, sempre que um elemento é inserido,
     * tamanho é incrementado, esse método o retorna.
     * 
     * @return int tamanho
     */
    @Override
    public int obterTamanho() {
        return tamanho;
    }

    /** Método que insere o objeto na árvore, ele chama um método auxiliar que
     * insere (ordenado) recursivamente na devida posição da árvore, mantendo-a
     * sempre balanceada após inserção.
     * 
     * @param c
     * @throws DadoExistenteException
     */
    @Override
    public void inserir(Comparable c) throws DadoExistenteException {
        CelulaArvore nova = new CelulaArvore(c);
        inserirAVL(raiz, nova);
    }

    /** Método auxiliar de inserção.
     * 
     * @param atual
     * @param nova
     * @throws DadoExistenteException
     */
    public void inserirAVL(CelulaArvore atual, CelulaArvore nova)throws DadoExistenteException {

        if (atual == null) {
            raiz = nova;
            tamanho++;
        } else {
                
            if (nova.getObjeto().toString().compareTo(atual.getObjeto().toString()) < 0) {

                if (atual.getEsquerda() == null) {
                    atual.setEsquerda(nova);
                    nova.setPai(atual);
                    this.verificarBalanceamento(atual);
                    tamanho++;
                } else {
                    this.inserirAVL(atual.getEsquerda(), nova);
                }
            } else if (nova.getObjeto().toString().compareTo(atual.getObjeto().toString()) > 0) {

                if (atual.getDireita() == null) {
                    atual.setDireita(nova);
                    nova.setPai(atual);
                    this.verificarBalanceamento(atual);
                    tamanho++;
                } else {
                    this.inserirAVL(atual.getDireita(), nova);
                }
            } else {
                throw (new DadoExistenteException());
            }
        }
    }

    /** Método que é responsável por identificar o fator de balanceamento de cada
     * célula da árvore, a partir do cálculo de sua altura, para fazer rotação
     * simples ou dupla. Para isso, há quatro métodos auxiliares de rotação.
     *
     * @param atual
     */
    public void verificarBalanceamento(CelulaArvore atual) {
        this.setBalanceamento(atual);
        int balanceamento = atual.getBalanceamento();
        
        if (balanceamento == -2) {

            if (this.altura(atual.getEsquerda().getEsquerda()) >= this.altura(atual.getEsquerda().getDireita())) {
                atual = this.rotacaoDireita(atual);
            } else {
                atual = this.duplaRotacaoEsquerdaDireita(atual);
            }
        } else if (balanceamento == 2) {

            if (this.altura(atual.getDireita().getDireita()) >= this.altura(atual.getDireita().getEsquerda())) {
                atual = this.rotacaoEsquerda(atual);
            } else {
                atual = this.duplaRotacaoDireitaEsquerda(atual);
            }
        }

        if (atual.getPai() != null) {
            this.verificarBalanceamento(atual.getPai());
        } else {
            raiz = atual;
        }
    }
    
    /** Método que modifica o fator de balanceamento da célula.
     * 
     * @param atual 
     */
    private void setBalanceamento(CelulaArvore atual) {
        atual.setBalanceamento(this.altura(atual.getDireita()) - this.altura(atual.getEsquerda()));
    }

    /** Método que balancea a árvore rotacionando-a para a esquerda.
     *
     * @param inicial
     * @return CelulaArvore direita
     */
    public CelulaArvore rotacaoEsquerda(CelulaArvore inicial) {
        CelulaArvore direita = inicial.getDireita();
        direita.setPai(inicial.getPai());
        inicial.setDireita(direita.getEsquerda());

        if (inicial.getDireita() != null) {
            inicial.getDireita().setPai(inicial);
        }
        direita.setEsquerda(inicial);
        inicial.setPai(direita);

        if (direita.getPai() != null) {

            if (direita.getPai().getDireita() == inicial) {
                direita.getPai().setDireita(direita);
            } else if (direita.getPai().getEsquerda() == inicial) {
                direita.getPai().setEsquerda(direita);
            }
        }
        this.setBalanceamento(inicial);
        this.setBalanceamento(direita);
        return direita;
    }

    /** Método que balancea a árvore rotacionando-a para a direita.
     *
     * @param inicial
     * @return CelulaArvore esquerda
     */
    public CelulaArvore rotacaoDireita(CelulaArvore inicial) {
        CelulaArvore esquerda = inicial.getEsquerda();
        esquerda.setPai(inicial.getPai());
        inicial.setEsquerda(esquerda.getDireita());

        if (inicial.getEsquerda() != null) {
            inicial.getEsquerda().setPai(inicial);
        }
        esquerda.setDireita(inicial);
        inicial.setPai(esquerda);

        if (esquerda.getPai() != null) {

            if (esquerda.getPai().getDireita() == inicial) {
                esquerda.getPai().setDireita(esquerda);
            } else if (esquerda.getPai().getEsquerda() == inicial) {
                esquerda.getPai().setEsquerda(esquerda);
            }
        }
        this.setBalanceamento(inicial);
        this.setBalanceamento(esquerda);
        return esquerda;
    }

    /** Método que balancea a árvore rotacionando-a primeiramente para a esquerda
     * e depois para a direita.
     *
     * @param inicial
     * @return CelulaArvore
     */
    public CelulaArvore duplaRotacaoEsquerdaDireita(CelulaArvore inicial) {
        inicial.setEsquerda(this.rotacaoEsquerda(inicial.getEsquerda()));
        return (this.rotacaoDireita(inicial));
    }

    /** Método que balancea a árvore rotacionando-a primeiramente para a direita
     * e depois para a esquerda.
     *
     * @param inicial
     * @return CelulaArvore
     */
    public CelulaArvore duplaRotacaoDireitaEsquerda(CelulaArvore inicial) {
        inicial.setDireita(this.rotacaoDireita(inicial.getDireita()));
        return (this.rotacaoEsquerda(inicial));
    }

    /** Método que retorna a altura das celulas.
     *
     * @param atual 
     * @return int altura
     */
    public int altura(CelulaArvore atual) {

        if (atual == null) {
            return -1;
        }
        
        if ((atual.getEsquerda() == null) && (atual.getDireita() == null)) {
            return 0;
        } else if (atual.getEsquerda() == null) {
            return (1 + altura(atual.getDireita()));
        } else if (atual.getDireita() == null) {
            return (1 + altura(atual.getEsquerda()));
        } else {
            return (1 + Math.max(altura(atual.getEsquerda()), altura(atual.getDireita())));
        }
    }

    /** Método que encontra o objeto da árvore a ser removido, ele chama um método
     * auxiliar recursivo para remover da devida posição da árvore, mantendo-a
     * sempre balanceada após remoção.
l     * 
     * @param c
     */
    @Override
    public void remover(Comparable c) {
        removerAVL(raiz, c);
    }

    /** Método auxiliar de remoção.
     * 
     * @param atual
     * @param c 
     */
    public void removerAVL(CelulaArvore atual, Comparable c) {

        if (atual != null) {

            if (atual.getObjeto().toString().compareTo(c.toString()) > 0) {
                this.removerAVL(atual.getEsquerda(), c);
            } else if (atual.getObjeto().toString().compareTo(c.toString()) < 0) {
                this.removerAVL(atual.getDireita(), c);
            } else if (atual.getObjeto().toString().compareTo(c.toString()) == 0) {
                tamanho--;
                this.removerNoEncontrado(atual);
            }
        }
    }

    /** Método que remove a célula (encontrada) de fato.
     * 
     * @param atual 
     */
    public void removerNoEncontrado(CelulaArvore atual) {
        CelulaArvore aux;

        if (atual.getEsquerda() == null || atual.getDireita() == null) {

            if (atual.getPai() == null) {
                this.raiz = null;
                atual = null;
                return;
            }
            aux = atual;
        } else {
            aux = sucessor(atual);
            atual.setObjeto(aux.getObjeto());
        }
        CelulaArvore aux2;

        if (aux.getEsquerda() != null) {
            aux2 = aux.getEsquerda();
        } else {
            aux2 = aux.getDireita();
        }

        if (aux2 != null) {
            aux2.setPai(aux.getPai());
        }

        if (aux.getPai() == null) {
            this.raiz = aux2;
        } else {

            if (aux == aux.getPai().getEsquerda()) {
                aux.getPai().setEsquerda(aux2);
            } else {
                aux.getPai().setDireita(aux2);
            }
            this.verificarBalanceamento(aux.getPai());
        }
        aux = null;
    }

    /** Método que define célula sucessora à célula a ser removida.
     * 
     * @param atual
     * @return CelulaArvore auxiliar
     * 
     */
    public CelulaArvore sucessor(CelulaArvore atual) {

        if (atual.getDireita() != null) {
            CelulaArvore aux = atual.getDireita();

            while (aux.getEsquerda() != null) {
                aux = aux.getEsquerda();
            }
            return aux;
        } else {
            CelulaArvore aux2 = atual.getPai();

            while (aux2 != null && atual == aux2.getDireita()) {
                atual = aux2;
                aux2 = atual.getPai();
            }
            return aux2;
        }
    }

    /** Método responsável por buscar algum objeto em qualquer posição da árvore
     * e retorná-lo, caso o encontre.
     *
     * @param c
     * @return Comparable objeto
     */
    @Override
    public Comparable buscar(Comparable c) {
        CelulaArvore atual = raiz;

        while (atual != null) {

            if (atual.getObjeto().toString().compareTo(c.toString()) == 0) {
                return (atual.getObjeto());
            }

            if (atual.getObjeto().toString().compareTo(c.toString()) < 0) {
                atual = atual.getDireita();
            } else {
                atual = atual.getEsquerda();
            }
        }
        return null;
    }
    
    /** Método que cria um objeto do tipo Iterador, seu construtor recebe como
     * parâmetro a raiz da árvore, o iterador é retornado, tal método percorre 
     * a estrutura e retorna cada objeto sequencialmente.
     * 
     * @return Iterador iterador
     */
    @Override
    public Iterador iterador() {
        Iterador iterador = new IteradorArvore(raiz);
        return iterador;
    }
}