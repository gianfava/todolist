# Todolist API

Uma API RESTful simples para gerenciar uma lista de tarefas, construída com Spring Boot.

## Visão Geral

Este projeto é uma API de lista de tarefas que permite aos usuários criar, ler, atualizar e excluir tarefas. É um exemplo de um aplicativo da web moderno construído com Java e o ecossistema Spring.

## Tecnologias Utilizadas

* **Java 17**: A linguagem de programação principal utilizada.
* **Spring Boot 3.5.6**: Framework para criar aplicativos baseados em Spring autônomos e de nível de produção.
* **Spring Data JPA**: Para simplificar a camada de acesso a dados.
* **PostgreSQL**: O banco de dados relacional usado para persistir os dados da tarefa.
* **Maven**: Ferramenta de automação de compilação e gerenciamento de dependências.
* **Lombok**: Para reduzir o código boilerplate em classes de modelo.
* **JUnit 5 & Mockito**: Para testes de unidade e integração.

## Estrutura do Projeto

O projeto segue uma estrutura de aplicativo Spring Boot padrão:

* `src/main/java`: Contém o código-fonte principal.
    * `br.com.todolist.controller`: Controladores REST para expor os endpoints da API.
    * `br.com.todolist.model`: Classes de entidade JPA que representam os modelos de dados.
    * `br.com.todolist.repository`: Interfaces do Spring Data JPA para acesso ao banco de dados.
    * `br.com.todolist.service`: Lógica de negócios para o aplicativo.
* `src/main/resources`: Arquivos de configuração e recursos.
    * `application.properties`: Configurações do aplicativo, como detalhes de conexão com o banco de dados.
* `src/test/java`: Testes de unidade e integração.
* `pom.xml`: Arquivo de configuração do projeto Maven, definindo dependências e configurações de compilação.

## Endpoints da API

A API expõe os seguintes endpoints REST para gerenciar tarefas:

* **`POST /api/tarefas`**: Cria uma nova tarefa.
* **`GET /api/tarefas`**: Lista todas as tarefas.
* **`GET /api/tarefas/{id}`**: Obtém uma tarefa específica por seu ID.
* **`PUT /api/tarefas/{id}`**: Atualiza uma tarefa existente.
* **`DELETE /api/tarefas/{id}`**: Exclui uma tarefa.

## Como Executar o Projeto

### Pré-requisitos

* JDK 17 ou superior
* Maven 3.9 ou superior
* PostgreSQL em execução na porta 5432

### Configuração

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/gianfava/todolist.git](https://github.com/gianfava/todolist.git)
    cd todolist
    ```

2.  **Configure o banco de dados:**
    * Crie um banco de dados PostgreSQL chamado `tarefas`.
    * Crie um esquema chamado `tarefas`.
    * Execute o script `schema.sql` para criar a tabela `tarefas`.
    * Atualize o arquivo `src/main/resources/application.properties` com seu nome de usuário e senha do PostgreSQL, se forem diferentes de "postgres".

3.  **Execute o aplicativo:**
    Use o Maven Wrapper para compilar e executar o aplicativo:
    ```bash
    ./mvnw spring-boot:run
    ```
    O aplicativo estará em execução em `http://localhost:8080`.

### Testes

Para executar os testes de unidade e integração, use o seguinte comando:
```bash
./mvnw test
