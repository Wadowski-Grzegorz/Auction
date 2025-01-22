package observers;

import java.util.HashMap;

public interface ObservedSubject {
    public void addObserver(Observer ob);
    public void notifyObserver(Observer ob, Notification notify, HashMap<String, Object> map);
    public void notifyAllObservers(Notification notify, HashMap<String, Object> map);
    public void removeObserver(Observer ob);
}
