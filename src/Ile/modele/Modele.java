package Ile.modele;

import java.util.ArrayList;
import java.util.Random;
import Ile.observer.*;
import Ile.joueur.*;

public class Modele extends Observable {
    /**
     * On fixe la taille de la grille.
     */
    public static final int HAUTEUR = 6, LARGEUR = 6;
    /**
     * On stocke un tableau de cellules.
     */
    private Cellule[][] cellules;

    public static final int dx = LARGEUR + 2, dy = HAUTEUR + 2;

    public int heliX, heliY;
    public Joueur j = new Joueur(this,6,6);

    /**
     * Construction : on initialise un tableau de cellules.
     */
    public Modele() {
        /**
         * Pour éviter les problèmes aux bords, on ajoute une ligne et une
         * colonne de chaque côté, dont les cellules n'évolueront pas.
         */
        cellules = new Cellule[dx][dy];
        for (int i = 1; i <= LARGEUR; i++) {
            for (int j = 1; j <= HAUTEUR; j++) {
                cellules[i][j] = new Cellule(this, i, j);
            }
        }
        init();

        Random rd = new Random();
        this.heliX = 1 + rd.nextInt((LARGEUR - 1) + 1);
        this.heliY = 1 + rd.nextInt((HAUTEUR - 1) + 1);
    }

    //public void addJoueur(Joueur j){this.joueurs.add(j);}

    /**
     * Initialisation aléatoire des cellules, exceptées celle des bords qui ont
     * été ajoutés.
     */
    public void init() {
        for (int i = 1; i <= LARGEUR; i++) {
            for (int j = 1; j <= HAUTEUR; j++) {
                cellules[i][j].etat = Etat.normale;
            }
        }
    }

    public void inoRand() {

        int x = 1 + (int) (Math.random() * ((LARGEUR - 1) + 1));
        int y = 1 + (int) (Math.random() * ((HAUTEUR - 1) + 1));

        while (this.cellules[x][y].etat == Etat.submergee) {

            x = 1 + (int) (Math.random() * ((LARGEUR - 1) + 1));
            y = 1 + (int) (Math.random() * ((HAUTEUR - 1) + 1));

        }

        if (this.cellules[x][y].etat == Etat.inondee) {
            this.cellules[x][y].etat = Etat.submergee;
        } else {
            this.cellules[x][y].etat = Etat.inondee;
        }
    }

    public void finDeTour() {

        for (int i = 0; i < 3; i++) {
            inoRand();
        }

        notifyObservers();

    }

    public void deplace(Joueur.direction d) {
        this.j.move(d);
        notifyObservers();
    }


    /**
     * Calcul de la génération suivante.
     */

    public void avance() {
        /**
         * On procède en deux étapes. - D'abord, pour chaque cellule on évalue
         * ce que sera son état à la prochaine génération. - Ensuite, on
         * applique les évolutions qui ont été calculées.
         **/
        for (int i = 1; i < LARGEUR + 1; i++) {
            for (int j = 1; j < HAUTEUR + 1; j++) {
                // cellules[i][j].evalue();
            }
        }
        for (int i = 1; i < LARGEUR + 1; i++) {
            for (int j = 1; j < HAUTEUR + 1; j++) {
                // cellules[i][j].evolue();
            }
        }
        /**
         * Pour finir, le modèle ayant changé, on signale aux observateurs
         * qu'ils doivent se mettre à jour.
         **/
        notifyObservers();
    }

    /**
     * Une méthode pour renvoyer la cellule aux coordonnées choisies (sera
     * utilisée par la vue).
     */
    public Cellule getCellule(int x, int y) {
        return cellules[x][y];
    }
    /**
     * Notez qu'à l'intérieur de la classe [CModele], la classe interne est
     * connue sous le nom abrégé [Cellule]. Son nom complet est
     * [CModele.Cellule], et cette version complète est la seule à pouvoir être
     * utilisée depuis l'extérieur de [CModele]. Dans [CModele], les deux
     * fonctionnent.
     */
}