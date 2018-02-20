package br.uefs.ecomp.av.model;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.av.model.enums.CategoriaAtracao;
import br.uefs.ecomp.av.model.enums.FaixaPreco;
import br.uefs.ecomp.av.model.enums.TipoCozinha;
import br.uefs.ecomp.av.util.Coordenadas;

public class RestauranteTest {

    private Restaurante restaurante = null;
    private Local local = null;

    @Before
    public void setUp() throws Exception {
        local = new Local("Feira", new Coordenadas(12.266944, -38.966944), "Bahia", "Brasil");
        restaurante = new Restaurante(local, "Vivas", new Coordenadas(-12.254778, -38.956394), "Kalilândia", CategoriaAtracao.RESTAURANTE, FaixaPreco.BARATO, TipoCozinha.INTERNACIONAL);
    }

    @Test
    public void testBasic() {
        assertNotNull(restaurante);
        assertSame(local, restaurante.getLocal());
        assertEquals("Vivas", restaurante.getNome());
        assertEquals(new Coordenadas(-12.254778, -38.956394), restaurante.getLocalizacao());
        assertEquals("Kalilândia", restaurante.getBairro());
        assertEquals(CategoriaAtracao.RESTAURANTE, restaurante.getCategoria());
        assertEquals(FaixaPreco.BARATO, restaurante.getPreco());
        assertEquals(TipoCozinha.INTERNACIONAL, restaurante.getCozinha());
    }
}