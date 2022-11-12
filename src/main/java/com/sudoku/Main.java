package com.sudoku;

import com.sudoku.algortihm.SudokuGame;
import com.sudoku.user.UserService;

public class Main {

    public static void main(String [] args) {
        UserService userService = new UserService();
        SudokuGame sudokuGame = new SudokuGame();

        sudokuGame.resolveSudoku();
        userService.showBoard(sudokuGame.getSudokuBoard().getGameBoard());
    }
}
