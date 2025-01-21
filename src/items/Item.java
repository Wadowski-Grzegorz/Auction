package items;

import java.util.Random;


public class Item {
    String name;
    ItemType type;

    double value;
    double realValue;

    public Item(ItemType type, double value) {
        // specific item
        Random random = new Random();

        this.type = type;

        this.value = Math.round(value * 100.0) / 100.0;

        if(type.getChance() < random.nextDouble()){
            this.realValue = type.getRatio() * value;
        } else{
            this.realValue = value;
        }

        this.realValue = Math.round(this.realValue * 100.0) / 100.0;
    }

    public Item(){
        // random item
        Random random = new Random();

        ItemType[] values = ItemType.values();
        int randomIndex = random.nextInt(values.length);
        this.type = values[randomIndex];

        this.value = random.nextDouble() * 190 + 10;
        this.value = Math.round(this.value * 100.0) / 100.0;

        if(type.getChance() > random.nextDouble()){
            this.realValue = type.getRatio() * value;
        } else{
            this.realValue = value;
        }

        this.realValue = Math.round(this.realValue * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", value=" + value +
                ", realValue=" + realValue +
                '}';
    }
}
