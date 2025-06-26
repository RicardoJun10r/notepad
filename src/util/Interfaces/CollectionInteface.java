package util.Interfaces;

import util.Log.NaoTemNadaAqui;

import util.Log.ForaDosLimites;

/**
 * 
 * <h1>CollectionInteface</h1> é uma interface {@code CollectionInteface}
 * contendo os métodos base, que as seguintes classes irão implementar:
 * 
 * @see MinhaStack
 * @see MinhaQueue
 * @see ListaSimplesmenteEncadeada
 * @see ListaDuplamenteEncadeada
 * 
 */
public interface CollectionInteface<T> {
    public void adicionar(T valor);

    public T removerElemento() throws ForaDosLimites;

    public T mostrarPrimeiro() throws NaoTemNadaAqui;

    public void mostrar() throws NaoTemNadaAqui;
}
