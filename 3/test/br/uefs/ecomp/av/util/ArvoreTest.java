package br.uefs.ecomp.av.util;


import br.uefs.ecomp.av.model.exception.DadoExistenteException;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ArvoreTest {
    
    private Arvore arvore;
    private String s1 = "Feira", s2 = "Aracaju", s3 = "Blumenau", s4 = "São Paulo", s5 = "Salvador";
    
    
    @Before
    public void setUp() throws Exception {
        arvore = new Arvore();        
    }
    
    @Test
    public void testEstaVazia() throws DadoExistenteException {
        assertTrue(arvore.estaVazia());
        arvore.inserir(s1);
        assertFalse(arvore.estaVazia());
        arvore.inserir(s2);
        assertFalse(arvore.estaVazia());
        arvore.inserir(s3);
        assertFalse(arvore.estaVazia());
        arvore.remover(s3);
        assertFalse(arvore.estaVazia());
        arvore.remover(s2);
        assertFalse(arvore.estaVazia());
        arvore.remover(s1);
        assertTrue(arvore.estaVazia());
    }
    
    @Test
    public void testObterTamanho() throws DadoExistenteException {
        assertEquals(-1, arvore.altura(arvore.getRaiz()));    
        
        assertEquals(0, arvore.obterTamanho());
        arvore.inserir(s1);
        assertEquals(1, arvore.obterTamanho());
        arvore.inserir(s2);
        arvore.inserir(s3);
        arvore.inserir(s4);
        arvore.inserir(s5);
        assertEquals(5, arvore.obterTamanho());
        arvore.remover(s5);
        assertEquals(4, arvore.obterTamanho());
        arvore.remover(s4);
        arvore.remover(s3);
        arvore.remover(s2);
        assertEquals(1, arvore.obterTamanho());
        arvore.remover(s1);
        assertEquals(0, arvore.obterTamanho());
        
        assertEquals(-1, arvore.altura(arvore.getRaiz()));
    }
    
    @Test
    public void testInserirRemover() throws DadoExistenteException {
        assertNull(arvore.getRaiz());       
        
        arvore.inserir(s1);
        assertNotNull(arvore.getRaiz());
        arvore.inserir(s2);
        arvore.inserir(s3);
        arvore.inserir(s4);
        arvore.inserir(s5);
        arvore.remover(s5);
        arvore.remover(s4);
        arvore.remover(s3);
        arvore.remover(s2);
        arvore.remover(s1);        
        
        assertNull(arvore.getRaiz());
        
        arvore.inserir(s3);
        
        assertNotNull(arvore.getRaiz());
        
        arvore.inserir(s2);
        arvore.inserir(s1);
        arvore.remover(s1);
        arvore.remover(s2);
        arvore.remover(s3);
        
        assertNull(arvore.getRaiz());
    }

    @Test
    public void testBuscar() throws DadoExistenteException {
        arvore.inserir(s1);
        arvore.inserir(s2);
        arvore.inserir(s3);
        arvore.inserir(s4);
        arvore.inserir(s5);
        assertEquals(s1, arvore.buscar(s1));
        assertEquals(s3, arvore.buscar(s3));
        assertEquals(s5, arvore.buscar(s5));
        assertEquals(s2, arvore.buscar(s2));
        assertEquals(s3, arvore.buscar(s3));     
        
    }
    
    @Test
    public void testIterator() throws DadoExistenteException {
        arvore.inserir(s1);
        arvore.inserir(s2);
        arvore.inserir(s3);
        arvore.inserir(s4);
        arvore.inserir(s5);
        
        Iterador iterador = arvore.iterador();
        assertTrue(iterador.temProximo());
        assertEquals(s2, iterador.obterProximo());
        assertEquals(s3, iterador.obterProximo());
        assertEquals(s1, iterador.obterProximo());
        assertEquals(s5, iterador.obterProximo());
        assertEquals(s4, iterador.obterProximo());
        assertFalse(iterador.temProximo());        
    }
}