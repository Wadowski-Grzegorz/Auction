package items;

import java.util.Random;

//todo: zaokrąglanie do 2 miejsc po przecinku

public class Item {
    String name;
    ItemType type;

    double value;
    double realValue;

    public Item(ItemType type, double value) {
        Random random = new Random();
        this.type = type;
        this.value = value;
        if(type.getChance() < random.nextDouble()){
            this.realValue = type.getRatio() * value;
        } else{
            this.realValue = value;
        }
    }

    public Item(){
        Random random = new Random();

        ItemType[] values = ItemType.values();
        int randomIndex = random.nextInt(values.length);
        this.type = values[randomIndex];

        this.value = random.nextDouble() * 190 + 10;

        if(type.getChance() > random.nextDouble()){
            this.realValue = type.getRatio() * value;
        } else{
            this.realValue = value;
        }
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
