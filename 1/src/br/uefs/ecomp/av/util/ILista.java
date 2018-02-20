package br.uefs.ecomp.av.util;


public interface ILista {

    public boolean estaVazia();

    public int obterTamanho();
	
    public void inserir(int index, Object o);

    public void inserirInicio(Object o);

    public void inserirFinal(Object o);

    public Object remover(int index);

    public Object removerInicio();

    public Object removerFinal();

    public Object recuperar(int index);
	
    public Iterador iterador();
}