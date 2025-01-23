package people;

import auction.Auction;
import communication.Log;
import items.Item;
import observers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public abstract class Person implements Observer, ObservedSubject {

    protected Log logger;

    protected int id;

    protected double budget;
    protected double currOffer;
    protected int currId;

    protected ArrayList<Item> wantedItems;
    protected ArrayList<Item> boughtItems;
    protected Observer observer;

    public Person(int wantedCount, double budgetSet){
        // create person with random wanted items

        logger = Log.getInstance();
        wantedItems = new ArrayList<>();
        boughtItems = new ArrayList<>();
        for(int i = 0; i < wantedCount; i++){
            wantedItems.add(new Item());
        }

        budget = budgetSet;
        currOffer = 0.0;
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

    public abstract void log();
}
