package people;

import items.Item;

import java.util.ArrayList;


public abstract class Person {

    protected static int id_counter = 0;

    protected int id;

    protected double budget;

    protected ArrayList<Item> wantedItems;
    protected ArrayList<Item> boughtItems;

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

}
