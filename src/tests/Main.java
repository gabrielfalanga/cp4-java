package tests;

import enums.StatusEnum;
import models.GerenciadorTarefas;
import models.Tarefa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        //Nome: Gabriel Falanga e Guilherme Romanholi
        //      RM:555061         RM:557462

        ArrayList<Tarefa> listaTarefas = new ArrayList<>();
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        Scanner leitor = new Scanner(System.in);
        Scanner leitorNum = new Scanner(System.in);

        while (true) {
            System.out.print(
                    """
                   
                   |=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=|
                   |     M E N U - D E - T A R E F A S     |
                   |=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=|
                   | 1 - Adicionar                         |
                   | 2 - Listar                            |
                   | 3 - Pesquisar por título              |
                   | 4 - Filtrar por status                |
                   | 5 - Alterar                           |
                   | 6 - Excluir                           |
                   | 7 - Marcar Tudo como feito            |
                   | 8 - Ordenar em ordem alfabética       |
                   | 0 - Sair                              |
                   |=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=|
                   Digite a operação desejada:\s"""
            );


            String opcao = leitorNum.nextLine();

            if (opcao.equals("0")) {
                System.out.println("Volte Sempre!");
                break;

            } else if (opcao.equals("1")) {
                gerenciador.create(listaTarefas);
            } else if (opcao.equals("2")) {
                gerenciador.read(listaTarefas);
            } else if (opcao.equals("3")) {
                System.out.println("Digite o título da tarefa\n-> ");
                String titulo = leitor.nextLine().toLowerCase();
                boolean encontrado = false;
                for (Tarefa tarefa : listaTarefas) {
                    if (tarefa.getTitulo().toLowerCase().equals(titulo)) {
                        System.out.println("---------------------------------------");
                        System.out.println(tarefa.getStatus() + " - " + tarefa.getTitulo() + " | " + tarefa.getDescricao());
                        System.out.println("---------------------------------------");
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    System.out.println("Tarefa não encontrada.");
                }

            } else if (opcao.equals("4")) {
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
                StatusEnum finalStatus = status;
                listaTarefas.stream()
                            .filter(tarefa -> tarefa.getStatus() == finalStatus)
                            .forEach(System.out::println);
                boolean encontrou = false;
                for (Tarefa tarefa : listaTarefas) {
                    if (tarefa.getStatus() == status) {
                        encontrou = true;
                        break;
                    }
                }
                if (!encontrou) {
                    System.out.println("Nenhuma tarefa encontrada com o status especificado.");
                }

            } else if (opcao.equals("5")) {
                System.out.println("Digite o título da tarefa que deseja alterar\n-> ");
                String titulo = leitor.nextLine().toLowerCase();
                boolean encontrado = false;
                for (Tarefa tarefa : listaTarefas) {
                    if (tarefa.getTitulo().toLowerCase().equals(titulo)) {
                        System.out.println("---------------------------------------");
                        System.out.println(tarefa.getStatus() + " - " + tarefa.getTitulo() + " | " + tarefa.getDescricao());
                        System.out.println("---------------------------------------");
                        gerenciador.update(tarefa);
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    System.out.println("Tarefa não encontrada.");
                }

            } else if (opcao.equals("6")) {
                System.out.println("Digite o título da tarefa que deseja excluir\n-> ");
                gerenciador.delete(listaTarefas);

            } else if (opcao.equals("7")) {
                System.out.println("\n=-=-=-=-=-=-= Suas Tarefas =-=-=-=-=-=-=\n");
                listaTarefas.stream()
                        .map(tarefa -> new Tarefa(tarefa.getTitulo(), tarefa.getDescricao(), StatusEnum.FEITO))
                        .forEach(tarefa -> {
                            System.out.println("----------------------------------------");
                            System.out.println(tarefa.getStatus() + " - " + tarefa.getTitulo() + " | " + tarefa.getDescricao());
                            System.out.println("----------------------------------------");
                        });

            } else if (opcao.equals("8")){
                listaTarefas = (ArrayList<Tarefa>) listaTarefas.stream()
                                                    .sorted(Comparator.comparing(Tarefa::getTitulo))
                                                    .collect(Collectors.toList());
                System.out.println("\nLista organizada em ordem alfabética!");
                System.out.println("\n=-=-=-=-=-=-= Suas Tarefas =-=-=-=-=-=-=\n----------------------------------------");
                listaTarefas.stream()
                            .forEach(tarefa -> {
                            System.out.println(tarefa.getStatus() + " - " + tarefa.getTitulo() + " | " + tarefa.getDescricao());
                            System.out.println("----------------------------------------");
                        });
            } else {
                System.out.println("Por favor, digite uma opção válida.");
            }
        }
    }
}
