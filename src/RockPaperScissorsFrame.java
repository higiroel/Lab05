import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {

    private JButton rockButton;
    private JButton paperButton;
    private JButton scissorsButton;
    private JButton quitButton;
    private JTextArea gameLog;
    private JTextField playerWinsField;
    private JTextField computerWinsField;
    private JTextField tiesField;

    private int playerWins = 0;
    private int computerWins = 0;
    private int ties = 0;

    // Constructor to setup GUI components
    public RockPaperScissorsFrame() {
        // Setup JFrame
        setTitle("Rock Paper Scissors Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for Rock, Paper, Scissors and Quit buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Choices"));
        rockButton = new JButton("Rock");
        paperButton = new JButton("Paper");
        scissorsButton = new JButton("Scissors");
        quitButton = new JButton("Quit");

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(quitButton);

        add(buttonPanel, BorderLayout.NORTH);

        // Panel for statistics
        JPanel statsPanel = new JPanel();
        statsPanel.setBorder(BorderFactory.createTitledBorder("Stats"));

        JLabel playerWinsLabel = new JLabel("Player Wins: ");
        playerWinsField = new JTextField(5);
        JLabel computerWinsLabel = new JLabel("Computer Wins: ");
        computerWinsField = new JTextField(5);
        JLabel tiesLabel = new JLabel("Ties: ");
        tiesField = new JTextField(5);

        statsPanel.add(playerWinsLabel);
        statsPanel.add(playerWinsField);
        statsPanel.add(computerWinsLabel);
        statsPanel.add(computerWinsField);
        statsPanel.add(tiesLabel);
        statsPanel.add(tiesField);

        add(statsPanel, BorderLayout.SOUTH);

        // JTextArea for results
        gameLog = new JTextArea();
        JScrollPane scroll = new JScrollPane(gameLog);
        add(scroll, BorderLayout.CENTER);

        // Implement action listeners for buttons
        rockButton.addActionListener(new ButtonClickListener("Rock"));
        paperButton.addActionListener(new ButtonClickListener("Paper"));
        scissorsButton.addActionListener(new ButtonClickListener("Scissors"));
        quitButton.addActionListener(e -> System.exit(0));
    }

    // Inner class for handling button clicks
    private class ButtonClickListener implements ActionListener {
        private String playerChoice;

        public ButtonClickListener(String choice) {
            playerChoice = choice;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Generate a random choice for the computer
            String[] choices = {"Rock", "Paper", "Scissors"};
            Random random = new Random();
            int computerIndex = random.nextInt(3);
            String computerChoice = choices[computerIndex];

            // Determine the winner
            String result = determineWinner(playerChoice, computerChoice);

            // Update game log
            gameLog.append("Player chose: " + playerChoice + "\n");
            gameLog.append("Computer chose: " + computerChoice + "\n");
            gameLog.append(result + "\n");
            gameLog.append("----------------------\n");

            // Update stats
            playerWinsField.setText(String.valueOf(playerWins));
            computerWinsField.setText(String.valueOf(computerWins));
            tiesField.setText(String.valueOf(ties));
        }

        private String determineWinner(String player, String computer) {
            if (player.equals(computer)) {
                ties++;
                return "It's a tie!";
            } else if ((player.equals("Rock") && computer.equals("Scissors")) ||
                    (player.equals("Paper") && computer.equals("Rock")) ||
                    (player.equals("Scissors") && computer.equals("Paper"))) {
                playerWins++;
                return "Player wins!";
            } else {
                computerWins++;
                return "Computer wins!";
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RockPaperScissorsFrame frame = new RockPaperScissorsFrame();
            frame.setVisible(true);
        });
    }
}