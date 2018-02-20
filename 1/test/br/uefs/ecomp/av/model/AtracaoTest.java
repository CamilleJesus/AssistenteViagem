package br.uefs.ecomp.av.model;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.av.model.enums.CategoriaAtracao;
import br.uefs.ecomp.av.util.Coordenadas;

public class AtracaoTest {

    private Atracao atracao = null;
    private Local local = null;

    @Before
    public void setUp() throws Exception {
        local = new Local("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
        atracao = new Atracao(local, "Praca do Borogodo", new Coordenadas(-12.199689, -38.971118), CategoriaAtracao.PRACA);
    }

    @Test
    public void testBasic() {
        assertNotNull(atracao);
        assertSame(local, atracao.getLocal());
        assertEquals("Praca do Borogodo", atracao.getNome());
        assertEquals(new Coordenadas(-12.199689, -38.971118), atracao.getLocalizacao());
        assertEquals(CategoriaAtracao.PRACA, atracao.getCategoria());
    }
}