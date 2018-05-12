package modele;

import javafx.scene.control.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Définition d'une classe pour les cellules.
 * Cette classe fait encore partie du modèle.
 */
public class Cellule {

    // On conserve un pointeur vers la classe principale du modele.
    public Modele modele;

    // Coordonnees
    public final int x, y;

    // Etat de la Cellule
    public Etat etat;

    // Evenement de la Cellule
    public Event event;

    // Cle de la Cellule
    public Event cle;

    // check Artefacts et Cles
    public boolean isEvent;
    public boolean isCle;

    public Cellule(Modele modele, int x, int y) {
        this.modele = modele;
        this.x = x;
        this.y = y;
        this.etat = Etat.normale;
        this.event = null;
        this.cle = null;
        this.isEvent = false;
        this.isCle = false;
    }

    /* Les opérations sur "état" */
    public void assecher(){ etat = Etat.normale; }
    public boolean isInondee(){return etat == Etat.inondee;}
    public boolean isSubmergee(){return etat == Etat.submergee;}


    // Reinitialiser la grille
    public void reIni(){
        this.etat = null;
        this.event = null;
        this.cle = null;
        this.isEvent = false;
        this.isCle = false;
    }

    /* === Fonctionnalités avancées === */
    // Initialiser Artefacts, choissiez l'une des quatre
    public void iniEvent(){
        int p = new Random().nextInt(4);
        switch (p){
            case 0:
                this.event = Event.Air;
                this.isEvent = true;
                break;
            case 1:
                this.event = Event.Eau;
                this.isEvent = true;
                break;
            case 2:
                this.event = Event.Feu;
                this.isEvent = true;
                break;
            case 3:
                this.event = Event.Terre;
                this.isEvent = true;
                break;
        }
    }

    // Initialiser Cles, meme methode
    public void iniCle(){
        int p = new Random().nextInt(4);
        switch (p){
            case 0:
                this.cle = Event.Air;
                this.isCle = true;
                break;
            case 1:
                this.cle = Event.Eau;
                this.isCle = true;
                break;
            case 2:
                this.cle = Event.Feu;
                this.isCle = true;
                break;
            case 3:
                this.cle = Event.Terre;
                this.isCle = true;
                break;
        }
    }


    public String affiche(){
        return String.format("(%d, %d), etat: %s, event: %s",this.x,this.y,this.etat,this.event);
    }
}