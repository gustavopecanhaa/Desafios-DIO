package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SudokuBoard board = new SudokuBoard(args);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            board.display();
            if (board.isSolved()) {
                System.out.println("Parabéns! Você resolveu o Sudoku!");
                break;
            }
            System.out.println("Digite linha (1-9), coluna (1-9) e valor (1-9) separados por espaço ou 'sair':");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("sair")) break;
            String[] parts = input.split(" ");
            if (parts.length != 3) {
                System.out.println("Entrada inválida!");
                continue;
            }
            try {
                int row = Integer.parseInt(parts[0]) - 1;
                int col = Integer.parseInt(parts[1]) - 1;
                int value = Integer.parseInt(parts[2]);
                if (board.setCellValue(row, col, value)) {
                    System.out.println("Movimento válido!");
                } else {
                    System.out.println("Movimento inválido!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida!");
            }
        }
        scanner.close();
    }
}