package util.List.DuplamenteEncadeada;

import java.io.Serializable;

import util.Interfaces.List.InterList;
import util.Iterator.IterarListaEncadeada;
import util.Log.*;

/**
 * <h1>ListaDuplamenteEncadeada</h1> é uma classe
 * {@code ListaDuplamenteEncadeada} implementa estrutura de dados com
 * comportamento de lista duplamente encadeada.
 * <p>
 * Esta classe implementa {@link Serializable} para poder manipular arquivos.
 * </p>
 * <p>
 * Além disso, esta classe implementa as seguintes interfaces:
 * </p>
 * <ul>
 * <li>{@link InterList}</li>
 * <li>{@link CollectionInteface}</li>
 * </ul>
 * 
 * @author Ricardo Cezar Fernandes de Melo Junior
 */
public class ListaDuplamenteEncadeada<T> implements InterList<T>, Serializable {

    // #region Atributos

    private NoDuplo<T> primeiro;

    private NoDuplo<T> ultimo;

    private Integer tamanho;

    // #endregion

    // #region Construtor

    public ListaDuplamenteEncadeada() {

        this.tamanho = 0;
        this.primeiro = null;
        this.ultimo = null;

    }

    // #endregion

    // #region Métodos

    /**
     * Método para adicionar valor na <strong>primeira</strong> posição da lista
     * 
     * @param value é o valor para ser adicionado na lista
     * @return void
     */
    @Override
    public void adicionar(T value) {
        NoDuplo<T> noDuplo = new NoDuplo<>(value);
        if (this.primeiro == null) {
            this.primeiro = noDuplo;
            this.ultimo = noDuplo;
        } else {
            noDuplo.setProximo(this.primeiro);
            this.primeiro.setAnterior(noDuplo);
            this.primeiro = noDuplo;
        }
        this.tamanho++;
    }

    /**
     * Método para adicionar valor na <strong>ultima</strong> posição da lista
     * 
     * @param value é o valor para ser adicionado na lista
     * @return void
     */
    @Override
    public void adicionarUltimo(T value) {
        NoDuplo<T> noDuplo = new NoDuplo<>(value);
        if (this.ultimo == null) {
            this.primeiro = noDuplo;
            this.ultimo = noDuplo;
        } else {
            noDuplo.setAnterior(this.ultimo);
            this.ultimo.setProximo(noDuplo);
            this.ultimo = noDuplo;
        }
        this.tamanho++;
    }

    /**
     * Método para adicionar valor na lista, a partir de algum critério dado.
     * 
     * @param crit  é o critério para adicionar o dado passado
     * @param value é o valor para ser adicionado na lista
     * @return void
     */
    @Override
    public void adicionarDepois(T value, T crit) {
        NoDuplo<T> noDuplo = procurarNo(crit);
        if (noDuplo == null) {
            throw new NaoTemNadaAqui("Critério [ NÃO ] EXISTE!!!");
        }
        NoDuplo<T> novo = new NoDuplo<>(value);
        if (noDuplo.equals(this.primeiro)) {
            novo.setProximo(this.primeiro.getProximo());
            novo.setAnterior(this.primeiro);
            this.primeiro.getProximo().setAnterior(novo);
            this.primeiro.setProximo(novo);
            this.tamanho++;
        } else if (noDuplo.equals(this.ultimo)) {
            adicionarUltimo(value);
        } else {
            novo.setAnterior(noDuplo);
            novo.setProximo(noDuplo.getProximo());
            noDuplo.getProximo().setAnterior(novo);
            noDuplo.setProximo(novo);
            this.tamanho++;
        }
    }

    /**
     * Método que mostra o primeiro elemento da lista
     * 
     * @return valor que está em primeiro.
     */
    @Override
    public T mostrarPrimeiro() {
        if (this.primeiro != null) {
            System.out.println("Valor da 1° posição igual = [ " + this.primeiro.getValor() + " ]");
            return this.primeiro.getValor();
        } else {
            throw new NaoTemNadaAqui("Lista [ VAZIA ] você tá mandando imprimir [ NADA ]!!!");
        }
    }

    /**
     * Método que mostra o ultimo elemento da lista
     * 
     * @return valor que está em ultimo.
     */
    @Override
    public T mostrarUltimo() {
        if (this.ultimo != null) {
            System.out.println("Valor da " + this.tamanho + "° posição igual = [ " + this.ultimo.getValor() + " ]");
            return this.ultimo.getValor();
        } else {
            throw new NaoTemNadaAqui("Lista [ VAZIA ] você tá mandando imprimir [ NADA ]!!!");
        }
    }

