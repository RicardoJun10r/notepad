package service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.Prioridade;
import model.Tarefa;
import util.Interfaces.List.InterList;
import util.Iterator.IterarListaEncadeada;
import util.List.DuplamenteEncadeada.ListaDuplamenteEncadeada;

/**
 * <h1>GerenciadorTarefas</h1> é uma classe {@code GerenciadorTarefas} que gerencia a entidade de {@link Tarefa}, criando um
 * CRUD para gerenciar esta entidade
 * 
 */
public class GerenciadorTarefas {

    //#region ATRIBUTOS

    private InterList<Tarefa> tarefas;

    private IterarListaEncadeada<Tarefa> iterarTarefas;

    private List<Tarefa> lista;

    //#endregion

    //#region CONSTRUTORES

    public GerenciadorTarefas(){
        this.tarefas = new ListaDuplamenteEncadeada<>();
        this.lista = new ArrayList<>();
    }

    //#endregion

    //#region MÉTODOS
    
    public String adicionarTarefa(Tarefa tarefa){
        this.lista.add(tarefa);
        this.tarefas.adicionar(tarefa);
        return "Tarefa de id = [ " + tarefa.getID() + " ] adicionada";
    }

    public List<Tarefa> listar(){
        if(!this.lista.isEmpty()) return this.lista;
        else return null;
    }

    public InterList<Tarefa> listarPorPrioridade(Prioridade prioridade){
        InterList<Tarefa> listaPrioridade = new ListaDuplamenteEncadeada<>();
        this.iterarTarefas = this.tarefas.getIterator();
        while (this.iterarTarefas.hasNextDuplo()) {
            if(this.iterarTarefas.getProximoDuplo().getValor().getPrioridade().equals(prioridade)){
                listaPrioridade.adicionar(this.iterarTarefas.getProximoDuplo().getValor());
            }
        }
        return listaPrioridade;
    }

    public Tarefa buscarPorNome(String nome){
        this.iterarTarefas = this.tarefas.getIterator();
        Tarefa tarefa = null;
        while (this.iterarTarefas.hasNextDuplo()) {
            if(this.iterarTarefas.getProximoDuplo().getValor().getNome().equals(nome)){
                tarefa = this.iterarTarefas.getProximoDuplo().getValor();
                break;
            }
        }
        return tarefa;
    }

    public Tarefa buscarPorId(UUID id){
        this.iterarTarefas = this.tarefas.getIterator();
        Tarefa tarefa = null;
        while (this.iterarTarefas.hasNextDuplo()) {
            if(this.iterarTarefas.getProximoDuplo().getValor().getID().equals(id)){
                tarefa = this.iterarTarefas.getProximoDuplo().getValor();
                break;
            }
        }
        return tarefa;
    }

    public Integer totalTarefas(){
        return this.lista.size();
    }

    public String remover(UUID id){
        Tarefa tarefa = buscarPorId(id);
        this.tarefas.remover(tarefa);
        return "Tarefa de id [ " + tarefa.getID() + " ] removido";
    }

    public String atualizar(UUID id, Tarefa att){
        Tarefa tarefa = buscarPorId(id);
        Tarefa prev = tarefa;

         // NOME
        if(!tarefa.getNome().equals(att.getNome())){
            tarefa.setNome(att.getNome());
        } else {
            tarefa.setNome(prev.getNome());
        }

        // DESCRIÇÃO
        if(!tarefa.getDescricao().equals(att.getDescricao())){
            tarefa.setDescricao(att.getDescricao());
        } else {
            tarefa.setDescricao(prev.getDescricao());
        }

        // PRIORIDADE
        if(!tarefa.getPrioridade().equals(att.getPrioridade())){
            tarefa.setPrioridade(att.getPrioridade());
        } else {
            tarefa.setPrioridade(prev.getPrioridade());
        }

        // DATA
        if(!tarefa.getData().equals(att.getData())){
            tarefa.setData(att.getData());
        } else {
            tarefa.setData(prev.getData());
        }
        
        return "Tarefa de id [ " + tarefa.getID() + " ] atualizado";
    }

    //#endregion
}
