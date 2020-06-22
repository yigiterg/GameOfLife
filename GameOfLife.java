package life;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GameOfLife extends JFrame {

    JPanel controlPanel;

    JPanel labelsPanel;
    JPanel labelsPanelContainer;
    JLabel generationLabel;
    JLabel aliveLabel;

    JPanel buttonsPanel;
    boolean paused = false;
    JToggleButton playToggleButton;
    boolean resetFlag = false;
    JButton resetButton;

    int size;
    JPanel boardPanel;
    JPanel[][] cellPanels;
    Border cellBorder;

    boolean closed = false;

    public static final int PANEL_SIZE = 125;
    public static final int PIXELS_PER_CELL = 10;

    private void setLabels(int generation, int numAlive) {
        generationLabel.setText("Generation #" + generation);
        aliveLabel.setText("Alive: " + numAlive);
    }

    public GameOfLife() {
        super("Game of Life");

        setLayout(new BorderLayout());

        controlPanel = new JPanel();

        buttonsPanel = new JPanel();
        playToggleButton = new JToggleButton("Play/Pause");
        playToggleButton.setName("PlayToggleButton");
        playToggleButton.addActionListener(e -> paused = !paused);
        buttonsPanel.add(playToggleButton);
        resetButton = new JButton("Reset");
        resetButton.setName("ResetButton");
        resetButton.addActionListener(e -> resetFlag = true);
        buttonsPanel.add(resetButton);
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        labelsPanel = new JPanel();
        generationLabel = new JLabel();
        generationLabel.setName("GenerationLabel");
        aliveLabel = new JLabel();
        aliveLabel.setName("AliveLabel");
        labelsPanel.add(generationLabel);
        labelsPanel.add(aliveLabel);
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
        labelsPanelContainer = new JPanel();
        labelsPanelContainer.add(labelsPanel);

        setLabels(1, 1); // Make tester happy

        controlPanel = new JPanel();
        controlPanel.add(buttonsPanel);
        controlPanel.add(labelsPanelContainer);
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        add(controlPanel, BorderLayout.WEST);

        boardPanel = new JPanel();
        cellBorder = BorderFactory.createLineBorder(Color.BLACK);
        add(boardPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    public boolean isPaused() {
        return paused;
    }

    public boolean resetRequested() {
        return resetFlag;
    }

    public void acknowledgeReset() {
        resetFlag = false;
        paused = false;
    }

    private void prepare(int size) {
        if (this.size != size) {
            this.size = size;
            setSize(size * PIXELS_PER_CELL + PANEL_SIZE, size * PIXELS_PER_CELL);
            boardPanel.setLayout(new GridLayout(size, size));
            cellPanels = new JPanel[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    cellPanels[i][j] = new JPanel();
                    cellPanels[i][j].setBorder(cellBorder);
                    boardPanel.add(cellPanels[i][j]);
                }
            }
        }
    }

    public void show(Board reference) {
        prepare(reference.getSize());
        setLabels(reference.getGeneration(), reference.getNumAlive());
        for (int i = 0; i < reference.getSize(); i++) {
            for (int j = 0; j < size; j++) {
                if (reference.getStateOfCell(i, j)) {
                    cellPanels[i][j].setBackground(Color.BLACK);
                } else {
                    cellPanels[i][j].setBackground(Color.WHITE);
                }
            }
        }
        if (!isVisible()) {
            setVisible(true);
            requestFocus();
        }
    }

    public void indicateClosed() {
        closed = true;
    }

    public boolean isClosed() {
        return closed;
    }

}