/*
 * Autora: Camille de L. Jesus
 * Componente Curricular: EXA863 - MI Programação
 * Data: 16/4/15
 * Declaro que este código foi elaborado por mim de forma individual e não contém
 * algum trecho de código de outro autor, tais como provindos de livros ou apostilas,
 * e páginas ou documentos eletrônicos da internet. Qualquer trecho de código de
 * outra autoria, que não minha, poderá estar destacado com uma citação para o
 * autor ou a fonte do código e estou ciente que estes trechos não serão considerados
 * para fins de avaliação.
 *
 */

package br.uefs.ecomp.av.model;


import static br.uefs.ecomp.av.model.Usuario.toSha1;   //Importação do método toSha1() da classe Usuario.
import br.uefs.ecomp.av.model.enums.*;
import br.uefs.ecomp.av.util.Coordenadas;
import br.uefs.ecomp.av.util.ILista;
import br.uefs.ecomp.av.util.Iterador;
import br.uefs.ecomp.av.util.Lista;

/**
 * Classe AssistenteViagem, um singleton; padrão que garante a existência de
 * apenas uma instância de uma classe, mantendo um ponto global de acesso ao seu
 * objeto.
 * 
 * @author Camille Jesus
 */
public class AssistenteViagem {

    private static AssistenteViagem assistente;   //Variavel estática que conterá a instância do método.
    private ILista listaLocal, listaUsuario, listaAtracoes, listaAvaliacoes;
    
    //Construtor privado, que suprime o construtor público padrão, cria as listas:
    private AssistenteViagem() {
        listaLocal = new Lista();
        listaUsuario = new Lista();
        listaAtracoes = new Lista();
        listaAvaliacoes = new Lista();
    }

    /** Método que retorna a lista de locais.
     * 
     * @return ILista listaLocal
     */
    public ILista getListaLocal() {
        return listaLocal;
    }
    
    /** Método que retorna a lista de usuários.
     * 
     * @return ILista listaUsuario
     */
    public ILista getListaUsuario() {
        return listaUsuario;
    }

    /** Método que retorna a lista geral de Atrações.
     * 
     * @return ILista listaAtracoes
     */
    public ILista getListaAtracoes() {
        return listaAtracoes;
    }

    /** Método que retorna a lista geral de Avaliações.
     * 
     * @return ILista listaAvaliacoes
     */
    public ILista getListaAvaliacoes() {
        return listaAvaliacoes;
    }

    /** Método que (re)inicia o sistema.
     */
    public static void zerarSingleton() {
        assistente = null;
    }

    /** Método público estático de acesso único ao objeto.
     * 
     * @return AssistenteViagem assistente
     */
    public static AssistenteViagem getInstance() {
 
        if (assistente == null) {
            assistente = new AssistenteViagem(); 
        }
        return assistente; 
    }

    /** Método que cadastra um local, ele recebe todas as suas informações, 
     * sua referência é salva ao final de uma lista encadeada.
     * 
     * @param nome
     * @param localizacao
     * @param estado
     * @param pais
     */
    public void cadastrarLocal(String nome, Coordenadas localizacao, String estado,
                               String pais) {
        Local local = new Local(nome, localizacao, estado, pais);
        listaLocal.inserirFinal(local);
    }

    /** Método que retorna um objeto da classe Iterador, que faz referência à
     * lista de locais cadastrados no Assistente de Viagem, eles são listados em
     * ordem alfabética.
     * 
     * @return Iterador iterador
     */
    public Iterador listarLocais() {
        Local[] vetorLocal = new Local[listaLocal.obterTamanho()];
        
        for (int i = 0; i < listaLocal.obterTamanho(); i++) {
            vetorLocal[i] = (Local) listaLocal.recuperar(i);
        }
        int ini = 0, fim = vetorLocal.length - 1;
        QuickSort ordenacao = new QuickSort();
        ordenacao.ordemAlfabetica(vetorLocal, ini, fim);
        listaLocal = new Lista();
        
        for (int i = 0; i < vetorLocal.length; i++) {
            listaLocal.inserirFinal(vetorLocal[i]);
        }
        return (listaLocal.iterador());
    }
    
    /** Método para obter (encontrar) um local, recebe como parâmetro seu nome,
     * a lista de locais é percorrida até que algum voluntário tenha o nome
     * desejado ou ela chegue ao fim, ao ser encontrado, sua referência é 
     * retornada, caso contrário retorna nulo.
     * 
     * @param nome
     * @return Local local
     */
    public Local buscarLocal(String nome) {
        
        for (int i = 0; i < listaLocal.obterTamanho(); i++) {
            Local local = (Local) listaLocal.recuperar(i);
            
            if (nome.equals(local.getNome())) {
                return local;
            }
        }
        return null;        
    }

