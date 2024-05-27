import javax.swing.*;
import java.awt.*;

public class PasswordStroge {
    private JFrame frame;
    private JTextField siteNameField, siteUsernameField;
    private JPasswordField sitePasswordField;
    private String siteName;
    private String username;
    private String password;

    public PasswordStroge() {
        frame = new JFrame("Password Storage");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String iconPath = "lock_box_icon.png";
        frame.setIconImage(new ImageIcon(iconPath).getImage());
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(4, 1));
        siteNameField = new JTextField();
        siteNameField.setBorder(BorderFactory.createTitledBorder("Site Name"));
        siteUsernameField = new JTextField();
        siteUsernameField.setBorder(BorderFactory.createTitledBorder("Username"));
        sitePasswordField = new JPasswordField();
        sitePasswordField.setBorder(BorderFactory.createTitledBorder("Password"));

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            siteName = siteNameField.getText();
            username = siteUsernameField.getText();
            password = new String(sitePasswordField.getPassword());
            if (siteName.isEmpty() || username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all the fields");
            } else {
                JOptionPane.showMessageDialog(frame, "Password saved successfully");
            }
        });

        panel.add(siteNameField);
        panel.add(siteUsernameField);
        panel.add(sitePasswordField);
        panel.add(saveButton);

        frame.add(panel);
    }

    public void open() {
        frame.setVisible(true);
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setUsername(String siteName) {
        this.siteName = siteName;
    }

    public void setPassword(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}