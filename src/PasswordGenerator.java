import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class CharacterPool {
    private final List<Character> characters;

    public CharacterPool() {
        characters = new ArrayList<>();
    }

    public void addUpperCase() {
        for (char c = 'A'; c <= 'Z'; c++) {
            characters.add(c); // Büyük harfleri ekler
        }
    }

    public void addLowerCase() {
        for (char c = 'a'; c <= 'z'; c++) {
            characters.add(c); // Küçük harfleri ekler
        }
    }

    public void addNumbers() {
        for (char c = '0'; c <= '9'; c++) {
            characters.add(c); // Rakamları ekler
        }
    }

    public void addSymbols() {
        char[] symbols = "!@#$%^&*()_+[]{}|;:,.<>?".toCharArray();
        for (char c : symbols) {
            characters.add(c); // Sembolleri ekler
        }
    }

    public String generatePassword(int length) {
        if (characters.isEmpty()) {
            throw new IllegalStateException("Character pool is empty"); // Karakter havuzu boşsa hata verir
        }
        Collections.shuffle(characters); // Karakter havuzunu karıştırır
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            password.append(characters.get(random.nextInt(characters.size()))); // Rastgele parola oluşturur
        }
        return password.toString();
    }
}

class PasswordStrength {
    private final String password;

    public PasswordStrength(String password) {
        this.password = password;
    }

    public int calculateStrength() {
        int strength = 0;
        if (password.length() >= 8) {
            strength += 20; // Uzunluk 8'den büyükse puan ekler
        }
        if (password.matches(".*[A-Z].*")) {
            strength += 20; // Büyük harf içeriyorsa puan ekler
        }
        if (password.matches(".*[a-z].*")) {
            strength += 20; // Küçük harf içeriyorsa puan ekler
        }
        if (password.matches(".*\\d.*")) {
            strength += 20; // Rakam içeriyorsa puan ekler
        }
        if (password.matches(".*[!@#$%^&*()].*")) {
            strength += 20; // Sembol içeriyorsa puan ekler
        }
        return strength;
    }

    public Color calculateStrengthColor(int strength) {
        Color color;
        if (strength <= 20) {
            color = Color.red;
        } else if (strength <= 40) {
            color = Color.orange;
        } else if (strength <= 60) {
            color = Color.yellow;
        } else {
            color = Color.green;
        }
        return color;
    }
}

public class PasswordGenerator {
    private JFrame frame;
    private JCheckBox upperCaseCheckBox, lowerCaseCheckBox, numbersCheckBox, symbolsCheckBox;
    private JTextField lengthField, passwordField;
    private JProgressBar strengthBar;

    public PasswordGenerator() {
        try {
            frame = new JFrame("Password Generator");
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.setLocationRelativeTo(null);
            String iconPath = "lock_box_icon.png";
            frame.setIconImage(new ImageIcon(iconPath).getImage());

            JPanel panel = new JPanel(new GridLayout(8, 1));

            upperCaseCheckBox = new JCheckBox("Include Uppercase Letters");
            lowerCaseCheckBox = new JCheckBox("Include Lowercase Letters");
            numbersCheckBox = new JCheckBox("Include Numbers");
            symbolsCheckBox = new JCheckBox("Include Symbols");
            lengthField = new JTextField();
            lengthField.setBorder(BorderFactory.createTitledBorder("Enter Your Password Length"));
            passwordField = new JTextField();
            passwordField.setEditable(false);
            passwordField.setBorder(BorderFactory.createTitledBorder("Generated Password"));
            strengthBar = new JProgressBar();
            strengthBar.setBorder(BorderFactory.createTitledBorder("Password Strength"));

            JButton generateButton = new JButton("Generate Password");
            generateButton.addActionListener(e -> {
                generatePassword(); // Parola oluşturma fonksiyonunu çağırır
            });

            panel.add(upperCaseCheckBox);
            panel.add(lowerCaseCheckBox);
            panel.add(numbersCheckBox);
            panel.add(symbolsCheckBox);
            panel.add(lengthField);
            panel.add(passwordField);
            panel.add(generateButton);

            frame.add(panel, BorderLayout.CENTER);
            frame.add(strengthBar, BorderLayout.SOUTH);

            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace(); // Hata durumunda hata mesajını yazdırır
        }
    }

    public void generatePassword() {
        CharacterPool characterPool = new CharacterPool();
        if (upperCaseCheckBox.isSelected()) {
            characterPool.addUpperCase(); // Büyük harfleri ekler
        }
        if (lowerCaseCheckBox.isSelected()) {
            characterPool.addLowerCase(); // Küçük harfleri ekler
        }
        if (numbersCheckBox.isSelected()) {
            characterPool.addNumbers(); // Rakamları ekler
        }
        if (symbolsCheckBox.isSelected()) {
            characterPool.addSymbols(); // Sembolleri ekler
        }
        String password;
        try {
            if (Integer.parseInt(lengthField.getText()) >= 4) {
                password = characterPool.generatePassword(Integer.parseInt(lengthField.getText()));
                passwordField.setText(password);
                PasswordStrength passwordStrength = new PasswordStrength(password);
                int strength = passwordStrength.calculateStrength(); // Parola gücünü hesaplar
                strengthBar.setValue(strength); // Parola gücünü gösterir
                strengthBar.setForeground(passwordStrength.calculateStrengthColor(strength));
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a value greater than 4!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Please Enter a Valid Value!");
            lengthField.setText("");
        }
    }
}
class AutoPassGenerator {
    public String getAutoPassGenerator() {
        CharacterPool characterPool = new CharacterPool();
        Random random = new Random();
        characterPool.addSymbols();
        characterPool.addUpperCase();
        characterPool.addLowerCase();
        characterPool.addNumbers();
        return characterPool.generatePassword(random.nextInt(8, 20));
    }
}
