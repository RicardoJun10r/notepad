package util.List.DuplamenteEncadeada;

import java.io.Serializable;

/**
 * <h1>NoDuplo</h1> é uma classe {@code NoDuplo} para armazenar os dados na
 * lista encadeada.
 * <p>
 * Esta classe implementa {@link Serializable} para poder manipular arquivos.
 * </p>
 * 
 * @author Ricardo Cezar Fernandes de Melo Junior
 */
public class NoDuplo<T> implements Serializable {

    // #region Atributos

    private T valor;

    private NoDuplo<T> proximo;

    private NoDuplo<T> anterior;

    // #endregion

    // #region Construtor

    public NoDuplo(T valor) {
        this.valor = valor;
        this.anterior = null;
        this.proximo = null;
    }

    // #endregion

    // #region Métodos

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public NoDuplo<T> getProximo() {
        return proximo;
    }

    public void setProximo(NoDuplo<T> proximo) {
        this.proximo = proximo;
    }

    public NoDuplo<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(NoDuplo<T> anterior) {
        this.anterior = anterior;
    }

    // #endregion
}
