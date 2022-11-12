package com.sudoku.user;

import com.sudoku.board.SingleSudokuElement;
import com.sudoku.board.SudokuRow;
import com.sudoku.user.MoveDTO;

import java.util.List;
import java.util.Scanner;

public class UserService {
    private final Scanner scanner = new Scanner(System.in);

    public void showBoard(List<SudokuRow> gameBoard) {
        System.out.println();
        showHorizontalEdge(gameBoard.size());
        for (SudokuRow row : gameBoard) {
            System.out.print("                ");
            for (SingleSudokuElement sudokuElement : row.getSingleRow()) {
                if (sudokuElement.getValue() == -1) System.out.print("|   ");
                else System.out.print("| " + (sudokuElement.getValue() + 1) + " ");
            }
            System.out.println("|");
            showHorizontalEdge(gameBoard.size());
        }
    }

    private void showHorizontalEdge(int size) {
        System.out.print("                ");
        for (int i=0; i<size; i++) {
            System.out.print(" ---");
        }
        System.out.println();
    }

    public void showEnteringDataError() {
        System.out.println("------------Incorrect data------------");
    }

    public String askForMakeMove() {
        System.out.println();
        System.out.print("If you want to solve sudoku write SUDOKU \n" +
                "If continue entering data write anything else \n" +
                "Enter decision: ");
        return scanner.next();
    }

    public int choseSize() {
        System.out.println();
        System.out.print("Available game board size: \n" +
                "1 - 3x3\n" +
                "2 - 6x6\n" +
                "3 - 9x9\n" +
                "Chose game board size: ");
        return scanner.nextInt();
    }

    public MoveDTO makeMove() {
        System.out.print("Chose row: "); int row = scanner.nextInt() - 1;
        System.out.print("Chose column: "); int column = scanner.nextInt() - 1;
        System.out.print("Chose value: "); int value = scanner.nextInt() - 1;
        return new MoveDTO(row, column, value);
    }

    public void showErrorInformation() {
        System.out.println("Error!");
    }
}