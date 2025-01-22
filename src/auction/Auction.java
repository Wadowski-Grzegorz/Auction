package auction;

import items.Item;
import observers.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Auction implements ObservedSubject, Observer {
    private ArrayList<Item> items;
    private ArrayList<Observer> observers;

    private double currentPrice;
    private int currentId;
    private boolean priceChanged;


    public Auction(int numberOfItems){
        items = new ArrayList<>();
        for(int i = 0; i < numberOfItems; i++){
            items.add(new Item());
        }
    }

    public void start(){

    }


    public ArrayList<Item> getItems() {
        return items;
    }

    @Override
    public void addObserver(Observer ob) {
        observers.add(ob);
    }

    @Override
    public void notifyObserver(Observer ob, Notification notify, HashMap<String, Object> map) {
        ob.update(notify, map);
    }

    @Override
    public void notifyAllObservers(Notification notify, HashMap<String, Object> map) {
        for(Observer ob : observers){
            ob.update(notify, map);
        }
    }

    @Override
    public void removeObserver(Observer ob) {
        observers.remove(ob);
    }

    @Override
    public void update(Notification notify, HashMap<String, Object> map) {
        switch(notify){
            case Notification.OFFER:
                // check notification type
                // check if price is higher,
                // if is take id and set new price
                // change bool saying price is changed
                break;

            default:
                // nothing
        }
    }
}

