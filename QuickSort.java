/**
 * Metodo de Ordenamiento QuickSort que soporta 1000000 de Cartas
 * Jose Rios Jose
 */
public class QuickSort{
    
    public void quicksort(Carta cartas[])
    {
       quicksortS(cartas, 0,cartas.length - 1);
    }
    
    public void quicksortS(Carta lista1[], int izq, int der){
		int i=izq;
		int j=der;
		Carta pivote=lista1[(i+j)/2];
		do {
			while (lista1[i].compareTo(pivote)<0){
				i++;
			}
			while (lista1[j].compareTo(pivote)>0){
				j--;
			}
			if (i<=j){
				Carta aux=lista1[i];
				lista1[i]=lista1[j];
				lista1[j]=aux;
				i++;
				j--;
			}
		}while(i<=j);
		if (izq<j){
			quicksortS(lista1, izq, j);
		}
		if (i<der){
			quicksortS(lista1, i, der);
		}
	}
    
    
}