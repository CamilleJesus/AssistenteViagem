package br.uefs.ecomp.av.model;


import br.uefs.ecomp.av.util.Coordenadas;
import br.uefs.ecomp.av.util.ILista;
import br.uefs.ecomp.av.util.Lista;
import br.uefs.ecomp.av.util.Iterador;

import java.io.Serializable;

import java.util.Objects;

/**
 * Classe Abstrata Atracao, responsável pela criação de cada atração de um local.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public abstract class Atracao implements Comparable<Atracao>, Serializable {
    
    private final Local local;
    private final String nome, bairro;
    private int ordem;
    private final Coordenadas localizacao;
    private ILista listaAvaliacao;

    /** Construtor da classe, responsável pela criação de uma nova atração, recebe
     * as informações e cria um objeto.
     * 
     * @param local
     * @param nome
     * @param localizacao
     * @param bairro
     */
    public Atracao(Local local, String nome, Coordenadas localizacao, String bairro) {
        this.local = local;
        this.nome = nome;
        this.localizacao = localizacao;
        this.bairro = bairro;
        listaAvaliacao = new Lista();   //Cada atração criada possuirá uma lista de avaliações.
    }

    /** Método que retorna o local de uma atração.
     * 
     * @return Local local
     */
    public Local getLocal() {
        return local;
    }

    /** Método que retorna o nome de uma atração.
     * 
     * @return String nome
     */
    public String getNome() {
        return nome;
    }

    /** Método que retorna a ordem (no ranking) de uma atração.
     * 
     * @return String nome
     */
    public int getOrdem() {
        return ordem;
    }

    /** Método que altera a ordem (no ranking) de uma atração.
     * 
     * @param ordem
     */
    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    /** Método que retorna a localizacao de uma atração.
     * 
     * @return Coordenadas localizacao
     */
    public Coordenadas getLocalizacao() {
        return localizacao;
    }

    /** Método que retorna o nome do bairro de uma atração.
     * 
     * @return String bairro
     */
    public String getBairro() {
        return bairro;
    }

    /** Método que retorna a lista de avaliações de uma atração.
     * 
     * @return ILista listaAvaliacao
     */
    public ILista getListaAvaliacao() {
        return listaAvaliacao;
    }
    
    /** Método que retorna um código único da atração, para ser usado em sua busca.
     * 
     * @return int hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.nome);
        hash = 89 * hash + Objects.hashCode(this.localizacao);
        return hash;
    }

    /** Método que verifica se duas atrações são iguais retornando um booleano,
     * onde true significa que o objeto é igual e false que o objeto é diferente.
     * 
     * @param o
     * @return boolean (true ou false)
     */
    @Override
    public boolean equals(Object o) {
        
        if(o instanceof Atracao) {
            Atracao atracao =  (Atracao) o;
            
            if (nome.equals(atracao.getNome()) && localizacao.equals(atracao.getLocalizacao())) {
                return true;
            }
        }
        return false;
    }

    /** Método abstrato que dá uma representação em String de uma atração.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return nome;
    }
    
    /** Método que retorna um objeto da classe Iterador, que faz referência à
     * lista de avaliações aceitas de determinada atração, as avaliações são
     * listadas da mais recente para a mais antiga.
     * 
     * @return Iterador iterador
     */
    public Iterador listarAvaliacoes() {
        listaAvaliacao.ordenar();
        return (listaAvaliacao.iterador());
    }
    
    /** Método que calcula e retorna a média de pontos das avaliações de uma atração.
     * 
     * @return double mediaPontos
     */
    public double pontuacaoMedia() {
        double totalPontos = 0;
        
        for (int i = 0; i < listaAvaliacao.obterTamanho(); i++) {
            totalPontos += ((Avaliacao) listaAvaliacao.recuperar(i)).getPontos();
        }
        return (totalPontos / listaAvaliacao.obterTamanho());
    }

    /** Método que recebe uma atração como parâmetro e a compara com a atração atual,
     * o critério é a média de pontos avaliados. Se a média da atracão atual for
     * menor, retorna -1, se for maior, retorna 1 e por fim, se for igual retorna 0.
     * O retorno é usado para ordenar a lista de atrações com base neste critério.
     * 
     * @param atracao
     * @return int
     */
    @Override
    public int compareTo(Atracao atracao) {
        
        if (this.pontuacaoMedia() > atracao.pontuacaoMedia()) {
            return -1;
        } else if (this.pontuacaoMedia() < atracao.pontuacaoMedia()) {
            return 1;
        }
        return 0;
    }
}