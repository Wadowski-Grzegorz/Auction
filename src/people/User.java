package people;

public class User extends Person{

    public User(){
        super(3, 2500);
        this.id = id_counter;
        System.out.println("User, my id is " + this.id);
    }

    @Override
    void chooseAuction() {

    }
}
