package br.uefs.ecomp.av.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.av.model.enums.CategoriaAtracao;
import br.uefs.ecomp.av.model.enums.FaixaPreco;
import br.uefs.ecomp.av.model.enums.Sexo;
import br.uefs.ecomp.av.model.enums.TipoCozinha;
import br.uefs.ecomp.av.model.enums.TipoRefeicao;
import br.uefs.ecomp.av.model.enums.TipoVisita;
import br.uefs.ecomp.av.util.Coordenadas;

public class AvaliacaoRestauranteTest {

    private Local local = null;
    private Usuario usuario = null;
    private Restaurante restaurante = null;
    private AvaliacaoRestaurante avaliacao = null;
    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Before
    public void setUp() throws Exception {
        Date data = format.parse("12/12/2014");
        
        usuario = new Usuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        local = new Local("Salvador", new Coordenadas(-12.971111, -38.510833), "Bahia", "Brasil");
        restaurante = new Restaurante(local, "Los Pampas", new Coordenadas(-12.287416, -38.957770), "Queimadinha", CategoriaAtracao.RESTAURANTE, FaixaPreco.CARO, TipoCozinha.NACIONAL);
        avaliacao = new AvaliacaoRestaurante(usuario, restaurante, 2, "Pessimo", "Nunca passei por uma experiencia tão constrangedora", data, TipoVisita.JANTAR_A_DOIS, TipoRefeicao.JANTAR, 4, 5, 3);
    }

    @Test
    public void testBasic() throws ParseException {
        assertNotNull(avaliacao);
        assertSame(usuario, avaliacao.getAvaliador());
        assertSame(restaurante, avaliacao.getAtracaoAvaliada());
        assertEquals(2, avaliacao.getPontos());
        assertEquals("Pessimo", avaliacao.getTitulo());
        assertEquals("Nunca passei por uma experiencia tão constrangedora", avaliacao.getTexto());
        assertEquals(format.parse("12/12/2014"), avaliacao.getData());
        assertEquals(TipoVisita.JANTAR_A_DOIS, avaliacao.getTipoVisita());
        assertEquals(TipoRefeicao.JANTAR, avaliacao.getTipoRefeicao());
        assertEquals(4, avaliacao.getAtendimento());
        assertEquals(5, avaliacao.getComida());
        assertEquals(3, avaliacao.getCustoBeneficio());
        
    }
}