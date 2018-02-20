package br.uefs.ecomp.av.controller;


import java.util.Date;

import br.uefs.ecomp.av.model.AssistenteViagem;
import br.uefs.ecomp.av.model.Atracao;
import br.uefs.ecomp.av.model.Local;
import br.uefs.ecomp.av.model.Usuario;
import br.uefs.ecomp.av.model.enums.*;
import br.uefs.ecomp.av.util.Coordenadas;
import br.uefs.ecomp.av.util.Iterador;

/**
 * Classe ControllerUsuario, faz todos os requisitos do programa relacionados às
 * funções do Usuário.
 * 
 * @author Camille Jesus
 */
public class ControllerUsuario {
    
    private AssistenteViagem assistente;
    private Usuario usuarioAtual;
    private Local localAtual;
    private Atracao atracaoAtual;

    /** Construtor da classe, a instância do assistente é atribuída à um atributo
     * do tipo AssistenteViagem.
     */
    public ControllerUsuario() {
        this.assistente = AssistenteViagem.getInstance();
    }

    /** Usuário se cadastra no sistema.
     * 
     * @param emailLogin
     * @param hashSenha
     * @param sexo
     * @param nomeCompleto
     */
    public void cadastrarUsuario(String emailLogin, String hashSenha, Sexo sexo,
                                 String nomeCompleto) {
        assistente.cadastrarUsuario(emailLogin, hashSenha, sexo, nomeCompleto);
    }

    /** Usuário faz login no sistema.
     * 
     * @param emailLogin
     * @param hashSenha
     */
    public void fazerLogin(String emailLogin, String hashSenha) {
        usuarioAtual = assistente.fazerLogin(emailLogin, hashSenha);
    }

    /** Usuário cadastra atração.
     * 
     * @param local
     * @param nome
     * @param localizacao
     * @param categoria
     */
    public void cadastrarAtracao(Local local, String nome, Coordenadas localizacao,
                                 CategoriaAtracao categoria) {
        assistente.cadastrarAtracao(local, nome, localizacao, categoria);
    }

    /** Usuário cadastra restaurante.
     * 
     * @param local
     * @param nome
     * @param localizacao
     * @param categoria
     * @param preco
     * @param cozinha
     * @param bairro
     */
    public void cadastrarRestaurante(Local local, String nome, Coordenadas localizacao,
            CategoriaAtracao categoria, FaixaPreco preco, TipoCozinha cozinha, String bairro) {
        assistente.cadastrarRestaurante(local, nome, localizacao, categoria,preco, cozinha, bairro);
    }

    /** Usuário procura um local pelo nome.
     * 
     * @param nome
     */
    public void buscarLocal(String nome) {
        localAtual = assistente.buscarLocal(nome);
    }

    /** Usuário escolhe um local, sendo listadas suas atrações em ordem alfabética.
     * 
     * @return Iterador iterador
     */
    public Iterador listarAtracoesOrdemAlfabetica() {
        return (localAtual.listarAtracoesOrdemAlfabetica());
    }

    /** Usuário escolhe um local, sendo listadas suas atrações em ordem de pontuação
     * média.
     * 
     * @return Iterador iterador
     */
    public Iterador listarAtracoesOrdemPontuacaoMedia() {
        return (localAtual.listarAtracoesOrdemPontuacaoMedia());
    }

    /** Usuário procura uma atração pelo nome.
     * 
     * @param nome
     */
    public void buscarAtracao(String nome) {
        atracaoAtual = assistente.buscarAtracao(nome);
    }

    /** Usuário avalia uma atração qualquer.
     * 
     * @param pontos
     * @param titulo
     * @param texto
     * @param data
     */
    public void avaliarAtracao(int pontos, String titulo, String texto, Date data) {
        usuarioAtual.avaliarAtracao(atracaoAtual, pontos, titulo, texto, data);
    }

    /** Usuário escolhe uma atração, sendo listadas suas avaliações, da mais
     * recente para a mais antiga.
     * 
     * @return Iterador iterador
     */
    public Iterador listarAvaliacoesPorAtracao() {
        return (atracaoAtual.listarAvaliacoes());
    }
}