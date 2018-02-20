/*
 * Autores: Camille de L. Jesus e Rodrigo B. Santos
 * Componente Curricular: EXA863 - MI Programação
 * Data: 17/8/15
 * Declaro que este código foi elaborado por mim de forma individual e não contém
 * algum trecho de código de outro autor, tais como provindos de livros ou apostilas,
 * e páginas ou documentos eletrônicos da internet. Qualquer trecho de código de
 * outra autoria, que não minha, poderá estar destacado com uma citação para o
 * autor ou a fonte do código e estou ciente que estes trechos não serão considerados
 * para fins de avaliação.
 */

package br.uefs.ecomp.av.model;


import static br.uefs.ecomp.av.model.Usuario.toSha1;
import br.uefs.ecomp.av.model.enums.CategoriaAtracao;
import br.uefs.ecomp.av.model.enums.FaixaPreco;
import br.uefs.ecomp.av.model.enums.Sexo;
import br.uefs.ecomp.av.model.enums.TipoCozinha;
import br.uefs.ecomp.av.util.Coordenadas;
import br.uefs.ecomp.av.util.ILista;
import br.uefs.ecomp.av.util.Lista;
import br.uefs.ecomp.av.util.Mapa;
import br.uefs.ecomp.av.util.Iterador;

import java.io.Serializable;

/**
 * Classe AssistenteViagem, um singleton; padrão que garante a existência de
 * apenas uma instância da classe, mantendo um ponto global de acesso ao seu
 * objeto.
 * 
 * @author Camille Jesus
 */
public class AssistenteViagem implements Serializable {
    
    private static AssistenteViagem assistente;   //Variavel estática que conterá a instância do método.
    private ILista listaLocal, listaUsuario, listaAtracoes, listaAvaliacoes;
    private Mapa mapaLocal, mapaAtracao;
    
    //Construtor privado, que suprime o construtor público padrão, cria as listas e mapas:
    private AssistenteViagem() {
        listaLocal = new Lista();
        listaUsuario = new Lista();
        listaAtracoes = new Lista();
        listaAvaliacoes = new Lista();
        mapaLocal = new Mapa(100);
        mapaAtracao = new Mapa(100);
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

    /** Método que retorna a lista geral de atrações.
     * 
     * @return ILista listaAtracoes
     */
    public ILista getListaAtracoes() {
        return listaAtracoes;
    }

    /** Método que retorna a lista geral de avaliações.
     * 
     * @return ILista listaAvaliacoes
     */
    public ILista getListaAvaliacoes() {
        return listaAvaliacoes;
    }

    /** Método que retorna o mapa de locais.
     * 
     * @return IMapa mapaLocal
     */
    public Mapa getMapaLocal() {
        return mapaLocal;
    }
    
    /** Método que retorna o mapa de atrações.
     * 
     * @return IMapa mapaAtracao
     */
    public Mapa getMapaAtracao() {
        return mapaAtracao;
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
     * sua referência é salva ao final de uma lista encadeada e no mapa.
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
        mapaLocal.inserir(nome, local);
    }
    
    /** Método que retorna um objeto da classe Iterador, que faz referência à
     * lista de locais cadastrados no Assistente de Viagem, eles são listados em
     * ordem alfabética.
     * 
     * @return Iterador iterador
     */
    public Iterador listarLocais() {
        listaLocal.ordenar();
        return (listaLocal.iterador());
    }
    
    /** Método para obter (encontrar) um local, recebe como parâmetro seu nome,
     * o mapa é percorrido até que algum local tenha o nome desejado ou ele chegue
     * ao fim, ao ser encontrado, sua referência é retornada, caso contrário retorna nulo.
     * 
     * @param nome
     * @return Local local
     */
    public Local buscarLocal(String nome) {
        return ((Local) mapaLocal.recuperar(nome));
    }
    
    /** Método que cadastra um usuário, ele recebe todas as suas informações, 
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
    
    /** Método que permite um usuário cadastrado logar no sistema.
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
    
    /** Método que cadastra um restaurante (um tipo específico de atração), ele
     * recebe todas as suas informações, sua referência é salva ao final de duas
     * listas encadeadas; uma de geral de atrações e uma referente ao local onde
     * ela está, e no mapa.
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
            String bairro, CategoriaAtracao categoria, FaixaPreco preco, TipoCozinha cozinha) {
        Restaurante restaurante = new Restaurante(local, nome, localizacao, bairro,
                                                  categoria, preco, cozinha);
        listaAtracoes.inserirFinal(restaurante);        
        local.getListaAtracao().inserirFinal(restaurante);
        mapaAtracao.inserir(nome, restaurante);
    }
    
    /** Método que cadastra um hotel (um tipo específico de atração), ele recebe
     * todas as suas informações, sua referência é salva ao final de duas listas
     * encadeadas; uma de geral de atrações e uma referente ao local onde ela está,
     * e no mapa.
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
     */
    public void cadastrarHotel(Local local, String nome, Coordenadas localizacao,
            String bairro, CategoriaAtracao categoria, int estrelas, boolean ar,
            boolean tv, boolean servicoQuarto) {
        Hotel hotel = new Hotel(local, nome, localizacao, bairro, categoria,
                                estrelas, ar, tv, servicoQuarto);
        listaAtracoes.inserirFinal(hotel);        
        local.getListaAtracao().inserirFinal(hotel);
        mapaAtracao.inserir(nome, hotel);
    }
    
    /** Método para obter (encontrar) uma atração, recebe como parâmetro seu nome,
     * o mapa é percorrido até que alguma tenha o nome desejado ou ele chegue ao
     * fim, ao ser encontrada, sua referência é retornada, caso contrário retorna nulo.
     * 
     * @param nome
     * @return Atracao atracao
     */
    public Atracao buscarAtracao(String nome) {
        return ((Atracao) mapaAtracao.recuperar(nome));
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
    
    /** Batch para recalcular os rankings de todas as atrações, em ordem decrescente
     * de  média de pontos avaliados.
     */
    public void recalcularTodosRankingsAtracoes() {
        listaAtracoes.ordenar();        
        
        for (int i = 0; i < listaAtracoes.obterTamanho(); i++) {
            Atracao atracao = (Atracao) listaAtracoes.recuperar(i);
            atracao.setOrdem(i + 1);
        }
    }
}