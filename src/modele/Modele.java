package modele;

import java.util.Random;

/**
 * Le modèle : le coeur de l'application.
 *
 * Le modèle étend la classe [Observable] : il va posseder un certain nombre
 * d'observateurs (ici, un : la partie de la vue responsable de l'affichage)
 * et devra les prevenir avec [notifyObservers] lors des modifications.
 */

public class Modele extends Observable {
    /**
     * On fixe la taille de la grille.
     */
    public static final int HAUTEUR = 6, LARGEUR = 6;

    /**
     * Ajouter les Joueurs
     */
    public Joueur[] joueurs;

    /**
     * Identification du joueur
     */
    public int idJoueur;
    /**
     * Tour de Jeu
     */
    public int tour;
    public int moveRest;

    public int nbSubmergee;
    /**
     * On stocke un tableau de cellules.
     */
    private Cellule[][] cellules;

    /**
     * Construction : on initialise un tableau de cellules.
     */
    public Modele(int idJoueur) {
        /**
         * Pour éviter les problèmes aux bords, on ajoute une ligne et une
         * colonne de chaque côté, dont les cellules n'évolueront pas.
         */
        cellules = new Cellule[HAUTEUR + 2][LARGEUR + 2];
        for (int i = 0; i < LARGEUR + 2; i++) {
            for (int j = 0; j < HAUTEUR + 2; j++) {
                cellules[i][j] = new Cellule(this, i, j);
            }
        }
        this.idJoueur = idJoueur;
        this.joueurs = new Joueur[idJoueur];
        this.tour = 0;
        this.moveRest = 3;
        init();
    }


    /**
     * Initialisation des cellules, exceptées celle des bords qui ont
     * été ajoutés.
     */
    public void init() {
        for (int i = 1; i <= LARGEUR; i++) {
            for (int j = 1; j <= HAUTEUR; j++) {
                cellules[i][j].etat = Etat.normale;
            }
        }
    }

    public void iniEvent(Cellule c, Event e) {
        c.setEvent(e);
    }

    /**
     * Calcul de la cellule suivante
     */
    public void rdInondee() {
        Random rd = new Random();
        int x = rd.nextInt(LARGEUR) + 1;
        int y = rd.nextInt(HAUTEUR) + 1;

        while (this.cellules[x][y].isSubmergee()) {
            x = rd.nextInt(LARGEUR) + 1;
            y = rd.nextInt(LARGEUR) + 1;
        }
        if (this.cellules[x][y].isInondee()) {
            this.cellules[x][y].setEtat(Etat.submergee);
        } else {
            this.cellules[x][y].setEtat(Etat.inondee);
        }
        /** Quitter si toutes les case est submergee */
        if(this.cellules[x][y].isSubmergee()){
            nbSubmergee++;
        }
        // System.out.println("Test Inondee: (" + x + "," + y + ")");
    }

    public void rdmCle() {
        int rdCle = new Random().nextInt(5);
        if (rdCle < 4) {
            this.joueurs[tour].getCle(rdCle);
            System.out.println("Cle Obtenue: " + rdCle);
        } else {
            System.out.println("Pas de cle.");
        }
    }

    public void finDeTour() {
        /**  */
        if(nbSubmergee==36){
            System.exit(0);
        }else if (nbSubmergee < LARGEUR * HAUTEUR){
        for (int i = 0; i < 3; i++) {
            rdInondee();
        }
}
        /** A la fin de tour, joueurs peuvent obtenir une cle */
        rdmCle();

        moveRest = 3;
        if (this.tour + 1 == this.idJoueur) {
            this.tour = 0;
        } else {
            this.tour += 1;
        }

        /**Pour finir, le modele ayant change, on signale aux observateurs
         * qu'ils doivent se mettre a jour.
         */
        notifyObservers();
    }

    public void assecher(Cellule c) {
        if (c.isInondee()) {
            cellules[c.getX()][c.getY()].setEtat(Etat.normale);
        }  else {
            System.out.println("Assecher impossible.");
        }
    }

    public void assecherDble(Cellule c){
        if (c.isInondee()){
            cellules[c.getX()][c.getY()].setEtat(Etat.normale);
        }else if (c.isSubmergee()) {
            cellules[c.getX()][c.getY()].setEtat(Etat.inondee);
        }else {
            System.out.println("Assecher impossible.");
        }
    }

    public boolean hasArtefact(Event e) {
        Joueur j = this.getJoueur(tour);
        Cellule currentCellule = getCellule(j.px + 1, j.py + 1);
        if (j.cle[currentCellule.cleToArtefact(currentCellule.getEvent())]) {
            j.getEvent(e);
            return true;
        } else {
            return false;
        }
    }

    public void actionRest() {
        moveRest -= 1;
        System.out.println("Nb de mouvement reste: " + moveRest);
    }

    /**
     * Une méthode pour renvoyer la cellule aux coordonnées choisies (sera
     * utilisée par la vue).
     */
    public Cellule getCellule(int x, int y) {
        return cellules[x][y];
    }

    public Joueur getJoueur(int id) {
        return joueurs[id];
    }
    /**
     * Notez qu'à l'intérieur de la classe [Modele], la classe interne est
     * connue sous le nom abrégé [modele.Cellule]. Son nom complet est
     * [Modele.modele.Cellule], et cette version complète est la seule à pouvoir être
     * utilisée depuis l'extérieur de [Modele]. Dans [Modele], les deux
     * fonctionnent.
     */
}