package mvvm.mvvmpuro.viewmodel;

import mvvm.mvvmpuro.binding.Observer;
import java.util.ArrayList;
import java.util.List;

public class Observable {
    
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer){
        if (observer != null) {
              observers.add(observer);
        }
      
    }

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    public void notifyObservers(String propertyName,Object oldValue, Object newValue){
        for(Observer observer: observers){
            observer.onPropertyChanged(propertyName, oldValue, newValue)
            ;
        }
    }



}
