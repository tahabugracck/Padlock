import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginScreen {
    private final JFrame frame;

    public LoginScreen() {
        frame = new JFrame("Login");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 2));
        frame.setLocationRelativeTo(null);
        String iconPath = "lock_box_icon.png";
        frame.setIconImage(new ImageIcon(iconPath).getImage());

        JPanel userLoginScreen = new JPanel(new GridLayout(2, 2));
        JTextField userName = new JTextField();
        JPasswordField password = new JPasswordField();
        userLoginScreen.add(new JLabel("Username : "));
        userLoginScreen.add(userName);
        userLoginScreen.add(new JLabel("Password : "));
        userLoginScreen.add(password);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton loginButton = new JButton("Login");


        JButton registerButton = new JButton("Register");
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        frame.add(userLoginScreen);
        frame.add(buttonPanel);

        frame.setVisible(true);



    }
}
