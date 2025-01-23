package auction;

import people.Person;

import java.util.ArrayList;
import java.util.LinkedList;

public class AuctionHouse {
    // creates auctions and feed with participants
    // one auction at the same time, in order

    protected LinkedList<Auction> auctions;
    boolean openAuction;

    public AuctionHouse(){
        auctions = new LinkedList<>();
    }

    public void createAuctions(int numberOfAuctions, int numberOfItems){
        openAuction = false;
        for(int i = 0; i < numberOfAuctions; i++){
            auctions.add(new Auction(numberOfItems));
        }
    }

    public void feedAuctions(ArrayList<Person> people){
        // ask people if they want to join auctions
        for(Person p : people){
            for(Auction a : auctions){
                boolean want = p.wantAuction(a.getItems());
                if(want){
                    a.addObserver(p);
                }
            }
        }
    }

    public int step(){
        if(!openAuction){
            // there are no open auctions
            if(auctions.isEmpty()){
                // there are no more auctions
                return 1;
            }else {
                // open auction
                openAuction = true;
                auctions.getFirst().activate();
            }
        }

        // auction make a move
        if(auctions.getFirst().step() == 1){
            // auction ended
            auctions.remove();
            openAuction = false;
            return 0;
        }

        return 0;
    }

    public LinkedList<Auction> getAuctions() {
        return auctions;
    }
}
