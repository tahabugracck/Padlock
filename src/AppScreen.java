import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AppScreen {
    private JFrame frame;
    private DefaultListModel<String> siteListModel;
    private JList<String> siteList;
    private List<PasswordStroge> passwordStorageList;
    private JTextField siteNameField, siteUsernameField;
    private JPasswordField sitePasswordField;
    private JButton editButton, saveButton;
    private JProgressBar strengthBar;

    public AppScreen() {
        try {
            frame = new JFrame("Padlock-App");
            frame.setSize(1200, 800);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());
            String iconPath = "lock_box_icon.png";
            frame.setIconImage(new ImageIcon(iconPath).getImage());

            siteListModel = new DefaultListModel<>();
            siteList = new JList<>(siteListModel);
            passwordStorageList = new ArrayList<>();

            JPanel leftPanel = new JPanel(new BorderLayout());
            leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel siteListLabel = new JLabel("Site List");
            leftPanel.add(siteListLabel, BorderLayout.NORTH);
            leftPanel.add(new JScrollPane(siteList), BorderLayout.CENTER);

            JPanel leftButtonPanel = new JPanel(new GridLayout(2, 1));
            JButton passwordGeneratorButton = new JButton("Generate Password");
            JButton addNewPasswordButton = new JButton("Add New Password");
            setButtonSize(passwordGeneratorButton);
            setButtonSize(addNewPasswordButton);
            leftButtonPanel.add(passwordGeneratorButton);
            leftButtonPanel.add(addNewPasswordButton);
            leftPanel.add(leftButtonPanel, BorderLayout.SOUTH);

            JPanel rightPanel = new JPanel(new GridLayout(8, 1));

            siteNameField = new JTextField();
            siteUsernameField = new JTextField();
            sitePasswordField = new JPasswordField();
            siteNameField.setEditable(false);
            sitePasswordField.setEditable(false);
            siteUsernameField.setEditable(false);
            rightPanel.add(new JLabel("Site Name:"));
            rightPanel.add(siteNameField);
            rightPanel.add(new JLabel("Username:"));
            rightPanel.add(siteUsernameField);
            rightPanel.add(new JLabel("Password:"));
            rightPanel.add(sitePasswordField);

            editButton = new JButton("Edit");
            saveButton = new JButton("Save");
            JCheckBox passView = new JCheckBox("Show Password");
            JButton deleteButton = new JButton("Delete");
            strengthBar = new JProgressBar();
            strengthBar.setBorder(BorderFactory.createTitledBorder("Password Strength"));
            setButtonSize(editButton);
            setButtonSize(saveButton);
            editButton.setEnabled(false); // Başlangıçta düzenleme butonu devre dışı
            saveButton.setEnabled(false); // Başlangıçta kaydet butonu devre dışı
            rightPanel.add(editButton);
            rightPanel.add(saveButton);
            rightPanel.add(passView);
            rightPanel.add(deleteButton);
            rightPanel.add(strengthBar);

            // Parola oluşturma ekranını açar
            passwordGeneratorButton.addActionListener(e -> new PasswordGenerator());
            // Yeni parola ekleme ekranını açar
            addNewPasswordButton.addActionListener(e -> {
                PasswordStroge passwordStroge = new PasswordStroge(siteListModel);
                passwordStorageList.add(passwordStroge);
                passwordStroge.open();
            });

            deleteButton.addActionListener(e -> {
                if (siteList.getSelectedIndex()>-1){
                    siteListModel.removeElementAt(siteList.getSelectedIndex());
                    sitePasswordField.setText("");
                    siteUsernameField.setText("");
                    siteNameField.setText("");
                    strengthBar.setValue(0);
                }else {
                    JOptionPane.showMessageDialog(frame,"Please Select Item");
                }

            });

            passView.addActionListener(e -> {
                if (passView.isSelected()) {
                    sitePasswordField.setEchoChar((char) 0);
                    sitePasswordField.putClientProperty("JPasswordField.cutCopyAllowed", true);
                } else {
                    sitePasswordField.setEchoChar('*');
                }
            });

            siteList.addListSelectionListener(e -> {
                updateRightPanel();
            });

            // Düzenleme moduna geçer
            editButton.addActionListener(e -> {
                editButton.setEnabled(false);
                saveButton.setEnabled(true);
                enableFields();
            });

            // Değişiklikleri kaydeder
            saveButton.addActionListener(e -> {
                try {
                    int selectedIndex = siteList.getSelectedIndex();
                    if (selectedIndex >= 0 && selectedIndex < passwordStorageList.size()) {
                        PasswordStroge selectedStorage = passwordStorageList.get(selectedIndex);
                        selectedStorage.setSiteName(siteNameField.getText());
                        selectedStorage.setUsername(siteUsernameField.getText());
                        selectedStorage.setPassword(new String(sitePasswordField.getPassword()));
                        siteListModel.setElementAt(siteNameField.getText(),selectedIndex);
                        editButton.setEnabled(true);
                        saveButton.setEnabled(false);
                        JOptionPane.showMessageDialog(frame, "Changes saved successfully.");
                        disableFields();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace(); // Hata durumunda hata mesajını yazdırır
                }
            });

            frame.add(leftPanel, BorderLayout.WEST);
            frame.add(rightPanel, BorderLayout.CENTER);

            frame.pack();
            frame.setLocationRelativeTo(null);
        } catch (Exception e) {
            e.printStackTrace(); // Hata durumunda hata mesajını yazdırır
        }
    }

    private void setButtonSize(JButton button) {
        button.setPreferredSize(new Dimension(120, 30)); // Buton boyutunu ayarlar
    }

    private void updateRightPanel() {
        try {
            int selectedIndex = siteList.getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < passwordStorageList.size()) {
                editButton.setEnabled(true);
                PasswordStroge selectedStorage = passwordStorageList.get(selectedIndex);
                PasswordStrength strength = new PasswordStrength(selectedStorage.getPassword());
                siteNameField.setText(selectedStorage.getSiteName());
                siteUsernameField.setText(selectedStorage.getUsername());
                sitePasswordField.setText(selectedStorage.getPassword());
                strengthBar.setValue(strength.calculateStrength());
                strengthBar.setForeground(strength.calculateStrengthColor(strength.calculateStrength()));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Hata durumunda hata mesajını yazdırır
        }
    }

    private void disableFields() {
        siteNameField.setEditable(false); // Alanları düzenlenemez yapar
        siteUsernameField.setEditable(false);
        sitePasswordField.setEditable(false);
    }

    private void enableFields() {
        siteNameField.setEditable(true); // Alanları düzenlenebilir yapar
        siteUsernameField.setEditable(true);
        sitePasswordField.setEditable(true);
    }

    public void open() {
        frame.setVisible(true); // Ekranı görünür yapar
    }
}