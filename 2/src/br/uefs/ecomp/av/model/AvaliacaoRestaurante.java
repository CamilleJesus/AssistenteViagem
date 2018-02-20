package br.uefs.ecomp.av.model;


import br.uefs.ecomp.av.model.enums.TipoRefeicao;
import br.uefs.ecomp.av.model.enums.TipoVisita;

import java.util.Date;

/**
 * Classe AvaliacaoRestaurante, subclasse da superclasse Avaliacao, responsável pela
 * criação de cada avaliação de um restaurante.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
class AvaliacaoRestaurante extends Avaliacao {
    private final TipoVisita tipoVisita;
    private final TipoRefeicao tipoRefeicao;
    private final int atendimento, comida, custoBeneficio;

    /** Construtor da classe, responsável pela criação de uma nova avaliação de
     * restaurante, recebe as informações e cria um objeto.
     * 
     * @param avaliador
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
    public AvaliacaoRestaurante(Usuario avaliador, Atracao atracaoAvaliada, int
            pontos, String titulo, String texto, Date data, TipoVisita tipoVisita,
            TipoRefeicao tipoRefeicao, int atendimento, int comida, int custoBeneficio) {
        super(avaliador, atracaoAvaliada, pontos, titulo, texto, data);
        this.tipoVisita = tipoVisita;
        this.tipoRefeicao = tipoRefeicao;
        this.atendimento = atendimento;
        this.comida = comida;
        this.custoBeneficio = custoBeneficio;
    }

    /** Método que retorna o tipo da visita ao restaurante.
     * 
     * @return TipoVisita tipoVisita
     */
    public TipoVisita getTipoVisita() {
        return tipoVisita;
    }

    /** Método que retorna o tipo da refeição no restaurante.
     * 
     * @return TipoRefeicao tipoRefeicao
     */
    public TipoRefeicao getTipoRefeicao() {
        return tipoRefeicao;
    }

    /** Método que retorna a nota do atendimento no restaurante.
     * 
     * @return int atendimento
     */
    public int getAtendimento() {
        return atendimento;
    }

    /** Método que retorna a nota da refeição no restaurante.
     * 
     * @return int comida
     */
    public int getComida() {
        return comida;
    }

    /** Método que retorna a relação custo-benefício do restaurante.
     * 
     * @return int custoBeneficio
     */
    public int getCustoBeneficio() {
        return custoBeneficio;
    }   
}