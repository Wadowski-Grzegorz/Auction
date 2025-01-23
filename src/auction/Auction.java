package auction;

import items.Item;
import observers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Auction implements ObservedSubject, Observer {
    private LinkedList<Item> items;
    private ArrayList<Observer> observers;

    private double currentPrice;
    private int currentId;
    private boolean priceChanged;
    private boolean itemChosen; // if currently selling an item


    public Auction(int numberOfItems){
        items = new LinkedList<>();
        for(int i = 0; i < numberOfItems; i++){
            items.add(new Item());
        }
        observers = new ArrayList<>();
    }

    public int step(){
        // open an auction
        // announce current item
        if(itemChosen){
            // continue selling current item
            continueSell();
        }else if(!items.isEmpty()){
            // there are more items to sell -> choose new
            startSell();
        }else{
            // no items, end auction
            return 1;
        }
        return 0;
    }

    private void continueSell(){
        if(priceChanged){
            // announce new price
            HashMap<String, Object> map = new HashMap<>();
            map.put("price", currentPrice);
            map.put("id", currentId);
            notifyAllObservers(Notification.OFFER, map);
        }else{
            // sell item
            HashMap<String, Object> map = new HashMap<>();
            map.put("item", items.getFirst());
            map.put("price", currentPrice);
            map.put("id", currentId);

            notifyAllObservers(Notification.WIN, map);

            endSell();
        }
    }

    private void endSell(){
        // clean
        items.remove();
        itemChosen = false;
        priceChanged = false;
        currentId = -1;
        currentPrice = -1.0;

        notifyAllObservers(Notification.END, null);
    }

    private void startSell(){
        // choose an item and set values
        Item currItem = items.getFirst();
        currentPrice = currItem.getValue() * 0.4;

        priceChanged = false;
        itemChosen = true;
        currentId = -1;

        // announce
        HashMap<String, Object> map = new HashMap<>();
        map.put("item", currItem);
        map.put("price", currentPrice);

        notifyAllObservers(Notification.START, map);
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }

    public LinkedList<Item> getItems() {
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

