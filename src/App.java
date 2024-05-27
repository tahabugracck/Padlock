import javax.swing.*;
public class App {
    public static void main(String[] args) {
        try {
            new LoginScreen(); // Giriş ekranını başlatır
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Hata Mesajı: "+e);
        }
    }
}