import auction.AuctionHouse;
import communication.Log;
import items.Item;
import people.Participant;
import people.Person;
import people.User;

import java.util.ArrayList;

public class AppManager {

    private Log logger = Log.getInstance();
    private AuctionHouse auctionHouse;
    private ArrayList<Person> people;

    public void setup(int numberOfAuctions, int numberOfItems, int wantedItems, double budget, boolean withUser) {
        // Create auctionHouse and auctions
        auctionHouse = new AuctionHouse();
        auctionHouse.createAuctions(numberOfAuctions, numberOfItems);

        // Create people
        people = new ArrayList<>();
        if(withUser){
            people.add(new User(logger.getId(), wantedItems, budget));
        }
        for (int i = 0; i < 3; i++) {
            people.add(new Participant(logger.getId(), wantedItems, budget));
        }

        // Ask people if they want to participate in auctions
        auctionHouse.feedAuctions(people);

        // Logging before auctions
        logger.cleanMess("Before auctions:");
        for (Person p : people) {
            p.log();
        }
    }

    public void start(){
        // Auction loop
        int result = 0;
        while (result == 0) {
            result = auctionHouse.step();
            for (Person p : people) {
                p.step();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void end(){
        // Logging after auctions
        logger.cleanMess("After auctions:");
        for (Person p : people) {
            p.log();
        }

        auctionHouse = null;
        people = null;
    }
}
