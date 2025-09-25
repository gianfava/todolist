package br.com.todolist.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity // Marca esta classe como uma entidade JPA (será uma tabela no banco)
@Table(name = "tarefas") // Define o nome da tabela
@Data // Anotação do Lombok que gera Getters, Setters, toString, equals, hashCode
@NoArgsConstructor // Gera um construtor sem argumentos
@AllArgsConstructor // Gera um construtor com todos os argumentos
public class Tarefa {

    @Id // Marca este campo como a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O banco de dados gerenciará a geração do ID
    private Long id;

    @Column(nullable = false) // Garante que a coluna não pode ser nula
    private String titulo;

    @Column
    private String descricao;

    @Column(nullable = false)
    private boolean concluida = false; // Valor padrão será 'false'

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @PrePersist // Método que será executado antes de salvar a entidade pela primeira vez
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
    }
}