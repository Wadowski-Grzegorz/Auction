package people;

import auction.Auction;
import communication.Log;
import items.Item;
import observers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public abstract class Person implements Observer, ObservedSubject {

    protected Log logger;

    protected int id;
    protected int currId;

    protected double budget;

    protected ArrayList<Item> wantedItems;
    protected ArrayList<Item> boughtItems;
    protected Observer observer;

    public Person(int setid, int wantedCount, double budgetSet){
        // create person with random wanted items

        logger = Log.getInstance();
        wantedItems = new ArrayList<>();
        boughtItems = new ArrayList<>();
        for(int i = 0; i < wantedCount; i++){
            wantedItems.add(new Item());
        }

        id = setid;
        budget = budgetSet;
        observer = null;
        currId = -1;
    }

    public abstract boolean wantAuction(LinkedList<Item> items);

    public abstract void step();

    @Override
    public void addObserver(Observer ob) {
        observer = ob;
    }

    @Override
    public void notifyObserver(Observer ob, Notification notify, HashMap<String, Object> map) {
        ob.update(notify, map);
    }

    @Override
    public void notifyAllObservers(Notification notify, HashMap<String, Object> map) {
        if(observer != null){
            observer.update(notify, map);
        }
    }

    @Override
    public void removeObserver(Observer ob) {
        observer = null;
    }

    public int getId() {
        return id;
    }

    public double getBudget() {
        return budget;
    }

    public ArrayList<Item> getWantedItems() {
        return wantedItems;
    }

    public ArrayList<Item> getBoughtItems() {
        return boughtItems;
    }

    public Observer getObserver() {
        return observer;
    }

    public String getString(List<Item> items){
        StringBuilder str = new StringBuilder();
        for(Item i : items){
            str.append(i.toString()).append(", ");
        }
        if (str.length() > 2) {
            str.setLength(str.length() - 2);
        }
        return str.toString();
    }

    public void log() {
        String wantedStr = getString(wantedItems);
        String boughtStr = getString(boughtItems);

        logger.mess(this.toString(),
                "Wanted items: " + wantedStr
                        + ".\n\tBought items: " + boughtStr
                        + ".\n\tBalance: " + budget
        );
    }

    protected void boughtItem(Item item, double price){

        budget -= price;
        budget = Math.round(budget * 100.0) / 100.0;
        boughtItems.add(item);

        // remove item from wanted items
        for(Item wItem: wantedItems){
            if(wItem.getType() == item.getType()){
                wantedItems.remove(wItem);
                break;
            }
        }
    }
}
