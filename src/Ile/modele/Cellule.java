package Ile.modele;

/**
 * D¨¦finition d'une classe pour les cellules.
 * Cette classe fait encore partie du mod¨¨le.
 */
public class Cellule {
    /**
     * On conserve un pointeur vers la classe principale du modele.
     */
    private Modele modele;

    public State etat;
    public Event eve;
    /**
     * On stocke les coordonnees pour pouvoir les passer au modele lors
     * de l'appel a [compteVoisines].
     */
    private final int x, y;
    private boolean prochainEtat;

    public Cellule(Modele modele, int x, int y) {
        this.modele = modele;
        this.etat = State.normale;
        this.eve = Event.no;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean getEtat() {
        return this.etat == State.normale || this.etat == State.inondee;
    }

}
/**
 * Fin de la classe Ile.Cellule, et du modele en general.
 */