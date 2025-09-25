package br.com.todolist.repository;

import br.com.todolist.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    // JpaRepository<Tipo da Entidade, Tipo do ID>
    // Todos os métodos básicos (save, findById, findAll, deleteById, etc.) já estão disponíveis!
}