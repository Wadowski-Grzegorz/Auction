import auction.Auction;
import auction.AuctionHouse;
import items.Item;
import people.Participant;
import people.Person;
import people.User;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){

        // Create auctionHouse and auctions
        AuctionHouse auctionHouse = new AuctionHouse();
        auctionHouse.createAuctions(2, 5);

        // Create people
        ArrayList<Person> people = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            people.add(new Participant());
        }
        people.add(new User());

        // Ask people if they want to participate to auctions
        auctionHouse.feedAuctions(people);

        // Start auctions
        auctionHouse.start();

        ArrayList<Auction> auctions = auctionHouse.getAuctions();
        for(Auction auc: auctions){
            ArrayList<Item> items = auc.getItems();
            for(Item it: items){
                System.out.println(it);
            }
        }
    }
}
