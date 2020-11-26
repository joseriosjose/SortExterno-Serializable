/**
 * Clase que Elimine los archivos temporales cuando ya se usaron 
 * Se puede omitir 
 * Jose Rios Jose
 */
import java.io.*;
public class borrarArchivosTemp
{
    
   public void borra(int c,String ar){
    for(int i=0; i<c; i++)
    {
        String temp =ar+i+".txt";
        File temp1 = new File(temp);
        temp1.delete();
    }
    }
}
