
package grafos.lista;

import java.util.LinkedList;

public class Grafo<T> implements TDAGrafo<T> {

    private LinkedList<Vertice<T>> vertices;
    private boolean dirigido;

    public Grafo(boolean dirigido) {
        this.dirigido = dirigido;
        this.vertices = new LinkedList<>();
    }

    public LinkedList<Vertice<T>> getVertices() {
        return vertices;
    }

    public boolean isDirigido() {
        return dirigido;
    }

    public void setVertices(LinkedList<Vertice<T>> vertices) {
        this.vertices = vertices;
    }

    public void setDirigido(boolean dirigido) {
        this.dirigido = dirigido;
    }
    
    @Override
    public void agregarVertice(T elem) {
        if (buscarVertice(elem) != null) {
            return;
        }
        
        Vertice<T> v = new Vertice<>(elem);
        this.vertices.add(v);
    }

    @Override
    public void removerVertice(T elem) {
        Vertice<T> v = buscarVertice(elem);
        
        if (v == null) {
            return;
        }
        
        v.removerTodosArcos();
        
        for (Vertice iter: this.vertices) {
            this.removerArco((T) iter.getContenido(), elem);
        }
        
        this.vertices.remove(v);
    }

    @Override
    public void agregarArco(T elem1, T elem2) {
        
        Vertice<T> v1 = buscarVertice(elem1);
        Vertice<T> v2 = buscarVertice(elem2);
        
        if (v1 == null || v2 == null) {
            return;
        }
        
        v1.agregarArco(v2);
        if (!dirigido) {
            v2.agregarArco(v1);
        }
    }
    
    @Override
    public void agregarArco(T elem1, T elem2, int peso) {
        
        Vertice<T> v1 = buscarVertice(elem1);
        Vertice<T> v2 = buscarVertice(elem2);
        
        if (v1 == null || v2 == null) {
            return;
        }
        
        v1.agregarArco(v2, peso);
        if (!dirigido) {
            v2.agregarArco(v1, peso);
        }
    }

    @Override
    public void removerArco(T elem1, T elem2) {
        Vertice<T> v1 = buscarVertice(elem1);
        Vertice<T> v2 = buscarVertice(elem2);
        
        if (v1 == null || v2 == null) {
            return;
        }
        
        v1.removerArco(v2);
        if (!dirigido) {
            v2.removerArco(v1);
        }
    }
    
    @Override
    public Vertice<T> buscarVertice(T elem) {
        for (Vertice<T> v : this.vertices) {
            if (v.getContenido().equals(elem)){
                return v;
            }
        }
        
        return null;
    }
    
    @Override
    public void resetearVisitados() {
        for(Vertice<T> v: this.vertices) {
            v.setVisitado(false);
        }
    }

    @Override
    public String toString() {
        String s = "{\n";
        for (Vertice<T> v : this.vertices) {
            s+= "\t" + v.getContenido() + ":" + v.getArcos() + "\n";
        }
        s+="}\n";
        return s;
    }
}
