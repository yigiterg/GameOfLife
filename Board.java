package life;

import java.util.Random;

public class Board {

    private int size;
    private int generation;
    private boolean[][] board;
    private boolean[][] next;

    private void basicSetup(int size) {
        this.size = size;
        this.generation = 1;
        board = new boolean[size][size];
        next = new boolean[size][size];
    }

    public Board(int size) {
        basicSetup(size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = random.nextBoolean();
            }
        }
    }

    public int getSize() {
        return size;
    }

    public int getGeneration() {
        return generation;
    }

    public boolean getStateOfCell(int row, int col) {
        return board[row][col];
    }

    public int getNumAlive() {
        int numAlive = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j]) {
                    numAlive++;
                }
            }
        }
        return numAlive;
    }

    public void setNextStateOfCell(int row, int col, boolean state) {
        next[row][col] = state;
    }

    public void update() {
        board = next;
        next = new boolean[size][size];
        generation++;
    }

}