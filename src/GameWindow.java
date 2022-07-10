import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class GameWindow extends JFrame {
    private JMenuBar menuBar;
    private JMenu appMenu;
    private JMenuItem restartGameMenuItem;
    private JMenuItem newGameMenuItem;
    private JMenuItem scoreTableMenuItem;
    private JMenuItem exitGameMenuItem;

    private Sudoku game = new Sudoku();
    GameTablePanel gameTablePanel;

    public GameWindow() {
        super("Sudoku Game");

        restartGameMenuItem = new JMenuItem("Restart");
        restartGameMenuItem.addActionListener(new RestartGameActionListener());
        newGameMenuItem = new JMenuItem("New game");
        newGameMenuItem.addActionListener(new NewGameActionListener());
        scoreTableMenuItem = new JMenuItem("Score table");
        scoreTableMenuItem.addActionListener(new ScoreTableActionListener());
        exitGameMenuItem = new JMenuItem("Exit");
        exitGameMenuItem.addActionListener(new CloseActionListener());

        appMenu = new JMenu("Menu");
        appMenu.add(restartGameMenuItem);
        appMenu.add(newGameMenuItem);
        appMenu.add(scoreTableMenuItem);
        appMenu.add(exitGameMenuItem);

        menuBar = new JMenuBar();
        menuBar.add(appMenu);

        setJMenuBar(menuBar);

        gameTablePanel = new GameTablePanel();
        getContentPane().add(gameTablePanel, BorderLayout.CENTER);
        
        setSize(400, 450);
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
                    cell.setAlignmentX(row);
                    cell.setAlignmentY(column);
                    if (game.Board[row][column] != 0) {
                        cell.setText(String.valueOf(game.Board[row][column]));
                    }
                    add(cell, BorderLayout.CENTER);
                }
            }
            return;
        }
    }
    
    private class RestartGameActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
        }
    }
    
    private class NewGameActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            game.New();
            gameTablePanel.DrawTablePanel();
            add(gameTablePanel, BorderLayout.CENTER);
            
            System.out.println("New game started.");
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
