package br.uefs.ecomp.av.model;


import br.uefs.ecomp.av.model.enums.CategoriaAtracao;
import br.uefs.ecomp.av.model.enums.FaixaPreco;
import br.uefs.ecomp.av.model.enums.TipoCozinha;
import br.uefs.ecomp.av.util.Coordenadas;

/**
 * Classe Restaurante, subclasse da superclasse Atração, responsável pela criação
 * de cada restaurante de um local.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class Restaurante extends Atracao {

    private final CategoriaAtracao categoria;
    private final FaixaPreco preco;
    private final TipoCozinha cozinha;
    
    /** Construtor da classe, responsável pela criação um novo restaurante, recebe
     * as informações e cria um objeto.
     * 
     * @param local
     * @param nome
     * @param localizacao
     * @param bairro
     * @param categoria
     * @param preco
     * @param cozinha
     */
    public Restaurante(Local local, String nome, Coordenadas localizacao, String
            bairro, CategoriaAtracao categoria, FaixaPreco preco, TipoCozinha cozinha) {
        super(local, nome, localizacao, bairro);
        this.categoria = categoria;
        this.preco = preco;
        this.cozinha = cozinha;
    }

    /** Método que retorna a categoria de um restaurante.
     * 
     * @return FaixaPreco preco
     */
    public CategoriaAtracao getCategoria() {
        return categoria;
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
}