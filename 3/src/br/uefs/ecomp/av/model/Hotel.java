package br.uefs.ecomp.av.model;


import br.uefs.ecomp.av.model.enums.CategoriaAtracao;
import br.uefs.ecomp.av.util.Coordenadas;

/**
 * Classe Restaurante, subclasse da superclasse Atracao, responsável pela criação
 * de cada hotel de um local.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class Hotel extends Atracao {

    private final CategoriaAtracao categoria;
    private final int estrelas;
    private final boolean ar, tv, servicoQuarto;
    
    /** Construtor da classe, responsável pela criação um novo hotel, recebe as
     * informações e cria um objeto.
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
    public Hotel(Local local, String nome, Coordenadas localizacao,
            String bairro, CategoriaAtracao categoria, int estrelas, boolean ar,
                 boolean tv, boolean servicoQuarto) {
        super(local, nome, localizacao, bairro);
        this.categoria = categoria;
        this.estrelas = estrelas;
        this.ar = ar;
        this.tv = tv;
        this.servicoQuarto = servicoQuarto;
    }

    /** Método que retorna a categoria de um hotel.
     * 
     * @return CategoriaAtracao categoria
     */
    public CategoriaAtracao getCategoria() {
        return categoria;
    }

    /** Método que retorna a quantidade de estrelas de um hotel.
     * 
     * @return int estrelas
     */
    public int getEstrelas() {
        return estrelas;
    }

    /** Método que retorna se há ar condicionado em um hotel.
     * 
     * @return boolean ar
     */
    public boolean getAr() {
        return ar;
    }
    
    /** Método que retorna se há televisão em um hotel.
     * 
     * @return boolean ar
     */
    public boolean getTv() {
        return tv;
    }
    
    /** Método que retorna se há serviço de quarto em um hotel.
     * 
     * @return boolean ar
     */
    public boolean getServicoQuarto() {
        return servicoQuarto;
    }
}