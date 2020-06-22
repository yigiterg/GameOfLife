package life;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("BusyWait")
public class Main {

    public static final int BOARD_SIZE = 50;

    public static void main(String[] args) {

        Board board = new Board(BOARD_SIZE);
        GameOfLife gui = new GameOfLife();
        gui.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                gui.indicateClosed();
            }
        });

        while (!gui.isClosed()) {

            if (gui.resetRequested()) {
                board = new Board(BOARD_SIZE);
                gui.acknowledgeReset();
            }

            if (!gui.isPaused()) {
                gui.show(board);
                Rules.advanceGeneration(board);
            }

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                System.out.println("Unable to suspend this thread.");
                System.exit(2);
            }

        }

    }

}