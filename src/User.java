import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String username;
    private String password;
    private static final String FILE_NAME = "user_data.dat";

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        try {
            return username; // Kullanıcı adını döndürür
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setUsername(String username) {
        try {
            this.username = username; // Kullanıcı adını ayarlar
        } catch (Exception e) {
            e.printStackTrace();
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

    public void setPassword(String password) {
        try {
            this.password = password; // Parolayı ayarlar
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME, true))) {
            outputStream.writeObject(this); // Kullanıcıyı dosyaya yazar
            JOptionPane.showMessageDialog(null, "Registration Successful");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while saving user data");
        }
    }

    public static List<User> loadFromFile() {
        List<User> userList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            while (true) {
                try {
                    User user = (User) inputStream.readObject();
                    userList.add(user); // Kullanıcıyı listeye ekler
                } catch (EOFException eofException) {
                    break; // Dosyanın sonuna geldiğinde döngüden çıkar
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
