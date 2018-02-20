package br.uefs.ecomp.av.model;


import br.uefs.ecomp.av.util.Coordenadas;
import br.uefs.ecomp.av.util.ILista;
import br.uefs.ecomp.av.util.Iterador;
import br.uefs.ecomp.av.util.Lista;

import java.util.Objects;

/**
 * Classe Local, responsável pela criação de cada local do Assistente de Viagem.
 * 
 * @author Camille Jesus
 */
public class Local {
    
    private final String nome, estado, pais;
    private final Coordenadas localizacao;
    private ILista listaAtracao;

    public Local(String nome, Coordenadas localizacao, String estado, String pais) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.estado = estado;
        this.pais = pais;
        listaAtracao = new Lista();   //Cada local criado possuirá uma lista para atrações.
    }

    /** Método que retorna o nome de um local.
     * 
     * @return String nome
     */
    public String getNome() {
        return nome;
    }

    /** Método que retorna a localizacao de um local.
     * 
     * @return Coordenadas localizacao
     */
    public Coordenadas getLocalizacao() {
        return localizacao;
    }

    /** Método que retorna o nome do estado em que um local se situa.
     * 
     * @return String estado
     */
    public String getEstado() {
        return estado;
    }

    /** Método que retorna o nome do país em que um local se situa.
     * 
     * @return String estado
     */
    public String getPais() {
        return pais;
    }

    /** Método que retorna a lista de atrações de um local.
     * 
     * @return ILista listaAatracao
     */
    public ILista getListaAtracao() {
        return listaAtracao;
    }

    /** Método que retorna um código único do local, para ser usado em sua busca.
     * 
     * @return int hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.nome);
        hash = 53 * hash + Objects.hashCode(this.localizacao);
        return hash;
    }
    
    /** Método que verifica se dois locais são iguais retornando um booleano,
     * onde true significa que o objeto é igual e false que o objeto é diferente.
     * 
     * @param o
     * @return boolean (true ou false)
     */
    @Override
    public boolean equals(Object o) {
        if(o instanceof Local) {
            Local local = (Local) o;
            
            if (nome.equals(local.getNome()) && localizacao.equals(local.getLocalizacao())) {
                return true;
            }
        }
        return false;
    }

    /** Método que dá uma representação em String de um local.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return ("Local - " + "Nome: " + nome + localizacao.toString() + ", Estado: " + estado + ", País: " + pais + ".");
    }

    /** Método que retorna um objeto da classe Iterador, que faz referência à
     * lista de atrações de um determinado local, elas são listadas em ordem
     * alfabética.
     * 
     * @return Iterador iterador
     */
    public Iterador listarAtracoesOrdemAlfabetica() {
        Atracao[] vetorAtracao = new Atracao[listaAtracao.obterTamanho()];
        
        //A lista de atrações de um local é passada para um vetor de mesmo tamanho:
        for (int i = 0; i < listaAtracao.obterTamanho(); i++) {
            vetorAtracao[i] = (Atracao) listaAtracao.recuperar(i);
        }
        int ini = 0, fim = vetorAtracao.length - 1;
        //Um objeto da classe QuickSort é criado, para obter acesso ao método de ordenação:
        QuickSort ordenacao = new QuickSort();        
        ordenacao.ordemAlfabetica(vetorAtracao, ini, fim);   //O método é chamado.
        listaAtracao = new Lista();   //A lista é reiniciada.
        
        //O vetor de atrações de um local ordenado é passado para uma lista de mesmo tamanho:
        for (int i = 0; i < vetorAtracao.length; i++) {
            listaAtracao.inserirFinal(vetorAtracao[i]);
        }
        return (listaAtracao.iterador());
    }

    /** Método que retorna um objeto da classe Iterador, que faz referência à
     * lista de atrações de um determinado local, elas são listadas em ordem
     * de pontuação média.
     * 
     * @return Iterador iterador
     */
    public Iterador listarAtracoesOrdemPontuacaoMedia() {
        Atracao[] vetorAtracao = new Atracao[listaAtracao.obterTamanho()];
        
        //A lista de atrações de um local é passada para um vetor de mesmo tamanho:
        for (int i = 0; i < listaAtracao.obterTamanho(); i++) {
            vetorAtracao[i] = (Atracao) listaAtracao.recuperar(i);
        }
        int ini = 0, fim = vetorAtracao.length - 1;
        //Um objeto da classe QuickSort é criado, para obter acesso ao método de ordenação:
        QuickSort ordenacao = new QuickSort();
        ordenacao.ordemNumerica(vetorAtracao, ini, fim);   //O método é chamado.
        listaAtracao = new Lista();   //A lista é reiniciada.
        
        //O vetor de atrações de um local ordenado é passado para uma lista de mesmo tamanho:
        for (int i = 0; i < vetorAtracao.length; i++) {
            listaAtracao.inserirFinal(vetorAtracao[i]);
        }
        return (listaAtracao.iterador());
    }
}