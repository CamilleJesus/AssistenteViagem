package br.uefs.ecomp.av.util;


import java.io.Serializable;

/**
 * Classe CélulaLista, responsável pela criação de cada elemento da árvore, recebe
 * um objeto para criação individual do elemento.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class CelulaArvore implements Serializable, Comparable {
    
    private Comparable objeto;
    private CelulaArvore pai, esquerda, direita;
    private int alturaE, alturaD, balanceamento;
    
    /** Construtor da classe, responsável pela criação de uma nova célula, recebe
     * um objeto e cria uma célula específica para ele.
     * 
     * @param objeto
     */
    public CelulaArvore(Comparable objeto){
        this.objeto = objeto;
    }

    /** Método que retorna o objeto de uma célula.
     * 
     * @return Object objeto
     */
    public Comparable getObjeto() {
        return objeto;
    }
    
    /** Método que recebe um objeto como parâmetro e altera seu valor.
     * 
     * @param objeto
     */
    public void setObjeto(Comparable objeto) {
        this.objeto = objeto;
    }

    /** Método que retorna o pai de uma célula.
     * 
     * @return CelulaArvore pai
     */
    public CelulaArvore getPai() {
        return pai;
    }

    /** Método que recebe uma célula como parâmetro e altera o valor do pai.
     * 
     * @param pai
     */
    public void setPai(CelulaArvore pai) {
        this.pai = pai;
    }

    /** Método que retorna a célula à esquerda da célula atual.
     * 
     * @return CelulaArvore esquerda
     */
    public CelulaArvore getEsquerda() {
        return esquerda;
    }

    /** Método que recebe uma célula como parâmetro e altera o valor da célula à
     * esquerda.
     * 
     * @param esquerda 
     */
    public void setEsquerda(CelulaArvore esquerda) {
        this.esquerda = esquerda;
    }

    /** Método que retorna a célula à direita da célula atual.
     * 
     * @return CelulaArvore esquerda
     */
    public CelulaArvore getDireita() {
        return direita;
    }

    /** Método que recebe uma célula como parâmetro e altera o valor da célula à
     * direita.
     * 
     * @param direita
     */
    public void setDireita(CelulaArvore direita) {
        this.direita = direita;
    }

    /** Método que retorna a altura à esquerda.
     * 
     * @return alturaE
     */
    public int getAlturaE() {
        return alturaE;
    }

    /** Método que altera o valor da altura à esquerda.
     * 
     * @param alturaE
     */
    public void setAlturaE(int alturaE) {
        this.alturaE = alturaE;
    }

    /** Método que retorna a altura à direita.
     * 
     * @return alturaD
     */
    public int getAlturaD() {
        return alturaD;
    }

    /** Método que altera o valor da altura à direita.
     * 
     * @param alturaD
     */
    public void setAlturaD(int alturaD) {
        this.alturaD = alturaD;
    }

    /** Método que retorna o fator de balanceamento.
     * 
     * @return int balanceamento
     */
    public int getBalanceamento() {
        return balanceamento;
    }

    /** Método que altera o valor do fator de balanceamento.
     * 
     * @param balanceamento
     */
    public void setBalanceamento(int balanceamento) {
        this.balanceamento = balanceamento;
    }

    /** Método não utilizado, implementado somente pela necessidade de implementar
     * da classe Comparable.
     * 
     * @param o
     */
    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}