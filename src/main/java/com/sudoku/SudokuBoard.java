package com.sudoku;

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

    public int getSize() {
        return size;
    }
}
