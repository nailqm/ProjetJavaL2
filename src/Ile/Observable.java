package Ile;

import java.util.ArrayList;

/**
 * Classe des objets pouvant être observés.
 */
abstract class Observable {
    /**
     * On a une liste [observers] d'observateurs, initialement vide, à laquelle
     * viennent s'inscrire les observateurs via la méthode [addObserver].
     */
    private ArrayList<Observer> observers;
    public Observable() {
        this.observers = new ArrayList<Observer>();
    }
    public void addObserver(Observer o) {
        observers.add(o);
    }

    /**
     * Lorsque l'etat de l'objet observe change, il est convenu d'appeler la
     * methode [notifyObservers] pour prevenir l'ensemble des observateurs
     * enregistres.
     * On le fait ici concretement en appelant la methode [update] de chaque
     * observateur.
     */
    public void notifyObservers() {
        for(Observer o : observers) {
            o.update();
        }
    }
}
/** Fin du schéma observateur/observé. */