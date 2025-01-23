package strategies;

import java.util.Random;

public class AggresiveStrategy implements IStrategy{
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

        double ratio = 0.5 * currOffer / firstOffer;

        Random rand = new Random();
        double chance = rand.nextDouble();

        if(chance * ratio < 0.1){
            newOffer = currOffer * 1.4;
        }else if(chance * ratio < 0.2){
            newOffer = currOffer * 1.3;
        }else if (chance * ratio < 0.9){
            newOffer = currOffer * 1.2;
        }else{
            newOffer = 0.0;
        }

        return Math.round(Math.min(newOffer, budget) * 100.0) / 100.0;
    }
}
