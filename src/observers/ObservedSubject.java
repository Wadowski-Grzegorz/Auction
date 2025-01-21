package observers;

import java.util.ArrayList;

public class ObservedSubject {


    private ArrayList<Observer> observers;

    public ObservedSubject(){
        observers = new ArrayList<>();
    }

    public void addObserver(Observer ob){
        observers.add(ob);
    }

    public void removeObserver(Observer ob){
        observers.remove(ob);
    }

    public void removeAllObservers(){
        observers.clear();
    }

    public void notifyObserver(Observer ob, Notification context){
        ob.update(context);
    }

    public void notifyAllObservers(Notification context){
        for(Observer ob: observers){
            notifyObserver(ob, context);
        }
    }


    public int getSize(){
        return observers.size();
    }

}
