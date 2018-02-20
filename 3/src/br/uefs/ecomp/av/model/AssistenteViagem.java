/*
 * Autores: Camille de L. Jesus e Rodrigo B. Santos
 * Componente Curricular: EXA863 - MI Programação
 * Data: 18/9/15
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
import br.uefs.ecomp.av.model.exception.CampoVazioException;
import br.uefs.ecomp.av.model.exception.DadoExistenteException;
import br.uefs.ecomp.av.model.exception.DadoInexistenteException;
import br.uefs.ecomp.av.util.Arvore;
import br.uefs.ecomp.av.util.Coordenadas;
import br.uefs.ecomp.av.util.ILista;
import br.uefs.ecomp.av.util.Lista;
import br.uefs.ecomp.av.util.Mapa;
import br.uefs.ecomp.av.util.Iterador;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Classe AssistenteViagem, um singleton; padrão que garante a existência de
 * apenas uma instância da classe, mantendo um ponto global de acesso ao seu
 * objeto.
 *
 * @author Camille Jesus e Rodrigo Santos
 */
public class AssistenteViagem implements Serializable {

    private static AssistenteViagem assistente;   //Variavel estática que conterá a instância do método.
    private final ILista listaLocal, listaUsuario, listaAtracoes, listaAvaliacoes;
    private final Mapa mapaLocal;
    private final Arvore arvoreAtracao;

    //Construtor privado, que suprime o construtor público padrão, cria as listas e mapas:
    private AssistenteViagem() {
        listaLocal = new Lista();
        listaUsuario = new Lista();
        listaAtracoes = new Lista();
        listaAvaliacoes = new Lista();
        mapaLocal = new Mapa(100);
        arvoreAtracao = new Arvore();
    }

    /**
     * Método que retorna a lista de locais.
     *
     * @return ILista listaLocal
     */
    public ILista getListaLocal() {
        return listaLocal;
    }

    /**
     * Método que retorna a lista de usuários.
     *
     * @return ILista listaUsuario
     */
    public ILista getListaUsuario() {
        return listaUsuario;
    }

    /**
     * Método que retorna a lista geral de atrações.
     *
     * @return ILista listaAtracoes
     */
    public ILista getListaAtracoes() {
        return listaAtracoes;
    }

    /**
     * Método que retorna a lista geral de avaliações.
     *
     * @return ILista listaAvaliacoes
     */
    public ILista getListaAvaliacoes() {
        return listaAvaliacoes;
    }

    /**
     * Método que retorna o mapa de locais.
     *
     * @return IMapa mapaLocal
     */
    public Mapa getMapaLocal() {
        return mapaLocal;
    }

    /**
     * Método que retorna o mapa de atrações.
     *
     * @return IMapa mapaAtracao
     */
    public Arvore getArvoreAtracao() {
        return arvoreAtracao;
    }

    /**
     * Método que (re)inicia o sistema.
     */
    public static void zerarSingleton() {
        assistente = null;
    }

    /**
     * Método público estático de acesso único ao objeto.
     *
     * @return AssistenteViagem assistente
     */
    public static AssistenteViagem getInstance() {

        if (assistente == null) {
            assistente = new AssistenteViagem();
        }
        return assistente;
    }

    /**
     * Método que cadastra um local, ele recebe todas as suas informações, sua
     * referência é salva ao final de uma lista encadeada e no mapa.
     *
     * @param nome
     * @param localizacao
     * @param estado
     * @param pais
     * @throws CampoVazioException
     */
    public void cadastrarLocal(String nome, Coordenadas localizacao, String estado,
                               String pais) throws CampoVazioException {
        
        if ((nome.trim().length() > 0) && (localizacao != null) && (estado.trim().length() > 0)
             && (pais.trim().length() > 0)) {
            Local local = new Local(nome, localizacao, estado, pais);
            assistente.listaLocal.inserirFinal(local);
            assistente.mapaLocal.inserir(nome, local);
        } else {
            throw (new CampoVazioException("Campos vazios!"));
        }
    }

    /**
     * Método que retorna um objeto da classe Iterador, que faz referência à
     * lista de locais cadastrados no Assistente de Viagem, eles são listados em
     * ordem alfabética.
     *
     * @return Iterador iterador
     */
    public Iterador listarLocais() {
        assistente.listaLocal.ordenar();
        return (assistente.listaLocal.iterador());
    }

    /**
     * Método para obter (encontrar) um local, recebe como parâmetro seu nome, o
     * mapa é percorrido até que algum local tenha o nome desejado ou ele chegue
     * ao fim, ao ser encontrado, sua referência é retornada, caso contrário
     * retorna nulo.
     *
     * @param nome
     * @return Local local
     * @throws DadoInexistenteException
     */
    public Local buscarLocal(String nome) throws DadoInexistenteException {
        Local local = ((Local) assistente.mapaLocal.recuperar(nome));
        
        if (local != null) {
            return local;
        }
        throw (new DadoInexistenteException("Local não encontrado!"));
    }

    /**
     * Método que cadastra um usuário, ele recebe todas as suas informações, sua
     * referência é salva ao final de uma lista encadeada.
     *
     * @param emailLogin
     * @param hashSenha
     * @param sexo
     * @param nomeCompleto
     * @throws CampoVazioException
     */
    public void cadastrarUsuario(String emailLogin, String hashSenha, Sexo sexo,
                                 String nomeCompleto) throws CampoVazioException {
        
        if ((emailLogin.trim().length() > 0) && (hashSenha.trim().length() > 0) && (sexo != null) && (nomeCompleto.trim().length() > 0)) {
            assistente.listaUsuario.inserirFinal(new Usuario(emailLogin, hashSenha, sexo, nomeCompleto));
        } else {
            throw (new CampoVazioException("Campos vazios!"));
        }
    }

