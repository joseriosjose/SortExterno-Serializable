/**
 * Jose Rios Jose
 */
import java.io.*;
import java.util.*;

public class AdministrarCartas
{
    private static int    arrCP       [];
    private static String arrCalles   [] = {"Norte", "Oriente", "Poniente", "Sur"};
    private static String arrColonias [];
    private static String arrNombres  [];
    private static String arrApellidos[];
    
    ObjectInputStream entrada;//flujo de salida 

    /** Cargar los arreglos de cp, colonias, nombres y apellidos */
    public AdministrarCartas() 
    { 
        try
        {
            File   fColonias   = new File ("Colonias.csv");//creacion de un fichero con los datos de colonias
            File   fNombres    = new File ("Nombres.csv" );
            
            int    nColonias   = fSize ("Colonias.csv");//obtenemos el tamaño de con un metodo 
            int    nNombres    = fSize ("Nombres.csv" );
            
            arrCP              = new int   [nColonias];
            arrColonias        = new String[nColonias];
            arrNombres         = new String[nNombres];
            arrApellidos       = new String[nNombres];
            
            int nObj           = 0;     //contador de objetos
            String datos[];
        
        
            /** declarar un buffer de entrada para las cadenas desde disco */
            BufferedReader c = new BufferedReader (new FileReader(fColonias)); //los ficheros creados
            BufferedReader n = new BufferedReader (new FileReader(fNombres));
            
            /** leer el primer registro del archivo de Colonias */
            String s  = c.readLine();
            
            /** ciclo de recorrido del archivo */
            while (s != null)
            {
                datos             = s.split(",");
                arrCP[nObj]       = Integer.parseInt(datos[0]);
                arrColonias[nObj] = datos[1];
                s                 = c.readLine();
                nObj++;
            }
            c.close();
            
            nObj = 0;
            /** leer el primer registro del archivo de nombres */
            s         = n.readLine();
            
            /** ciclo de recorrido del archivo */
            while (s != null)
            {
                datos              = s.split(",");
                arrNombres[nObj]   = datos[0];
                arrApellidos[nObj] = datos[1];
                s                  = n.readLine();
                nObj++;
            }
            n.close();
        }
        catch (IOException ioe)
        {
            System.out.println("Excepcion de IO inesperada.");
        }
    }
    
    private int fSize(String nArchivo) throws IOException
    {
        File size        = new File(nArchivo);
        BufferedReader c = new BufferedReader(new FileReader(size));
        int contador     = 0;
        String s         = c.readLine();
                       
        while (s != null)
        {
            contador++;
            s = c.readLine();
        }
            
        c.close();
        
        return contador;
    }
    
    
    
    public Carta[] leerObjetos(int nCartas)
    {
        Carta[] misiva = new Carta[nCartas];
        int nObjetos   = 0;
        
        System.out.println("\nIniciando lectura de objetos ...");
        
        try {
            /**ObjectInputStream*/
            entrada = new ObjectInputStream(new FileInputStream("Cartas-Ordenadas.txt"));
            
            Carta obj1 = (Carta) entrada.readObject();
            
            while (obj1 != null)
            {
                if (nObjetos == 0 || nObjetos % 50000 == 0 || nObjetos == nCartas - 1)
                    System.out.format("%10d%2s%-60s%n",nObjetos,"",obj1);
                misiva[nObjetos] = obj1;
                nObjetos++;
                obj1 = (Carta) entrada.readObject();
               System.out.println( obj1.toString());
            }
            
            System.out.println(nObjetos + " leidos");
            entrada.close();
            
        } catch (IOException ex) {
            System.out.println(nObjetos + "  leidos");
            System.out.println(nObjetos);
            entrada.close();
            System.out.println("EOF");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);            
        } finally {
            return misiva;
        }
    }
    
    
    /** El argumento es el numero de objetos a generar */
    public String guardarObjetos2(int nCartas)
    {
        String nombre="Cartas.txt";
        
        try {
            //FileWriter fw = new FileWriter(nombre);
            PrintWriter pw =  new PrintWriter(new FileWriter(nombre));
            
            for (int i = 0; i < nCartas; i++)
            {
                int cp = (int) (Math.random() * arrCP.length);
                int no = (int) (Math.random() * arrNombres.length);
                int a1 = (int) (Math.random() * arrApellidos.length);
                int a2 = (int) (Math.random() * arrApellidos.length);
                
                Carta misiva = new Carta (arrCP[cp], arrCalles[(int) (Math.random() * 4)],
                                          (int) (Math.random() * 190) + 1, arrColonias[cp],
                                          arrNombres[no] + " " + arrApellidos[a1] + " " + arrApellidos[a2]
                                         );
                pw.println(misiva);
                
                if (i == 0 || i % 50000 == 0 || i == nCartas - 1)
                    System.out.format("%10d%2s%-60s%n",i,"",misiva);
            }
            
            pw.close();
            
            System.out.println("Guardados " + nCartas + " cartas en "+nombre);
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return nombre;
    }
}
