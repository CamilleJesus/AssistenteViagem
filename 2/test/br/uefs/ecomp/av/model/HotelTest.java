package br.uefs.ecomp.av.model;


import br.uefs.ecomp.av.model.enums.CategoriaAtracao;
import br.uefs.ecomp.av.util.Coordenadas;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class HotelTest {

    private Hotel hotel = null;
    private Local local = null;

    @Before
    public void setUp() throws Exception {
        local = new Local("Feira", new Coordenadas(12.266944, -38.966944), "Bahia", "Brasil");
        hotel = new Hotel(local, "GoldPalace", new Coordenadas(-12.254778, -38.956394), "Centro", CategoriaAtracao.HOTEL,5, false, true, false);
    }

    @Test
    public void testBasic() {
        assertNotNull(hotel);
        assertSame(local, hotel.getLocal());
        assertEquals("GoldPalace", hotel.getNome());
        assertEquals(new Coordenadas(-12.254778, -38.956394), hotel.getLocalizacao());       
        assertEquals("Centro", hotel.getBairro());
        assertEquals(CategoriaAtracao.HOTEL, hotel.getCategoria());
        assertEquals(false, hotel.getAr());
        assertEquals(true, hotel.getTv());
        assertEquals(false, hotel.getServicoQuarto());
    }
}