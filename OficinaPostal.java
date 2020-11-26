/**
 * Jose Rios Jose
 */
public class OficinaPostal
{
    public static void main(String[] args)
    {
        ExternalSort ne= new ExternalSort ();
        ne.Ordenar(1000000);
       
        System.out.println("Cartas ordenadas exitosamente.");
        System.out.println("Guardadas en Cartas-Ordenadas.txt");
    }
}
