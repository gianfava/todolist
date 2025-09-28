import axios from 'axios';

// Cria uma instância do Axios com a URL base da sua API
const apiClient = axios.create({
    baseURL: 'http://localhost:8080/api',
    headers: {
        'Content-Type': 'application/json'
    }
});

// Funções para cada endpoint da API de tarefas
export const getAllTarefas = () => apiClient.get('/tarefas');

export const getTarefaById = (id) => apiClient.get(`/tarefas/${id}`);

export const createTarefa = (tarefa) => apiClient.post('/tarefas', tarefa);

export const updateTarefa = (id, tarefa) => apiClient.put(`/tarefas/${id}`, tarefa);

export const deleteTarefa = (id) => apiClient.delete(`/tarefas/${id}`);