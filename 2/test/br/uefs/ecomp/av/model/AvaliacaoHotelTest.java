package br.uefs.ecomp.av.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.av.model.enums.CategoriaAtracao;
import br.uefs.ecomp.av.model.enums.ObjetivoVisita;
import br.uefs.ecomp.av.model.enums.Sexo;
import br.uefs.ecomp.av.util.Coordenadas;

public class AvaliacaoHotelTest {

    private Local local = null;
    private Usuario usuario = null;
    private Hotel hotel = null;
    private AvaliacaoHotel avaliacao = null;
    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Before
    public void setUp() throws Exception {
        Date data = format.parse("13/10/2014");
        
        usuario = new Usuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        local = new Local("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
        hotel = new Hotel(local, "GoldPalace", new Coordenadas(-12.254778, -38.956394), "Centro", CategoriaAtracao.HOTEL,5, false, true, false);
        avaliacao = new AvaliacaoHotel(usuario, hotel, 4, "Bom pra relaxar!", "O GoldPalace é oq é há!", data, ObjetivoVisita.FAMILIA, 8, 8, 8);
    }

    @Test
    public void testBasic() throws ParseException {
        assertNotNull(avaliacao);
        assertSame(usuario, avaliacao.getAvaliador());
        assertSame(hotel, avaliacao.getAtracaoAvaliada());
        assertEquals(4, avaliacao.getPontos());
        assertEquals("Bom pra relaxar!", avaliacao.getTitulo());
        assertEquals("O GoldPalace é oq é há!", avaliacao.getTexto());
        assertEquals(format.parse("13/10/2014"), avaliacao.getData());
        assertEquals(ObjetivoVisita.FAMILIA, avaliacao.getObjetivoVisita());
        assertEquals(8, avaliacao.getAtendimento());
        assertEquals(8, avaliacao.getQuartos());
        assertEquals(8, avaliacao.getQualidadeSono());
    }
}