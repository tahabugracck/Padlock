import javax.swing.*;
import java.awt.*;

public class LoginScreen {
    private JFrame frame;

    public LoginScreen() {
        try {
            frame = new JFrame("Padlock-Login");
            frame.setSize(300, 150);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(3, 1));
            frame.setLocationRelativeTo(null);
            String iconPath = "lock_box_icon.png";
            frame.setIconImage(new ImageIcon(iconPath).getImage());

            JPanel userLoginScreen = new JPanel(new GridLayout(2, 2));
            JTextField userName = new JTextField();
            JPasswordField password = new JPasswordField();
            userLoginScreen.add(new JLabel("Username: "));
            userLoginScreen.add(userName);
            userLoginScreen.add(new JLabel("Password: "));
            userLoginScreen.add(password);

            JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
            JButton loginButton = new JButton("Login");
            JButton registerButton = new JButton("Register");

            // Giriş butonuna tıklama işlemi
            loginButton.addActionListener(e -> {
                try {
                    String username = userName.getText();
                    String pass = new String(password.getPassword());
                    if (username.equals("bugra") && pass.equals("5252")) {
                        AppScreen appScreen = new AppScreen();
                        appScreen.open();
                        frame.dispose(); // Giriş başarılıysa ana ekranı açar ve giriş ekranını kapatır
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid Username or Password"); // Hatalı giriş mesajı gösterir
                    }
                } catch (Exception ex) {
                    ex.printStackTrace(); // Hata durumunda hata mesajını yazdırır
                }
            });

            // Kayıt butonuna tıklama işlemi
            registerButton.addActionListener(e -> {
                try {
                    String username = userName.getText();
                    String pass = new String(password.getPassword());
                    User newUser = new User(username, pass);
                    newUser.saveToFile(); // Yeni kullanıcıyı dosyaya kaydeder
                } catch (Exception ex) {
                    ex.printStackTrace(); // Hata durumunda hata mesajını yazdırır
                }
            });

            buttonPanel.add(loginButton);
            buttonPanel.add(registerButton);

            frame.add(userLoginScreen);
            frame.add(buttonPanel);

            frame.setVisible(true); // Ekranı görünür yapar
        } catch (Exception e) {
            e.printStackTrace(); // Hata durumunda hata mesajını yazdırır
        }
    }
}
