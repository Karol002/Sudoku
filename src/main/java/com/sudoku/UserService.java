package com.sudoku;

import java.util.List;
import java.util.Scanner;

public class UserService {
    private final Scanner scanner = new Scanner(System.in);

    public void showBoard(List<SudokuRow> gameBoard) {
        System.out.println();
        showHorizontalEdge(gameBoard.size());
        for (SudokuRow row : gameBoard) {
            for (SingleSudokuElement sudokuElement : row.getSingleRow()) {
                if (sudokuElement.getValue() == -1) System.out.print("|   ");
                else System.out.print("| " + (sudokuElement.getValue() + 1) + " ");
            }
            System.out.println("|");
            showHorizontalEdge(gameBoard.size());
        }
    }

    private void showHorizontalEdge(int size) {
        for (int i=0; i<size; i++) {
            System.out.print(" ---");
        }
        System.out.println();
    }

    public int choseSize() {
        System.out.print("Chose game board size: ");
        return scanner.nextInt();
    }

    public MoveDTO makeMove() {
        System.out.print("Chose row: "); int row = scanner.nextInt();
        System.out.print("Chose column: "); int column = scanner.nextInt();
        System.out.print("Chose value: "); int value = scanner.nextInt();
        return new MoveDTO(row, column, value);
    }

    public void showErrorInformation() {
        System.out.println("Error!!!");
    }
}