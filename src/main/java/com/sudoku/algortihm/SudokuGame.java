package com.sudoku.algortihm;

import com.sudoku.board.SingleSudokuElement;
import com.sudoku.board.SudokuBoard;
import com.sudoku.user.MoveDTO;
import com.sudoku.user.UserService;

import java.util.ArrayList;
import java.util.List;

public class SudokuGame {
    private static final int POSSIBLE_VALUE_SIZE = 9;
    private static final int BLOCK_SIZE = 3;
    private static final UserService userService = new UserService();
    private final List<GameBank> backtrack = new ArrayList<>();
    private SingleSudokuElement[][] playBoard;
    private SudokuBoard sudokuBoard;
    private int size;

    public void setStartedBoard() {
        MoveDTO moveDTO;

        userService.showBoard(sudokuBoard.getGameBoard());
        while (!userService.askForMakeMove().equals("SUDOKU")) {
            moveDTO = userService.makeMove();
            if (moveDTO.getRow() < size && moveDTO.getRow() >= 0
                    && moveDTO.getColumn() < size && moveDTO.getColumn() >= 0
                    && moveDTO.getValue() < POSSIBLE_VALUE_SIZE && moveDTO.getValue() >= 0
                    &&!isWritten(moveDTO.getRow(), moveDTO.getColumn(), moveDTO.getValue())){
                sudokuBoard.getGameBoard()
                        .get(moveDTO.getRow())
                        .getSingleRow().get(moveDTO.getColumn())
                        .setValueBoard(moveDTO.getValue());
            } else {
                userService.showEnteringDataError();
            }
            userService.showBoard(sudokuBoard.getGameBoard());
        }

    }

    public void choseSize() {;
        int localSize = userService.choseSize();
        if (localSize == 1 || localSize == 2 || localSize == 3) {
            localSize *= BLOCK_SIZE;
            sudokuBoard = new SudokuBoard(localSize);
            playBoard = new SingleSudokuElement[localSize][localSize];
            size = localSize;
        } else {
            userService.showEnteringDataError();
            choseSize();
        }
    }
    
    public void resolveSudoku() {
        choseSize();
        createPlayBoard();
        setStartedBoard();

        try {
            startSudokuAlgorithm();
        } catch (moveImpossible e) {
            userService.showErrorInformation();
        }
    }

    private boolean startSudokuAlgorithm() throws moveImpossible {
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
            if (!action) {
                choseValueForEmptyElement();
            }
        }
        return false;
    }

    private boolean startAlgorithmForRow(int row, int column) throws moveImpossible {
        boolean action = false;

        for (int i=0; i<POSSIBLE_VALUE_SIZE; i++) {
            if (setIfOnlyOnePossible(row, column, i)) action = true;
            if (!isWritten(row, column, i) && !isPossibleInRow(row, column, i)) {
                playBoard[row][column].setValueBoard(i);
                action = true;
            }
            if (isError(row, column))  {
                if (!reactOnImpossibleMove()) throw new moveImpossible();
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
            if (i != column && !playBoard[row][i].getImpossibleValues()[value]
                    && playBoard[row][i].getValue() == -1) return true;
        }
        return false;
    }

    private boolean startAlgorithmForColumn(int row, int column) throws moveImpossible {
        boolean action = false;

        for (int i=0; i<POSSIBLE_VALUE_SIZE; i++) {
            if (setIfOnlyOnePossible(row, column, i)) action = true;
            if (!isWritten(row, column, i) && !isPossibleInColumn(row, column, i)) {
                playBoard[row][column].setValueBoard(i);
                action = true;
            }
            if (isError(row, column))  {
                if (!reactOnImpossibleMove()) throw new moveImpossible();
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
            if (i != row && !playBoard[i][column].getImpossibleValues()[value]
                    && playBoard[i][column].getValue() == -1) return true;
        }
        return false;
    }

    private boolean startAlgorithmForBlock(int row, int column) throws moveImpossible {
        boolean action = false;

        for (int i=0; i<POSSIBLE_VALUE_SIZE; i++) {
            if (setIfOnlyOnePossible(row, column, i)) action = true;
            if (!isWritten(row, column, i) && !isPossibleInBlock(row, column, i)) {
                playBoard[row][column].setValueBoard(i);
                action = true;
            }
            if (isError(row, column))  {
                if (!reactOnImpossibleMove()) throw new moveImpossible();
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
                    if (!playBoard[i][j].getImpossibleValues()[value] && playBoard[i][j].getValue() == -1) return true;
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

    private boolean isWritten(int row, int column, int value) {
        boolean isWritten = false;
        if (isWrittenInBlock(row, column, value)) isWritten = true;
        if (isWrittenInColumn(column, value)) isWritten = true;
        if (isWrittenInRow(row, value)) isWritten = true;

        return isWritten;
    }

    private boolean setIfOnlyOnePossible(int row, int column, int i) {
        boolean action = false;
        if (isWritten(row, column, i) && !playBoard[row][column].getImpossibleValues()[i]) {
            playBoard[row][column].setImpossibleValue(i);
            action = true;
        }
        if (playBoard[row][column].getPossibleValuesQuantity() == 1
                && playBoard[row][column].getValue() == -1
                && !isWritten(row, column, i)) {
            playBoard[row][column].setValueBoard(playBoard[row][column].getSinglePossibleValue());
            action = true;
        }
        return action;
    }

    private boolean choseValueForEmptyElement() {
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                if (playBoard[i][j].getValue() == -1) {
                    for (int k=0; k<POSSIBLE_VALUE_SIZE; k++) {
                        if (!isWritten(i, j, k)  && !playBoard[i][j].getImpossibleValues()[k]) {
                            saveMoveToBacktrack(i, j, k);
                            playBoard[i][j].setValueBoard(k);
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean reactOnImpossibleMove() {
        if (!backtrack.isEmpty()) {
            GameBank gameBank = backtrack.get(backtrack.size()-1);
            this.sudokuBoard = gameBank.getBank();
            createPlayBoard();
            playBoard[gameBank.getRow()][gameBank.getColumn()].setImpossibleValue(gameBank.getValue());
            backtrack.remove(backtrack.size()-1);
            return true;
        } else {
            return false;
        }
    }

    private void saveMoveToBacktrack(int row, int column, int value) {
        backtrack.add(new GameBank(sudokuBoard.clone(), row, column, value));
    }

    private boolean isError(int row, int column) {
        for (int i = 0; i < POSSIBLE_VALUE_SIZE; i++) {
            if (playBoard[row][column].getValue() == -1 && isWritten(row, column, i)
                    && playBoard[row][column].getPossibleValuesQuantity() == 1
                    && playBoard[row][column].getSinglePossibleValue() == i) {
                return true;
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