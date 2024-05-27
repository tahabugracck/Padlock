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
        try {
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
            // "Save" butonuna tıklandığında yapılacak işlemler
            saveButton.addActionListener(e -> {
                try {
                    siteName = siteNameField.getText();
                    username = siteUsernameField.getText();
                    password = new String(sitePasswordField.getPassword());
                    if (siteName.isEmpty() || username.isEmpty() || password.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please fill all the fields");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Password saved successfully");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "An error occurred while saving the password.");
                }
            });

            panel.add(siteNameField);
            panel.add(siteUsernameField);
            panel.add(sitePasswordField);
            panel.add(saveButton);

            frame.add(panel);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while initializing the Password Storage.");
        }
    }

    public void open() {
        try {
            frame.setVisible(true); // Ekranı görünür yapar
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while opening the Password Storage.");
        }
    }

    public void setSiteName(String siteName) {
        try {
            this.siteName = siteName; // Site ismini ayarlar
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUsername(String username) {
        try {
            this.username = username; // Kullanıcı adını ayarlar
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPassword(String password) {
        try {
            this.password = password; // Parolayı ayarlar
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSiteName() {
        try {
            return siteName; // Site ismini döndürür
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getUsername() {
        try {
            return username; // Kullanıcı adını döndürür
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getPassword() {
        try {
            return password; // Parolayı döndürür
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
