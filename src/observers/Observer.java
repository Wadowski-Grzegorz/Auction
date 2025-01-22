package observers;

import java.util.HashMap;

public interface Observer {
    public void update(Notification notify, HashMap<String, Object> map);
}
