package service;

import model.Prioridade;
import model.Tarefa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class GerenciadorTarefas {

    private final List<Tarefa> tarefas;

    public GerenciadorTarefas() {
        this.tarefas = new ArrayList<>();
    }

    public void adicionarTarefa(Tarefa tarefa) {
        this.tarefas.add(tarefa);
    }

    public List<Tarefa> listarTodas() {
        return new ArrayList<>(this.tarefas);
    }

    public List<Tarefa> listarPorPrioridade(Prioridade prioridade) {
        return this.tarefas.stream()
                .filter(tarefa -> tarefa.getPrioridade() == prioridade)
                .collect(Collectors.toList());
    }

    public Optional<Tarefa> buscarPorId(UUID id) {
        return this.tarefas.stream()
                .filter(tarefa -> tarefa.getId().equals(id))
                .findFirst();
    }

    public void remover(UUID id) {
        this.tarefas.removeIf(tarefa -> tarefa.getId().equals(id));
    }

    public boolean atualizar(UUID id, Tarefa tarefaAtualizada) {
        Optional<Tarefa> tarefaExistenteOpt = buscarPorId(id);
        if (tarefaExistenteOpt.isPresent()) {
            Tarefa tarefaExistente = tarefaExistenteOpt.get();

            tarefaExistente.setNome(tarefaAtualizada.getNome());
            tarefaExistente.setDescricao(tarefaAtualizada.getDescricao());
            tarefaExistente.setPrioridade(tarefaAtualizada.getPrioridade());
            tarefaExistente.setData(tarefaAtualizada.getData());
            tarefaExistente.setNotas(tarefaAtualizada.getNotas());

            return true;
        }
        return false;
    }

    public int totalTarefas() {
        return this.tarefas.size();
    }
}