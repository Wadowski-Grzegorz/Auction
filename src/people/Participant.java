package people;


import items.Item;
import observers.Notification;
import strategies.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Participant extends Person {
    // strategy always must be set

    IStrategy strategy;
    double firstOffer;

    public Participant(){
        super(3, 2500);

        this.id = id_counter;
        this.strategy = new DummyStrategy();
        this.firstOffer = 0.0;

//        System.out.println("Person, my id is " + this.id);
    }
    @Override
    public boolean wantAuction(LinkedList<Item> items){
        // return true if wanted item is in parameter list
        for(Item item : items){
            for(Item wantedItem : wantedItems){
                if(item.getType() == wantedItem.getType()){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void step() {
        // execute strategy
        boolean isMine = id == currId;
        strategy.execute(budget, currOffer, firstOffer, isMine);
    }

    private boolean isWanted(Item item){
        for(Item it: wantedItems){
            if(it.getType() == item.getType()){
                return true;
            }
        }

        return false;
    }

    void boughtItem(Item item, double price){
        budget -= price;

        boughtItems.add(item);

        // remove item from wanted items
        for(Item wItem: wantedItems){
            if(wItem.getType() == item.getType()){
                wantedItems.remove(wItem);
                break;
            }
        }
    }

    @Override
    public void update(Notification notify, HashMap<String, Object> map) {
        // check notification type and act adequately
        switch (notify) {
            case Notification.START:
                currId = -1;

                // read from a map
                Item mapItem = (Item) map.get("item");
                Double mapPrice = (Double) map.get("price");
                currOffer = mapPrice;
                firstOffer = mapPrice;

                // set strategy
                Random rand = new Random();
                if(isWanted(mapItem)){
                    if(rand.nextDouble() < 0.8)
                        strategy = new AggresiveStrategy();
                    else{
                        strategy = new BalancedStrategy();
                    }
                }else{
                    if(rand.nextDouble() < 0.8){
                        strategy = new PassiveStrategy();
                    }else{
                        strategy = new BalancedStrategy();
                    }
                }

                break;

            case Notification.OFFER:
                // set new offer
                currOffer = (Double) map.get("price");
                currId = (Integer) map.get("id");
                break;

            case Notification.WIN:
                // if you're a winner, set item as yours
                Integer mapId = (Integer) map.get("id");
                if(id == mapId){
                    Item wonItem = (Item) map.get("item");
                    Double cost = (Double) map.get("price");

                    boughtItem(wonItem, cost);
                }
                break;

            case Notification.END:
                // unset strategy
                strategy = new DummyStrategy();
                currOffer = 0.0;
                firstOffer = 0.0;
                currId = -1;
                break;

            default:
                // nothing
        }
    }
}
