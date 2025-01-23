package strategies;

public class DummyStrategy implements IStrategy{
    @Override
    public double execute(double budget,
                          double currOffer,
                          double firstOffer,
                          boolean isMine){
        // do nothing - starting strategy
        return 0.0;
    }
}
