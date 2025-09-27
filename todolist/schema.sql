CREATE DATABASE tarefas;

CREATE SCHEMA tarefas;

CREATE TABLE tarefas.tarefas (
    id bigserial NOT NULL PRIMARY KEY,
    titulo character varying(150) NOT NULL, 
    descricao character varying(255),
    concluida boolean NOT NULL DEFAULT false,  
    data_criacao timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,  
    data_conclusao timestamp,  
    status character varying(50),  
    observacoes text  
);