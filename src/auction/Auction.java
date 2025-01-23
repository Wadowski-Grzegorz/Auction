package auction;

import communication.Log;
import items.Item;
import observers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Auction implements ObservedSubject, Observer {
    private LinkedList<Item> items;
    private ArrayList<Observer> observers;

    private Log logger;

    private double currentPrice;
    private int currentId;
    private boolean priceChanged;
    private boolean itemChosen; // if currently selling an item


    public Auction(int numberOfItems){
        logger = Log.getInstance();
        items = new LinkedList<>();
        for(int i = 0; i < numberOfItems; i++){
            items.add(new Item());
        }
        observers = new ArrayList<>();
    }

    public int step(){
//        logger.mess(this.toString(), "step");
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
            logger.cleanMess("----------AUCTION ENDED----------");
            return 1;
        }
        return 0;
    }

    private void continueSell(){
        if(priceChanged){
            // announce new price
            priceChanged = false;
            HashMap<String, Object> map = new HashMap<>();
            map.put("price", currentPrice);
            map.put("id", currentId);
            notifyAllObservers(Notification.OFFER, map);
        }else{
            // sell item
            if(currentId > 0){
                sellItem();
            }else{
                logger.mess(this.toString(),
                        "no one bought an item");
            }
            endSell();
        }
    }

    private void sellItem(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("item", items.getFirst());
        map.put("price", currentPrice);
        map.put("id", currentId);

        notifyAllObservers(Notification.WIN, map);
        logger.mess(this.toString(),
                "SOLD to " + currentId
                        + " for " + currentPrice);
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
        currentPrice = Math.round(currItem.getValue() * 0.4 * 100.0) / 100.0;

        priceChanged = false;
        itemChosen = true;
        currentId = -1;

        // announce
        HashMap<String, Object> map = new HashMap<>();
        map.put("item", currItem);
        map.put("price", currentPrice);

        notifyAllObservers(Notification.START, map);
        logger.cleanMess("");
        logger.mess(this.toString(),
                "START selling " + currItem.getType()
                        + " for " + currentPrice);
    }

    public void activate(){
        // say you're opening auction
        HashMap<String, Object> map = new HashMap<>();
        map.put("auction", this);

        notifyAllObservers(Notification.START_AUCTION, map);
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
                double newOffer = (Double) map.get("price");
                int id = (Integer) map.get("id");
                if(newOffer > currentPrice && currentId != id){
                    currentPrice = Math.round(newOffer * 100.0) / 100.0;
                    currentId = id;
                    priceChanged = true;
                    logger.mess(this.toString(),
                            "new offer from " + id + ". " + newOffer);
                }
                break;

            default:
                // nothing
        }
    }


    @Override
    public String toString() {
        return "Auction";
    }
}

