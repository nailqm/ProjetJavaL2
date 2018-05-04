package Ile;

/**
 * Définition d'une classe pour les cellules.
 * Cette classe fait encore partie du modèle.
 */
class Cellule {
    /**
     * On conserve un pointeur vers la classe principale du modele.
     */
    private Modele modele;

    protected State.Etat etat;
    /**
     * On stocke les coordonnees pour pouvoir les passer au modele lors
     * de l'appel a [compteVoisines].
     */
    private final int x, y;
    private boolean prochainEtat;

    public Cellule(Modele modele, int x, int y) {
        this.modele = modele;
        this.etat = State.Etat.normale;
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
        return this.etat == State.Etat.normale || this.etat == State.Etat.inondee;
    }

}
/**
 * Fin de la classe Ile.Cellule, et du modele en general.
 */