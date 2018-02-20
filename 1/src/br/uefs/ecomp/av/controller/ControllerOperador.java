package br.uefs.ecomp.av.controller;


import br.uefs.ecomp.av.model.AssistenteViagem;
import br.uefs.ecomp.av.model.Avaliacao;
import br.uefs.ecomp.av.util.Coordenadas;
import br.uefs.ecomp.av.util.Iterador;

/**
 * Classe ControllerOperador, faz todos os requisitos do programa relacionados às
 * funções do Operador.
 * 
 * @author Camille Jesus
 */
public class ControllerOperador {
	
    private AssistenteViagem assistente;	
    private Avaliacao avaliacaoAtual;

    /** Construtor da classe, a instância do assistente é atribuída à um atributo
     * do tipo AssistenteViagem.
     */
    public ControllerOperador() {
        this.assistente = AssistenteViagem.getInstance();
    }

    /** Método que zera o sistema e instancia um novo assistente.
     */
    public void zerarSistema() {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();
    }

    /** Operador cadastra um local.
     * 
     * @param nome
     * @param localizacao
     * @param estado
     * @param pais
     */
    public void cadastrarLocal(String nome, Coordenadas localizacao, String estado, String pais) {
        assistente.cadastrarLocal(nome, localizacao, estado, pais);
    }

    /** Operador lista locais em ordem alfabética.
     * 
     * @return Iterador iterador
     */
    public Iterador listarLocais() {
        return (assistente.listarLocais());
    }

    /** Operador processa novas avaliações.
     */
    public void processarProximaAvaliacao() {
        avaliacaoAtual = assistente.processarProximaAvaliacao();
    }

    /** Operador rejeita a avaliação que está sendo processada.
     */
    public void rejeitarAvaliacao() {
        avaliacaoAtual = null;
    }

    /** Operador aceita a avaliação que está sendo processada.
     */
    public void aceitarAvaliacao() {
        avaliacaoAtual.aceitar();
        avaliacaoAtual = null;
    }

    /** Operador roda batch para recalcular os rankings de todas as atrações.
     */
    public void recalcularRankingsAtracoes() {
        assistente.recalcularTodosRankingsAtracoes();
    }
}