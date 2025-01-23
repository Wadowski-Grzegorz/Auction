package people;


import auction.Auction;
import communication.UserInteract;
import items.Item;
import observers.Notification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class User extends Person{

    boolean activeAuction;

    public User(int id, int wantedCount, double budgetSet){
        super(id, wantedCount, budgetSet);
        UserInteract.tellId(id);
        activeAuction = false;
    }

    @Override
    public boolean wantAuction(LinkedList<Item> items){
        // ask user if he wants to be at auction
        UserInteract.mess("Items you want to get: " + getString(wantedItems));
        return UserInteract.getBool("Items in auction: " + getString(items)
                + "\nDo you want to participate in this auction? ");
    }

    @Override
    public void step() {
        // user input
        if(!activeAuction || currId == id){
            return;
        }

        double answer = 0.0;
        while(true){
            answer = UserInteract.getPositiveDouble("Type in your offer, 0 for no offer."
                    + "\nCurrent budget " + budget + ".");
            answer = Math.round(answer * 100.0) / 100.0;
            if(answer > budget){
                UserInteract.mess("Not enough money. Try again.");
            }else{
                break;
            }
        }

        if(answer != 0.0){
            HashMap<String, Object> map = new HashMap<>();
            map.put("price", answer);
            map.put("id", id);
            notifyObserver(observer, Notification.OFFER, map);
        }
    }

    @Override
    public void update(Notification notify, HashMap<String, Object> map) {
        // check notification type and act adequately
        switch(notify){
            case Notification.START_AUCTION:
                // prepare for new auction
                Auction mapAuction = (Auction) map.get("auction");
                addObserver(mapAuction);
                break;

            case Notification.START:
                UserInteract.mess("Your auction started!");
                activeAuction = true;
                currId = -1;
                break;

            case Notification.END:
                activeAuction = false;
                break;

            case Notification.OFFER:
                // tell user new offer
                currId = (Integer) map.get("id");
                break;

            case Notification.WIN:
                // if you're a winner, set item as yours
                Integer mapId = (Integer) map.get("id");
                if(id == mapId){
                    Item wonItem = (Item) map.get("item");
                    Double cost = (Double) map.get("price");

                    boughtItem(wonItem, cost);
                    UserInteract.mess("You bought an item.");
                }
                break;

            default:
                // nothing
        }
    }

    @Override
    public String toString() {
        return "User " + id;
    }

}
