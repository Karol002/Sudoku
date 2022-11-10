package com.sudoku;

public class SudokuGame {
    private static final int BLOCK_SIZE = 3;
    private static final UserService userService = new UserService();
    private static final int POSSIBLE_VALUE_SIZE = 9;
    private final SudokuBoard sudokuBoard = new SudokuBoard(9);
    private final SingleSudokuElement [][] playBoard = new SingleSudokuElement[9][9];
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
                            if (startAlgorithmForRow(i, j)) action = true;
                            if (startAlgorithmForColumn(i, j)) action = true;
                            if (startAlgorithmForBlock(i, j)) action = true;
                    }
                }
            }
            if (!action) choseValueForEmptyElement();
        }
    }

    private boolean startAlgorithmForRow(int row, int column) {
        boolean action = false;

        for (int i=0; i<POSSIBLE_VALUE_SIZE; i++) {
            if (isWrittenInRow(row, i) && !playBoard[row][column].getPossibleValues()[i]) {
                playBoard[row][column].setPossibleValues(i);
                action = true;
            }
            if (playBoard[row][column].getPossibleValuesQuantity() == 1
                    && playBoard[row][column].getValue() == -1) {
                playBoard[row][column].setValueBoard(playBoard[row][column].getSinglePossibleValue());
                action = true;
            }
            if (!isWrittenInRow(row, i) && !isPossibleInRow(row, column, i)) {
                playBoard[row][column].setValueBoard(i);
                action = true;
            }
        }
        return action;
    }

    private boolean isWrittenInRow(int row, int value) {
        for (int i=0; i<size; i++) {
            if (playBoard[row][i].getValue() == value) return true;
        }
        return false;
    }

    private boolean isPossibleInRow(int row, int column, int value) {
        for (int i=0; i<size; i++) {
            if (i != column && !playBoard[row][i].getPossibleValues()[value]
                    && playBoard[row][i].getValue() == -1) return true;
        }
        return false;
    }

    private boolean startAlgorithmForColumn(int row, int column) {
        boolean action = false;

        for (int i=0; i<POSSIBLE_VALUE_SIZE; i++) {
            if (isWrittenInColumn(column, i) && !playBoard[row][column].getPossibleValues()[i]) {
                playBoard[row][column].setPossibleValues(i);
                action = true;
            }
            if (playBoard[row][column].getPossibleValuesQuantity() == 1
                    && playBoard[row][column].getValue() == -1) {
                playBoard[row][column].setValueBoard(playBoard[row][column].getSinglePossibleValue());
                action = true;
            }
            if (!isWrittenInColumn(column, i) && !isPossibleInColumn(row, column, i)) {
                playBoard[row][column].setValueBoard(i);
                action = true;
            }
        }
        return action;
    }

    private boolean isWrittenInColumn(int column, int value) {
        for (int i=0; i<size; i++) {
            if (playBoard[i][column].getValue() == value) return true;
        }
        return false;
    }

    private boolean isPossibleInColumn(int row, int column, int value) {
        for (int i=0; i<size; i++) {
            if (i != row && !playBoard[i][column].getPossibleValues()[value]
                    && playBoard[i][column].getValue() == -1) return true;
        }
        return false;
    }

    private boolean startAlgorithmForBlock(int row, int column) {
        boolean action = false;

        for (int i=0; i<POSSIBLE_VALUE_SIZE; i++) {
            if (isWrittenInBlock(row, column, i) && !playBoard[row][column].getPossibleValues()[i]) {
                playBoard[row][column].setPossibleValues(i);
                action = true;
            }
            if (playBoard[row][column].getPossibleValuesQuantity() == 1
                    && playBoard[row][column].getValue() == -1) {
                playBoard[row][column].setValueBoard(playBoard[row][column].getSinglePossibleValue());
                action = true;
            }
            if (!isWrittenInBlock(row, column, i) && !isPossibleInBlock(row, column, i)) {
                playBoard[row][column].setValueBoard(i);
                action = true;
            }
        }
        return action;
    }

    private boolean isPossibleInBlock(int row, int column, int value) {
        int rowBlock = row - row % BLOCK_SIZE;
        int columnBlock = column - column  % BLOCK_SIZE;

        for (int i=rowBlock; i<rowBlock + BLOCK_SIZE; i++) {
            for (int j=columnBlock; j<columnBlock + BLOCK_SIZE; j++) {
                if (i != row || j != column) {
                    if (!playBoard[i][j].getPossibleValues()[value] && playBoard[i][j].getValue() == -1) return true;
                }
            }
        }
        return false;
    }

    private boolean isWrittenInBlock(int row, int column, int value) {
        int rowBlock = row - row % BLOCK_SIZE;
        int columnBlock = column - column % BLOCK_SIZE;

        for (int i=rowBlock; i<rowBlock + BLOCK_SIZE; i++) {
            for (int j=columnBlock; j<columnBlock + BLOCK_SIZE; j++) {
                if (playBoard[i][j].getValue() == value) return true;
            }
        }
        return false;
    }

    private boolean choseValueForEmptyElement() {
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                if (playBoard[i][j].getValue() == -1) {
                    for (int k=0; k<POSSIBLE_VALUE_SIZE; k++) {
                        if (!isWrittenInBlock(i, j, k) && !isWrittenInColumn(j, k) && !isWrittenInRow(i, k)) {
                            playBoard[i][j].setValueBoard(k);
                            return false;
                        }
                    }
                }
            }
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














