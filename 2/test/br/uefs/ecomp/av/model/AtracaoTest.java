package br.uefs.ecomp.av.model;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.av.model.enums.CategoriaAtracao;
import br.uefs.ecomp.av.model.enums.FaixaPreco;
import br.uefs.ecomp.av.model.enums.TipoCozinha;
import br.uefs.ecomp.av.util.Coordenadas;

public class AtracaoTest {

    private Atracao atracao = null;
    private Local local = null;

    @Before
    public void setUp() throws Exception {
        local = new Local("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
        atracao = new Restaurante(local, "Gauchão", new Coordenadas(-12.199689, -38.971118), "Brasília", CategoriaAtracao.RESTAURANTE, FaixaPreco.BARATO, TipoCozinha.NACIONAL);
    }

    @Test
    public void testBasic() {
        assertNotNull(atracao);
        assertSame(local, atracao.getLocal());
        assertEquals("Gauchão", atracao.getNome());
        assertEquals(new Coordenadas(-12.199689, -38.971118), atracao.getLocalizacao());
        assertEquals("Brasília", atracao.getBairro());
    }
}