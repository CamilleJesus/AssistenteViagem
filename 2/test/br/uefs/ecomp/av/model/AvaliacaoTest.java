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

public class AvaliacaoTest {

    private Local local = null;
    private Usuario usuario = null;
    private Atracao atracao = null;
    private Avaliacao avaliacao = null;
    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Before
    public void setUp() throws Exception {
        Date data = format.parse("12/10/2014");
        
        usuario = new Usuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        local = new Local("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
        atracao = new Restaurante(local, "Vivas", new Coordenadas(-12.254778, -38.956394), "Kalilandia", CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL);
        avaliacao = new AvaliacaoRestaurante(usuario, atracao, 5, "Otimo restaurante!", "Bom servico, pratos bem saborosos, preco um pouco alto.", data, TipoVisita.AMIGOS, TipoRefeicao.ALMOCO, 4, 5, 3);
    }

    @Test
    public void testBasic() throws ParseException {
        assertNotNull(avaliacao);
        assertSame(usuario, avaliacao.getAvaliador());
        assertSame(atracao, avaliacao.getAtracaoAvaliada());
        assertEquals(5, avaliacao.getPontos());
        assertEquals("Otimo restaurante!", avaliacao.getTitulo());
        assertEquals("Bom servico, pratos bem saborosos, preco um pouco alto.", avaliacao.getTexto());
        assertEquals(format.parse("12/10/2014"), avaliacao.getData());
    }
}