package util.Stack;

import util.Interfaces.List.InterList;
import util.Interfaces.StackQueue.StackQueueInteface;
import util.List.DuplamenteEncadeada.ListaDuplamenteEncadeada;
import util.Log.ForaDosLimites;
import util.Log.NaoTemNadaAqui;

/**
 * <h1>MinhaStackComLista</h1> é uma classe {@code MinhaStackComLista}
 * implementa estrutura de dados com comportamento de pilha.
 * <p>
 * Esta pilha utiliza lista encadeada.
 * </p>
 * <p>
 * Esta classe implementa as seguintes interfaces:
 * </p>
 * <ul>
 * <li>{@link StackQueueInteface}</li>
 * <li>{@link CollectionInteface}</li>
 * </ul>
 * 
 * @author Ricardo Cezar Fernandes de Melo Junior
 */
public class MinhaStackComLista<T> implements StackQueueInteface<T> {

    // #region Atributos

    private InterList<T> pilhaComList; // Lista encadeada, para simular comportamento de pilha

    private Integer ultimo;

    private Integer tamanho;

    // #endregion

    // #region Construtor

    /**
     * Método construtor da classe {@code MinhaStackComLista}
     * 
     * @param tamanho estabele o tamanho da pilha
     */
    public MinhaStackComLista(Integer tamanho) {
        this.pilhaComList = new ListaDuplamenteEncadeada<>();
        this.ultimo = -1;
        this.tamanho = tamanho;
    }

    // #endregion

    // #region Métodos

    /**
     * Método que adiciona um elemento na pilha
     * 
     * @param valor é o valor que será adicionado
     * @return void
     */
    @Override
    public void adicionar(T valor) {
        if (isFull()) {
            throw new ForaDosLimites("Pilha [ CHEIA ]");
        }
        this.pilhaComList.adicionar(valor);
        this.ultimo++;
    }

    /**
     * Método que remove um elemento na pilha
     * 
     * @return valor removido
     */
    @Override
    public T removerElemento() throws ForaDosLimites {
        if (isEmpty()) {
            throw new ForaDosLimites("Pilha [ VAZIA ]");
        }
        T valor = this.pilhaComList.removerElemento();
        this.ultimo--;
        return valor;
    }

    /**
     * Método que mostra o primeiro elemento da pilha
     * 
     * @return valor que está em primeiro.
     */
    @Override
    public T mostrarPrimeiro() throws NaoTemNadaAqui {
        if (isEmpty()) {
            throw new NaoTemNadaAqui("Pilha [ VAZIA ]");
        }
        T valor = (T) this.pilhaComList.mostrarPrimeiro();
        return valor;
    }

    /**
     * Método que percorre toda pilha e imprime seus valores
     */
    @Override
    public void mostrar() throws NaoTemNadaAqui {
        if (isEmpty()) {
            throw new NaoTemNadaAqui("Pilha [ Vazia ]");
        }
        this.pilhaComList.mostrar();
    }

    /**
     * Método que verifica se a pilha está cheia
     * 
     * @return <strong>verdadeiro</strong> se cheio OU <strong>falso</strong> se não
     *         cheio
     */
    @Override
    public boolean isFull() {
        if (this.ultimo == this.tamanho)
            return true;
        else
            return false;
    }

    /**
     * Método que verifica se a pilha está vazia
     * 
     * @return <strong>verdadeiro</strong> se vazio OU <strong>falso</strong> se não
     *         vazio
     */
    @Override
    public boolean isEmpty() {
        if (this.ultimo == -1)
            return true;
        else
            return false;
    }

}
