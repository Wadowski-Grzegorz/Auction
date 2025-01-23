public class Main {
    public static void main(String[] args) {
        // Initialize and start the application
        AppManager appManager = new AppManager();

        appManager.setup(3, 2, 4, 500, true);
        appManager.start();
        appManager.end();
    }
}
