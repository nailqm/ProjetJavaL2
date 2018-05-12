package vue;

import javax.swing.*;
import java.awt.*;

import modele.*;

/**
 * Une classe pour représenter la zone d'affichage des cellules.
 * <p>
 * JPanel est une classe d'éléments graphiques, pouvant comme JFrame contenir
 * d'autres éléments graphiques.
 * <p>
 * Cette vue va être un observateur du modèle et sera mise à jour à chaque
 * nouvelle génération des cellules.
 */
class VueGrille extends JPanel implements Observer {
    /**
     * On maintient une référence vers le modèle.
     */
    private Modele modele;
    /**
     * Définition d'une taille (en pixels) pour l'affichage des cellules.
     */
    private final static int TAILLE = 80;

    public Cellule joueur;

    /**
     * Constructeur.
     */
    public VueGrille(Modele modele) {
        this.modele = modele;
        /** On enregistre la vue [this] en tant qu'observateur de [modele]. */
        modele.addObserver(this);
        /**
         * Définition et application d'une taille fixe pour cette zone de
         * l'interface, calculée en fonction du nombre de cellules et de la
         * taille d'affichage.
         */
        Dimension dim = new Dimension(TAILLE * Modele.LARGEUR + 2, TAILLE * Modele.HAUTEUR + 2);
        this.setPreferredSize(dim);
        this.joueur = new Cellule(modele, modele.j.px, modele.j.py);
    }

    /**
     * L'interface [Observer] demande de fournir une méthode [update], qui
     * sera appelée lorsque la vue sera notifiée d'un changement dans le
     * modèle. Ici on se content de réafficher toute la grille avec la méthode
     * prédéfinie [repaint].
     */
    public void update() {
        repaint();
    }

    /**
     * Les éléments graphiques comme [JPanel] possèdent une méthode
     * [paintComponent] qui définit l'action à accomplir pour afficher cet
     * élément. On la redéfinit ici pour lui confier l'affichage des cellules.
     * <p>
     * La classe [Graphics] regroupe les éléments de style sur le dessin,
     * comme la couleur actuelle.
     */
    public void paintComponent(Graphics g) {
        super.repaint();
        /** Pour chaque cellule... */
        for (int i = 0; i < Modele.LARGEUR + 2; i++) {
            g.setColor(Color.WHITE);
            g.fillRect(i * TAILLE, 0, TAILLE, TAILLE);
        }

        for (int i = 0; i < Modele.LARGEUR + 2; i++) {
            g.setColor(Color.WHITE);
            g.fillRect(i * TAILLE, (Modele.HAUTEUR + 2 - 1) * TAILLE, TAILLE, TAILLE);
        }

        for (int j = 0; j < Modele.HAUTEUR + 2; j++) {
            g.setColor(Color.WHITE);
            g.fillRect(0, j * TAILLE, TAILLE, TAILLE);
        }

        for (int j = 0; j < Modele.HAUTEUR + 2; j++) {
            g.setColor(Color.WHITE);
            g.fillRect((Modele.LARGEUR + 2 - 1) * TAILLE, j * TAILLE, TAILLE, TAILLE);
        }

        for (int i = 1; i <= Modele.LARGEUR; i++) {
            for (int j = 1; j <= Modele.HAUTEUR; j++) {
                /**
                 * ... Appeler une fonction d'affichage auxiliaire. On lui
                 * fournit les informations de dessin [g] et les coordonnees du
                 * coin en haut a gauche.
                 */
                paint(g, modele.getCellule(i, j), i * TAILLE, j * TAILLE);
            }
        }

        paintJoueur(g);

        //paint(g, joueur, this.joueur.getX()*TAILLE, this.joueur.getY()*TAILLE);
    }

    public void paintJoueur(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(this.modele.j.px * TAILLE,
                this.modele.j.py * TAILLE,
                (int) (0.5 * TAILLE), (int) (0.5 * TAILLE));
    }

    /**
     * Fonction auxiliaire de dessin d'une cellule.
     * Ici, la classe [Cellule] ne peut être désignée que par l'intermédiaire
     * de la classe [Modele] à laquelle elle est interne, d'où le type
     * [Modele.Cellule].
     * Ceci serait impossible si [Cellule] était déclarée privée dans [Modele].
     */
    private void paint(Graphics g, Cellule c, int x, int y) {
        // Sélection d'une couleur.
        if (c.etat == Etat.normale) {
            g.setColor(Color.WHITE);
        } else if (c.etat == Etat.inondee) {
            g.setColor(Color.CYAN);
        } else {
            g.setColor(Color.GRAY);
        }
        // Coloration d'un rectangle.
        g.fillRect(x, y, TAILLE, TAILLE);
        g.setColor(Color.BLACK);
        g.fillRect(x, y, TAILLE, 1);
        g.fillRect(x, y, 1, TAILLE);
        g.fillRect(x + TAILLE, y, 1, TAILLE);
        g.fillRect(x, y + TAILLE, TAILLE, 1);

        paintJoueur(g);

    }
}