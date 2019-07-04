
package grafo.ui;

import java.util.LinkedList;
import java.util.ListIterator;
import grafos.lista.Arco;
import grafos.lista.Grafo;
import grafos.lista.Vertice;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class GrafoUI<T> {
    private Graph graph;
    
    public GrafoUI(Grafo<T> g){
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        graph = new SingleGraph("Grafo");
        
        graph.addAttribute("ui.stylesheet", "url(./lib/styles.css)");
        
        for (Vertice<T> v: g.getVertices()) {
            graph.addNode(v.getContenido().toString()).addAttribute("ui.label", v.getContenido().toString());
        }
        
        for (Vertice<T> v1: g.getVertices()) {
            for (Vertice<T> v2: g.getVertices()) {
                Arco<T> arco = v1.buscarArco(v2);
                if(arco != null) {
                    String cv1 = v1.getContenido().toString();
                    String cv2 = v2.getContenido().toString();
                    
                    if (!g.isDirigido()) {
                        if (graph.getNode(cv1).hasEdgeBetween(cv2)) {
                            continue;
                        }
                    }
                    
                    Edge edge = graph.addEdge(cv1+cv2, cv1, cv2, g.isDirigido());
                    
                    if (arco.getPeso() != -1) {
                        edge.addAttribute("ui.label", arco.getPeso());
                    }
                }
            }
        }
    }
    
    public void mostrarGrafo() {
        graph.display();
    }
    
    public void mostrarRecorrido(LinkedList<T> vertices){
        ListIterator<T> k = vertices.listIterator();

        while (k.hasNext()) {
            T v = k.next();
            Node nodo = graph.getNode(v.toString());
            nodo.setAttribute("ui.class", "marked");
            try { Thread.sleep(3000); } catch (Exception e) {}
        }
    }
}
