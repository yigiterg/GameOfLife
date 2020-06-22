package life;

public class Rules {

    private static int getCongruenceClass(int value, int size) {
        while (value >= size) {
            value -= size;
        }
        while (value < 0) {
            value += size;
        }
        return value;
    }

    public static void advanceGeneration(Board board) {
        int size = board.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int numNeighbors = 0;
                for (int iMod = -1; iMod <= 1; iMod++) {
                    for (int jMod = -1; jMod <= 1; jMod++) {
                        if (!(iMod == 0 && jMod == 0)) {
                            int nowI = getCongruenceClass(i + iMod, size);
                            int nowJ = getCongruenceClass(j + jMod, size);
                            if (board.getStateOfCell(nowI, nowJ)) {
                                numNeighbors++;
                            }
                        }
                    }
                }
                if (board.getStateOfCell(i, j)) { // alive
                    board.setNextStateOfCell(i, j, numNeighbors >= 2 && numNeighbors <= 3);
                } else { // dead
                    board.setNextStateOfCell(i, j, numNeighbors == 3);
                }
            }
        }
        board.update();
    }

}