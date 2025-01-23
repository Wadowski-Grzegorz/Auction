package strategies;

import java.util.Random;

public class PassiveStrategy implements IStrategy {
    @Override
    public double execute(double budget,
                          double currOffer,
                          double firstOffer,
                          boolean isMine){

        double newOffer;

        if(currOffer >= budget){
            newOffer = 0;
            return newOffer;
        }

        double ratio = 4 * currOffer / firstOffer;

        Random rand = new Random();
        double chance = rand.nextDouble();

        if (chance * ratio < 0.4) {
            newOffer = currOffer * 1.1;
        } else {
            newOffer = 0;
        }

        return Math.round(Math.min(newOffer, budget) * 100.0) / 100.0;
    }
}
