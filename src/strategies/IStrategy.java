package strategies;

public interface IStrategy {

    // strategies need currPrice, budget, if currId != myId
    public void execute();
}
