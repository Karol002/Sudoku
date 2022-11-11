package com.sudoku;

public class SingleSudokuElement {
    private final static int EMPTY = -1;
    private final static int POSSIBLE_VALUES_QUANTITY = 9;
    private boolean [] impossibleValues;
    private int value;

    public SingleSudokuElement() {
        value = EMPTY;
        impossibleValues = new boolean[POSSIBLE_VALUES_QUANTITY];
    }

    public boolean[] getImpossibleValues() {
        return impossibleValues;
    }

    public int getPossibleValuesQuantity() {
        int possibleValuesQuantity = 0;
        for (boolean possibleValue : impossibleValues) {
            if (!possibleValue) possibleValuesQuantity++;
        }
        return possibleValuesQuantity;
    }

    public void setImpossibleValue(int value) {
        impossibleValues[value] = true;
    }

    public int getValue() {
        return value;
    }

    public int getSinglePossibleValue() {
        int singlePossibleValue = 0;
        for (boolean possibleValue : impossibleValues) {
            if (!possibleValue) return singlePossibleValue;
            singlePossibleValue++;
        }
        return 999;
    }

    public void setValueBoard(int value) {
        this.value = value;
        impossibleValues[value] = true;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public void setImpossibleValues(boolean[] impossibleValues) {
        this.impossibleValues = impossibleValues;
    }

    public SingleSudokuElement clone() {
        SingleSudokuElement clonedElement = new SingleSudokuElement();
        clonedElement.setImpossibleValues(this.impossibleValues.clone());
        clonedElement.setValue(this.value);
        return clonedElement;
    }
}
