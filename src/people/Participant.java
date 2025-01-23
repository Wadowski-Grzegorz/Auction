package people;


import auction.Auction;
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

    public Participant(int id){
        super(3, 2500);

        this.id = id;
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
        double newOffer = strategy.execute(budget, currOffer, firstOffer, isMine);

        if(newOffer == 0.0){
            return;
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("price", newOffer);
        map.put("id", id);

        notifyObserver(observer, Notification.OFFER, map);
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

    @Override
    public void update(Notification notify, HashMap<String, Object> map) {
        // check notification type and act adequately
        switch (notify) {
            case Notification.START_AUCTION:
                // prepare for new auction
                Auction mapAuction = (Auction) map.get("auction");
                addObserver(mapAuction);
                break;

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

    @Override
    public void log() {
        String wantedStr = getString(wantedItems);
        String boughtStr = getString(boughtItems);

        logger.mess(this.toString(),
                "Wanted items: " + wantedStr
                + ".\n\tBought items: " + boughtStr
                + ".\n\tBalance: " + budget
        );
    }

    public String getString(ArrayList<Item> items){
        StringBuilder str = new StringBuilder();
        for(Item i : items){
            str.append(i.toString()).append(", ");
        }
        if (str.length() > 2) {
            str.setLength(str.length() - 2);
        }
        return str.toString();
    }

    @Override
    public String toString() {
        return "Participant " + id;
    }
}
