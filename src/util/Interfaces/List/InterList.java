package util.Interfaces.List;

import util.Interfaces.CollectionInteface;
import util.Iterator.IterarListaEncadeada;
import util.Log.NaoTemNadaAqui;

import util.Log.ForaDosLimites;

/**
 * <h1>InterList</h1> é uma interface {@code InterList} que extende de
 * {@link CollectionInteface}, adicionando métodos
 * específicos para as seguintes classes:
 * 
 * @see ListaSimplementeEncadeada
 * @see ListaDuplamenteEncadeada
 */
public interface InterList<T> extends CollectionInteface<T> {
    void adicionarUltimo(T value);

    void adicionarDepois(T value, T crit) throws NaoTemNadaAqui;

    T mostrarPrimeiro() throws NaoTemNadaAqui;

    T mostrarUltimo() throws NaoTemNadaAqui;

    T procurar(T crit) throws NaoTemNadaAqui;

    T removerUltimo() throws ForaDosLimites;

    T remover(T crit) throws ForaDosLimites;

    void mostrar() throws NaoTemNadaAqui;

    void mostrarInverso() throws NaoTemNadaAqui;

    Integer getSize();

    IterarListaEncadeada<T> getIterator();
}
