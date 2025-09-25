package br.com.todolist.service;

import br.com.todolist.model.Tarefa;
import br.com.todolist.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> buscarPorId(Long id) {
        return tarefaRepository.findById(id);
    }

    public Tarefa criar(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public Optional<Tarefa> atualizar(Long id, Tarefa tarefaDetalhes) {
        return tarefaRepository.findById(id).map(tarefaExistente -> {
            tarefaExistente.setTitulo(tarefaDetalhes.getTitulo());
            tarefaExistente.setDescricao(tarefaDetalhes.getDescricao());

            // Lógica para data de conclusão
            if (tarefaDetalhes.isConcluida() && !tarefaExistente.isConcluida()) {
                tarefaExistente.setDataConclusao(LocalDateTime.now());
            } else if (!tarefaDetalhes.isConcluida()) {
                tarefaExistente.setDataConclusao(null);
            }
            tarefaExistente.setConcluida(tarefaDetalhes.isConcluida());

            return tarefaRepository.save(tarefaExistente);
        });
    }

    public boolean deletar(Long id) {
        if (tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}