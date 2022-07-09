import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginWindow extends JFrame {
    private JTextField nameText;
    private JLabel nameLabel;
    private JButton startButton;
    private JButton exitButton;

    public LoginWindow() {
        super("Sudoku Game");

        nameLabel = new JLabel("Enter your name: ");
        nameLabel.setBounds(10, 10, 260, 30);
        nameText = new JTextField(25);
        nameText.setBounds(10, 40, 260, 30);
        startButton = new JButton("Play");
        startButton.setBounds(145, 80, 125, 30);
        startButton.addActionListener(new OpenActionListener());
        exitButton = new JButton("Exit");
        exitButton.setBounds(10, 80, 125, 30);
        exitButton.addActionListener(new CloseActionListener());

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.add(nameLabel);
        loginPanel.add(nameText);
        loginPanel.add(startButton);
        loginPanel.add(exitButton);

        getContentPane().add(loginPanel);
        setSize(300, 165);
        setLocation(500, 240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class OpenActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String nameValue = nameText.getText();
            String errorMessage = "Please enter your name.";
            if (nameValue.equals("")) {
                JOptionPane.showMessageDialog(rootPane, errorMessage);
            } else {
                new GameWindow();
                dispose();
            }
        }
    }
    
    private class CloseActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(NORMAL);
        }
    }
}
