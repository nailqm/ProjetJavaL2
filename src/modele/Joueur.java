package modele;

public class Joueur {
    // Position du joueur sur la grille
    public int px, py;

    // Modele du Jeu
    public Modele modele;

    // Nom du Joueur
    public String nom;

    // Character Speciale
    public Character chCha;

    // Les artefacts portés par le joueur:
    // Air - Eau - Feu - Terre
    public boolean[] artefact;

    // Les clés portées par le joueur,
    // du même ordre que les artefacts
    public boolean[] cle;

    public Joueur(Modele modele, int x, int y, String nom) {
        this.modele = modele;
        this.px = x - 1;
        this.py = y - 1;
        this.nom = nom;
        this.artefact = new boolean[4];
        this.cle = new boolean[4];
    }

    public Joueur(Modele modele, int x, int y, String nom, Character c) {
        this.modele = modele;
        this.px = x - 1;
        this.py = y - 1;
        this.nom = nom;
        this.chCha = c;
        this.artefact = new boolean[4];
        this.cle = new boolean[4];
    }

    // Deplacement du Joueur
    public void move(Deplace d) {
        switch (d) {
            case GAUCHE:
                px -= 1;
                break;
            case DROITE:
                px += 1;
                break;
            case HAUT:
                py -= 1;
                break;
            case BAS:
                py += 1;
                break;
            case HAUT_GAUCHE:
                py -= 1;
                px -= 1;
                break;
            case HAUT_DROITE:
                py -= 1;
                px += 1;
                break;
            case BAS_GAUCHE:
                py += 1;
                px -= 1;
                break;
            case BAS_DROITE:
                py += 1;
                px += 1;
                break;
        }
    }

    // Verification de deplacement
    public boolean checkMove(Deplace d, int x, int y) {
        switch (d) {
            case GAUCHE:
                if (x > 0) {
                    if (modele.getCellule(x, y + 1).getEtat() != Etat.submergee)
                        return true;
                }
                break;
            case DROITE:
                if (x < modele.LARGEUR - 1) {
                    if (modele.getCellule(x + 1 + 1, y + 1).getEtat() != Etat.submergee)
                        return true;
                }
                break;
            case HAUT:
                if (y > 0) {
                    if (modele.getCellule(x + 1, y).getEtat() != Etat.submergee)
                        return true;
                }
                break;
            case BAS:
                if (y < modele.HAUTEUR - 1) {
                    if (modele.getCellule(x + 1, y + 1 + 1).getEtat() != Etat.submergee)
                        return true;
                }
                break;
            case HAUT_GAUCHE:
                return (checkMove(Deplace.HAUT, x, y) && checkMove(Deplace.GAUCHE, x, y));

            case HAUT_DROITE:
                return (checkMove(Deplace.HAUT, x, y) && checkMove(Deplace.DROITE, x, y));

            case BAS_GAUCHE:
                return (checkMove(Deplace.BAS, x, y) && checkMove(Deplace.GAUCHE, x, y));

            case BAS_DROITE:
                return (checkMove(Deplace.BAS, x, y) && checkMove(Deplace.BAS, x, y));

            default:
                return false;
        }
        return false;
    }

    public boolean isValide(Deplace d) {
        if (this.checkMove(d, this.px, this.py)) {
            this.move(d);
            return true;
        } else {
            System.out.println("Mouvement invalide !");
            return false;
        }
    }

    /* === Fonctionnalités avancées === */
    public void getEvent(Event e) {
        switch (e) {
            case Air:
                this.artefact[0] = true;
                break;
            case Eau:
                this.artefact[1] = true;
                break;
            case Feu:
                this.artefact[2] = true;
                break;
            case Terre:
                this.artefact[3] = true;
                break;
            default:
                break;
        }
    }

    public void getCle(int i) {
        this.cle[i] = true;
    }

    public Character getChCha() {
        return chCha;
    }

    public boolean isExplo() {
        return chCha == Character.Explorateur;
    }

    // Afficher les Evenements
    public static String affiche(Event e) {
        switch (e) {
            case Air:
                return "Air";
            case Eau:
                return "Eau";
            case Feu:
                return "Feu";
            case Terre:
                return "Terre";
            default:
                return "";
        }
    }
}