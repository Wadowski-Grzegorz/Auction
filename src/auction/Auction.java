package auction;

import items.Item;
import observers.ObservedSubject;

import java.util.ArrayList;

public class Auction {
    private ArrayList<Item> items;


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
}

