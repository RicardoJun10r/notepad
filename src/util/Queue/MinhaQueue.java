package util.Queue;

import util.Interfaces.StackQueue.StackQueueInteface;
import util.Log.ForaDosLimites;
import util.Log.NaoTemNadaAqui;

/**
 * <h1>MinhaQueue</h1> é uma classe {@code MinhaQueue} implementa estrutura de
 * dados com comportamento de fila.
 * <p>
 * Esta fila utiliza vetor.
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
public class MinhaQueue<T> implements StackQueueInteface<T> {

    // #region Atributos

    private Object[] fila;

    private int primeiro;

    private int ultimo;

    private int size;

    // #endregion

    // #region Construtor

    /**
     * Método construtor da classe {@code MinhaQueue}
     * 
     * @param tamanho estabele o tamanho da fila
     */
    public MinhaQueue(int tamanho) {
        this.size = tamanho;
        this.fila = new Object[tamanho];
        this.primeiro = -1;
        this.ultimo = -1;
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
            throw new ForaDosLimites("fila está [ CHEIA ]");
        } else {
            if (isEmpty())
                this.primeiro = 0;
            int lastTemp = (this.ultimo + 1) % this.size;
            this.ultimo = lastTemp;
            this.fila[this.ultimo] = valor;
        }
    }

    /**
     * Método que remove um elemento na fila
     * 
     * @return valor removido
     */
    @SuppressWarnings("unchecked")
    @Override
    public T removerElemento() {
        if (isEmpty()) {
            throw new ForaDosLimites("fila está [ VAZIA ]");
        } else {
            T valor = (T) this.fila[this.primeiro];
            if (this.primeiro == this.ultimo) {
                this.primeiro = -1;
                this.ultimo = -1;
            } else {
                this.primeiro = (this.primeiro + 1) % this.size;
            }
            return valor;
        }
    }

    /**
     * Método que mostra o primeiro elemento da fila
     * 
     * @return valor que está em primeiro.
     */
    @SuppressWarnings("unchecked")
    @Override
    public T mostrarPrimeiro() {
        if (isEmpty())
            throw new NaoTemNadaAqui("A fila está [ VAZIA ], você tá procurando [ NADA ]!!!");
        T tmp = (T) this.fila[this.primeiro];
        return tmp;
    }

    /**
     * Método que percorre toda fila e imprime seus valores
     */
    @Override
    public void mostrar() {
        for (int i = this.primeiro; i <= this.ultimo; i++) {
            System.out.println((i + 1) + "° = " + this.fila[i]);
        }
    }

    /**
     * Método que verifica se a fila está cheia
     * 
     * @return <strong>verdadeiro</strong> se cheia OU <strong>falso</strong> se não
     *         cheia
     */
    @Override
    public boolean isFull() {
        int lastTmp = (this.ultimo + 1) % this.size;
        if (this.ultimo == lastTmp)
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
        if (this.primeiro == -1)
            return true;
        else
            return false;
    }

    // #endregion
}
