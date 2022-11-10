package com.sudoku;

public class Main {

    public static void main(String [] args) {
        UserService userService = new UserService();
        SudokuGame sudokuGame = new SudokuGame();

        sudokuGame.getSudokuBoard().getGameBoard().get(0).getSingleRow().get(1).setValueBoard(1);
        sudokuGame.getSudokuBoard().getGameBoard().get(1).getSingleRow().get(0).setValueBoard(7);
        sudokuGame.getSudokuBoard().getGameBoard().get(2).getSingleRow().get(1).setValueBoard(2);

        sudokuGame.getSudokuBoard().getGameBoard().get(0).getSingleRow().get(3).setValueBoard(4);
        sudokuGame.getSudokuBoard().getGameBoard().get(0).getSingleRow().get(5).setValueBoard(0);
        sudokuGame.getSudokuBoard().getGameBoard().get(1).getSingleRow().get(3).setValueBoard(1);
        sudokuGame.getSudokuBoard().getGameBoard().get(1).getSingleRow().get(5).setValueBoard(2);
        sudokuGame.getSudokuBoard().getGameBoard().get(2).getSingleRow().get(4).setValueBoard(5);

        sudokuGame.getSudokuBoard().getGameBoard().get(0).getSingleRow().get(7).setValueBoard(8);
        sudokuGame.getSudokuBoard().getGameBoard().get(1).getSingleRow().get(8).setValueBoard(5);
        sudokuGame.getSudokuBoard().getGameBoard().get(2).getSingleRow().get(7).setValueBoard(6);

        sudokuGame.getSudokuBoard().getGameBoard().get(3).getSingleRow().get(2).setValueBoard(0);
        sudokuGame.getSudokuBoard().getGameBoard().get(4).getSingleRow().get(0).setValueBoard(4);
        sudokuGame.getSudokuBoard().getGameBoard().get(4).getSingleRow().get(1).setValueBoard(3);
        sudokuGame.getSudokuBoard().getGameBoard().get(5).getSingleRow().get(2).setValueBoard(1);

        /*sudokuGame.getSudokuBoard().getGameBoard().get(3).getSingleRow().get(6).setValueBoard(5);
        sudokuGame.getSudokuBoard().getGameBoard().get(4).getSingleRow().get(7).setValueBoard(0);
        sudokuGame.getSudokuBoard().getGameBoard().get(4).getSingleRow().get(8).setValueBoard(8);
        sudokuGame.getSudokuBoard().getGameBoard().get(5).getSingleRow().get(6).setValueBoard(6);

        sudokuGame.getSudokuBoard().getGameBoard().get(6).getSingleRow().get(1).setValueBoard(8);
        sudokuGame.getSudokuBoard().getGameBoard().get(7).getSingleRow().get(0).setValueBoard(1);
        sudokuGame.getSudokuBoard().getGameBoard().get(8).getSingleRow().get(1).setValueBoard(0);

        sudokuGame.getSudokuBoard().getGameBoard().get(6).getSingleRow().get(4).setValueBoard(2);
        sudokuGame.getSudokuBoard().getGameBoard().get(7).getSingleRow().get(3).setValueBoard(7);
        sudokuGame.getSudokuBoard().getGameBoard().get(7).getSingleRow().get(5).setValueBoard(3);
        sudokuGame.getSudokuBoard().getGameBoard().get(8).getSingleRow().get(3).setValueBoard(8);
        sudokuGame.getSudokuBoard().getGameBoard().get(8).getSingleRow().get(5).setValueBoard(6);


        sudokuGame.getSudokuBoard().getGameBoard().get(6).getSingleRow().get(7).setValueBoard(7);
        sudokuGame.getSudokuBoard().getGameBoard().get(7).getSingleRow().get(8).setValueBoard(6);
        sudokuGame.getSudokuBoard().getGameBoard().get(8).getSingleRow().get(7).setValueBoard(5);*/


        userService.showBoard(sudokuGame.getSudokuBoard().getGameBoard());
        sudokuGame.resolveSudoku();
        userService.showBoard(sudokuGame.getSudokuBoard().getGameBoard());
    }
}
