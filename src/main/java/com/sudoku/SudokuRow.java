package com.sudoku;

import java.util.ArrayList;
import java.util.List;

public class SudokuRow {
    private List<SingleSudokuElement> singleRow;
    private final int size;

    public SudokuRow(int size) {
        singleRow = new ArrayList<>();
        this.size = size;
        createRow();
    }

    public void createRow() {
        for (int i=0; i<size; i++) {
            singleRow.add(new SingleSudokuElement());
        }
    }

    public List<SingleSudokuElement> getSingleRow() {
        return singleRow;
    }
}
