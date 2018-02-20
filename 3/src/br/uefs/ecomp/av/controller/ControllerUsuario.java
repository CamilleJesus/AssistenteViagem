package br.uefs.ecomp.av.controller;


import java.util.Date;

import br.uefs.ecomp.av.model.AssistenteViagem;
import br.uefs.ecomp.av.model.Atracao;
import br.uefs.ecomp.av.model.Local;
import br.uefs.ecomp.av.model.Usuario;
import br.uefs.ecomp.av.model.enums.*;
import br.uefs.ecomp.av.model.exception.CampoVazioException;
import br.uefs.ecomp.av.model.exception.DadoExistenteException;
import br.uefs.ecomp.av.model.exception.DadoInexistenteException;
import br.uefs.ecomp.av.util.Coordenadas;
import br.uefs.ecomp.av.util.Iterador;

/**
 * Classe ControllerUsuario, faz todos os requisitos do programa relacionados às
 * funções do Usuário.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class ControllerUsuario {
	
    private AssistenteViagem assistente = null;
    private Usuario usuarioAtual = null;
    private Local localAtual = null;
    private Atracao atracaoAtual = null;

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
     * @throws CampoVazioException
     */
    public void cadastrarUsuario(String emailLogin, String hashSenha, Sexo sexo,
                                 String nomeCompleto) throws CampoVazioException {
        assistente.cadastrarUsuario(emailLogin, hashSenha, sexo, nomeCompleto);
    }

    /** Usuário faz login no sistema.
     * 
     * @param emailLogin
     * @param hashSenha
     * @return 
     * @throws DadoInexistenteException 
     */
    public Usuario fazerLogin(String emailLogin, String hashSenha) throws DadoInexistenteException {
        return (usuarioAtual = assistente.fazerLogin(emailLogin, hashSenha));
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
     * @throws DadoExistenteException
     */
    public void cadastrarRestaurante(Local local, String nome, Coordenadas localizacao,
            String bairro, CategoriaAtracao categoria, FaixaPreco preco, TipoCozinha
                                     cozinha) throws DadoExistenteException {
        assistente.cadastrarRestaurante(local, nome, localizacao, bairro, categoria,preco, cozinha);
    }

    /** Usuário cadastra hotel.
     * 
     * @param local
     * @param nome
     * @param localizacao
     * @param bairro
     * @param categoria
     * @param estrelas
     * @param ar
     * @param tv
     * @param servicoQuarto
     * @throws DadoExistenteException
     */
    public void cadastrarHotel(Local local, String nome, Coordenadas localizacao,
            String bairro, CategoriaAtracao categoria, int estrelas, boolean ar,
            boolean tv, boolean servicoQuarto) throws DadoExistenteException {
        assistente.cadastrarHotel(local, nome, localizacao, bairro, categoria, estrelas, ar, tv, servicoQuarto);
    }

    /** Usuário procura um local pelo nome.
     *
     * @param nome
     * @return local 
     * @throws DadoInexistenteException 
     */
    public Local buscarLocal(String nome) throws DadoInexistenteException {
        return (localAtual = assistente.buscarLocal(nome));
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
     * @return atracao
     * @throws DadoInexistenteException
     */
    public Atracao buscarAtracao(String nome) throws DadoInexistenteException {
        return (atracaoAtual = assistente.buscarAtracao(nome));
    }

    /** Usuário avalia um restaurante.
     * 
     * @param pontos
     * @param titulo
     * @param texto
     * @param data
     * @param tipoVisita
     * @param tipoRefeicao
     * @param atendimento
     * @param comida
     * @param custoBeneficio
     */
    public void avaliarRestaurante(int pontos, String titulo, String texto, Date
            data, TipoVisita tipoVisita, TipoRefeicao tipoRefeicao, int atendimento, int comida, int custoBeneficio) {
        usuarioAtual.avaliarAtracao(atracaoAtual, pontos, titulo, texto, data,
                tipoVisita, tipoRefeicao, atendimento, comida, custoBeneficio);
    }

    /** Usuário avalia um hotel.
     * 
     * @param pontos
     * @param titulo
     * @param texto
     * @param data
     * @param objetivoVisita
     * @param atendimento
     * @param quartos
     * @param qualidadeSono
     */
    public void avaliarHotel(int pontos, String titulo, String texto, Date data, 
            ObjetivoVisita objetivoVisita, int atendimento, int quartos, int qualidadeSono) {
        usuarioAtual.avaliarAtracao(atracaoAtual, pontos, titulo, texto, data, 
                objetivoVisita, atendimento, quartos, qualidadeSono);
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