package auction;

import observers.ObservedSubject;
import people.Person;

import java.util.ArrayList;

public class AuctionHouse {
    // creates auction and feeds with participants

    protected ArrayList<Auction> auctions;
    private ObservedSubject publisher;
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
