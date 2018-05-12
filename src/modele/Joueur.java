package modele;

import java.util.HashSet;
import java.util.Random;

public class Joueur {
    // Position du Joueur
    public int px, py;

    // Modele du Jeu
    public Modele modele;

    // Deplacement du Joueur
    public Deplace d;

    // Les Artifacts et cles
    public HashSet<Event> artifacts;
    public HashSet<Event> cles;

    // check deplacement
    public boolean isMove;

    public Joueur(Modele modele, int x, int y) {
        this.px = x;
        this.py = y;
        this.modele = modele;
        this.d = null;

        this.artifacts = new HashSet<Event>();
        this.cles = new HashSet<Event>();
    }

    // Deplacement du Joueur
    public void move(Deplace d) {
        switch (d) {
            case HAUT:
                if ((this.px - 1) >= 1) {
                    if (!modele.getCellule(px, py - 1).isSubmergee() && !this.isMove) {
                        py -= 1;
                        isMove = true;
                    } else if (this.isMove) {
                        System.out.println("Deja Deplace H!");
                    } else {
                        System.out.println("Submergee! Deplacement Haut Impossible.");
                    }
                }
                break;

            case BAS:
                if ((this.py + 1) <= this.modele.HAUTEUR) {
                    if (!modele.getCellule(px, py + 1).isSubmergee() && !this.isMove) {
                        py += 1;
                        isMove = true;
                    } else if (this.isMove) {
                        System.out.println("Deja Deplace B!");
                    } else {
                        System.out.println("Submergee! Deplacement Bas Impossible.");
                    }
                }
                break;

            case GAUCHE:
                if ((this.px - 1) >= 1) {
                    if (!modele.getCellule(px - 1, py).isSubmergee() && !this.isMove) {
                        px -= 1;
                        isMove = true;
                    } else if (this.isMove) {
                        System.out.println("Deja Deplace G!");
                    } else {
                        System.out.println("Submergee! Deplacement Gauche Impossible.");
                    }
                }
                break;

            case DROITE:
                if ((this.px + 1) <= this.modele.LARGEUR) {
                    if (!modele.getCellule(px + 1, py).isSubmergee() && !this.isMove) {
                        px += 1;
                        isMove = true;
                    } else if (this.isMove) {
                        System.out.println("Deja Deplace D!");
                    } else {
                        System.out.println("Submergee! Deplacement Droite Impossible.");
                    }
                }
                break;
        }
    }


    /* === Fonctionnalités avancées === */
    // Obtenir les cles aleatoirement
    public void rdgetCle() {
        // 50% de probabilité d'obtenir la clé
        int p = new Random().nextInt(2);
        while (p == 1) {
            // Choisissez l'une des quatre clés
            int q = new Random().nextInt(4);
            switch (q) {
                case 0:
                    this.cles.add(Event.Air);
                    System.out.println("Cle Air");
                    break;
                case 1:
                    this.cles.add(Event.Eau);
                    System.out.println("Cle Eau");
                    break;
                case 2:
                    this.cles.add(Event.Feu);
                    System.out.println("Cle Feu");
                    break;
                case 3:
                    this.cles.add(Event.Terre);
                    System.out.println("Cle Terre");
                    break;
            }
        }
    }

    // recuprer le Artifact avec cle
    public void getArti(Event e) {
        this.artifacts.add(e);
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