
package grafos.lista;

public interface TDAGrafo<T> {
    
    public void agregarVertice(T elem);
    public void removerVertice(T elem);
    public void agregarArco(T elem1, T elem2);
    
    public void agregarArco(T elem1, T elem2, int peso);
    public void removerArco(T elem1, T elem2);
    
    public Vertice<T> buscarVertice(T elem);
    public void resetearVisitados();
    
}
