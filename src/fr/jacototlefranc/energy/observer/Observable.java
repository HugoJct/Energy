package fr.jacototlefranc.energy.observer;

public interface Observable {
    public void addObserver(Observer o);
    public void notifyObserver();
}
