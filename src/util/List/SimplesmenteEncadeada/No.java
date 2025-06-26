package util.List.SimplesmenteEncadeada;

/**
 * <h1>No</h1> é uma classe {@code No} para armazenar os dados na lista
 * encadeada.
 * 
 * @author Ricardo Cezar Fernandes de Melo Junior
 */
public class No<T> {

    // #region Atributos

    private T valor;

    private No<T> proximo;

    // #endregion

    // #region Construtor

    public No(T valor) {
        this.valor = valor;
    }

    // #endregion

    // #region Métodos

    public T getValor() {
        return valor;
    }

    public No<T> getProximo() {
        return proximo;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public void setProximo(No<T> proximo) {
        this.proximo = proximo;
    }

    // #endregion
}
