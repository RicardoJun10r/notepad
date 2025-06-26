package util.Stack;

import util.Interfaces.StackQueue.StackQueueInteface;
import util.Log.ForaDosLimites;
import util.Log.NaoTemNadaAqui;

/**
 * <h1>MinhaStack</h1> é uma classe {@code MinhaStack} implementa estrutura de
 * dados com comportamento de pilha.
 * <p>
 * Esta pilha utiliza vetor.
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
public class MinhaStack<T> implements StackQueueInteface<T> {

    // #region Atributos

    public Object[] pilha;

    public int ultimo;

    public int tamanho;

    // #endregion

    // #region Construtor

    /**
     * Método construtor da classe {@code MinhaStack}
     * 
     * @param tamanho estabele o tamanho da pilha
     */
    public MinhaStack(int tamanho) {
        this.tamanho = tamanho;
        this.pilha = new Object[this.tamanho];
        this.ultimo = -1;
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
            throw new ForaDosLimites("Pilha tá [ CHEIA ]");
        }
        this.ultimo++;
        this.pilha[this.ultimo] = valor;
    }

    /**
     * Método que remove um elemento na pilha
     * 
     * @return valor removido
     */
    @SuppressWarnings("unchecked")
    @Override
    public T removerElemento() {
        if (isEmpty()) {
            throw new ForaDosLimites("Pilha tá [ VAZIA ]");
        }
        T tmp = (T) this.pilha[this.ultimo];
        this.ultimo--;

        return tmp;
    }

    /**
     * Método que mostra o primeiro elemento da pilha
     * 
     * @return valor que está em primeiro.
     */
    @SuppressWarnings("unchecked")
    @Override
    public T mostrarPrimeiro() {
        if (isEmpty()) {
            throw new NaoTemNadaAqui("A pilha está [ VAZIA ], você tá procurando [ NADA ]!!!");
        }

        T tmp = (T) this.pilha[0];
        return tmp;
    }

    /**
     * Método que percorre toda pilha e imprime seus valores
     */
    @Override
    public void mostrar() {
        for (int i = 0; i <= this.ultimo; i++) {
            System.out.println((i + 1) + "° = [ " + this.pilha[i] + " ]");
        }
        System.out.println();
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

    // #endregion
}
