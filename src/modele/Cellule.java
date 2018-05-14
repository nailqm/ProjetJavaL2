package modele;

import java.util.Random;

/**
 * Définition d'une classe pour les cellules.
 * Cette classe fait encore partie du modèle.
 */
public class Cellule {

    // On conserve un pointeur vers la classe principale du modele.
    public Modele modele;

    // Coordonnees
    private final int x, y;

    // Etat de la Cellule
    public Etat etat;

    // Evenement de la Cellule
    private Event event;
    public boolean hasArtefact;

    public Cellule(Modele modele, int x, int y) {
        this.modele = modele;
        this.x = x;
        this.y = y;
        this.etat = Etat.normale;
        this.event = Event.NA;
        this.hasArtefact = false;
    }

    /* Les opérations sur "état" */

    public boolean isInondee() {
        return etat == Etat.inondee;
    }

    public boolean isSubmergee() {
        return etat == Etat.submergee;
    }

    /* getter & setter */

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    /* Les operations sur "evenement" */
    public Event getEvent() {
        return event;
    }
    public Event setEvent(Event e) {this.event = e; return e;}


    public int getX(){return this.x;}
    public int getY(){return this.y;}

    /* === Fonctionnalités avancées === */

//    public void rdArtefact(){
//        int p = new Random().nextInt(4);
//        if(p==0){
//
//        }
//    }

    // Lien entre artefacts et cles
    public static int cleToArtefact(Event e) {
        switch (e) {
            case Air:
                return 0;
            case Eau:
                return 1;
            case Terre:
                return 2;
            case Feu:
                return 3;
            case NA:
            default:
                return 4;
        }
    }
}