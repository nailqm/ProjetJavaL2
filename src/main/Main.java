package main;

import modele.Character;
import vue.*;
import modele.*;

public class Main {
    public static void main(String[] args) {
        Modele modele = new Modele(4);
        modele.joueurs[0] = new Joueur(modele,2,2,"j1",Character.Explorateur);
        modele.joueurs[1] = new Joueur(modele,3,3,"j2");
        modele.joueurs[2] = new Joueur(modele,4,4,"j3");
        modele.joueurs[3] = new Joueur(modele,1,1,"j4");
        modele.iniEvent(modele.getCellule(2,2),Event.Feu);
        for (int i = 0; i < modele.idJoueur; i++) {
            System.out.println(modele.joueurs[i].nom);
        }

        Vue vue = new Vue(modele);
    }
}
