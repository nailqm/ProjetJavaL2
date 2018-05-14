package main;

import modele.Character;
import vue.*;
import modele.*;

public class Main {
    public static void main(String[] args) {
        Modele modele = new Modele(4);
        modele.joueurs[0] = new Joueur(modele, 1, 1, "j1", Character.Explorateur);
        modele.joueurs[1] = new Joueur(modele, 2, 2, "j2");
        modele.joueurs[2] = new Joueur(modele, 3, 3, "j3", Character.Ingenieur);
        modele.joueurs[3] = new Joueur(modele, 4, 4, "j4");
        modele.iniEvent(modele.getCellule(2, 2), Event.Feu);
        modele.iniEvent(modele.getCellule(1,3),Event.Air);
        modele.iniEvent(modele.getCellule(4,6),Event.Eau);
        modele.iniEvent(modele.getCellule(5,5),Event.Terre);
        for (int i = 0; i < modele.idJoueur; i++) {
            System.out.println("Joueur: " + modele.joueurs[i].nom);
        }
        System.out.print("===Artefacts===\nFeu:(2,2)\nTerre:(5,5)\nAir(1,3)\nEau(4,6)\n");

        Vue vue = new Vue(modele);
    }
}
