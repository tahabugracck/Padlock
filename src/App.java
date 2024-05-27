public class App {
    public static void main(String[] args) {
        try {
            new LoginScreen(); // Giriş ekranını başlatır
        } catch (Exception e) {
            e.printStackTrace(); // Hata durumunda hata mesajını yazdırır
        }
    }
}
