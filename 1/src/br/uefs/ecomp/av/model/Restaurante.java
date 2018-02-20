package br.uefs.ecomp.av.model;


import br.uefs.ecomp.av.model.enums.CategoriaAtracao;
import br.uefs.ecomp.av.model.enums.FaixaPreco;
import br.uefs.ecomp.av.model.enums.TipoCozinha;
import br.uefs.ecomp.av.util.Coordenadas;

/**
 * Classe Restaurante, subclasse da superclasse Atração, responsável pela criação
 * de cada restaurante de um local.
 * 
 * @author Camille Jesus
 */
public class Restaurante extends Atracao {

    private final FaixaPreco preco;
    private final TipoCozinha cozinha;
    private final String bairro;
    
    /** Construtor da classe, responsável pela criação um novo restaurante, recebe
     * as informações e cria um objeto.
     * 
     * @param local
     * @param nome
     * @param localizacao
     * @param categoria
     * @param preco
     * @param cozinha
     * @param bairro
     */
    public Restaurante(Local local, String nome, Coordenadas localizacao,
            CategoriaAtracao categoria, FaixaPreco preco, TipoCozinha cozinha,
            String bairro) {
        super(local, nome, localizacao, categoria);
        this.preco = preco;
        this.cozinha = cozinha;
        this.bairro = bairro;
    }

    /** Método que retorna a faixa de preco de um restaurante.
     * 
     * @return FaixaPreco preco
     */
    public FaixaPreco getPreco() {
        return preco;
    }

    /** Método que retorna a o tipo de cozinha de um restaurante.
     * 
     * @return TipoCozinha cozinha
     */
    public TipoCozinha getCozinha() {
        return cozinha;
    }

    /** Método que retorna o nome do bairro de um restaurante.
     * 
     * @return String bairro
     */
    public String getBairro() {
        return bairro;
    }

    /** Método que dá uma representação em String de uma restaurante.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return ("Restaurante - " + "Nome: " + super.getNome() + ", Preço: " + preco + ","
               + "Cozinha: " + cozinha + ", Bairro: " + bairro + '.');
    }
}