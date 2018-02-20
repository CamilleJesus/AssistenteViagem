package br.uefs.ecomp.av.model;


import java.util.Date;

/**
 * Classe QuickSort, onde se encontram os algoritmos de ordenação utilizados no
 * projeto.
 * 
 * @author Camille Jesus
 */
public class QuickSort {

    /** As posições do vetor são reposicionadas em ordem alfabética de nomes,
     * usado para listar locais e atrações de um local.
     * 
     * @param vetorObject
     * @param ini
     * @param fim
     */
    public void ordemAlfabetica(Object[] vetorObject, int ini, int fim) {

        //Se o vetor for nulo ou tiver tamanho igual a 0:
        if (vetorObject == null || vetorObject.length == 0){
            return;   //Retorna ele mesmo.
        } else if (ini >= fim){   //Se o vetor já estiver ordenado:
            return;   //Retorna ele mesmo.
        }
        //Uma posição "meio" é escolhida.
        int meio = ini + (fim - ini) / 2, i = ini, j = fim;
        Object pivo = vetorObject[meio];   //Um objeto pivor é escolhido com base nessa posição do meio.
        
        while(i <= j){
            
            //As strings são comparadas:
            while(vetorObject[i].getClass().getName().compareToIgnoreCase(pivo.getClass().getName()) < 0) {
                i++;   //Se vier depois, desloca para a posição à direita.
            }
            
            while (vetorObject[j].getClass().getName().compareToIgnoreCase(pivo.getClass().getName()) > 0) {
                j--;   //Se vier antes, desloca para a posição à esquerda.
            }
            
            if (i <= j){  //Troca:
                Object temp = vetorObject[i];
                vetorObject[i] = vetorObject[j];
                vetorObject[j] = temp;
                i++;
                j--;
            }
        }
        //Caso ainda não esteja ordenado:
        if (ini < j){
            ordemAlfabetica(vetorObject, ini, j);   //Método recursivo.
        }
        
        if (fim > i){
            ordemAlfabetica(vetorObject, i, fim);   //Método recursivo.
        }
    }
    
    /** As posições do vetor são reposicionadas em ordem crescente de média de pontos,
     * usado para listar o ranking das atrações e a lista de atrações de um local.
     * 
     * @param vetor
     * @param ini
     * @param fim
     */
    public void ordemNumerica(Atracao[] vetor, int ini, int fim) {
        
        //Se o vetor for nulo ou tiver tamanho igual a 0:
        if (vetor == null || vetor.length == 0){
            return;   //Retorna ele mesmo.
        } else if (ini >= fim){   //Se o vetor já estiver ordenado:
            return;   //Retorna ele mesmo.
        }
        //Uma posição "meio" é escolhida.
        int meio = ini + (fim - ini) / 2, i = ini, j = fim;
        double pivo = vetor[meio].getMediaPontos();   //Um número pivor é escolhido com base nessa posição do meio.
        
        while(i <= j) {
            
            //As médias são comparadas:
            while (vetor[i].getMediaPontos() > pivo) {
                i++;   //Se vier depois, desloca para a posição à direita.
            } 
            
            while (vetor[j].getMediaPontos() < pivo) {
                j--;   //Se vier antes, desloca para a posição à esqueda.
            }
            
            if (i <= j) {   //Troca:
                Atracao temp = vetor[i];
                vetor[i] = vetor[j];
                vetor[j] = temp;
                i++;
                j--;
            }
        }
        
        //Caso ainda não esteja ordenado:
        if (ini < j) {
            ordemNumerica(vetor, ini, j);   //Método recursivo.
        }
        
        if (fim > i) {
            ordemNumerica(vetor, i, fim);   //Método recursivo.
        }
    }
    
    /** As posições do vetor são reposicionadas em descrescente de datas, usado
     * para listar as avaliações de uma atração.
     * 
     * @param vetor
     * @param ini
     * @param fim
     */
    public void ordemCronologica(Avaliacao[] vetor, int ini, int fim) {
        
        //Se o vetor for nulo ou tiver tamanho igual a 0:
        if (vetor == null || vetor.length == 0){
            return;   //Retorna ele mesmo.
        } else if (ini >= fim){   //Se o vetor já estiver ordenado:
            return;   //Retorna ele mesmo.
        }
        //Uma posição "meio" é escolhida.
        int meio = ini + (fim - ini) / 2, i = ini, j = fim;
        Date pivo = vetor[meio].getData();   //Uma data pivor é escolhido com base nessa posição do meio.
        
        
        while(i <= j) {
            //As datas são comparadas:
            while (vetor[i].getData().after(pivo)) {
                i++;   //Se vier depois, desloca para a posição à direita.
            } 
            
            while (vetor[j].getData().before(pivo)) {
                j--;   //Se vier antes, desloca para a posição à esquerda.
            }
            
            if (i <= j) {   //Troca:
                Avaliacao temp = vetor[i];
                vetor[i] = vetor[j];
                vetor[j] = temp;
                i++;
                j--;
            }
        }
        
        //Caso ainda não esteja ordenado:
        if (ini < j) {
            ordemCronologica(vetor, ini, j);   //Método recursivo.
        }
        
        if (fim > i) {
            ordemCronologica(vetor, i, fim);   //Método recursivo.
        }
    }
}