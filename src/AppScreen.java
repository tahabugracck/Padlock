import javax.swing.*;
import java.awt.*;

public class AppScreen {
    private JFrame frame;
    private DefaultListModel<String> siteListModel;
    private JList<String> siteList;
    private JTextField siteNameField, siteUsernameField;
    private JPasswordField sitePasswordField;

    public AppScreen() {
        frame = new JFrame("Padlock-App");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        siteListModel = new DefaultListModel<>();
        siteList = new JList<>(siteListModel);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JScrollPane(siteList), BorderLayout.CENTER);

        JPanel leftButtonPanel = new JPanel(new GridLayout(2, 1));
        JButton passwordGeneratorButton = new JButton("Generate Password");
        JButton addNewPasswordButton = new JButton("Add New Password");
        setButtonSize(passwordGeneratorButton);
        setButtonSize(addNewPasswordButton);
        leftButtonPanel.add(passwordGeneratorButton);
        leftButtonPanel.add(addNewPasswordButton);
        leftPanel.add(leftButtonPanel, BorderLayout.SOUTH);

        passwordGeneratorButton.addActionListener(e -> new PasswordGenerator());

        JPanel rightPanel = new JPanel(new GridLayout(6, 1));
        siteNameField = new JTextField();
        siteUsernameField = new JTextField();
        sitePasswordField = new JPasswordField();
        rightPanel.add(new JLabel("Site Name:"));
        rightPanel.add(siteNameField);
        rightPanel.add(new JLabel("Username:"));
        rightPanel.add(siteUsernameField);
        rightPanel.add(new JLabel("Password:"));
        rightPanel.add(sitePasswordField);

        JPanel rightButtonPanel = new JPanel(new GridLayout(1, 2));
        JButton editButton = new JButton("Edit");
        JButton saveButton = new JButton("Save");
        setButtonSize(editButton);
        setButtonSize(saveButton);
        rightButtonPanel.add(editButton);
        rightButtonPanel.add(saveButton);
        rightPanel.add(rightButtonPanel);

        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    private void setButtonSize(JButton button) {
        button.setPreferredSize(new Dimension(80, 20));
    }

    public void open() {
        frame.setVisible(true);
    }
}
