package items;

import java.util.Random;

public enum ItemType {
    JEWELERY(0.2, 1.5),
    BOOK(0.3, 2.0),
    ANTIQUE(0.8, 3),
    ELECTRONICS(0, 0),
    PAINTING(0.6, 1.3),
    TOY(0.1, 0.1),
    MUSICAL_INSTRUMENT(0.2, 0.1),
    TABLEWARE(0.2, 0.2);

    private final double chance;
    private final double ratio;


    ItemType(double chance, double ratio){
        this.chance = chance;
        this.ratio = ratio;
    }

    public double getChance(){
        return chance;
    }

    public double getRatio(){
        return ratio;
    }
}
