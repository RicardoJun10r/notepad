package util.List.SimplesmenteEncadeada;

import util.Interfaces.List.InterList;
import util.Iterator.IterarListaEncadeada;
import util.Log.*;

/**
 * <h1>ListaSimplesmenteEncadeada</h1> é uma classe
 * {@code ListaSimplesmenteEncadeada} implementa estrutura de dados com
 * comportamento de lista simplesmente encadeada.
 * <p>
 * Esta classe implementa as seguintes interfaces:
 * </p>
 * <ul>
 * <li>{@link InterList}</li>
 * <li>{@link CollectionInteface}</li>
 * </ul>
 * 
 * @author Ricardo Cezar Fernandes de Melo Junior
 */
public class ListaSimplesmenteEncadeada<T> implements InterList<T> {

    // #region Atributos

    private No<T> primeiro;

    private No<T> ultimo;

    private Integer tamanho;

    // #endregion

    // #region Construtor

    public ListaSimplesmenteEncadeada() {
        this.primeiro = null;
        this.ultimo = null;
        this.tamanho = 0;
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
        No<T> no = new No<>(value);
        if (this.primeiro == null) {
            this.primeiro = no;
            this.ultimo = no;
        } else {
            no.setProximo(this.primeiro);
            this.primeiro = no;
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
        No<T> no = new No<>(value);
        if (this.ultimo == null) {
            this.primeiro = no;
            this.ultimo = no;
        } else {
            this.ultimo.setProximo(no);
            this.ultimo = no;
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
        No<T> noCriterio = procurarNo(crit);
        if (noCriterio == null) {
            throw new NaoTemNadaAqui("Critério [ NÃO ] EXISTE!!!");
        }
        if (noCriterio.equals(this.primeiro)) {
            adicionar(value);
        } else if (noCriterio.equals(this.ultimo)) {
            adicionarUltimo(value);
        } else {
            No<T> no = new No<>(value);
            No<T> anterior = noAnterior(noCriterio);
            anterior.setProximo(no);
            no.setProximo(noCriterio);
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
            System.out.println("Valor de posição 1° = [ " + this.primeiro.getValor() + " ]");
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
            System.out.println("Valor de posição " + this.tamanho + "° = [ " + this.ultimo.getValor() + " ]");
            return this.ultimo.getValor();
        } else {
            throw new NaoTemNadaAqui("Lista [ VAZIA ] você tá mandando imprimir [ NADA ]!!!");
        }
    }

    /**
     * Método que procura o nó da lista contendo o critério
     * 
     * @param crit é o valor que está contido no nó em procura
     * @return Nó que está sendo procurado
     */
    private No<T> procurarNo(T crit) {
        No<T> index = this.primeiro;

        if (index == null) {
            throw new NaoTemNadaAqui("Lista [ VAZIA ] você tá mandando imprimir [ NADA ]!!!");
        }

        while (index != null) {
            if (index.getValor().equals(crit)) {
                return index;
            }
            index = index.getProximo();
        }
        return null;
    }

    /**
     * Método que procura o nó anterior do nó passado no parâmetro da função.
     * 
     * @param no é o nó que servirá de critério para achar seu anterior.
     * @return Nó anterior ao que foi passado.
     */
    private No<T> noAnterior(No<T> no) {
        No<T> index = this.primeiro;

        if (index == null) {
            throw new NaoTemNadaAqui("Lista [ VAZIA ] não tem [ NADA ] aqui!!!");
        }

        No<T> anterior = null;
        while (index != null) {
            if (index.equals(no)) {
                return anterior;
            }
            anterior = index;
            index = index.getProximo();
        }
        return null;
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
        No<T> tmp = procurarNo(crit);
        if (tmp != null) {
            return tmp.getValor();
        } else {
            throw new NaoTemNadaAqui("Critério [ NÃO ] EXISTE!!!");
        }
    }

    /**
     * Método para remover valor na <strong>primeira</strong> posição da lista
     * 
     * @return valor removido
     */
    @Override
    public T removerElemento() {
        No<T> tmp;
        if (this.primeiro == null) { // Se a lista for vazia
            throw new ForaDosLimites("Lista [ VAZIA ], todos os elemento foram removidos");
        } else if (this.primeiro.equals(this.ultimo)) { // Se o primeiro NÓ for o unico elemento
            System.out.println("ULTIMO NÓ DA LISTA");
            T valor = this.primeiro.getValor();
            this.primeiro = null;
            this.ultimo = null;
            this.tamanho--;
            return valor;
        } else { // Se o primeiro NÓ estiver pelo meio da lista
            System.out.println("ELEMENTO NO MEIO");
            T valor = this.primeiro.getValor(); // Valor do primeiro elemento
            tmp = this.primeiro.getProximo(); // pegando proximo NÓ
            this.primeiro.setProximo(null); // desligando NÓ da lista
            this.primeiro = tmp; // atribuindo o novo valor do primeiro NÓ da lista
            System.out.println("Valor removido da 1° posição = [ " + valor + " ]");
            this.tamanho--;
            return valor; // Retornando valor removido
        }
    }

    /**
     * Método para remover valor na <strong>ultima</strong> posição da lista
     * 
     * @return valor removido
     */
    @Override
    public T removerUltimo() {
        No<T> tmp;
        if (this.ultimo == null) { // Se a lista for vazia
            throw new ForaDosLimites("Lista [ VAZIA ], todos os elemento foram removidos");
        } else if (this.ultimo.equals(this.primeiro)) { // Se o ultimo NÓ for o unico elemento
            System.out.println("ULTIMO NÓ DA LISTA");
            T valor = this.ultimo.getValor();
            this.primeiro = null;
            this.ultimo = null;
            this.tamanho--;
            return valor;
        } else { // Se o ultimo NÓ estiver pelo meio da lista
            System.out.println("ELEMENTO NO MEIO");
            T valor = this.ultimo.getValor(); // Valor do ultimo elemento
            tmp = noAnterior(this.ultimo); // pegando NÓ anterior
            this.ultimo = tmp; // atribuindo o novo valor do ultimo NÓ da lista
            this.ultimo.setProximo(null); // desligando NÓ da lista
            System.out.println("Valor removido da " + this.tamanho + "° posição = [ " + valor + " ]");
            this.tamanho--;
            return valor; // Retornando valor removido
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
        No<T> noCriterio = procurarNo(crit);
        if (noCriterio == null) {
            throw new NaoTemNadaAqui("Critério [ NÃO ] EXISTE!!!");
        }
        T valor;
        if (noCriterio.equals(this.primeiro)) {
            valor = removerElemento();
            return valor;
        } else if (noCriterio.equals(this.ultimo)) {
            valor = removerUltimo();
            return valor;
        } else {
            No<T> anterior = noAnterior(noCriterio);
            anterior.setProximo(noCriterio.getProximo());
            valor = noCriterio.getValor();
            noCriterio.setProximo(null);
            System.out.println("Valor removido da " + this.tamanho + "° posição = [ " + valor + " ]");
            this.tamanho--;
            return valor;
        }
    }

    /**
     * Método que percorre toda lista encadeada e imprime seus valores
     */
    @Override
    public void mostrar() {
        No<T> index = this.primeiro;

        if (index == null) {
            throw new NaoTemNadaAqui("Lista [ VAZIA ] você tá mandando imprimir [ NADA ]!!!");
        }

        while (index != null) {
            System.out.println("Valor: [ " + index.getValor() + " ]");
            index = index.getProximo();
        }
    }

    /**
     * Método que percorre toda lista encadeada e imprime seus valores na
     * <strong>ordem inversa</strong>
     */
    @Override
    public void mostrarInverso() {
        No<T> index = this.ultimo;
        if (index == null) {
            throw new NaoTemNadaAqui("Lista [ VAZIA ] você tá mandando imprimir [ NADA ]!!!");
        }
        while (index != null) {
            System.out.println("Valor: [ " + index.getValor() + " ]");
            index = noAnterior(index);
        }
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
