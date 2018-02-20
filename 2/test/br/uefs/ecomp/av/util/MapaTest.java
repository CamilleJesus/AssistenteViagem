package br.uefs.ecomp.av.util;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MapaTest {
    private int tamanho;
    private Mapa mapa;
    String s1 = "Feira", s2 = "Salvador", s3 = "Fortaleza", s4 = "Recife", s5 = "Natal";


    @Before
    public void setUp() throws Exception {
        tamanho = 100;
        mapa = new Mapa(tamanho);
    }

    @Test
    public void testEstaVazia() {
        assertTrue(mapa.estaVazio());
        mapa.inserir("Feira", s1);
        assertFalse(mapa.estaVazio());
        mapa.inserir("Salvador",s2);
        assertFalse(mapa.estaVazio());
        mapa.inserir("Fortaleza", s3);
        assertFalse(mapa.estaVazio());
        mapa.remover("Feira");
        assertFalse(mapa.estaVazio());
        mapa.remover("Fortaleza");
        assertFalse(mapa.estaVazio());
        mapa.remover("Salvador");
        assertTrue(mapa.estaVazio());
    }

    @Test
    public void testObterTamanhoEQuantidade() {
        assertEquals(tamanho, mapa.obterTamanho());
        assertEquals(0, mapa.obterQuantidade());
        mapa.inserir("Feira", s1);
        assertEquals(tamanho, mapa.obterTamanho());
        assertEquals(1, mapa.obterQuantidade());
        mapa.inserir("Salvador",s2);
        assertEquals(tamanho, mapa.obterTamanho());
        assertEquals(2, mapa.obterQuantidade());
        mapa.inserir("Fortaleza", s3);
        assertEquals(tamanho, mapa.obterTamanho());
        assertEquals(3, mapa.obterQuantidade());
        mapa.remover("Fortaleza");
        assertEquals(tamanho, mapa.obterTamanho());
        assertEquals(2, mapa.obterQuantidade());
        mapa.remover("Feira");
        assertEquals(tamanho, mapa.obterTamanho());
        assertEquals(1, mapa.obterQuantidade());
        mapa.remover("Salvador");
        assertEquals(tamanho, mapa.obterTamanho());
        assertEquals(0, mapa.obterQuantidade());
    }
    
    @Test
    public void testInserirRemover() {
        assertNull(mapa.remover("Paris"));
        mapa.inserir("Feira", s1);
        mapa.inserir("Fortaleza", s3);
        mapa.inserir("Salvador", s2);
        assertEquals(s3, mapa.remover("Fortaleza"));
        assertEquals(s2, mapa.remover("Salvador"));
        assertEquals(s1, mapa.remover("Feira"));		
        assertNull(mapa.remover("Feira"));	
        mapa.inserir("Fortaleza", s3);
        mapa.inserir("Salvador", s2);
        mapa.inserir("Feira", s1);
        assertEquals(s1, mapa.remover("Feira"));
        assertEquals(s2, mapa.remover("Salvador"));
        assertEquals(s3, mapa.remover("Fortaleza"));		
        assertNull(mapa.remover("Paris"));
    }
    
    @Test
    public void testRecuperar(){
        assertNull(mapa.recuperar("Paris"));
        mapa.inserir("Feira", s1);
        assertEquals(s1, mapa.recuperar("Feira"));
        mapa.inserir("Salvador", s2);
        mapa.inserir("Fortaleza", s3);
        assertEquals(s2, mapa.recuperar("Salvador"));
        assertEquals(s3, mapa.recuperar("Fortaleza"));
    }
      
    @Test
    public void testContemChave(){
        assertFalse(mapa.contemChave("Feira"));
        mapa.inserir("Feira", s1);
        assertTrue(mapa.contemChave("Feira"));
        mapa.inserir("Fortaleza", s3);
        assertTrue(mapa.contemChave("Fortaleza"));
        mapa.inserir("Salvador", s2);
        assertTrue(mapa.contemChave("Salvador"));
    }
    
    @Test
    public void testChaves() {
        Iterador it = mapa.chaves();
        mapa.inserir("Feira", s1);
        mapa.inserir("Fortaleza", s3);
        mapa.inserir("Salvador", s2);
        assertTrue(it.temProximo());
        assertSame("Fortaleza", it.obterProximo());
        assertTrue(it.temProximo());
        assertSame("Salvador", it.obterProximo());
        assertTrue(it.temProximo());
        assertSame("Feira", it.obterProximo());
    }
    
    @Test
    public void testValores() {
        Iterador it = mapa.valores();
        mapa.inserir("Salvador", s2);
        mapa.inserir("Fortaleza", s3);
        mapa.inserir("Feira", s1);
        assertTrue(it.temProximo());
        assertSame(s3, it.obterProximo());
        assertTrue(it.temProximo());
        assertSame(s2, it.obterProximo());
        assertTrue(it.temProximo());
        assertSame(s1, it.obterProximo());
    }
    
    @Test
    public void testRedimensionar() {
        assertEquals(tamanho, mapa.obterTamanho());
        assertEquals(0, mapa.obterQuantidade());
        mapa.inserir("Feira", s1);
        mapa.inserir("Fortaleza", s3);
        mapa.inserir("Salvador", s2);
        assertEquals(tamanho, mapa.obterTamanho());
        assertEquals(3, mapa.obterQuantidade());
        mapa.redimensionar();
        assertEquals(s1, mapa.recuperar("Feira"));
        assertEquals(s2, mapa.recuperar("Salvador"));
        assertEquals(s3, mapa.recuperar("Fortaleza"));
        assertEquals((tamanho * 2), mapa.obterTamanho());
        assertEquals(3, mapa.obterQuantidade());
        mapa.remover("Fortaleza");
        mapa.remover("Salvador");
        mapa.remover("Feira");
        assertEquals((tamanho * 2), mapa.obterTamanho());
        assertEquals(0, mapa.obterQuantidade());
    }
}