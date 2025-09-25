
package br.com.todolist.controller;

import br.com.todolist.model.Tarefa;
import br.com.todolist.repository.TarefaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@SpringBootTest // Sobe o contexto completo da aplicação para o teste
@AutoConfigureMockMvc // Configura o MockMvc para simular requisições HTTP
class TarefaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private ObjectMapper objectMapper; // Para converter objetos Java em JSON

    @BeforeEach
    void setUp() {
        // Limpa o banco de dados antes de cada teste para garantir isolamento
        tarefaRepository.deleteAll();
    }

    @Test
    void deveCriarTarefaERetornarStatusCreated() throws Exception {
        Tarefa tarefa = new Tarefa(null, "Comprar Leite", "Caixa de leite integral", false, null, null);

        mockMvc.perform(post("/api/tarefas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tarefa)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo", is("Comprar Leite")));
    }

    @Test
    void deveListarTodasAsTarefas() throws Exception {
        Tarefa tarefa1 = new Tarefa(null, "Tarefa 1", "Desc 1", false, null, null);
        tarefaRepository.save(tarefa1);

        mockMvc.perform(get("/api/tarefas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo", is("Tarefa 1")));
    }
}