package br.uefs.ecomp.av.model;


import br.uefs.ecomp.av.model.enums.ObjetivoVisita;
import br.uefs.ecomp.av.model.enums.Sexo;
import br.uefs.ecomp.av.model.enums.TipoRefeicao;
import br.uefs.ecomp.av.model.enums.TipoVisita;

import java.io.Serializable;
import java.security.MessageDigest;   //Classe usada para criptografia de string.
import java.security.NoSuchAlgorithmException;   //Classe usada para tratamento de excessão.
import java.util.Date;
import java.util.Formatter;   //Classe usada para conversão de bytes.
import java.util.Objects;

/**
 * Classe Usuario, responsável pela criação de cada usuário do Assistente de Viagem.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class Usuario implements Comparable<Usuario>, Serializable {
    
    private final String emailLogin, hashSenha, nomeCompleto;
    private final Sexo sexo;
    private static AssistenteViagem assistente;

    /** Construtor da classe, responsável pela criação um novo usuário, recebe
     * as informações e cria um objeto.
     * 
     * @param emailLogin
     * @param hashSenha
     * @param sexo
     * @param nomeCompleto
     */
    public Usuario(String emailLogin, String hashSenha, Sexo sexo, String nomeCompleto) {
        this.emailLogin = emailLogin;
        this.hashSenha = hashSenha;
        this.sexo = sexo;
        this.nomeCompleto = nomeCompleto;
    }

    /** Método que retorna o e-mail de um usuário.
     * 
     * @return String emailLogin
     */
    public String getEmailLogin() {
        return emailLogin;
    }

    /** Método que retorna a senha de um usuário.
     * 
     * @return String hashSenha
     */
    public String getHashSenha() {
        return hashSenha;
    }

    /** Método que retorna o sexo de um usuário.
     * 
     * @return Sexo sexo
     */
    public Sexo getSexo() {
        return sexo;
    }

    /** Método que retorna o nome completo de um usuário.
     * 
     * @return String nomeCompleto
     */
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    /** Método que retorna um código único do usuário, para ser usado em sua busca.
     * 
     * @return int hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.emailLogin);
        return hash;
    }
    
    /** Método que verifica se dois usuários são iguais retornando um booleano,
     * onde true significa que o objeto é igual e false que o objeto é diferente.
     * 
     * @param o
     * @return boolean (true ou false)
     */
    @Override
    public boolean equals(Object o) {
        
        if(o instanceof Usuario) {
            Usuario usuario = (Usuario) o;
            
            if (emailLogin.equals(usuario.getEmailLogin()) &&
                this.getHashSenha().equals(usuario.getHashSenha())) {
                return true;
            }
        }
        return false;
    }

    /** Método que dá uma representação em String de um usuário.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return ("Usuário - " + "Nome Completo: " + nomeCompleto);
    }
    
    /** Método que permite a criptografia da senha de login do usuário.
     * 
     * @param s
     * @return String
     */
    public static String toSha1(final String s) {
        MessageDigest string = null;        
        
        try {
            string = MessageDigest.getInstance("SHA-1");
            string.reset();
            string.update(s.getBytes());
        } catch (NoSuchAlgorithmException e) { }
        return ((string != null) ? byteToHex(string.digest()) : null);
    }
    
    /** Método que auxilia na função hash e converte os bytes da string em hexadecimal.
     * 
     * @param vetorByte
     * @return String
     */
    private static String byteToHex(byte[] vetorByte) {
        String s;
        
        try (Formatter formatter = new Formatter()) {
            
            for (byte b : vetorByte) {
                formatter.format("%02x", b);
            }
            s = formatter.toString();
        }
        return s;
    }
    
    /** Método que retorna a senha criptografada.
     * 
     * @return String
     */
    public String getHashSenhaCrip() {
        return (toSha1(hashSenha));
    }

    /** Método que compara dois usuários pelo nome.
     * [Não é utilizado, implementado somente pela necessidade de usar lista que
     * trabalha com Comparable.]
     * 
     * @param usuario
     * @return int
     */
    @Override
    public int compareTo(Usuario usuario) {
        return (nomeCompleto.compareTo(usuario.getNomeCompleto()));
    }

    /** Método que permite ao usuário avaliar um restaurante de um local, inserindo
     * a avaliação ao final da lista geral de avaliações do assistente para posterior
     * processamento.
     * 
     * @param atracaoAvaliada
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
    public void avaliarAtracao(Atracao atracaoAvaliada, int
            pontos, String titulo, String texto, Date data, TipoVisita tipoVisita,
            TipoRefeicao tipoRefeicao, int atendimento, int comida, int custoBeneficio) {
        assistente = AssistenteViagem.getInstance();
        AvaliacaoRestaurante avaliacaoRestaurante = new AvaliacaoRestaurante(this,
            atracaoAvaliada, pontos, titulo, texto, data, tipoVisita, tipoRefeicao,
            atendimento, comida, custoBeneficio);
        assistente.getListaAvaliacoes().inserirFinal(avaliacaoRestaurante);
    }

    /** Método que permite ao usuário avaliar um hotel de um local, inserindo
     * a avaliação ao final da lista geral de avaliações do assistente para posterior
     * processamento.
     * 
     * @param atracaoAvaliada
     * @param pontos
     * @param titulo
     * @param texto
     * @param data
     * @param objetivoVisita
     * @param atendimento
     * @param quartos
     * @param qualidadeSono
     */
    public void avaliarAtracao(Atracao atracaoAvaliada, int
            pontos, String titulo, String texto, Date data, ObjetivoVisita objetivoVisita,
            int atendimento, int quartos, int qualidadeSono) {
        assistente = AssistenteViagem.getInstance();
        AvaliacaoHotel avaliacaoHotel = new AvaliacaoHotel(this, atracaoAvaliada,
            pontos, titulo, texto, data, objetivoVisita, atendimento, quartos,
            qualidadeSono);
        assistente.getListaAvaliacoes().inserirFinal(avaliacaoHotel);
    }
}