package br.uefs.ecomp.av.model;


import br.uefs.ecomp.av.util.Arvore;
import br.uefs.ecomp.av.util.Coordenadas;
import br.uefs.ecomp.av.util.IArvore;
import br.uefs.ecomp.av.util.ILista;
import br.uefs.ecomp.av.util.Iterador;
import br.uefs.ecomp.av.util.Lista;

import java.io.Serializable;

import java.util.Objects;

/**
 * Classe Local, responsável pela criação de cada local do Assistente de Viagem.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class Local implements Comparable<Local>, Serializable {
    
    private final String nome, estado, pais;
    private final Coordenadas localizacao;
    private ILista listaAtracao;
    private IArvore arvoreAtracao;

    public Local(String nome, Coordenadas localizacao, String estado, String pais) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.estado = estado;
        this.pais = pais;
        listaAtracao = new Lista();   //Cada local criado possuirá uma lista de atrações.
        arvoreAtracao = new Arvore();
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

    /** Método que retorna o nome do estado de um local.
     * 
     * @return String estado
     */
    public String getEstado() {
        return estado;
    }

    /** Método que retorna o nome do país de um local.
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

    public IArvore getArvoreAtracao() {
        return arvoreAtracao;
    }

    /** Método que retorna um código único do local, para ser usado em sua busca.
     * 
     * @return int hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.nome);
        hash = 41 * hash + Objects.hashCode(this.localizacao);
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
        return nome;
    }

    /** Método que retorna um objeto da classe Iterador, que faz referência à
     * lista de atrações de um determinado local, elas são listadas em ordem
     * alfabética.
     * 
     * @return Iterador iterador
     */
    public Iterador listarAtracoesOrdemAlfabetica() {
        return (arvoreAtracao.iterador());
    }

    /** Método que retorna um objeto da classe Iterador, que faz referência à
     * lista de atrações de um determinado local, elas são listadas em ordem
     * de pontuação média.
     * 
     * @return Iterador iterador
     */
    public Iterador listarAtracoesOrdemPontuacaoMedia() {
        listaAtracao.ordenar();
        return (listaAtracao.iterador());
    }

    /** Método que recebe um local como parâmetro e o compara com o local atual,
     * o critério é o nome do mesmo. Se o nome atual vier antes, retorna -1, se
     * vier depois, retorna 1 e por fim, se for igual retorna 0.
     * O retorno é usado para ordenar a lista de locais em ordem alfabética.
     * 
     * @param local
     * @return int
     */
    @Override
    public int compareTo(Local local) {
        return (nome.compareTo(local.getNome()));
    }
}