package models;

import enums.StatusEnum;
import repository.IRepository;

import java.util.List;
import java.util.Scanner;

public class GerenciadorTarefas implements IRepository {

    @Override
    public void create(List<Tarefa> listaTarefas) {
        Scanner leitor = new Scanner(System.in);
        System.out.print("\nTítulo da tarefa\n-> ");
        String titulo = leitor.nextLine();
        for (Tarefa tarefa : listaTarefas) {
            if (tarefa.getTitulo().equals(titulo)) {
                System.out.println("Essa tarefa já existe.");
                return;
            }
        }

        System.out.print("Descrição\n-> ");
        String descricao = leitor.nextLine();

        StatusEnum status = null;
        while (status == null) {
            System.out.print("Status:\n(F)eito\n(E)m andamento\n(A) Fazer\n-> ");
            String opcaoStatus = leitor.nextLine().toUpperCase();

            switch (opcaoStatus) {
                case "F":
                    status = StatusEnum.FEITO;
                    break;
                case "E":
                    status = StatusEnum.EM_ANDAMENTO;
                    break;
                case "A":
                    status = StatusEnum.A_FAZER;
                    break;
                default:
                    System.out.println("Opção de status inválida. Por favor, tente novamente.");
                    break;
            }
        }
        Tarefa tarefa = new Tarefa(titulo, descricao, status);
        listaTarefas.add(tarefa);
        System.out.println("\n=-=- Tarefa adicionada com sucesso! -=-=");
    }

    @Override
    public void read(List<Tarefa> listaTarefas) {
        System.out.println("\n=-=-=-=-=-=-= Suas Tarefas =-=-=-=-=-=-=\n----------------------------------------");
        listaTarefas.stream()
                .forEach(tarefa -> {
                    System.out.println(tarefa.getStatus() + " - " + tarefa.getTitulo() + " | " + tarefa.getDescricao());
                    System.out.println("----------------------------------------");
                });
    }

    @Override
    public void update(Tarefa tarefa) {
        Scanner leitor = new Scanner(System.in);

        System.out.print("Novo título da tarefa\n-> ");
        String tituloNovo = leitor.nextLine();

        System.out.print("Nova descrição da tarefa\n-> ");
        String descricaoNova = leitor.nextLine();

        StatusEnum statusNovo = null;
        while (statusNovo == null) {
            System.out.print("Status:\n(F)eito\n(E)m andamento\n(A) Fazer\n-> ");
            String opcaoStatus = leitor.nextLine().toUpperCase();

            switch (opcaoStatus) {
                case "F":
                    statusNovo = StatusEnum.FEITO;
                    break;
                case "E":
                    statusNovo = StatusEnum.EM_ANDAMENTO;
                    break;
                case "A":
                    statusNovo = StatusEnum.A_FAZER;
                    break;
                default:
                    System.out.println("Opção de status inválida. Por favor, tente novamente.");
                    break;
            }
        }
        tarefa.setTitulo(tituloNovo);
        tarefa.setDescricao(descricaoNova);
        tarefa.setStatus(statusNovo);
    }

    @Override
    public void delete(List<Tarefa> listaTarefas) {
        Scanner leitor = new Scanner(System.in);
        String titulo = leitor.nextLine().toLowerCase();
        boolean encontrado = false;
        for (Tarefa tarefa : listaTarefas) {
            if (tarefa.getTitulo().toLowerCase().equals(titulo)) {
                listaTarefas.remove(tarefa);
                System.out.println("Tarefa excluída com sucesso!");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Tarefa não encontrada.");
        }
    }
}
