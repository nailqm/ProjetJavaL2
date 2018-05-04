package Ile.joueur;
import Ile.modele.*;
/**
 * Classe Joueur
 */
public class Joueur {
    public int px, py;
    public Modele modele;
    public direction d;
    public enum direction {UP, DOWN, LEFT, RIGHT}
    public boolean death;

    public Joueur(Modele modele, int x, int y) {
        this.px = x;
        this.py = y;
        this.modele = modele;
        this.d = null;
        this.death = false;
    }

    public void move(direction d) {
        switch (d) {
            case UP:
                if ((this.px - 1) >= 1) {
                    py -= 1;
                }
                break;

            case DOWN:
                if ((this.py + 1) <= this.modele.HAUTEUR) {
                    py += 1;
                }
                break;

            case LEFT:
                if ((this.px - 1) >= 1) {
                    px -= 1;
                }
                break;
            case RIGHT:
                if ((this.px + 1) <= this.modele.LARGEUR) {
                    px += 1;
                }
                break;
        }
    }



}