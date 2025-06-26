package util.Iterator;

import util.List.DuplamenteEncadeada.NoDuplo;
import util.List.SimplesmenteEncadeada.No;

public class IterarListaEncadeada<T> {

    private NoDuplo<T> noDuplo;

    private No<T> no;

    public IterarListaEncadeada(NoDuplo<T> primeiro) {
        this.noDuplo = primeiro;
    }

    public IterarListaEncadeada(No<T> primeiro) {
        this.no = primeiro;
    }

    public boolean hasNextDuplo() {
        if (this.noDuplo.getProximo() != null)
            return true;
        else
            return false;
    }

    public NoDuplo<T> getProximoDuplo() {
        if (this.noDuplo.getProximo() != null) {
            this.noDuplo = this.noDuplo.getProximo();
            return noDuplo;
        } else {
            return this.noDuplo;
        }

    }

    public boolean hasNext() {
        if (this.no.getProximo() != null)
            return true;
        else
            return false;
    }

    public No<T> getProximo() {
        this.no = this.no.getProximo();
        return no;
    }
}
