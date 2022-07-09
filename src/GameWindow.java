import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GameWindow extends JFrame {
    private JMenuBar menuBar;
    private JMenu appMenu;
    private JMenuItem restartGameMenuItem;
    private JMenuItem newGameMenuItem;
    private JMenuItem scoreTableMenuItem;
    private JMenuItem exitGameMenuItem;

    private Sudoku game = new Sudoku();

    public GameWindow() {
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class RestartGameActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
        }
    }
    
    private class NewGameActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            game.New();
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
