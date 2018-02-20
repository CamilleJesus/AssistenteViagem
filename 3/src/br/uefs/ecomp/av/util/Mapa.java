package br.uefs.ecomp.av.util;


import java.io.Serializable;

/**
 * Classe Mapa, implementada a partir da interface IMapa.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class Mapa implements IMapa, Serializable {

    private Object[] mapa;

    /** Construtor da classe, faz o atributo mapa receber um novo vetor de objetos
     * do tamanho t, passado por parâmetro.
     * 
     * @param t
     */
    public Mapa(int t) {
        this.mapa = new Object[t];
    }
    
    /** Método que calcula o índice, que deve ser a posição em que a célula será
     * inserida. O índice é calculado obtendo-se o módulo do resto da divisão do
     * hashCode da chave pelo tamanho do mapa.
     * 
     * @param chave
     * @return int indice
     */
    private int calculaIndice(Object chave){
        return (Math.abs(chave.hashCode()) % this.obterTamanho());
    }
    
    /** Método que verifica se o mapa está vazio, a condição já é feita no retorno;
     * se a quantidade de objetos no mapa for 0, retorna "true" (valor lógico,
     * verdadeiro) portanto a lista está vazia, caso não, retorna "false" e há
     * pelo menos um elemento no mapa.
     * 
     * @return boolean (true ou false)
     */
    @Override
    public boolean estaVazio() {
        return (obterQuantidade() == 0);
    }
    
    /** Método que verifica o tamanho absoluto do mapa, levando-se em consideração
     * as posições ocupadas por objetos e as nulas.
     * 
     * @return int tamanho
     */
    @Override
    public int obterTamanho() {
        return mapa.length;
    }

    /** Método que verifica o tamanho relativo do mapa, levando-se em consideração
     * somente as posições ocupadas por objetos.
     * 
     * @return int quantidade
     */
    public int obterQuantidade() {
        int quantidade = 0;
        
        for (int i = 0; i < mapa.length; i++) {
            
            if (mapa[i] != null) {
                quantidade++;
            }
        }
        return quantidade;
    }

    /** Método que insere uma célula na posição do mapa correspondente ao índice
     * calculado. Primeiramente, se a quantidade de elementos no mapa for maior
     * que 50% de seu tamanho, ele será redimensionado. Logo após, o índice para
     * achar a posição é calculado, enquanto a posição baseada no índice estiver
     * ocupada o mapa é percorrido e a célula é inserida na próxima posição vazia.
     * 
     * @param chave
     * @param valor
     */
    @Override
    public void inserir(Object chave, Object valor) {
        
        if (!(contemChave(chave))) {
            
            if (this.obterQuantidade() > (this.obterTamanho() / 2)) {
                this.redimensionar();
            }
            int indice = calculaIndice(chave);
                    
            while (indice < this.obterTamanho()) {

                if (mapa[indice] == null) {
                    mapa[indice] = new CelulaMapa(chave, valor);
                    return;
                }
                indice++;
                
                //Se os índices a partir do gerado estiverem ocupados, o mapa ainda pode
                // ter posições vazias anteriores à esta:
                if (indice == this.obterTamanho()) {
                    indice = 0;
                }
            }
       }
    }

    /** Método que remove uma célula na posição do mapa correspondente ao índice
     * calculado. Primeiramente, verifica-se se o mapa contém tal chave, se sim,
     *  um auxiliar é criado e recebe a célula da posição correspondente, a chave
     * (caso ela exista) passa a ser nula e o campo valor da célula auxiliar é
     * retornado. Se não, retorna null.
     * 
     * @param chave
     * @return Object valor
     */
    @Override
    public Object remover(Object chave) {
        
        if (this.contemChave(chave)) {
            int indice = this.calculaIndice(chave);
            CelulaMapa aux = (CelulaMapa) mapa[indice];            
            
            while (aux == null || !(chave.equals(aux.getChave()))) {
                indice++;
                
                //Se os índices a partir do gerado estiverem ocupados, o mapa ainda pode
                // ter posições vazias anteriores à esta:
                if (!(indice < this.obterTamanho())) {
                    indice = 0;
                }
                aux = (CelulaMapa) mapa[indice];
            }
            mapa[indice] = null;
            return (aux.getValor());
        }
        return null;
    }

    /** Método que recupera uma célula na posição do mapa correspondente ao índice
     * calculado. O método recebe uma chave, caso o mapa a contenha, o índice é
     * calculado e incrementado até que a chave recebida e a atual sejam iguais,
     * fazendo com que ele avance até encontrar tal posição e o objeto desejado é
     * retornado, mas caso a posiçao não exista o valor nulo é retornado.
     * 
     * @param chave
     * @return Object valor
     */
    @Override
    public Object recuperar(Object chave) {
        
        if (this.contemChave(chave)) {
            int indice = this.calculaIndice(chave);
            
            while (mapa[indice] == null || !(chave.equals(((CelulaMapa) mapa[indice]).getChave()))) {
                indice++;
                
                //Se os índices a partir do gerado estiverem ocupados, o mapa ainda pode
                // ter posições vazias anteriores à esta:
                if (!(indice < this.obterTamanho())) {
                    indice = 0;
                }
            }
            return (((CelulaMapa) mapa[indice]).getValor());
        }
        return null;        
    }

    /** Método que verifica se uma chave está presente no mapa. Se a posição
     * calculada com base na chave recebida como parâmetro não for nula, retorna
     * "true" (valor lógico, verdadeiro) portanto o mapa contém a chave, caso não,
     * retorna "false" e a chave não existe.
     * 
     * @param chave
     * @return boolean (true ou false)
     */
    @Override
    public boolean contemChave(Object chave) {
        int indice = this.calculaIndice(chave);
            
        while (indice < this.obterTamanho()) {
        
            if (mapa[indice] != null && (chave.equals(((CelulaMapa) mapa[indice]).getChave()))) {
                return true;
            }
            indice++;
        }
        
        //Se os índices a partir do gerado estiverem ocupados, o mapa ainda pode
        // ter posições vazias anteriores à esta:
        if (!(indice < this.obterTamanho())) {
            indice = 0;
        }
        
        while (indice < this.obterTamanho()) {
        
            if (mapa[indice] != null && (chave.equals(((CelulaMapa) mapa[indice]).getChave()))) {
                return true;
            }
            indice++;
        }
        return false;
    }

    /** Método que cria um objeto do tipo Iterador, seu construtor recebe como
     * parâmetro o próprio mapa, o iterador é retornado, tal método percorre o
     * mapa e retorna cada chave sequencialmente.
     * 
     * @return Iterador iterador
     */
    @Override
    public Iterador chaves() {
        Iterador iterador = new IteradorMapaChave(mapa);
        return iterador;
    }

    /** Método que cria um objeto do tipo Iterador, seu construtor recebe como
     * parâmetro o próprio mapa, o iterador é retornado, tal método percorre o
     * mapa e retorna cada valor sequencialmente.
     * 
     * @return Iterador iterador
     */
    @Override
    public Iterador valores() {
        Iterador iterador = new IteradorMapaValor(mapa);
        return iterador;
    }
    
    /** Método extra; que cria um objeto do tipo Iterador, seu construtor recebe
     * como parâmetro o próprio mapa, o iterador é retornado, tal método percorre
     * o mapa e retorna cada par sequencialmente.
     * 
     * @return Iterador iterador
     */
    public Iterador par() {
        Iterador iterador = new IteradorMapaPar(mapa);
        return iterador;
    }
    
    /** Método que redimensiona o mapa, se necessário.
     */    
    public void redimensionar() {
        int t = primo(this.obterTamanho() * 2);
        Object[] temp = new Object[t];
        Iterador iterador = this.par();        
        Lista lista = new Lista();     
        
        while (iterador.temProximo()) {
            CelulaMapa aux = ((CelulaMapa) iterador.obterProximo());
            lista.inserirFinal(aux);
        }
        
        for (int i = 0; i < lista.obterTamanho(); i++) {
            CelulaMapa aux = ((CelulaMapa) lista.recuperar(i));
            int indice = (Math.abs(aux.getChave().hashCode()) % temp.length);
            
            while (indice < temp.length) {
               
                if (temp[indice] == null) {
                    temp[indice] = aux;
                    break;
                }
                indice++;
            }
        }
        mapa = temp;
    }
        
    private int primo(int n) {
        
        for (int i = n; true; i++) {
        
            if (ePrimo(i)) {
                return i;
            }
        }
    }

    private boolean ePrimo(int p) {
        
        for (int i = 2; i < p; i++) {
        
            if (p % i == 0) {
                return false;
            }
        }
        return true;
    }
}