package org.example;

import java.util.HashSet;
import java.util.Set;

public class SudokuBoard {
    public static final int SIZE = 9;
    private final Cell[][] grid;

    public SudokuBoard(String[] initialCells) {
        grid = new Cell[SIZE][SIZE];
        // Inicializa células com valores padrão
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                grid[row][col] = new Cell(0, false);
            }
        }
        // Processa células iniciais
        for (String cellStr : initialCells) {
            String[] parts = cellStr.split(";");
            if (parts.length != 2) continue;
            String[] coords = parts[0].split(",");
            if (coords.length != 2) continue;
            int x = Integer.parseInt(coords[0]);
            int y = Integer.parseInt(coords[1]);
            String[] valueFixed = parts[1].split(",");
            int value = Integer.parseInt(valueFixed[0]);
            boolean isFixed = Boolean.parseBoolean(valueFixed[1]);
            if (x >= 0 && x < SIZE && y >= 0 && y < SIZE) {
                grid[x][y] = new Cell(value, isFixed);
            }
        }
    }

    public boolean setCellValue(int row, int col, int value) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) return false;
        Cell cell = grid[row][col];
        if (cell.isFixed() || value < 1 || value > 9 || !isValidMove(row, col, value)) return false;
        cell.setValue(value);
        return true;
    }

    private boolean isValidMove(int row, int col, int value) {
        // Verifica linha e coluna
        for (int i = 0; i < SIZE; i++) {
            if (i != col && grid[row][i].getValue() == value) return false;
            if (i != row && grid[i][col].getValue() == value) return false;
        }
        // Verifica subgrade 3x3
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (r != row || c != col) {
                    if (grid[r][c].getValue() == value) return false;
                }
            }
        }
        return true;
    }

    public void display() {
        for (int row = 0; row < SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("+-------+-------+-------+");
            }
            for (int col = 0; col < SIZE; col++) {
                if (col % 3 == 0) System.out.print("| ");
                Cell cell = grid[row][col];
                String val = cell.getValue() == 0 ? "." : String.valueOf(cell.getValue());
                System.out.print((cell.isFixed() ? "\033[32m" + val + "\033[0m" : val) + " ");
            }
            System.out.println("|");
        }
        System.out.println("+-------+-------+-------+");
    }

    public boolean isSolved() {
        // Verifica todas as linhas, colunas e subgrades
        for (int i = 0; i < SIZE; i++) {
            Set<Integer> rowSet = new HashSet<>();
            Set<Integer> colSet = new HashSet<>();
            Set<Integer> subgridSet = new HashSet<>();
            int startRow = (i / 3) * 3;
            int startCol = (i % 3) * 3;
            for (int j = 0; j < SIZE; j++) {
                // Linhas
                if (grid[i][j].getValue() == 0 || !rowSet.add(grid[i][j].getValue())) return false;
                // Colunas
                if (grid[j][i].getValue() == 0 || !colSet.add(grid[j][i].getValue())) return false;
                // Subgrades
                int r = startRow + j / 3;
                int c = startCol + j % 3;
                if (grid[r][c].getValue() == 0 || !subgridSet.add(grid[r][c].getValue())) return false;
            }
        }
        return true;
    }
}