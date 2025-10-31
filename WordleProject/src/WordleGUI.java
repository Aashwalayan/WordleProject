import java.util.Random;
import javax.swing.*;
import java.awt.*;

public class WordleGUI {
    static String[] words =
            WordleWords.words1;

    static String[] messageOnGuess = {
            "Perfect!", "Amazing!", "Great!", "Nice!", "Good!", "Phew!",
    };
    static int numberOfGuesses = 1;

    public static void main(String[] args) {
        JFrame f = new JFrame("Wordle");
        f.setSize(600,800);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setLayout(null);

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(6,5,5,5));
        p.setBounds(50,50,500,600);
        p.setBackground(Color.lightGray);
        p.setBorder(BorderFactory.createEmptyBorder(100,100,200,100));
        f.add(p);

        JLabel [] boxes = new JLabel[30];
        for(int i1 = 0; i1<30; i1++) {
            boxes[i1] = new JLabel("", SwingConstants.CENTER);
            boxes[i1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            p.add(boxes[i1]);
        }
        JTextField t = new JTextField();
        t.setBounds(250, 670, 100, 30);
        t.setBackground(Color.lightGray);
        t.setBorder(BorderFactory.createLineBorder(Color.black));
        f.add(t);

        Random rand = new Random();
        int random = rand.nextInt(50);
        String wordToBeGuessed = words[random];

        t.addActionListener(e -> {
            String guess = t.getText().toUpperCase();

            if (numberOfGuesses > 5) {
                int choice = JOptionPane.showConfirmDialog(
                        f,
                        "Game Over, The word was" + wordToBeGuessed.toUpperCase(),
                        "Game Over",
                        JOptionPane.DEFAULT_OPTION
                );
                if (choice == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }

            if (guess.length() != 5) {
                JOptionPane.showMessageDialog(f, "Invalid!, Enter a 5 letter word.");
            } else if (guess.equals(wordToBeGuessed.toUpperCase())) {
                for (int i = 0; i < 5; i++) {
                    boxes[(numberOfGuesses - 1) * 5 + i].setText(String.valueOf(guess.charAt(i)));
                    boxes[(numberOfGuesses - 1) * 5 + i].setOpaque(true);
                    boxes[(numberOfGuesses - 1) * 5 + i].setBackground(Color.green);

                }
                JOptionPane.showMessageDialog(f, messageOnGuess[numberOfGuesses - 1], "Perfect", JOptionPane.INFORMATION_MESSAGE);
                numberOfGuesses++;
                t.setText("");
                System.exit(0);

            } else {
                for (int i = 0; i < 5; i++) {
                    boxes[(numberOfGuesses - 1) * 5 + i].setText(String.valueOf(guess.charAt(i)));
                    boxes[(numberOfGuesses - 1) * 5 + i].setOpaque(true);
                    if (guess.charAt(i) == wordToBeGuessed.toUpperCase().charAt(i)) {
                        boxes[(numberOfGuesses - 1) * 5 + i].setBackground(Color.green);
                    } else if (wordToBeGuessed.toUpperCase().contains(String.valueOf(guess.charAt(i)))) {
                        boxes[(numberOfGuesses - 1) * 5 + i].setBackground(Color.yellow);
                    } else {
                        boxes[(numberOfGuesses - 1) * 5 + i].setBackground(Color.darkGray);
                    }
                }
                numberOfGuesses++;
                t.setText("");
            }
        });


        f.setVisible(true);
    }
}

