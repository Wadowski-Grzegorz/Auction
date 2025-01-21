package people;


import strategies.IStrategy;

public class Participant extends Person {

    public Participant(){
        super(3, 2500);
        this.id = id_counter;
        System.out.println("Person, my id is " + this.id);

        IStrategy strategy;
    }
    @Override
    void chooseAuction() {

    }


}
