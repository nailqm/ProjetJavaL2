package modele;

import java.util.HashSet;
import java.util.Random;

import javafx.util.Pair;
import vue.*;

public class Modele extends Observable {
    /**
     * On fixe la taille de la grille.
     */
    public static final int HAUTEUR = 6, LARGEUR = 6;
    public int heliX, heliY;
    /**
     * On stocke un tableau de cellules.
     */
    private Cellule[][] cellules;
    // public ArrayList<modele.Joueur> joueurs;
    public Joueur j = new Joueur(this, 6, 6);

    /**
     * Construction : on initialise un tableau de cellules.
     */
    public Modele() {
        /**
         * Pour éviter les problèmes aux bords, on ajoute une ligne et une
         * colonne de chaque côté, dont les cellules n'évolueront pas.
         */
        cellules = new Cellule[HAUTEUR + 2][LARGEUR + 2];
        for (int i = 1; i <= LARGEUR; i++) {
            for (int j = 1; j <= HAUTEUR; j++) {
                cellules[i][j] = new Cellule(this, i, j);
            }
        }
        init();

        Random rd = new Random();
        this.heliX = rd.nextInt(LARGEUR - 1);
        this.heliY = rd.nextInt(HAUTEUR - 1);
    }

    //public void addJoueur(modele.Joueur j){this.joueurs.add(j);}

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

    public void initBord() {
        for (int i = 0; i < LARGEUR + 2; i++) {
            getCellule(i, 0).reIni();
        }
        for (int i = 0; i < LARGEUR + 2; i++) {
            getCellule(i, HAUTEUR + 2 - 1).reIni();
        }
        for (int j = 0; j < HAUTEUR + 2; j++) {
            getCellule(0, j).reIni();
        }
        for (int j = 0; j < HAUTEUR + 2; j++) {
            getCellule(LARGEUR + 2 - 1, j).reIni();
        }
    }

    public Pair<Integer, Integer> geneRandom(int x, int boundx, int y, int boundy) {
        Random rd = new Random();
        return new Pair<>(x + rd.nextInt(boundx), y + rd.nextInt(boundy));
    }

    public void inonAleatoire() {
        Random rd = new Random();
        int x = 1 + rd.nextInt((LARGEUR - 1) + 1);
        int y = 1 + rd.nextInt((HAUTEUR - 1) + 1);
        while (this.cellules[x][y].etat == Etat.submergee) {
            x = 1 + rd.nextInt((LARGEUR - 1) + 1);
            y = 1 + rd.nextInt((HAUTEUR - 1) + 1);
        }

        if (this.cellules[x][y].etat == Etat.inondee) {
            this.cellules[x][y].etat = Etat.submergee;
        } else {
            this.cellules[x][y].etat = Etat.inondee;
        }
    }

    public void finDeTour() {
        for (int i = 0; i < 3; i++) {
            inonAleatoire();
        }
        notifyObservers();
    }

    public void deplace(Deplace d) {
        this.j.move(d);
        notifyObservers();
    }

    public void eventAleatoire() {
        Random rd = new Random();

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
     * connue sous le nom abrégé [modele.Cellule]. Son nom complet est
     * [CModele.modele.Cellule], et cette version complète est la seule à pouvoir être
     * utilisée depuis l'extérieur de [CModele]. Dans [CModele], les deux
     * fonctionnent.
     */
}