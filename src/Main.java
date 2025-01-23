import auction.Auction;
import auction.AuctionHouse;
import communication.Log;
import items.Item;
import observers.Observer;
import people.Participant;
import people.Person;
import people.User;

import java.util.ArrayList;
import java.util.LinkedList;

// todo:



public class Main {

    static Log logger = Log.getInstance();
    public static void main(String[] args){

        // Create auctionHouse and auctions
        AuctionHouse auctionHouse = new AuctionHouse();
        auctionHouse.createAuctions(2, 5);

        // Create people
        ArrayList<Person> people = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            people.add(new Participant(logger.getId()));
        }
//        people.add(new User());

        // Ask people if they want to participate to auctions
        auctionHouse.feedAuctions(people);

        logger.cleanMess("Before auctions:");
        for(Person p: people){
            p.log();
        }

        // loop
        // end when auctionHouse say(?)
        int result = 0;
        while(result == 0){
            result = auctionHouse.step();

            for(Person p : people){
                p.step();
            }
        }

        logger.cleanMess("After auctions:");
        for(Person p: people){
            p.log();
        }
    }
}
