package com.sudoku;

public class SingleSudokuElement {
    private final static int EMPTY = -1;
    private final static int POSSIBLE_VALUES_QUANTITY = 9;
    private boolean [] possibleValues;
    private int value;

    public SingleSudokuElement() {
        value = EMPTY;
        possibleValues = new boolean[POSSIBLE_VALUES_QUANTITY];
    }

    public boolean[] getPossibleValues() {
        return possibleValues;
    }

    public int getPossibleValuesQuantity() {
        int possibleValuesQuantity = 0;
        for (boolean possibleValue : possibleValues) {
            if (!possibleValue) possibleValuesQuantity++;
        }
        return possibleValuesQuantity;
    }

    public void setPossibleValues(int value) {
        possibleValues[value] = true;
    }

    public int getValue() {
        return value;
    }

    public void setValueBoard(int value) {
        this.value = value;
        possibleValues[value] = true;
    }
}
