package br.uefs.ecomp.av.util;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ListaTest {
	
    private ILista lista;
    private String s1 = "Feira", s2 = "Aracaju", s3 = "Blumenau", s4 = "São Paulo", s5 = "Salvador";


    @Before
    public void setUp() throws Exception {
        lista = new Lista();
    }

    @Test
    public void testEstaVazia() {
        assertTrue(lista.estaVazia());
        lista.inserirFinal(s1);
        assertFalse(lista.estaVazia());
        lista.inserirFinal(s2);
        assertFalse(lista.estaVazia());
        lista.inserirFinal(s3);
        assertFalse(lista.estaVazia());
        lista.removerFinal();
        assertFalse(lista.estaVazia());
        lista.removerFinal();
        assertFalse(lista.estaVazia());
        lista.removerFinal();
        assertTrue(lista.estaVazia());
    }

    @Test
    public void testObterTamanho() {
        assertEquals(0, lista.obterTamanho());
        lista.inserirFinal(s1);
        assertEquals(1, lista.obterTamanho());
        lista.inserirFinal(s2);
        assertEquals(2, lista.obterTamanho());
        lista.inserirFinal(s3);
        assertEquals(3, lista.obterTamanho());
        lista.removerFinal();
        assertEquals(2, lista.obterTamanho());
        lista.removerFinal();
        assertEquals(1, lista.obterTamanho());
        lista.removerFinal();
        assertEquals(0, lista.obterTamanho());
    }
    
    @Test
    public void testInserirRemover() {
        assertNull(lista.remover(0));
        lista.inserir(0, s1);
        lista.inserir(1, s2);
        lista.inserir(2, s3);
        assertEquals(s3, lista.remover(2));
        assertEquals(s2, lista.remover(1));
        assertEquals(s1, lista.remover(0));		
        assertNull(lista.remover(0));	
        lista.inserir(0, s1);
        lista.inserir(1, s2);
        lista.inserir(2, s3);
        assertEquals(s1, lista.remover(0));
        assertEquals(s2, lista.remover(0));
        assertEquals(s3, lista.remover(0));		
        assertNull(lista.remover(0));	
        lista.inserir(0, s1);
        lista.inserir(1, s2);
        lista.inserir(2, s3);
        lista.inserir(3, s4);
        lista.inserir(4, s5);
        assertEquals(s3, lista.remover(2));
        assertEquals(s1, lista.remover(0));
        assertEquals(s5, lista.remover(2));	
        assertEquals(s4, lista.remover(1));
        assertEquals(s2, lista.remover(0));		
    }
    
    @Test
    public void testInserirInicioRemoverInicio() {
        assertNull(lista.removerInicio());
        lista.inserirInicio(s1);
        lista.inserirInicio(s2);
        lista.inserirInicio(s3);
        assertEquals(s3, lista.removerInicio());
        assertEquals(s2, lista.removerInicio());
        assertEquals(s1, lista.removerInicio());		
        assertNull(lista.removerInicio());
    }
    
    @Test
    public void testInserirInicioRemoverFinal() {
        assertNull(lista.removerFinal());
        lista.inserirInicio(s1);
        lista.inserirInicio(s2);
        lista.inserirInicio(s3);
        assertEquals(s1, lista.removerFinal());
        assertEquals(s2, lista.removerFinal());
        assertEquals(s3, lista.removerFinal());		
        assertNull(lista.removerFinal());
    }
    
    @Test
    public void testInserirFinalRemoverInicio() {
        assertNull(lista.removerInicio());
        lista.inserirFinal(s1);
        lista.inserirFinal(s2);
        lista.inserirFinal(s3);
        assertEquals(s1, lista.removerInicio());
        assertEquals(s2, lista.removerInicio());
        assertEquals(s3, lista.removerInicio());		
        assertNull(lista.removerInicio());
    }

    @Test
    public void testInserirFinalRemoverFinal() {
        assertNull(lista.removerFinal());
        lista.inserirFinal(s1);
        lista.inserirFinal(s2);
        lista.inserirFinal(s3);
        assertEquals(s3, lista.removerFinal());
        assertEquals(s2, lista.removerFinal());
        assertEquals(s1, lista.removerFinal());		
        assertNull(lista.removerFinal());
    }

    @Test
    public void testInserirRecuperar() {
        assertNull(lista.recuperar(0));
        lista.inserir(0, s1);
        lista.inserir(1, s2);
        lista.inserir(2, s3);
        assertEquals(s1, lista.recuperar(0));
        assertEquals(s2, lista.recuperar(1));
        assertEquals(s3, lista.recuperar(2));
        lista.remover(0);
        assertEquals(s2, lista.recuperar(0));
        lista.remover(0);
        assertEquals(s3, lista.recuperar(0));		
        lista.remover(0);
        assertNull(lista.recuperar(0));	
    }

    @Test
    public void testIterador() {
        Iterador it = lista.iterador();
        assertFalse(it.temProximo());
        assertNull(it.obterProximo());

        lista.inserir(0, s1);
        lista.inserir(1, s2);
        lista.inserir(2, s3);
        it = lista.iterador();
        assertTrue(it.temProximo());
        assertSame(s1, it.obterProximo());
        assertTrue(it.temProximo());
        assertSame(s2, it.obterProximo());
        assertTrue(it.temProximo());
        assertSame(s3, it.obterProximo());
        assertFalse(it.temProximo());
        assertNull(it.obterProximo());

        lista.remover(1);
        it = lista.iterador();
        assertTrue(it.temProximo());
        assertSame(s1, it.obterProximo());
        assertTrue(it.temProximo());
        assertSame(s3, it.obterProximo());
        assertFalse(it.temProximo());
        assertNull(it.obterProximo());
    }    
    
    @Test
    public void testOrdenar() {
        assertNull(lista.recuperar(0));
        lista.inserir(0, s1);
        lista.inserir(1, s2);
        lista.inserir(2, s3);
        lista.ordenar();
        assertEquals(s2, lista.recuperar(0));
        assertEquals(s3, lista.recuperar(1));
        assertEquals(s1, lista.recuperar(2));     
    }    
}