    /**
     * Método que permite um usuário cadastrado logar no sistema.
     *
     * @param emailLogin
     * @param hashSenha
     * @return Usuario usuario
     * @throws DadoInexistenteException
     */
    public Usuario fazerLogin(String emailLogin, String hashSenha) throws DadoInexistenteException {
        Iterador it = assistente.listaUsuario.iterador();
        
        while (it.temProximo()) {
            Usuario usuario = ((Usuario) it.obterProximo());          

            if ((emailLogin.equals(usuario.getEmailLogin())) && (toSha1(hashSenha).equals(usuario.getHashSenhaCrip()))) {
                return usuario;
            }
        }
        throw (new DadoInexistenteException("Usuário não encontrado!"));
    }

    /**
     * Método que cadastra um restaurante (um tipo específico de atração), ele
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
     * @throws DadoExistenteException
     */
    public void cadastrarRestaurante(Local local, String nome, Coordenadas localizacao,
            String bairro, CategoriaAtracao categoria, FaixaPreco preco, TipoCozinha
                                     cozinha) throws DadoExistenteException {
        Restaurante restaurante = new Restaurante(local, nome, localizacao, bairro,
                                                  categoria, preco, cozinha);
        assistente.listaAtracoes.inserirFinal(restaurante);
        assistente.arvoreAtracao.inserir(restaurante);
        local.getListaAtracao().inserirFinal(restaurante);
        local.getArvoreAtracao().inserir(restaurante);
    }

    /**
     * Método que cadastra um hotel (um tipo específico de atração), ele recebe
     * todas as suas informações, sua referência é salva ao final de duas listas
     * encadeadas; uma de geral de atrações e uma referente ao local onde ela
     * está, e no mapa.
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
        Hotel hotel = new Hotel(local, nome, localizacao, bairro, categoria,
                                estrelas, ar, tv, servicoQuarto);
        assistente.listaAtracoes.inserirFinal(hotel);
        assistente.arvoreAtracao.inserir(hotel);
        local.getListaAtracao().inserirFinal(hotel);
        local.getArvoreAtracao().inserir(hotel);
    }

    /**
     * Método para obter (encontrar) uma atração, recebe como parâmetro seu
     * nome, o mapa é percorrido até que alguma tenha o nome desejado ou ele
     * chegue ao fim, ao ser encontrada, sua referência é retornada, caso
     * contrário retorna nulo.
     *
     * @param nome
     * @return Atracao atracao
     * @throws DadoInexistenteException
     */
    public Atracao buscarAtracao(String nome) throws DadoInexistenteException {
        Atracao atracao = ((Atracao) assistente.arvoreAtracao.buscar(nome));
        
        if (atracao != null) {
            return atracao;
        }
        throw (new DadoInexistenteException("Atração não existe!"));
    }

    /**
     * Método que remove e retorna avaliação por avaliação da lista geral de
     * avaliações, para que ela seja processada e entre na lista de avaliações
     * específicas de uma atração.
     *
     * @return Avaliacao avaliacao
     * @throws br.uefs.ecomp.av.model.exception.DadoInexistenteException
     */
    public Avaliacao processarProximaAvaliacao() throws DadoInexistenteException {
        Avaliacao avaliacao = ((Avaliacao) assistente.listaAvaliacoes.removerInicio());
        if (avaliacao != null) {
            return avaliacao;
        }
        throw (new DadoInexistenteException("Avaliacao não existe!"));
    }

    /**
     * Batch para recalcular os rankings de todas as atrações, em ordem
     * decrescente de média de pontos avaliados.
     */
    public void recalcularTodosRankingsAtracoes() {
        assistente.listaAtracoes.ordenar();        
        
        for (int i = 0; i < assistente.listaAtracoes.obterTamanho(); i++) {
            ((Atracao) assistente.listaAtracoes.recuperar(i)).setOrdem(i + 1);
        }
    }

    /** Operador carrega os dados do sistema de arquivo.
     *     
     * @throws IOException
     * @throws ClassNotFoundException
     */   
    public void carregarEstadoSistema() throws IOException, ClassNotFoundException  {
        
        try {
            //Carrega o arquivo:
            FileInputStream file = new FileInputStream("AV3.txt");
            //Classe responsavel por recuperar os objetos do arquivo:
            ObjectInputStream obj = new ObjectInputStream(file);
            //Grava o arquivo no objeto assistente:
            assistente = (AssistenteViagem) obj.readObject();
            obj.close();
            file.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Arquivo não encontrado!");
        }
    }

    /** Operador carrega os dados do sistema de arquivo.
     * 
     * @throws IOException
     */
    public void salvarEstadoSistema() throws IOException {
        
        try {
            //Gera o arquivo para armazenar o objeto:
            FileOutputStream file = new FileOutputStream("AV3.txt");
            //Classe responsavel por inserir os objetos:
            ObjectOutputStream obj = new ObjectOutputStream(file);
            //Grava o objeto assistente no arquivo:
            obj.writeObject(assistente);
            obj.flush();
            obj.close();
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar sistema!");
        }
    }
}