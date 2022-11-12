package com.sudoku.board;

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

    public void setSingleRow(List<SingleSudokuElement> singleRow) {
        this.singleRow = singleRow;
    }

    public SudokuRow clone() {
        SudokuRow clonedRow = new SudokuRow(size);
        List<SingleSudokuElement> clonedSingleRow = new ArrayList<>();

        for (SingleSudokuElement singleElement : singleRow) {
            clonedSingleRow.add(singleElement.clone());
        }

        clonedRow.setSingleRow(clonedSingleRow);
        return clonedRow;
    }
}
