/**
 * Jose Rios Jose
 */
import java.util.Arrays;
import java.util.Random;
import java.io.*;


public class ExternalSort
{
    static int N=100; 
    static int M=5;
    
    public static void Ordenar(int n){
        
      N=n;
      M=N/20;
      String fileN= generateInput(n);
      externalSort(fileN);
      /**
       * borra los archivos temporales despues de que ordeno todas las cartas
       */
      borrarArchivosTemp b = new borrarArchivosTemp();
      b.borra(20,"archivo-temporal-");
    }
 
    public static void externalSort(String fileName)
    {  
        String tfile = "archivo-temporal-";
        Carta [] bufferC = new Carta[M < N ? M : N];
  
        try
        {
            FileReader fr =  new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            
            int slices = (int) Math.ceil((double) N/M);
   
            int i, j;
            i = j = 0;
            /** Iterar a través de los elementos del archivo */
            for (i = 0; i < slices; i++)
            {
                /** Leer fragmentos de M-elementos a la vez desde el archivo */
                for (j = 0; j < (M < N ? M : N); j++)
                {
                    String t = br.readLine();
                    String [] arre;
                    if (t != null){
                        arre=t.split(",");
                        bufferC[j]= new Carta(Integer.parseInt(arre[0]),arre[1],Integer.parseInt(arre[2]),arre[3],arre[4]);
                     
                    }else{
                        break;
                    }
                }
                /** Sort con QuickSort M elementos */
             
                
                 QuickSort quick =new QuickSort ();
                  quick.quicksort(bufferC);
                

                /** Escriba los cartas ordenadas en el archivo temporal */
                 FileWriter fw =new FileWriter(tfile + Integer.toString(i) + ".txt");
                 PrintWriter pw = new PrintWriter(fw);;
                 
                for (int k = 0; k < j; k++)
                    pw.println(bufferC[k].toString());
    
                pw.close();
                fw.close();
            }
   
            br.close();
            fr.close();
   
            /** Ahora abra cada archivo y combinelos, luego vuelva a escribirlos en el disco */
              Carta [] topCart =new Carta[slices];
             BufferedReader[] brs = new BufferedReader[slices] ;
             
              
            for (i = 0; i < slices; i++)
            {
                 brs[i]= new BufferedReader((new FileReader(tfile + Integer.toString(i) + ".txt")));
               
                String t = brs[i].readLine();
                String [] arret =t.split(",");
                
                if (t != null)
                    topCart[i] = new Carta(Integer.parseInt(arret[0]),arret[1],Integer.parseInt(arret[2]),arret[3],arret[4]);
                else
                  //carta maxima
                    topCart[i]= new Carta (69999,"Zocalo",999,"Zimatlan","Zulma Xochimiltco Villa");
                   
            }
   
            
            FileWriter fw = new FileWriter("Cartas-Ordenadas.txt");
            PrintWriter pw = new PrintWriter (fw);
          
   
            for (i = 0; i < N; i++)
            {
                Carta min =topCart[0];
                int minFile = 0;
    
                for (j = 0; j < slices; j++)
                {
                    if (min.compareTo(topCart[j])>0)
                    {
                        min = topCart[j];
                        minFile = j;
                    }
                }
    
                pw.println(min);
                
                 String t = brs[minFile].readLine();
                 String [] atrib;
                
                if (t != null){
                   atrib=t.split(",");
                    topCart[minFile] =new Carta(Integer.parseInt(atrib[0]),atrib[1],Integer.parseInt(atrib[2]),atrib[3],atrib[4]);
                }else{
                    topCart[minFile]= new Carta (69999,"Zocalo",999,"Zimatlan","Zulma Xochimiltco Villa"); 
                   
                }
            }
            
            for (i = 0; i < slices; i++)
                brs[i].close();
   
            pw.close();
            fw.close();
        } catch (FileNotFoundException e)
        {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    static String generateInput(int n)
    { 
        AdministrarCartas genera= new AdministrarCartas();
         return genera.guardarObjetos2(n);
       
       
    }
        
   
}

