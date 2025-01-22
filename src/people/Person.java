package people;

import items.Item;
import observers.*;

import java.util.ArrayList;
import java.util.HashMap;


public abstract class Person implements Observer, ObservedSubject {

    protected static int id_counter = 0;

    protected int id;

    protected double budget;

    protected ArrayList<Item> wantedItems;
    protected ArrayList<Item> boughtItems;
    protected Observer observer;

    abstract void chooseAuction();

    public Person(int wantedCount, double budgetSet){
        id_counter++;

        wantedItems = new ArrayList<>();
        boughtItems = new ArrayList<>();
        for(int i = 0; i < wantedCount; i++){
            wantedItems.add(new Item());
        }

        budget = budgetSet;
    }

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

    }
}
