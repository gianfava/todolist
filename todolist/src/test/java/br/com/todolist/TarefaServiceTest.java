package br.com.todolist;

import br.com.todolist.model.Tarefa;
import br.com.todolist.repository.TarefaRepository;
import br.com.todolist.service.TarefaService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // Extensão do Mockito para o JUnit 5
class TarefaServiceTest {

    @Mock
    private TarefaRepository tarefaRepository; // Simula o repositório

    @InjectMocks
    private TarefaService tarefaService; // A classe a ser testada

    @Test
    void deveCriarTarefa() {
        Tarefa tarefa = new Tarefa(null, "Comprar Leite", "Caixa de leite integral", false, null, null);
        when(tarefaRepository.save(tarefa)).thenReturn(tarefa); // Simula o comportamento do save

        Tarefa tarefaCriada = tarefaService.criar(tarefa); // Chama o método de criação

        assertNotNull(tarefaCriada); // Verifica que a tarefa não é nula
        assertEquals("Comprar Leite", tarefaCriada.getTitulo()); // Verifica se o título está correto
    }

    @Test
    void deveBuscarTarefaPorId() {
        Tarefa tarefa = new Tarefa(1L, "Comprar Leite", "Caixa de leite integral", false, null, null);
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa)); // Simula o comportamento do findById

        Optional<Tarefa> tarefaEncontrada = tarefaService.buscarPorId(1L);

        assertTrue(tarefaEncontrada.isPresent()); // Verifica se a tarefa foi encontrada
        assertEquals("Comprar Leite", tarefaEncontrada.get().getTitulo()); // Verifica o título da tarefa
    }

    @Test
    void deveRetornarVazioQuandoTarefaNaoEncontrada() {
        when(tarefaRepository.findById(999L)).thenReturn(Optional.empty()); // Simula que não encontrou a tarefa

        Optional<Tarefa> tarefa = tarefaService.buscarPorId(999L);

        assertFalse(tarefa.isPresent()); // Verifica que não foi encontrada nenhuma tarefa
    }
}
