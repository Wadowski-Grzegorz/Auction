package people;

import items.Item;

import java.util.ArrayList;


abstract class Person {

    private static int id_counter = 0;
    private int id;

    private double budget;

    private ArrayList<Item> wantedItems;
    private ArrayList<Item> boughtItems;

    abstract void chooseAuction();

}
