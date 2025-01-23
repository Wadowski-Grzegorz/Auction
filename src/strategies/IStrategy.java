package strategies;

public interface IStrategy {

    // strategies need currPrice, budget, if currId != myId
    public double execute(double budget,
                          double currOffer,
                          double firstOffer,
                          boolean isMine);
}
