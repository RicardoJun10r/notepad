package util.Queue;

import util.Interfaces.List.InterList;
import util.Interfaces.StackQueue.StackQueueInteface;
import util.List.DuplamenteEncadeada.ListaDuplamenteEncadeada;
import util.Log.ForaDosLimites;
import util.Log.NaoTemNadaAqui;

/**
 * <h1>MinhaQueueComLista</h1> é uma classe {@code MinhaQueueComLista}
 * implementa estrutura de dados com comportamento de fila.
 * <p>
 * Esta fila utiliza lista encadeada.
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
public class MinhaQueueComLista<T> implements StackQueueInteface<T> {

    // #region Atributos

    private InterList<T> filaComList; // Lista encadeada, para simular comportamento de pilha

    private Integer primeiro;

    private Integer ultimo;

    private Integer tamanho;

    // #endregion

    // #region Construtor

    /**
     * Método construtor da classe {@code MinhaQueueComLista}
     * 
     * @param tamanho estabele o tamanho da fila
     */
    public MinhaQueueComLista(Integer tamanho) {
        this.filaComList = new ListaDuplamenteEncadeada<>();
        this.primeiro = -1;
        this.ultimo = -1;
        this.tamanho = tamanho;
    }

    // #endregion

    // #region Métodos

    /**
     * Método que adiciona um elemento na fila
     * 
     * @param valor é o valor que será adicionado
     * @return void
     */
    @Override
    public void adicionar(T valor) {
        if (isFull()) {
            throw new ForaDosLimites("Fila [ CHEIA ]");
        }
        this.filaComList.adicionarUltimo(valor);
        this.primeiro++;
        this.ultimo++;
    }

    /**
     * Método que remove um elemento na fila
     * 
     * @return valor removido
     */
    @Override
    public T removerElemento() throws ForaDosLimites {
        if (isEmpty()) {
            throw new ForaDosLimites("Fila [ VAZIA ]");
        }
        T valor = this.filaComList.removerElemento();
        this.primeiro--;
        this.ultimo--;
        return valor;
    }

    /**
     * Método que mostra o primeiro elemento da fila
     * 
     * @return valor que está em primeiro.
     */
    @Override
    public T mostrarPrimeiro() throws NaoTemNadaAqui {
        if (isEmpty()) {
            throw new ForaDosLimites("Fila [ CHEIA ]");
        }
        T valor = this.filaComList.mostrarPrimeiro();
        return valor;
    }

    /**
     * Método que percorre toda fila e imprime seus valores
     */
    @Override
    public void mostrar() throws NaoTemNadaAqui {
        if (isEmpty()) {
            throw new NaoTemNadaAqui("Fila [ VAZIA ]");
        }
        this.filaComList.mostrar();
    }

    /**
     * Método que verifica se a fila está cheia
     * 
     * @return <strong>verdadeiro</strong> se cheia OU <strong>falso</strong> se não
     *         cheia
     */
    @Override
    public boolean isFull() {
        if (this.ultimo == this.tamanho)
            return true;
        else
            return false;
    }

    /**
     * Método que verifica se a fila está cheia
     * 
     * @return <strong>verdadeiro</strong> se cheio OU <strong>falso</strong> se não
     *         cheio
     */
    @Override
    public boolean isEmpty() {
        if (this.primeiro == this.ultimo)
            return true;
        else
            return false;
    }

    // #endregion
}
