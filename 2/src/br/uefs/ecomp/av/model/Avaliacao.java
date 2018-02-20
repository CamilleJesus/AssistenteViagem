package br.uefs.ecomp.av.model;


import java.io.Serializable;
import java.util.Date;   //Classe usada para tipo data.
import java.util.Objects;

/**
 * Classe Abstrata Avaliacao, responsável pela criação de cada avaliação de uma atração.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public abstract class Avaliacao implements Comparable<Avaliacao>, Serializable {
    
    private final Usuario avaliador;
    private final Atracao atracaoAvaliada;
    private final int pontos;
    private final String titulo, texto;
    private final Date data;
    
    /** Construtor da classe, responsável pela criação de uma nova avaliação,
     * recebe as informações e cria um objeto.
     * 
     * @param avaliador
     * @param atracaoAvaliada
     * @param pontos
     * @param titulo
     * @param texto
     * @param data
     */
    public Avaliacao(Usuario avaliador, Atracao atracaoAvaliada, int pontos, String titulo
                     , String texto, Date data) {
        this.avaliador = avaliador;
        this.atracaoAvaliada = atracaoAvaliada;
        this.pontos = pontos;
        this.titulo = titulo;
        this.texto = texto;
        this.data = data;
    }

    /** Método que retorna o usuário responsável pela avaliação.
     * 
     * @return Usuario avaliador
     */
    public Usuario getAvaliador() {
        return avaliador;
    }

    /** Método que retorna a atracação avaliada.
     * 
     * @return Atracao atracaoAvaliada
     */
    public Atracao getAtracaoAvaliada() {
        return atracaoAvaliada;
    }

    /** Método que retorna os pontos de uma avalição.
     * 
     * @return int pontos
     */
    public int getPontos() {
        return pontos;
    }

    /** Método que retorna o título de uma avalição.
     * 
     * @return String titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /** Método que retorna o texto de uma avalição.
     * 
     * @return String texto
     */
    public String getTexto() {
        return texto;
    }

    /** Método que retorna a data de uma avalição.
     * 
     * @return Date data
     */
    public Date getData() {
        return data;
    }
    
    /** Método que retorna um código único da avaliação, para ser usado em sua busca.
     * 
     * @return int hash
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.titulo);
        hash = 23 * hash + Objects.hashCode(this.texto);
        return hash;
    }
    
    /** Método que verifica se duas avaliações são iguais retornando um booleano,
     * onde true significa que o objeto é igual e false que o objeto é diferente.
     * 
     * @param o
     * @return boolean (true ou false)
     */
    @Override
    public boolean equals(Object o) {
        
        if(o instanceof Avaliacao) {
            Avaliacao avaliacao = (Avaliacao) o;
            
            if (titulo.equals(avaliacao.getTitulo()) && texto.equals(avaliacao.getTexto())) {
                return true;
            }
        }
        return false;
    }

    /** Método que dá uma representação em String de uma avaliação.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return ("Avaliacao - " + avaliador.toString() + ", Atração: " + atracaoAvaliada.getNome()
                + "Pontos: " + pontos + ", Data: " + data + ", Titulo: " + titulo
                + ", Texto: " + texto + '.');
    }

    /** Método que aceita uma avaliação, inserindo-a ao fim da lista de avaliações
     * de determinado local.
     */
    public void aceitar() {
        atracaoAvaliada.getListaAvaliacao().inserirFinal(this);
    }
    
    /** Método que recebe uma avaliação como parâmetro e a compara com a avaliação
     * atual, o critério é a data das mesmas. Se a data da avaliacão atual for maior,
     * retorna -1, se for menor, retorna 1 e por fim, se for igual retorna 0.
     * O retorno é usado para ordenar a lista de avaliações com base neste critério.
     * 
     * @param avaliacao 
     * @return int
     */
    @Override
    public int compareTo(Avaliacao avaliacao) {
        if (data.compareTo(avaliacao.getData()) > 0){
            return -1;
        } else if (data.compareTo(avaliacao.getData()) < 0){
            return 1;
        }
        return 0;
    }
}