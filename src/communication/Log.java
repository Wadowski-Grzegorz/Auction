package communication;

public class Log {
    private static Log instance;
    private static int id = 0;

    private Log() {}

    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void mess(String from, String mess) {
        System.out.println(from + ": " + mess + ".");
    }

    public void cleanMess(String mess){
        System.out.println(mess);
    }

    public int getId(){
        return ++id;
    }

    public int checkId(){
        return id;
    }

}

