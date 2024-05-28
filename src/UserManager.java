import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

class UserManager {
    private ArrayList<User> users;

    public UserManager() {
        loadUsersFromFile();
    }

    public void addUser(User user) {
        if (!isUsernameTaken(user.getUsername())) {
            users.add(user);
            saveUsersToFile();
        } else {
            JOptionPane.showMessageDialog(null, "Username already taken.");
        }
    }

    public boolean authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void saveUsersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat"))) {
            oos.writeObject(users);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving users: " + e.getMessage());
        }
    }

    private void loadUsersFromFile() {
        File file = new File("users.dat");

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                users = (ArrayList<User>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Error loading users: " + e.getMessage());
            }
        } else {
            users = new ArrayList<>();
        }
    }
}
