package br.uefs.ecomp.av.controller;


import br.uefs.ecomp.av.model.AssistenteViagem;
import br.uefs.ecomp.av.model.Avaliacao;
import br.uefs.ecomp.av.model.exception.CampoVazioException;
import br.uefs.ecomp.av.model.exception.DadoInexistenteException;
import br.uefs.ecomp.av.util.Coordenadas;
import br.uefs.ecomp.av.util.Iterador;

import java.io.Serializable;
import java.io.FileNotFoundException;

/**
 * Classe ControllerOperador, faz todos os requisitos do programa relacionados
 * às funções do Operador.
 *
 * @author Camille Jesus e Rodrigo Santos
 */
public class ControllerOperador implements Serializable {

    private AssistenteViagem assistente = null;
    private Avaliacao avaliacaoAtual = null;

    /**
     * Construtor da classe, a instância do assistente é atribuída à um atributo
     * do tipo AssistenteViagem.
     */
    public ControllerOperador() {
        this.assistente = AssistenteViagem.getInstance();
    }

    /**
     * Método que zera o sistema e instancia um novo assistente.
     */
    public void zerarSistema() {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();
    }

    /**
     * Operador cadastra um local.
     *
     * @param nome
     * @param localizacao
     * @param estado
     * @param pais
     * @throws CampoVazioException
     */
    public void cadastrarLocal(String nome, Coordenadas localizacao, String estado, String pais)
                               throws CampoVazioException {
        assistente.cadastrarLocal(nome, localizacao, estado, pais);
    }

    /**
     * Operador lista locais em ordem alfabética.
     *
     * @return Iterador iterador
     */
    public Iterador listarLocais() {
        return (assistente.listarLocais());
    }

    /**
     * Operador processa novas avaliações.
     * @return avaliacaoAtual
     * @throws br.uefs.ecomp.av.model.exception.DadoInexistenteException
     */
    public Avaliacao processarProximaAvaliacao() throws DadoInexistenteException {
        return (avaliacaoAtual = assistente.processarProximaAvaliacao());
    }

    /**
     * Operador rejeita a avaliação que está sendo processada.
     */
    public void rejeitarAvaliacao() {
        avaliacaoAtual = null;
    }

    /**
     * Operador aceita a avaliação que está sendo processada.
     */
    public void aceitarAvaliacao() {
        avaliacaoAtual.aceitar();
        avaliacaoAtual = null;
    }

    /**
     * Operador roda batch para recalcular os rankings de todas as atrações.
     */
    public void recalcularRankingsAtracoes() {
        assistente.recalcularTodosRankingsAtracoes();
    }

    /**
     * Operador salva os dados do sistema em arquivo.
     *
     * @throws Exception
     */
    public void salvarEstadoSistema() throws Exception {
        assistente.salvarEstadoSistema();
    }

    /**
     * Operador carrega os dados do sistema de arquivo.
     *
     * @throws Exception
     * @throws FileNotFoundException
     */
    public void carregarEstadoSistema() throws Exception, FileNotFoundException {
        assistente.carregarEstadoSistema();
        assistente = AssistenteViagem.getInstance(); 
    }
}