
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen {
    private JFrame frame;

    public LoginScreen() {
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

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userName.getText();
                String pass = new String(password.getPassword());
                if (username.equals("bugra") && pass.equals("5252")) {
                    AppScreen appScreen = new AppScreen();
                    appScreen.open();
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userName.getText();
                String pass = new String(password.getPassword());
                User newUser = new User(username, pass);
                newUser.saveToFile();
            }
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        frame.add(userLoginScreen);
        frame.add(buttonPanel);

        frame.setVisible(true);
    }
}