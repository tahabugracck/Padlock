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

            // Parola oluşturma ekranını açar
            passwordGeneratorButton.addActionListener(e -> new PasswordGenerator());
            // Yeni parola ekleme ekranını açar
            addNewPasswordButton.addActionListener(e -> {
                PasswordStroge passwordStroge = new PasswordStroge();
                passwordStorageList.add(passwordStroge);
                passwordStroge.open();
                updateSiteList();
            });

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

            editButton = new JButton("Edit");
            saveButton = new JButton("Save");
            setButtonSize(editButton);
            setButtonSize(saveButton);
            editButton.setEnabled(false); // Başlangıçta düzenleme butonu devre dışı
            saveButton.setEnabled(false); // Başlangıçta kaydet butonu devre dışı
            rightPanel.add(editButton);
            rightPanel.add(saveButton);

            siteList.addListSelectionListener(e -> updateRightPanel());

            // Düzenleme moduna geçer
            editButton.addActionListener(e -> {
                editButton.setEnabled(false);
                saveButton.setEnabled(true);
                disableFields();
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
                        editButton.setEnabled(true);
                        saveButton.setEnabled(false);
                        enableFields();
                        JOptionPane.showMessageDialog(frame, "Changes saved successfully.");
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

    private void updateSiteList() {
        siteListModel.clear();
        for (PasswordStroge storage : passwordStorageList) {
            siteListModel.addElement(storage.getSiteName()); // Site listesi güncellenir
        }
    }

    private void updateRightPanel() {
        try {
            int selectedIndex = siteList.getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < passwordStorageList.size()) {
                PasswordStroge selectedStorage = passwordStorageList.get(selectedIndex);
                siteNameField.setText(selectedStorage.getSiteName());
                siteUsernameField.setText(selectedStorage.getUsername());
                sitePasswordField.setText(selectedStorage.getPassword());
                if (editButton.isEnabled()) {
                    disableFields(); // Alanları düzenlenemez yapar
                } else {
                    enableFields(); // Alanları düzenlenebilir yapar
                }
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
