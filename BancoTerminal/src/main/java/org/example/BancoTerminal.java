package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BancoTerminal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double saldo = 0.0;

        System.out.println("Bem-vindo ao Banco Terminal!");
        System.out.println("----------------------------");

        // Configuração inicial do saldo
        while(true) {
            try {
                System.out.print("Digite seu saldo inicial: R$ ");
                saldo = scanner.nextDouble();
                if(saldo < 0) {
                    System.out.println("Erro: O saldo não pode ser negativo!\n");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite um valor numérico válido!\n");
                scanner.nextLine(); // Limpa o buffer
            }
        }

        // Menu de operações
        while(true) {
            System.out.println("\nSaldo atual: R$ " + String.format("%.2f", saldo));
            System.out.println("\nOpções:");
            System.out.println("1 - Sacar");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = scanner.nextInt();

                switch(opcao) {
                    case 1: // Saque
                        System.out.print("\nDigite o valor para saque: R$ ");
                        double saque = scanner.nextDouble();

                        if(saque <= 0) {
                            System.out.println("Erro: Valor de saque inválido!");
                            break;
                        }

                        if(saldo >= saque) {
                            saldo -= saque;
                            System.out.println("Saque realizado com sucesso!");
                        } else {
                            System.out.println("Saldo insuficiente para esta operação!");
                        }
                        break;

                    case 2: // Depósito
                        System.out.print("\nDigite o valor para depósito: R$ ");
                        double deposito = scanner.nextDouble();

                        if(deposito <= 0) {
                            System.out.println("Erro: Valor de depósito inválido!");
                            break;
                        }

                        saldo += deposito;
                        System.out.println("Depósito realizado com sucesso!");
                        break;

                    case 3: // Sair
                        System.out.println("\nObrigado por usar nossos serviços!");
                        System.out.println("Saldo final: R$ " + String.format("%.2f", saldo));
                        scanner.close();
                        return;

                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida! Digite apenas números.\n");
                scanner.nextLine(); // Limpa o buffer
            }
        }
    }
}