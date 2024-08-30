package repository;

import models.Tarefa;

import java.util.List;

public interface IRepository {
    void create(List<Tarefa> listaTarefas);
    void read(List<Tarefa> listaTarefas);
    void update(Tarefa tarefa);
    void delete(List<Tarefa> listaTarefas);
}
