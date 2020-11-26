import java.io.*;
/**
 * Se implementa Serializable para poder escribir objetos  Carta en disco 
 * Se implementa Comparable para comparar objectos carta entre si 
 * Jose Rios Jose
 */
public class Carta implements Serializable ,Comparable
{
    private int    cp;
    private String calle;
    private int    numero;
    private String colonia;
    private String destinatario;
    
    public Carta(int cp, String calle, int numero, String colonia, String destinatario)
    {
        this.cp           = cp;
        this.calle        = calle;
        this.numero       = numero;
        this.colonia      = colonia;
        this.destinatario = destinatario;
    }

    
    public int getcp()
    {
        return cp;
    }
    
    public String getcalle()
    {
        return calle;
    }
    
    public int getnumero() 
    {
        return numero;
    }
    
    public String getcolonia()
    {
        return colonia;
    }
    
    public String getdestinatario()
    {
        return destinatario;
    }
    
    public String toString()
    {
        String s  = cp + "," + calle + "," + numero + ",";
               s += colonia + "," + destinatario;
               
        return s;
    }
    //Comparar para que queden bien ordenados
    public int compareTo(Object x) {
        
        if ( cp==((Carta) x).cp){
            
              if ( calle.compareTo(( (Carta) x).calle)==0 ){
                  
                         if ( numero==( (Carta) x).numero ){
                             
                              if ( colonia.compareTo(( (Carta) x).colonia)==0 ){
                                           
                                       if ( destinatario.compareTo(( (Carta) x).destinatario)==0 ){
                                          return 0;   
                                   }else{ if ( destinatario.compareTo(( (Carta)x).destinatario)<0){
                                            return -1;
                                        }else{
                                            return 1;}
                                      }  
                                  
                                  
                                   }else{ if ( colonia.compareTo(( (Carta)x).colonia)<0){
                                            return -1;
                                        }else{
                                            return 1;}
                                      }  
                                                          
                         }else{ if ( numero <( (Carta)x).numero){
                                 return -1;
                                }else{
                                return 1;}
                         }
                       
               }else{ if ( calle.compareTo(((Carta)x).calle)<0 ){
                       return -1;
                       }else{
                       return 1;}
                       }         
            
       }else{ if (cp<((Carta)x).cp ){
                return -1;
            }else{
                return 1;}
          }
        }
        
    
}

