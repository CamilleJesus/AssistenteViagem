package br.uefs.ecomp.av.util;


import br.uefs.ecomp.av.model.exception.DadoExistenteException;

public interface IArvore {
    
    public boolean estaVazia();
    
    public int obterTamanho();
    
    public void inserir(Comparable o) throws DadoExistenteException;

    public void remover(Comparable o);
    
    public Comparable buscar(Comparable o);
    
    public Iterador iterador();
}
