package com.sudoku;

import java.util.List;

public class GameBank {
    private final SudokuBoard bank;
    private final int row;
    private final int column;
    private final int value;

    public GameBank(SudokuBoard bank, int row, int column, int value) {
        this.bank = bank;
        this.row = row;
        this.column = column;
        this.value = value;
    }

    public SudokuBoard getBank() {
        return bank;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getValue() {
        return value;
    }
}
