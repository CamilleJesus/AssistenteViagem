package br.uefs.ecomp.av.util;


import java.io.Serializable;

/**
 * Classe Lista, implementada a partir dos métodos da interface ILista.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class Lista implements ILista, Serializable {

    private CelulaLista primeiro;
    private Comparable[] vetorComparable;
    
    /** Construtor da classe, faz o objeto célula "primeiro" apontar para nulo.
     */
    public Lista() {
        primeiro = null;
    }
    
    /** Método que verifica se a lista encadeada está vazia, a condição já é 
     * feita no retorno; se a célula aponta para nulo, retorna "true" (valor lógico,
     * verdadeiro) portanto a lista está vazia, caso não, retorna "false" e há
     * pelo menos um elemento na lista.
     * 
     * @return boolean (true ou false)
     */
    @Override
    public boolean estaVazia() {
        return (primeiro == null);
    }

    /** Método que verifica o tamanho da lista em tempo de execução, ele a percorre
     * contando cada elemento até que não haja mais elementos, o tamanho obtido
     * é retornado.
     * 
     * @return int tamanho
     */
    @Override
    public int obterTamanho() {
        int tamanho = 0;
        CelulaLista aux = primeiro;
        
        while (aux != null) {
            tamanho++;
            aux = aux.getProximo();
        }
        return tamanho;
    }

    /** Método que insere um elemento em qualquer posição da lista encadeada, o
     * auxiliar criado recebe a referência da lista, ela é percorrida até que se
     * ache a posição desejada, o anterior à posição passa a apontar para ele, e
     * o seu próximo aponta para o próximo do que estava na posição desejada, que
     * agora ele ocupa.
     * 
     * @param index
     * @param o
     */
    @Override
    public void inserir(int index, Comparable o) {
        CelulaLista nova = new CelulaLista(o);
        
        if (index == 0) {
            inserirInicio(o);
        } else if (index == obterTamanho()) {
            inserirFinal(o);
        } else if (index > 0) {
            CelulaLista aux = primeiro;
            CelulaLista auxAnterior = primeiro;
            for (int i = 0; i < index; i++) {
                auxAnterior = aux;
                aux = aux.getProximo();
            }
            auxAnterior.setProximo(nova);
            nova.setProximo(aux);
        }
    }

    /** Método que recebe um objeto para inserir no início da lista, após receber
     * o objeto, uma nova célula é criada, o campo próximo da nova célula aponta
     * para a primeira e agora, a primeira célula da lista é a que acabou de ser
     * inserida.
     * 
     * @param o
     */
    @Override
    public void inserirInicio(Comparable o) {
        CelulaLista nova = new CelulaLista(o);
        nova.setProximo(primeiro);
        primeiro = nova;
    }

    /** Método que recebe um objeto para inserir no final da lista encadeada,
     * após receber o objeto, uma nova célula é criada, assim como um auxiliar,
     * que recebe a referência (do primeiro elemento) da lista, quando essa
     * referência for vazia, o objeto será inserido no início da lista, que
     * nesse caso, também será o último, mas se a lista já conter elementos, o 
     * aulixiar que recebe o endereço da lista, a percorre, deixando a referência
     * principal intacta, quando esse auxiliar encontrar um elemento nulo, ele
     * se posiciona naquele lugar e no seu próximo é adicionado nulo.
     * 
     * @param o
     */
    @Override
    public void inserirFinal(Comparable o) {
        CelulaLista nova = new CelulaLista(o);        
        
        if (primeiro == null) {
            inserirInicio(o);
        } else {
            CelulaLista aux = primeiro;
            while (aux.getProximo() != null) {
                aux = aux.getProximo();
            }
            aux.setProximo(nova);
        }
    }

    /** Método que remove um elemento de qualquer posição da lista encadeada, o
     * auxiliar criado recebe a referência da lista, ela é percorrida até que se
     * ache o elemento desejado, o anterior à ele passa a apontar para o seu próximo,
     * ele deixa de fazer parte da lista e é retornado.
     * 
     * @param index
     * @return Object objeto
     */
    @Override
    public Comparable remover(int index) {
        
        if (index == 0) {
            return (removerInicio());
        } else if (index == obterTamanho() - 1) {
            return (removerFinal());
        } else if (index > 0) {
            CelulaLista aux = primeiro;
            CelulaLista auxAnterior = primeiro;
            for (int i = 0; i < index; i++) {
                auxAnterior = aux;
                aux = aux.getProximo();
            }
            auxAnterior.setProximo(aux.getProximo());
            return ((Comparable)(aux.getObjeto()));
        }
        return null;
    }

    /** Método que remove o primeiro elemento da lista encadeada, o auxiliar 
     * criado recebe a referência da lista, o primeiro que é a referência, passa
     * a apontar para seu próprio campo próximo, o segundo elemento se torna o
     * primeiro, retornando o antigo primeiro que o auxiliar guarda na sua referência.
     * 
     * @return Object
     */
    @Override
    public Comparable removerInicio() {
                
        if (primeiro == null) {
            return null;
        } else {
            CelulaLista aux = primeiro;
            primeiro = primeiro.getProximo();
            return ((Comparable)(aux.getObjeto()));
        }
    }

    /** Método que remove o último elemento da lista encadeada, um auxiliar é criado,
     * caso a lista encadeada esteja vazia, null é o retorno, se a lista contiver
     * apenas um elemento, tal elemento é removido e retornado, não sobrará 
     * elementos na lista, caso a lista tenha mais de um elemento, outro auxiliar
     * é criado, os auxiliares vão alternandamente percorrendo as células, até
     * achar o elemento que seu campo próximo aponta para null (última célula da
     * lista), feito isso, o auxiliar mais à frente está na última posição e o
     * outro na anterior, este passa a apontar para nulo em seu campo próximo,
     * a última posição, que já não faz mais parte da lista, é retornada.
     * 
     * @return Object objeto
    */
    @Override
    public Comparable removerFinal() {
        
        if (primeiro == null) {
            return null;
        } else if (primeiro.getProximo() == null) {
            return (removerInicio());
        } else {
            CelulaLista aux = primeiro;
            CelulaLista auxAnterior = primeiro;
            while (aux.getProximo() != null) {
                auxAnterior = aux;
                aux = aux.getProximo();
            }
            auxAnterior.setProximo(null);
            return ((Comparable)(aux.getObjeto()));
        }
    }

    /** Método que recupera um elemento de determinada posição da lista encadeada,
     * o método recebe um número que faz com que um aulixiar avance até encontrar
     * tal posição, o objeto desejado é retornado, caso a posiçao não exista, o
     * valor nulo é retornado.
     * 
     * @param index
     * @return Object objeto
     */
    @Override
    public Comparable recuperar(int index) {        
        
        if ((index >= 0) && index < (obterTamanho())) {
            CelulaLista aux = primeiro;
            for (int i = 0; i < index; i++) {
                aux = aux.getProximo();
            }
            return ((Comparable)(aux.getObjeto()));
        }
        return null;
    }

    /** Método que cria um objeto do tipo Iterador, seu construtor recebe como
     * parâmetro o primeiro elemento da lista encadeada, o iterador é retornado,
     * tal método percorre a lista e retorna cada objeto sequencialmente.
     * 
     * @return Iterador iterador
     */
    @Override
    public Iterador iterador() {
        Iterador iterador = new IteradorLista(primeiro);
        return iterador;
    }  

    /** As posições da lista são reposicionadas em ordem com o método de ordenação
     * QuickSort genérico.
     */
    @Override
    public void ordenar() {
        if (this.estaVazia() == false) {
            vetorComparable = new Comparable[this.obterTamanho()];

            for (int i = 0; i < vetorComparable.length; i++) {
                vetorComparable[i] = this.removerInicio();
            }
            int ini = 0, fim = vetorComparable.length - 1;
            this.quickSort(vetorComparable, ini, fim);

            for (Comparable vetorComparable1 : vetorComparable) {
                this.inserirFinal(vetorComparable1);
            }
        }
    }
    
    public void quickSort(Comparable[] vetorComparable, int ini, int fim) {
        
        if (ini >= fim) {   //Se o vetor já estiver ordenado:
            return;   //Retorna ele mesmo.
        }
        //Uma posição "meio" é escolhida.
        int meio = ini + (fim - ini) / 2, i = ini, j = fim;
        Comparable pivo = vetorComparable[meio];   //Um objeto pivor é escolhido com base nessa posição do meio.
        
        while (i <= j) {
            
            //As strings são comparadas:
            while (vetorComparable[i].compareTo(pivo) < 0) {
                i++;   //Se vier depois, desloca para a posição à direita.
            }
                       
            while (vetorComparable[j].compareTo(pivo) > 0) {
                j--;   //Se vier antes, desloca para a posição à esquerda.
            }
            
            if (i <= j) {  //Troca:
                Comparable temp = vetorComparable[i];
                vetorComparable[i] = vetorComparable[j];
                vetorComparable[j] = temp;
                i++;
                j--;
            }
        }
        
        //Caso ainda não esteja ordenado:
        if (ini < j) {
            quickSort(vetorComparable, ini, j);   //Método recursivo.
        }
        
        if (fim > i) {
            quickSort(vetorComparable, i, fim);   //Método recursivo.
        }
    }
 
    /** Método extra; recupera elemento específico da lista encadeada, o método
     * recebe uma célula que faz com que um aulixiar avance até encontrar tal
     * elemento, o objeto desejado é retornado, caso ele não exista, o valor nulo
     * é retornado.
     *
     * @param cm
     * @return Object objeto
     */
    public Object buscarChave(CelulaMapa cm) {
        
        if (!this.estaVazia()) {
            CelulaLista aux = primeiro;

            while (aux != null) {
                CelulaMapa aux2 = (CelulaMapa) aux.getObjeto();

                if ((aux2.compareTo(cm) == 0)) {
                    return (aux.getObjeto());
                }
                aux = aux.getProximo();
            }
            return null;
        }
        return null;
    }
}