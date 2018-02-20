package br.uefs.ecomp.av.model;


import br.uefs.ecomp.av.model.enums.CategoriaAtracao;
import br.uefs.ecomp.av.util.Coordenadas;
import br.uefs.ecomp.av.util.ILista;
import br.uefs.ecomp.av.util.Iterador;
import br.uefs.ecomp.av.util.Lista;

import java.util.Objects;

/**
 * Classe Atracao, responsável pela criação de cada atração de um local.
 * 
 * @author Camille Jesus
 */
public class Atracao {
    
    private final Local local;
    private final String nome;
    private int ordem;
    private final Coordenadas localizacao;
    private final CategoriaAtracao categoria;
    private ILista listaAvaliacao;
    private double mediaPontos;

    /** Construtor da classe, responsável pela criação uma nova atração, recebe
     * as informações e cria um objeto.
     * 
     * @param local
     * @param nome
     * @param localizacao
     * @param categoria
     */
    public Atracao(Local local, String nome, Coordenadas localizacao, CategoriaAtracao categoria) {
        this.local = local;
        this.nome = nome;
        this.localizacao = localizacao;
        this.categoria = categoria;
        listaAvaliacao = new Lista();   //Cada atração criada possuirá uma lista para avaliações.
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

    /** Método que retorna a categoria de uma atração.
     * 
     * @return CategoriaAtracao categoria
     */
    public CategoriaAtracao getCategoria() {
        return categoria;
    }

    /** Método que retorna a lista de avaliações de uma atração.
     * 
     * @return ILista listaAvaliacao
     */
    public ILista getListaAvaliacao() {
        return listaAvaliacao;
    }

    /** Método que retorna a média de pontos avaliados de uma atração.
     * 
     * @return double mediaPontos
     */
    public double getMediaPontos() {
        return mediaPontos;
    }

    /** Método que retorna um código único da atração, para ser usado em sua busca.
     * 
     * @return int hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.nome);
        hash = 17 * hash + Objects.hashCode(this.localizacao);
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
            Atracao atracao = (Atracao) o;
            
            if (nome.equals(atracao.getNome()) && localizacao.equals(atracao.getLocalizacao())) {
                return true;
            }
        }
        return false;
    }

    /** Método que dá uma representação em String de uma atração.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return ("Atracao - " + "Nome: " + nome + ", " + localizacao.toString() + 
                ", Ordem: " + ordem + ", Categoria: " + categoria + ", Local: " 
                + local.getNome() + '.');
    }

    /** Método que retorna um objeto da classe Iterador, que faz referência à
     * lista de avaliações aceitas de determinada atração, as avaliações são
     * listadas da mais recente para a mais antiga.
     * 
     * @return Iterador iterador
     */
    public Iterador listarAvaliacoes() {
        Avaliacao[] vetorAvaliacao = new Avaliacao[listaAvaliacao.obterTamanho()];
        
        //A lista de avaliações de uma atração é passada para um vetor de mesmo tamanho:
        for (int i = 0; i < listaAvaliacao.obterTamanho(); i++) {
            vetorAvaliacao[i] = (Avaliacao) listaAvaliacao.recuperar(i);
        }
        int ini = 0, fim = vetorAvaliacao.length - 1;
        //Um objeto da classe QuickSort é criado, para obter acesso ao método de ordenação:
        QuickSort ordenacao = new QuickSort();  
        ordenacao.ordemCronologica(vetorAvaliacao, ini, fim);   //O método é chamado.
        listaAvaliacao = new Lista();   //A lista é reiniciada.
        
        //O vetor de avaliações de uma atração ordenado é passado para uma lista de mesmo tamanho:
        for (int i = 0; i < vetorAvaliacao.length; i++) {
            listaAvaliacao.inserirFinal(vetorAvaliacao[i]);
        }
        return (listaAvaliacao.iterador());
    }

    /** Método que calcula e retorna a média de pontos das avaliações de uma atração.
     * 
     * @return double mediaPontos
     */
    public double pontuacaoMedia() {
        double totalPontos = 0;
        
        for (int i = 0; i < listaAvaliacao.obterTamanho(); i++) {
            Avaliacao avaliacao = (Avaliacao) listaAvaliacao.recuperar(i);
            totalPontos += avaliacao.getPontos();
        }
        return (mediaPontos = totalPontos / listaAvaliacao.obterTamanho());
    }
}