import React, { useState, useEffect } from 'react';
import { getAllTarefas, createTarefa, updateTarefa, deleteTarefa } from './services/api';
import './App.css';

function App() {
  const [tarefas, setTarefas] = useState([]);
  const [tarefaEditando, setTarefaEditando] = useState(null);
  const [titulo, setTitulo] = useState('');
  const [descricao, setDescricao] = useState('');

  // Função para carregar as tarefas da API
  const carregarTarefas = async () => {
    try {
      const response = await getAllTarefas();
      setTarefas(response.data);
    } catch (error) {
      console.error("Erro ao buscar tarefas:", error);
    }
  };

  // useEffect para carregar as tarefas quando o componente é montado
  useEffect(() => {
    carregarTarefas();
  }, []);

  // Limpa o formulário
  const limparFormulario = () => {
    setTitulo('');
    setDescricao('');
    setTarefaEditando(null);
  };

  // Lida com o envio do formulário (criar ou atualizar)
  const handleSave = async (e) => {
    e.preventDefault();
    const novaTarefa = { titulo, descricao, concluida: tarefaEditando ? tarefaEditando.concluida : false };

    try {
      if (tarefaEditando) {
        await updateTarefa(tarefaEditando.id, novaTarefa);
      } else {
        await createTarefa(novaTarefa);
      }
      limparFormulario();
      carregarTarefas(); // Recarrega a lista
    } catch (error) {
      console.error("Erro ao salvar tarefa:", error);
    }
  };
  
  // Prepara o formulário para edição
  const handleEdit = (tarefa) => {
    setTarefaEditando(tarefa);
    setTitulo(tarefa.titulo);
    setDescricao(tarefa.descricao);
  };
  
  // Deleta uma tarefa
  const handleDelete = async (id) => {
    try {
      await deleteTarefa(id);
      carregarTarefas(); // Recarrega a lista
    } catch (error) {
      console.error("Erro ao deletar tarefa:", error);
    }
  };

  // Alterna o status 'concluida' de uma tarefa
  const handleToggleConcluida = async (tarefa) => {
    const tarefaAtualizada = { ...tarefa, concluida: !tarefa.concluida };
    try {
      await updateTarefa(tarefa.id, tarefaAtualizada);
      carregarTarefas(); // Recarrega a lista
    } catch (error) {
      console.error("Erro ao atualizar status da tarefa:", error);
    }
  };

  return (
    <div className="app-container">
      <h1>Lista de Tarefas</h1>
      
      <form onSubmit={handleSave} className="task-form">
        <input 
          type="text"
          placeholder="Título da Tarefa"
          value={titulo}
          onChange={(e) => setTitulo(e.target.value)}
          required
        />
        <input 
          type="text"
          placeholder="Descrição"
          value={descricao}
          onChange={(e) => setDescricao(e.target.value)}
        />
        <button type="submit">{tarefaEditando ? 'Atualizar Tarefa' : 'Adicionar Tarefa'}</button>
      </form>

      <ul className="task-list">
        {tarefas.map((tarefa) => (
          <li key={tarefa.id} className={`task-item ${tarefa.concluida ? 'concluida' : ''}`}>
            <div className="task-content">
              <h3>{tarefa.titulo}</h3>
              <p>{tarefa.descricao}</p>
            </div>
            <div className="task-actions">
              <button className="btn-toggle" onClick={() => handleToggleConcluida(tarefa)}>
                {tarefa.concluida ? 'Reabrir' : 'Concluir'}
              </button>
              <button className="btn-edit" onClick={() => handleEdit(tarefa)}>Editar</button>
              <button className="btn-delete" onClick={() => handleDelete(tarefa.id)}>Excluir</button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;