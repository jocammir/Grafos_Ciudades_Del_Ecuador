
package tdas;

import grafos.lista.Arco;
import grafos.lista.Grafo;
import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

public class elabGrafo {
    
    public  Grafo<String> cargarGrafo ( String Archivo , HashMap<String,String> ciudades){
        Grafo<String> g = new Grafo<>(true);
        ciudades.entrySet().forEach((entry) -> {g.agregarVertice(entry.getKey());});
             
        try (Scanner sc = new Scanner(new File(Archivo))) {
            while(sc.hasNextLine()) {
                String[] destCiuDist= sc.nextLine().split("\\|");
                String partida= destCiuDist[0] ;
                for(int n=1; n<=40; n++){
                    String[] ciuDist=destCiuDist[n].split("\\,"); String llegada= ciuDist[0];
                    int distancia= parseInt(ciuDist[1]);
                    if(distancia>0){
                        g.agregarArco(partida, llegada,distancia);
                    }
                }
            }
        } catch (FileNotFoundException ex) {}
        
        return g;
    }
    
    
}
