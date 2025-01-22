package people;


import observers.Notification;
import strategies.IStrategy;

import java.util.HashMap;

public class Participant extends Person {

    public Participant(){
        super(3, 2500);
        this.id = id_counter;
        System.out.println("Person, my id is " + this.id);

        IStrategy strategy;
    }
    @Override
    void chooseAuction() {

    }

    @Override
    public void update(Notification notify, HashMap<String, Object> map) {
        // check notification type and act adequately
        switch (notify) {
            case Notification.START:
                // check what item
                // set strategy
                break;

            case Notification.END:
                // unset strategy
                break;

            case Notification.OFFER:
                // execute strategy
                break;

            case Notification.WIN:
                // set item as yours
                break;

            default:
                // nothing
        }
    }
}
