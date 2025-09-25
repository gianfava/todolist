package br.com.todolist.service;

import br.com.todolist.model.Tarefa;
import br.com.todolist.repository.TarefaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Habilita o Mockito para esta classe de teste
class TarefaServiceTest {

    @Mock // Cria um objeto mock (falso) do repositório
    private TarefaRepository tarefaRepository;

    @InjectMocks // Cria uma instância do service e injeta os mocks criados acima
    private TarefaService tarefaService;

    @Test
    void deveCriarTarefaComSucesso() {
        // Cenário
        Tarefa tarefaParaSalvar = new Tarefa(null, "Estudar Spring Boot", "Revisar testes", false, null, null);
        Tarefa tarefaSalva = new Tarefa(1L, "Estudar Spring Boot", "Revisar testes", false, null, null);

        // Ação - Simula o comportamento do repositório
        when(tarefaRepository.save(tarefaParaSalvar)).thenReturn(tarefaSalva);

        // Execução
        Tarefa resultado = tarefaService.criar(tarefaParaSalvar);

        // Verificação
        assertNotNull(resultado);
        assertEquals(tarefaSalva.getId(), resultado.getId());
        assertEquals("Estudar Spring Boot", resultado.getTitulo());
        verify(tarefaRepository, times(1)).save(tarefaParaSalvar); // Verifica se o método save foi chamado 1 vez
    }

    @Test
    void deveBuscarTarefaPorId() {
        // Cenário
        Long id = 1L;
        Tarefa tarefaMock = new Tarefa(id, "Teste", "Descrição Teste", false, null, null);
        when(tarefaRepository.findById(id)).thenReturn(Optional.of(tarefaMock));

        // Execução
        Optional<Tarefa> resultado = tarefaService.buscarPorId(id);

        // Verificação
        assertTrue(resultado.isPresent());
        assertEquals(id, resultado.get().getId());
    }
}