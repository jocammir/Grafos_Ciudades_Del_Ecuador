package ciudadesDeEcuador;
//Brew Cobos y Jocelyn Miranda
import grafos.lista.Grafo;
import java.util.*;
import tdas.*;

public class Main {
    
    public static void main(String[] args) {
        while(true){
            Scanner sc= new Scanner(System.in);
            ciudades cu= new ciudades();
            elabGrafo eg=new elabGrafo();
            generarRuta ruta=new generarRuta();
            System.out.println("+------------------------------------------+");
            System.out.println("|            CIUDADES DEL ECUADOR          |");
            System.out.println("+------------------------------------------+");

            System.out.print("Para que podamos ayudarlo a hallar la menor ruta entre dos ciudades. Ingrese:\n\n Ciudad de Partida: ");
            String c1= sc.nextLine();
            System.out.print("\n Ciudad de llegada: ");
            String c2=sc.nextLine();
            Grafo<String> grafo=eg.cargarGrafo("distancias.txt", cu.cargarCiudades("ciudades.txt"));

            System.out.println("\n Ruta: "+ruta.rutaMinDistancia(c1,c2));
            System.out.println(" La minima distancia entre "+c1+" y "+c2+" es: "+ruta.minDistancia(c1, c2)+" Kms.");
            System.out.print("\n¿Desea salir? (si o no): ");
            Scanner salir= new Scanner(System.in);
            String op=salir.nextLine();
            if(op.contentEquals("si")){
                System.out.println("Un placer servirle...");
                break;
            }
        }
    }
    
}
