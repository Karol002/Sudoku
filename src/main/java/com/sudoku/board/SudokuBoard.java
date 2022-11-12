package com.sudoku.board;

import java.util.ArrayList;
import java.util.List;

public class SudokuBoard {
    private List<SudokuRow> gameBoard;
    private final int size;

    public SudokuBoard(int size) {
        gameBoard = new ArrayList<>();
        this.size = size;
        createBoard();
    }

    private void createBoard() {
        for (int i=0; i<size; i++) {
            gameBoard.add(new SudokuRow(size));
        }
    }

    public List<SudokuRow> getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(List<SudokuRow> gameBoard) {
        this.gameBoard = gameBoard;
    }

    public SudokuBoard clone() {
        SudokuBoard clonedBoard = new SudokuBoard(size);
        List<SudokuRow> clonedRowList = new ArrayList<>();

        for (SudokuRow sudokuRow : gameBoard) {
            clonedRowList.add(sudokuRow.clone());
        }

        clonedBoard.setGameBoard(clonedRowList);
        return clonedBoard;
    }
}
