package people;

import items.Item;
import observers.Notification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class User extends Person{

    public User(int id){
        super(3, 2500);
        this.id = id;
        System.out.println("User, my id is " + this.id);
    }

    @Override
    public boolean wantAuction(LinkedList<Item> items){
        // ask user if he wants to be at auction
        return false;
    }

    @Override
    public void step() {
        // user input
    }

    @Override
    public void update(Notification notify, HashMap<String, Object> map) {
        // check notification type and act adequately
        switch(notify){
            case Notification.START:
                // check what item
                // tell user auction started
                break;

            case Notification.END:
                // tell user its end
                break;

            case Notification.OFFER:
                // tell user new offer
                break;

            case Notification.WIN:
                // set item as yours
                // tell user he won
                break;

            default:
                // nothing
        }
    }

    @Override
    public void log() {
        return;
    }
}
