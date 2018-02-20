package br.uefs.ecomp.av.model;


import br.uefs.ecomp.av.model.enums.ObjetivoVisita;

import java.util.Date;

/**
 * Classe AvaliacaoHotel, subclasse da superclasse Avaliacao, responsável pela
 * criação de cada avaliação de um hotel.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
class AvaliacaoHotel extends Avaliacao {
    private final ObjetivoVisita objetivoVisita;
    private final int atendimento, quartos, qualidadeSono;

    /** Construtor da classe, responsável pela criação de uma nova avaliação de
     * hotel, recebe as informações e cria um objeto.
     * 
     * @param avaliador
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
    public AvaliacaoHotel(Usuario avaliador, Atracao atracaoAvaliada, int pontos,
            String titulo, String texto, Date data, ObjetivoVisita objetivoVisita,
            int atendimento, int quartos, int qualidadeSono) {
        super(avaliador, atracaoAvaliada, pontos, titulo, texto, data);
        this.objetivoVisita = objetivoVisita;
        this.atendimento = atendimento;
        this.quartos = quartos;
        this.qualidadeSono = qualidadeSono;
    }

    /** Método que retorna o objetivo da visita ao hotel.
     * 
     * @return ObjetivoVisita objetivoVisita
     */
    public ObjetivoVisita getObjetivoVisita() {
        return objetivoVisita;
    }

    /** Método que retorna a nota do atendimento no hotel.
     * 
     * @return int atendimento
     */
    public int getAtendimento() {
        return atendimento;
    }

    /** Método que retorna a quantidade de quartos alugados no hotel.
     * 
     * @return int quartos
     */
    public int getQuartos() {
        return quartos;
    }

    /** Método que retorna a nota da qualidade de sono no hotel.
     * 
     * @return ObjetivoVisita objetivoVisita
     */
    public int getQualidadeSono() {
        return qualidadeSono;
    }
}