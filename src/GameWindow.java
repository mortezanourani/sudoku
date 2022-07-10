import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameWindow extends JFrame {
    private JMenuBar menuBar;
    private JMenu appMenu;
    private JMenuItem restartGameMenuItem;
    private JMenu newGameMenu;
    private JMenuItem newGameEasyMenuItem;
    private JMenuItem newGameMediumMenuItem;
    private JMenuItem newGameHardMenuItem;
    private JMenuItem scoreTableMenuItem;
    private JMenuItem exitGameMenuItem;

    private Sudoku game = new Sudoku();
    private int selectedCell;
    GameTablePanel gameTablePanel;

    private JPanel gameOptions;
    TimerPanel timerPanel;
    private JButton timerPause;
    private JLabel timerDisplay;
    ChoicePanel choicePanel;

    public GameWindow() {
        super("Sudoku Game");

        restartGameMenuItem = new JMenuItem("Restart");
        restartGameMenuItem.addActionListener(new RestartGameActionListener());

        newGameEasyMenuItem = new JMenuItem("Easy");
        newGameEasyMenuItem.addActionListener(new NewGameActionListener());
        newGameMediumMenuItem = new JMenuItem("Medium");
        newGameMediumMenuItem.addActionListener(new NewGameActionListener());
        newGameHardMenuItem = new JMenuItem("Hard");
        newGameHardMenuItem.addActionListener(new NewGameActionListener());
        newGameMenu = new JMenu("New game");
        newGameMenu.add(newGameEasyMenuItem);
        newGameMenu.add(newGameMediumMenuItem);
        newGameMenu.add(newGameHardMenuItem);
        
        scoreTableMenuItem = new JMenuItem("Score table");
        scoreTableMenuItem.addActionListener(new ScoreTableActionListener());
        exitGameMenuItem = new JMenuItem("Exit");
        exitGameMenuItem.addActionListener(new CloseActionListener());

        appMenu = new JMenu("Menu");
        appMenu.add(restartGameMenuItem);
        appMenu.add(newGameMenu);
        appMenu.add(scoreTableMenuItem);
        appMenu.add(exitGameMenuItem);

        menuBar = new JMenuBar();
        menuBar.add(appMenu);

        setJMenuBar(menuBar);

        gameTablePanel = new GameTablePanel();
        getContentPane().add(gameTablePanel, BorderLayout.CENTER);
        
        timerPause = new JButton("Pause");
        timerPause.setBounds(10, 10, 160, 30);
        timerPause.addActionListener(new PauseActionListener());
        timerDisplay = new JLabel("00:00");
        timerDisplay.setBounds(10, 10, 160, 30);
        timerPanel = new TimerPanel();
        timerPanel.setBorder(BorderFactory.createTitledBorder("Timer"));
        timerPanel.add(timerDisplay);
        timerPanel.add(timerPause);

        choicePanel = new ChoicePanel();

        gameOptions = new JPanel();
        gameOptions.add(timerPanel, BorderLayout.NORTH);
        gameOptions.add(choicePanel, BorderLayout.CENTER);
        getContentPane().add(gameOptions, BorderLayout.EAST);

        setSize(650, 450);
        setLocation(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private class GameTablePanel extends JPanel {
        public GameTablePanel() {
            super();
        }

        public void DrawTablePanel() {
            this.removeAll();
            setLayout(new GridLayout(9, 9));
            for (int row = 0; row <= 8; row++) {
                for (int column = 0; column <= 8; column++) {
                    JButton cell = new JButton();
                    if (game.Board[row][column] != 0) {
                        cell.setText(String.valueOf(game.Board[row][column]));
                    }

                    if (game.Puzzle[row][column] != 0) {
                        cell.setBackground(Color.GRAY);
                    } else {
                        cell.setBackground(Color.WHITE);
                    }
                    cell.setActionCommand(String.valueOf(row * 9 + column));
                    cell.addActionListener(new CellActionListener());
                    add(cell, BorderLayout.CENTER);
                }
            }
            this.revalidate();
            this.repaint();
            return;
        }
    }
    
    private class TimerPanel extends JPanel {
        public TimerPanel() {
            super();
        }

        public void Start() {
            // Code to start counting
            return;
        }

        public void Stop() {
            // Code to stop counting
            return;
        }

        public void Reset() {
            // Code to reset timer
            this.Start();
            return;
        }
    }

    private class PauseActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Start() if it was stoped.
            // Stop() if it was counting.
            timerPanel.Stop();
        }
    }

    private class ChoicePanel extends JPanel {
        public ChoicePanel() {
            super();
            this.setLayout(new GridLayout(3, 3));
            for (int choice = 1; choice <= 9; choice++) {
                JButton choiceButton = new JButton();
                choiceButton.setText(String.valueOf(choice));
                choiceButton.setActionCommand(String.valueOf(choice));
                choiceButton.addActionListener(new ChoiceActionListener());
                add(choiceButton, BorderLayout.CENTER);
            }
        }
    }

    private class ChoiceActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int choice = Integer.valueOf(e.getActionCommand());
            int selectedCellRow = selectedCell / 9;
            int selectedCellColumn = selectedCell % 9;
            game.Move(selectedCellRow, selectedCellColumn, choice);
            gameTablePanel.DrawTablePanel();
            if (game.GameStatus == Sudoku.Status.Win) {
                JOptionPane.showMessageDialog(rootPane, "You won!");
                timerPanel.Stop();
            }
        }
    }

    private class RestartGameActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            game.Restart();
            timerPanel.Reset();
            gameTablePanel.DrawTablePanel();
        }
    }
    
    private class NewGameActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Easy") {
                game.New(Sudoku.Difficulty.Easy);
            } else if (e.getActionCommand() == "Medium") {
                game.New(Sudoku.Difficulty.Medium);
            } else {
                game.New(Sudoku.Difficulty.Hard);

            }
            timerPanel.Reset();
            gameTablePanel.DrawTablePanel();
            add(gameTablePanel, BorderLayout.CENTER);
        }
    }
    
    private class CellActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            selectedCell = Integer.valueOf(e.getActionCommand());
        }
    }

    private class ScoreTableActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
        }
    }
    
    private class CloseActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(NORMAL);
        }
    }
}
