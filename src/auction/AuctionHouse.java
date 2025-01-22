package auction;

import people.Person;

import java.util.ArrayList;

public class AuctionHouse {
    // creates auctions and feed with participants
    // one auction at time in order

    protected ArrayList<Auction> auctions;

    public AuctionHouse(){
        auctions = new ArrayList<>();
    }

    public void createAuctions(int numberOfAuctions, int numberOfItems){
        for(int i = 0; i < numberOfAuctions; i++){
            auctions.add(new Auction(numberOfItems));
        }
    }

    public void feedAuctions(ArrayList<Person> people){

    }

    public void start(){

    }

    public ArrayList<Auction> getAuctions() {
        return auctions;
    }
}
