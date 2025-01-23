import auction.Auction;
import auction.AuctionHouse;
import items.Item;
import observers.Observer;
import people.Participant;
import people.Person;
import people.User;

import java.util.ArrayList;
import java.util.LinkedList;

// todo: po zakończeniu aukcji program robi dodatkowe okrążenie,
// todo: zbadaj czy to nie powoduje błędów
// zastanów się nad id, obecnie jeśli obserwator będzie z innej klasy
// niż Person, to nie zapewniam mu unikalnego id

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
//        people.add(new User());

        // Ask people if they want to participate to auctions
        auctionHouse.feedAuctions(people);


//        // List items at auction
//        LinkedList<Auction> auctions = auctionHouse.getAuctions();
//        for(Auction auc: auctions){
//            LinkedList<Item> items = auc.getItems();
//            for(Item it: items){
//                System.out.println(it);
//            }
//            System.out.println("----------");
//        }


        // loop
        // end when auctionHouse say(?)
//        while(true){
//            auctionHouse.step();
//
//            for(Person p : people){
//                p.step();
//            }
//        }
    }
}
