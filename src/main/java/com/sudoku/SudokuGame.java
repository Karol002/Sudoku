package com.sudoku;

public class SudokuGame {
    private static final int BLOCK_SIZE = 3;
    private static final UserService userService = new UserService();
    private static final int POSSIBLE_VALUE_SIZE = 9;
    private SudokuBoard sudokuBoard = new SudokuBoard(9);
    private SingleSudokuElement [][] playBoard = new SingleSudokuElement[9][9];
    private int size = 9;

    public boolean resolveSudoku() {
        createPlayBoard();
        startSudokuAlgorithm();
        return false;
    }

    private void startSudokuAlgorithm() {
        boolean action = true;

        while (isFull()) {
            action = false;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (playBoard[i][j].getValue() == -1) {
                        for (int k = 0; k < POSSIBLE_VALUE_SIZE; k++) {
                            if (isWrittenInOtherPlace(i, j, k)) action = true;
                            if (isNotWrittenAndNotPossible(i, j, k)) action = true;
                        }
                    }
                }
            }
            if (!action) choseValueForEmptyElement();
        }
    }

    private boolean choseValueForEmptyElement() {
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                if (playBoard[i][j].getValue() == -1) {
                    for (int k=0; k<playBoard[i][j].getPossibleValues().length; k++) {
                        if (!isWrittenInBlock(i, j, k) && !isWrittenInRowOrColumn(i, j, k)) {
                            playBoard[i][j].setValueBoard(k);
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isNotWrittenAndNotPossible(int rowNumber, int columnNumber, int value) {
        boolean action = false;

        if (!isWrittenInRowOrColumn(rowNumber, columnNumber, value) &&
                !isWrittenInBlock(rowNumber, columnNumber, value)) {
            if (isNotPossibleInBlock(rowNumber, columnNumber, value)
                    && isNotPossibleInRowOrColumn(rowNumber, columnNumber, value)) {
                playBoard[rowNumber][columnNumber].setValueBoard(value);
                action = true;
            }
        }
        return action;
    }

    private boolean isNotPossibleInBlock(int rowNumber, int columnNumber, int value) {
        int rowBlock = rowNumber - rowNumber % BLOCK_SIZE;
        int columnBlock = columnNumber - columnNumber  % BLOCK_SIZE;

        for (int i=rowBlock; i<rowBlock + BLOCK_SIZE; i++) {
            for (int j=columnBlock; j<columnBlock + BLOCK_SIZE; j++) {
                if (i != rowNumber || j != columnNumber) {
                    if (!playBoard[i][j].getPossibleValues()[value] && playBoard[i][j].getValue() != -1) return false;
                }
            }
        }
        return true;
    }

    private boolean isNotPossibleInRowOrColumn(int rowNumber, int columnNumber, int value) {
        for (int i=0; i<size; i++) {
            if (!playBoard[i][columnNumber].getPossibleValues()[value] && i != rowNumber &&
                    playBoard[i][columnNumber].getValue() != -1) return false;
            if (!playBoard[rowNumber][i].getPossibleValues()[value] && i != columnNumber &&
                    playBoard[rowNumber][i].getValue() != -1) {
                return false;
            }
        }
        return true;
    }


    private boolean isWrittenInOtherPlace(int rowNumber, int columnNumber, int value) {
        boolean action = false;

        if ((isWrittenInRowOrColumn(rowNumber, columnNumber, value) || isWrittenInBlock(rowNumber, columnNumber, value))
                && !playBoard[rowNumber][columnNumber].getPossibleValues()[value]){
            playBoard[rowNumber][columnNumber].setPossibleValues(value);

            if (playBoard[rowNumber][columnNumber].getPossibleValuesQuantity() == 1) {
                for (int j=0; j<POSSIBLE_VALUE_SIZE; j++) {
                    if (!isWrittenInRowOrColumn(rowNumber, columnNumber, j) && !isWrittenInBlock(rowNumber, columnNumber, j) &&
                            !playBoard[rowNumber][columnNumber].getPossibleValues()[j]) {
                        playBoard[rowNumber][columnNumber].setValueBoard(j);
                    }
                }
            }
            action = true;
        }
        return action;
    }

    private boolean isWrittenInBlock(int rowNumber, int columnNumber, int value) {
        int rowBlock = rowNumber - rowNumber % BLOCK_SIZE;
        int columnBlock = columnNumber - columnNumber % BLOCK_SIZE;

        for (int i=rowBlock; i<rowBlock + BLOCK_SIZE; i++) {
            for (int j=columnBlock; j<columnBlock + BLOCK_SIZE; j++) {
                if (playBoard[i][j].getValue() == value) return true;
            }
        }
        return false;
    }

    private boolean isWrittenInRowOrColumn(int rowNumber, int columnNumber, int value) {
        for (int i=0; i<size; i++) {
            if (playBoard[i][columnNumber].getValue() == value ||
                    playBoard[rowNumber][i].getValue() == value) return true;
        }
        return false;
    }

    private void createPlayBoard() {
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                playBoard[i][j] = sudokuBoard.getGameBoard().get(i).getSingleRow().get(j);
            }
        }
    }

    private boolean isFull() {
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                if (playBoard[i][j].getValue() == -1) return true;
            }
        }
        return false;
    }

    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }
}














