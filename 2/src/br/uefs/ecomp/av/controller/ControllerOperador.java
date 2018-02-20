package br.uefs.ecomp.av.controller;


import br.uefs.ecomp.av.model.AssistenteViagem;
import br.uefs.ecomp.av.model.Avaliacao;
import br.uefs.ecomp.av.util.Coordenadas;
import br.uefs.ecomp.av.util.Iterador;

import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Classe ControllerOperador, faz todos os requisitos do programa relacionados às
 * funções do Operador.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class ControllerOperador implements Serializable {
	
    private AssistenteViagem assistente = null;
    private Avaliacao avaliacaoAtual = null;

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

    /** Operador salva os dados do sistema em arquivo.
     * 
     * @throws java.lang.Exception
     */
    public void salvarEstadoSistema() throws Exception {
        
        try {
            //Gera o arquivo para armazenar o objeto:
            FileOutputStream file = new FileOutputStream("AV2.dat");
            //Classe responsavel por inserir os objetos:
            ObjectOutputStream obj = new ObjectOutputStream(file);
            //Grava o objeto assistente no arquivo:
            obj.writeObject(assistente);
            obj.flush();
            obj.close();
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar o estado do Sistema.");
        }
}

    /** Operador carrega os dados do sistema de arquivo.
     * 
     * @throws java.lang.Exception
     */
    public void carregarEstadoSistema() throws Exception {
        
        try {
            //Carrega o arquivo:
            FileInputStream file = new FileInputStream("AV2.dat");
            //Classe responsavel por recuperar os objetos do arquivo:
            ObjectInputStream obj = new ObjectInputStream(file);
            //Grava o arquivo no objeto assistente:
            assistente = (AssistenteViagem) obj.readObject();
            obj.close();
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao carregar o estado do Sistema.");
        }
    }
}