    /**
     * Método que verifica se o critério passado existe
     * 
     * @param crit é o valor que será verificado
     * @return <strong>verdadeiro</strong> se achado OU <strong>falso</strong> se
     *         não encontrado
     */
    @Override
    public T procurar(T crit) {
        NoDuplo<T> noDuplo = procurarNo(crit);
        if (noDuplo != null)
            return noDuplo.getValor();
        else
            throw new NaoTemNadaAqui("Critério [ NÃO ] EXISTE!!!");
    }

    /**
     * Método que procura o nó da lista contendo o critério
     * 
     * @param noDuplo é o valor que está contido no nó em procura
     * @return Nó que está sendo procurado
     */
    private NoDuplo<T> procurarNo(T noDuplo) {
        NoDuplo<T> index = this.primeiro;

        if (index == null) {
            throw new NaoTemNadaAqui("Lista [ VAZIA ] você tá mandando imprimir [ NADA ]!!!");
        }

        while (index != null) {
            if (index.getValor().equals(noDuplo)) {
                return index;
            }
            index = index.getProximo();
        }
        return null;
    }

    /**
     * Método para remover valor na <strong>primeira</strong> posição da lista
     * 
     * @return valor removido
     */
    @Override
    public T removerElemento() {
        T value;
        if (this.primeiro == null) {
            throw new ForaDosLimites("Lista [ VAZIA ], todos os elemento foram removidos");
        } else if (this.primeiro.equals(this.ultimo)) {
            value = this.primeiro.getValor();
            this.primeiro = null;
            this.ultimo = null;
            this.tamanho--;
            return value;
        } else {
            NoDuplo<T> tmp = this.primeiro;
            value = tmp.getValor();
            this.primeiro = this.primeiro.getProximo();
            this.primeiro.setAnterior(null);
            tmp.setProximo(null);
            this.tamanho--;
            return value;
        }
    }

    /**
     * Método para remover valor na <strong>ultima</strong> posição da lista
     * 
     * @return valor removido
     */
    @Override
    public T removerUltimo() {
        T value;
        if (this.ultimo == null) {
            throw new ForaDosLimites("Lista [ VAZIA ], todos os elemento foram removidos");
        } else if (this.ultimo.equals(this.primeiro)) {
            value = this.ultimo.getValor();
            this.primeiro = null;
            this.ultimo = null;
            this.tamanho--;
            return value;
        } else {
            NoDuplo<T> tmp = this.ultimo;
            value = tmp.getValor();
            this.ultimo = this.ultimo.getAnterior();
            this.ultimo.setProximo(null);
            tmp.setAnterior(null);
            this.tamanho--;
            return value;
        }
    }

    /**
     * Método para remover valor na lista, a partir de algum critério dado.
     * 
     * @param crit é o critério para remover o dado passado
     * @return valor removido
     */
    @Override
    public T remover(T crit) {
        NoDuplo<T> noDuplo = procurarNo(crit);
        if (noDuplo == null) {
            throw new NaoTemNadaAqui("Critério [ NÃO ] EXISTE!!!");
        }
        T value;
        if (noDuplo.equals(this.primeiro)) {
            value = removerElemento();
            return value;
        } else if (noDuplo.equals(this.ultimo)) {
            value = removerUltimo();
            return value;
        } else {
            noDuplo.getAnterior().setProximo(noDuplo.getProximo());
            noDuplo.getProximo().setAnterior(noDuplo.getAnterior());
            value = noDuplo.getValor();
            noDuplo.setAnterior(null);
            noDuplo.setProximo(null);
            this.tamanho--;
            return value;
        }
    }

    /**
     * Método que percorre toda lista encadeada e imprime seus valores
     */
    @Override
    public void mostrar() {
        NoDuplo<T> index = this.primeiro;
        if (index == null) {
            throw new NaoTemNadaAqui("Lista [ VAZIA ] você tá mandando imprimir [ NADA ]!!!");
        }
        while (index != null) {
            System.out.println("Valor = " + index.getValor());
            index = index.getProximo();
        }
        System.out.println();
    }

    /**
     * Método que percorre toda lista encadeada e imprime seus valores na
     * <strong>ordem inversa</strong>
     */
    @Override
    public void mostrarInverso() {
        NoDuplo<T> index = this.ultimo;
        if (index == null) {
            throw new NaoTemNadaAqui("Lista [ VAZIA ] você tá mandando imprimir [ NADA ]!!!");
        }
        while (index != null) {
            System.out.println("Valor = " + index.getValor());
            index = index.getAnterior();
        }
        System.out.println();
    }

    @Override
    public IterarListaEncadeada<T> getIterator() {
        return new IterarListaEncadeada<>(this.primeiro);
    }

    @Override
    public Integer getSize() {
        return tamanho;
    }

    // #endregion
}
