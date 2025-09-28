# Todolist Full Stack

Uma aplicação completa de lista de tarefas (To-Do List) com um backend robusto em Spring Boot e um frontend interativo em React.


## Alunos:

- FELIPE FERREIRA REZENDE
- GIAN CARLO FAVA

## Visão Geral

Este projeto é uma aplicação web que permite aos usuários gerenciar uma lista de tarefas. O backend é uma API RESTful que lida com as operações de negócio e a persistência de dados, enquanto o frontend oferece uma interface de usuário para interagir com a API.

## Funcionalidades

- **Criar, Ler, Atualizar e Deletar (CRUD)** tarefas.
- Marcar tarefas como **concluídas** ou **pendentes**.
- Interface de usuário reativa construída com React.

## Tecnologias Utilizadas

### Backend
- **Java 17**
- **Spring Boot 3.5.6**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**

### Frontend
- **React**
- **Axios** (para chamadas HTTP)
- **CSS** para estilização

## Como Executar o Projeto

### Pré-requisitos
- Java JDK 17 ou superior
- Maven
- Node.js e npm
- PostgreSQL rodando na porta 5432

### 1. Configuração do Backend (API)

1.  **Clone o repositório** e navegue até a pasta do backend (`todolist`).

2.  **Crie o Banco de Dados:**
    - Conecte-se ao seu PostgreSQL (usando DBeaver, pgAdmin, ou psql).
    - Execute o seguinte comando para criar o banco de dados que a aplicação usará:
      ```sql
      CREATE DATABASE tarefas;
      ```
    - **Você não precisa criar a tabela manualmente.** A aplicação fará isso por você.

3.  **Configure a Conexão com o Banco de Dados:**
    - Abra o arquivo `src/main/resources/application.properties`.
    - **Adapte as configurações para o seu ambiente.** O arquivo deve se parecer com o exemplo abaixo. **É muito importante que `spring.datasource.username` e `spring.datasource.password` correspondam às suas credenciais do PostgreSQL.**

      ```properties
      # Nome da aplicação (opcional)
      spring.application.name=tarefas

      # URL de conexão com o banco de dados. Garanta que o nome do banco seja 'tarefas'.
      spring.datasource.url=jdbc:postgresql://localhost:5432/tarefas

      # Suas credenciais do PostgreSQL. ATUALIZE CONFORME NECESSÁRIO.
      spring.datasource.username=postgres
      spring.datasource.password=postgres # <-- Altere para a sua senha!

      # Driver do PostgreSQL
      spring.datasource.driver-class-name=org.postgresql.Driver
      
      # Schema tarefas, se for usar public, delete ou comente a linha abaixo
      spring.jpa.properties.hibernate.default_schema=tarefas

      # Configuração do Hibernate para criar/atualizar a tabela automaticamente
      # Esta linha é crucial! Ela cria a tabela 'tarefas' na primeira vez que a aplicação roda.
      spring.jpa.hibernate.ddl-auto=update
      ```

4.  **Habilite o CORS**:
    - No arquivo `src/main/java/br/com/todolist/controller/TarefaController.java`, adicione a anotação `@CrossOrigin(origins = "http://localhost:3000")` acima da classe `TarefaController` para permitir a comunicação com o frontend.

5.  **Execute o backend:**

    **Opção A: Via Linha de Comando**
    - No terminal, dentro da pasta `todolist`, execute:
      ```bash
      ./mvnw spring-boot:run
      ```
    
    **Opção B: Via IDE (Eclipse, STS, IntelliJ)**
    - Importe o projeto `todolist` como um projeto Maven na sua IDE.
    - Encontre o arquivo `TodolistApplication.java` no caminho `src/main/java/br/com/todolist/`.
    - Clique com o botão direito no arquivo e selecione **"Run As" > "Java Application"** (ou a opção equivalente na sua IDE).

    A API estará rodando em `http://localhost:8080`.

### 2. Configuração do Frontend (React App)

1.  **Navegue até a pasta do frontend** (`todolist-frontend`).
2.  **Instale as dependências:**
    ```bash
    npm install
    ```
3.  **Execute o frontend:**
    ```bash
    npm start
    ```
    A aplicação React abrirá em `http://localhost:3000`.

### Endpoints da API

A API expõe os seguintes endpoints no prefixo `/api/tarefas`:

| Método | URL                | Descrição                      |
|--------|--------------------|--------------------------------|
| `POST` | `/api/tarefas`     | Cria uma nova tarefa.          |
| `GET`  | `/api/tarefas`     | Lista todas as tarefas.        |
| `GET`  | `/api/tarefas/{id}`| Busca uma tarefa pelo ID.      |
| `PUT`  | `/api/tarefas/{id}`| Atualiza uma tarefa existente. |
| `DELETE`| `/api/tarefas/{id}`| Deleta uma tarefa.             |


## Demonstração

### Backend (Teste no Postman)

<img src="https://github.com/gianfava/todolist/blob/main/todolist/printscreens/Slide1.PNG" alt="Teste da API no Postman" width="50%">

### Frontend (Aplicação React)

<img src="https://github.com/gianfava/todolist/blob/main/todolist/printscreens/Slide2.PNG" alt="Teste da API no Postman" width="50%">
