package br.uefs.ecomp.av.model;


import br.uefs.ecomp.av.model.enums.Sexo;

import java.security.MessageDigest;   //Classe usada para criptografia de strings.
import java.security.NoSuchAlgorithmException;   //Classe usada para tratamento de excessão.
import java.util.Date;   //Classe usada para tipo data.
import java.util.Formatter;   //Classe usada para conversão de bytes.
import java.util.Objects;

/**
 * Classe Usuario, responsável pela criação de cada usuário do Assistente de Viagem.
 * 
 * @author Camille Jesus
 */
public class Usuario {
    
    private final String emailLogin, hashSenha, nomeCompleto;
    private final Sexo sexo;
    private static AssistenteViagem assistente;

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
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.emailLogin);
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

    /** Método que permite ao usuário avaliar uma atração de um local, inserindo
     * a avaliação ao final da lista geral de avaliações do assistente para posterior
     * processamento.
     * 
     * @param atracaoAvaliada
     * @param pontos
     * @param titulo
     * @param texto
     * @param data
     */
    public void avaliarAtracao(Atracao atracaoAvaliada, int pontos, String titulo, String texto, Date data) {
        assistente = AssistenteViagem.getInstance();
        Avaliacao avaliacao = new Avaliacao(this, atracaoAvaliada, pontos, titulo, texto, data);
        assistente.getListaAvaliacoes().inserirFinal(avaliacao);
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
}