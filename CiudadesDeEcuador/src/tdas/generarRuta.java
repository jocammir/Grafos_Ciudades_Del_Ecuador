
package tdas;
import java.util.*;
import grafos.lista.*;
import java.util.PriorityQueue;

public class generarRuta {
    ciudades ciu=new ciudades();
    HashMap<String,String> dic=ciu.cargarCiudades("ciudades.txt");
    public void dijkstra(Grafo<String> g, String elem1) {
        PriorityQueue<Vertice<String>> cola = new PriorityQueue<>(g.getVertices().size(), (Vertice v1, Vertice v2) -> Integer.compare(v1.getDistancia(), v2.getDistancia()));
        
        for (Vertice v: g.getVertices()) {
            v.setDistancia(Integer.MAX_VALUE);
            v.setPrevio(null);
            v.setVisitado(false);
        }
        Vertice v0 = g.buscarVertice(elem1);
        v0.setDistancia(0);
        cola.add(v0);
        
        while (!cola.isEmpty()) {
            Vertice<String> minimo = cola.poll();
            minimo.setVisitado(true);
            for (Arco<String> a: minimo.getArcos()) {
                Vertice<String> destino = a.getDestino();
                if (!destino.isVisitado() && (destino.getDistancia() > minimo.getDistancia() + a.getPeso())) {
                    destino.setDistancia(minimo.getDistancia() + a.getPeso());
                    destino.setPrevio(minimo);
                    cola.add(destino);
                }
            }
        }
    }
    
    public int minDistancia(String elem1, String elem2) {
        elabGrafo g=new elabGrafo();
        Grafo<String> grafo=g.cargarGrafo("distancias.txt", dic);
        if(dic.containsValue(elem1)&& dic.containsValue(elem2)){
            dijkstra(grafo, ciu.buscarCodigoCiudad(dic, elem1));
            Vertice<String> v2 = grafo.buscarVertice(ciu.buscarCodigoCiudad(dic, elem2));
            return v2.getDistancia();
        }
        System.out.println("\n Una de las dos ciudades que ingresó no existe\n Asegurese de escribir la ciudad con la primera letra en Mayuscula\n");
        return 0;
    }
    
    public LinkedList<String> rutaMinDistancia(Grafo<String> g, String elem1, String elem2) {
        LinkedList<String> listaR = new LinkedList<>();
        String cod1=ciu.buscarCodigoCiudad(dic, elem1);
        String cod2=ciu.buscarCodigoCiudad(dic, elem2);
        if(cod1==null||cod2==null){return null;}
        dijkstra(g, cod1);
        
        Vertice<String> v2 = g.buscarVertice(cod2);
        Vertice<String> varTemp = v2;
        
        while(varTemp != null) {
            listaR.addFirst(varTemp.getContenido());
            varTemp = varTemp.getPrevio();
        }
        
        return listaR;
    }
    
    public String rutaMinDistancia(String elem1, String elem2){
        elabGrafo g=new elabGrafo();
        LinkedList<String> lista=rutaMinDistancia(g.cargarGrafo("distancias.txt", dic),elem1,elem2);
        if(lista==null){return null;}
        String ruta="Empieza con ";
        for(String c: lista){
            ruta+="->"+dic.get(c);
        }
        return ruta;
    }
}
