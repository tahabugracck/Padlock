import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PasswordGenerator implements PasswordGeneratorInterface {
    private CharacterPool characterPool;
    private JFrame frame;
    private JCheckBox upperCaseCheckBox;
    private JCheckBox lowerCaseCheckBox;
    private JCheckBox numbersCheckBox;
    private JCheckBox symbolsCheckBox;
    private JTextField lengthField;
    private JTextField passwordField;
    private JProgressBar strengthBar;

    public PasswordGenerator() {
        frame = new JFrame("Password Generator");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String iconPath = "lock_box_icon.png";
        frame.setIconImage(new ImageIcon(iconPath).getImage());
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(7, 1));
        upperCaseCheckBox = new JCheckBox("Use Uppercase Letters");
        lowerCaseCheckBox = new JCheckBox("Use Lowercase Letters");
        numbersCheckBox = new JCheckBox("Use Numbers");
        symbolsCheckBox = new JCheckBox("Use Symbols");
        lengthField = new JTextField();
        lengthField.setBorder(BorderFactory.createTitledBorder("Password Length"));
        passwordField = new JTextField();
        passwordField.setEditable(false);
        passwordField.setBorder(BorderFactory.createTitledBorder("Generated Password"));

        strengthBar = new JProgressBar(0, 6);
        strengthBar.setStringPainted(true);

        JButton generateButton = new JButton("Generate");

        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean addUpper = upperCaseCheckBox.isSelected();
                    boolean addLower = lowerCaseCheckBox.isSelected();
                    boolean addNumbers = numbersCheckBox.isSelected();
                    boolean addSymbols = symbolsCheckBox.isSelected();
                    int length = Integer.parseInt(lengthField.getText());
                    characterPool = new CharacterPool(addUpper, addLower, addNumbers, addSymbols);
                    String generatedPassword = generatePassword(length);
                    passwordField.setText(generatedPassword);
                    int strength = calculateStrength(addUpper, addLower, addNumbers, addSymbols, length);
                    strengthBar.setValue(strength);
                    strengthBar.setString("Strength: " + strength + "/6");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input for password length. Please enter a valid number.");
                }
            }
        });

        panel.add(upperCaseCheckBox);
        panel.add(lowerCaseCheckBox);
        panel.add(numbersCheckBox);
        panel.add(symbolsCheckBox);
        panel.add(lengthField);
        panel.add(passwordField);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(generateButton, BorderLayout.SOUTH);
        frame.add(strengthBar, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    @Override
    public String generatePassword(int length) {
        StringBuilder password = new StringBuilder();
        String pool = characterPool.getCharacterPool();
        int poolLength = pool.length();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * poolLength);
            password.append(pool.charAt(index));
        }
        return password.toString();
    }

    private int calculateStrength(boolean addUpper, boolean addLower, boolean addNumbers, boolean addSymbols, int length) {
        int score = 0;
        if (addUpper) score++;
        if (addLower) score++;
        if (addNumbers) score++;
        if (addSymbols) score++;
        if (length < 8) {
            score += 0;
        } else if (length <= 16) {
            score += 1;
        } else {
            score += 2;
        }
        return score;
    }

    @Override
    public void checkPassword() {
        String input = JOptionPane.showInputDialog(frame, "Enter your password:");
        PasswordStrength passwordStrength = new PasswordStrength(input);
        int strength = passwordStrength.calculateStrength();
        JOptionPane.showMessageDialog(frame, "Your password strength is: " + strength + "/6");
    }

    @Override
    public void printMenu() {
        String menuMessage = "Enter 1 - Generate Password\n" +
                "Enter 2 - Check Password Strength\n" +
                "Enter 3 - Useful Information\n" +
                "Enter 4 - Quit\n" +
                "Choice:";
        JOptionPane.showMessageDialog(frame, menuMessage);
    }

    @Override
    public void printQuitMessage() {
        JOptionPane.showMessageDialog(frame, "Closing the program. Goodbye!");
    }

    private class CharacterPool {
        private List<Character> pool;

        public CharacterPool(boolean addUpperCase, boolean addLowerCase, boolean addNumbers, boolean addSpecialCharacters) {
            pool = new ArrayList<>();
            if (addUpperCase) addCharacters('A', 'Z');
            if (addLowerCase) addCharacters('a', 'z');
            if (addNumbers) addCharacters('0', '9');
            if (addSpecialCharacters) addSpecialCharacters();
        }

        private void addCharacters(char start, char end) {
            for (char ch = start; ch <= end; ch++) {
                pool.add(ch);
            }
        }

        private void addSpecialCharacters() {
            String symbols = "!@#$%^&*()-_=+\\/~?";
            for (char ch : symbols.toCharArray()) {
                pool.add(ch);
            }
        }

        public String getCharacterPool() {
            StringBuilder result = new StringBuilder();
            for (Character ch : pool) {
                result.append(ch);
            }
            return result.toString();
        }
    }

    private class PasswordStrength {
        private String password;
        private int length;

        public PasswordStrength(String password) {
            this.password = password;
            this.length = password.length();
        }

        private int getCharacterType(char c) {
            if (Character.isUpperCase(c)) {
                return 1; // Büyük harf
            } else if (Character.isLowerCase(c)) {
                return 2; // Küçük harf
            } else if (Character.isDigit(c)) {
                return 3; // Sayı
            } else {
                return 4; // Sembol
            }
        }

        public int calculateStrength() {
            boolean usedUpper = false;
            boolean usedLower = false;
            boolean usedNum = false;
            boolean usedSym = false;
            int type;
            int score = 0;

            for (int i = 0; i < length; i++) {
                char c = password.charAt(i);
                type = getCharacterType(c);

                if (type == 1) usedUpper = true;
                if (type == 2) usedLower = true;
                if (type == 3) usedNum = true;
                if (type == 4) usedSym = true;
            }

            if (usedUpper) score += 1;
            if (usedLower) score += 1;
            if (usedNum) score += 1;
            if (usedSym) score += 1;

            if (length < 8) {
                score += 0;
            } else if (length <= 16) {
                score++;
            } else {
                score += 2;
            }

            return score;
        }

        @Override
        public String toString() {
            return password;
        }
    }


}


interface PasswordGeneratorInterface {
    String generatePassword(int length);

    void checkPassword();

    void printMenu();

    void printQuitMessage();
}