    /** Método que cadastra um usuario, ele recebe todas as suas informações, 
     * sua referência é salva ao final de uma lista encadeada.
     * 
     * @param emailLogin
     * @param hashSenha
     * @param sexo
     * @param nomeCompleto
     */
    public void cadastrarUsuario(String emailLogin, String hashSenha, Sexo sexo,
                                 String nomeCompleto) {
        Usuario usuario = new Usuario(emailLogin, hashSenha, sexo, nomeCompleto);
        listaUsuario.inserirFinal(usuario);
    }

    /** Método que que permite um usuario cadastrado logar no sistema.
     * 
     * @param emailLogin
     * @param hashSenha
     * @return Usuario usuario
     */
    public Usuario fazerLogin(String emailLogin, String hashSenha) {
        
        for (int i = 0; i < listaUsuario.obterTamanho(); i++) {
            Usuario usuario = (Usuario) listaUsuario.recuperar(i);
            if (emailLogin.equals(usuario.getEmailLogin()) && toSha1(hashSenha).equals(usuario.getHashSenhaCrip())) {
                return usuario;
            }
        }
        return null;
    }
    
    /** Método que cadastra uma atração, ele recebe todas as suas informações, 
     * sua referência é salva ao final de duas listas encadeadas; uma de geral
     * de atrações e uma referente ao local onde ela está.
     * 
     * @param local
     * @param nome
     * @param localizacao
     * @param categoria
     */
    public void cadastrarAtracao(Local local, String nome, Coordenadas localizacao,
                                 CategoriaAtracao categoria) {
        Atracao atracao = new Atracao(local, nome, localizacao, categoria);
        listaAtracoes.inserirFinal(atracao);       
        local.getListaAtracao().inserirFinal(atracao);
    }

    /** Método que cadastra uma restaurante, (uma tipo específico de atração)
     * ele recebe todas as suas informações, sua referência é salva ao final de
     * duas listas encadeadas; uma de geral de atrações e uma referente ao local
     * onde ela está.
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
              CategoriaAtracao categoria, FaixaPreco preco, TipoCozinha cozinha,
              String bairro) {
        Restaurante restaurante = new Restaurante(local, nome, localizacao, categoria,
                                                  preco, cozinha, bairro);
        listaAtracoes.inserirFinal(restaurante);        
        local.getListaAtracao().inserirFinal(restaurante);
    }
    
    /** Método para obter (encontrar) uma atração, recebe como parâmetro seu nome,
     * a lista de atrações é percorrida até que alguma tenha o nome desejado ou
     * ela chegue ao fim, ao ser encontrada, sua referência é retornada, caso
     * contrário retorna nulo.
     * 
     * @param nome
     * @return Atracao atracao
     */
    public Atracao buscarAtracao(String nome) {
        
        for (int i = 0; i < listaAtracoes.obterTamanho(); i++) {
            Atracao atracao = (Atracao) listaAtracoes.recuperar(i);
            
            if (nome.equals(atracao.getNome())) {
                return atracao;
            }
        }
        return null; 
    }   

    /** Método que remove e retorna avaliação por avaliação da lista geral de
     * avaliações, para que ela seja processada e entre na lista de avaliações
     * específicas de uma atração.
     * 
     * @return Avaliacao avaliacao
     */
    public Avaliacao processarProximaAvaliacao() {
        return ((Avaliacao) listaAvaliacoes.removerInicio());
    }

    /** Batch para recalcular os rankings de todas as atrações, em ordem crescente
     * de  média de pontos avaliados.
     */
    public void recalcularTodosRankingsAtracoes() {
        Atracao[] vetorAtracoes = new Atracao[listaAtracoes.obterTamanho()];
        
        for (int i = 0; i < listaAtracoes.obterTamanho(); i++) {
            vetorAtracoes[i] = (Atracao) listaAtracoes.recuperar(i);
        }
        int ini = 0, fim = vetorAtracoes.length - 1;
        QuickSort ordenacao = new QuickSort();
        ordenacao.ordemNumerica(vetorAtracoes, ini, fim);
        listaAtracoes = new Lista();
        
        for (int i = 0; i < vetorAtracoes.length; i++) {
            vetorAtracoes[i].setOrdem(i + 1);
            listaAtracoes.inserirFinal(vetorAtracoes[i]);
        }
    }
}