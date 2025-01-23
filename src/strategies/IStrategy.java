package strategies;

public interface IStrategy {

    // strategies return 0 if they don't want to raise
    public double execute(double budget,
                          double currOffer,
                          double firstOffer,
                          boolean isMine);
}
