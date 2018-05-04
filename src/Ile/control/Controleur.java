package Ile.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Ile.modele.*;
import Ile.joueur.*;

/**
 * Classe pour notre contrôleur rudimentaire.
 *
 * Le contrôleur implémente l'interface [ActionListener] qui demande
 * uniquement de fournir une méthode [actionPerformed] indiquant la
 * réponse du contrôleur à la réception d'un événement.
 */
public class Controleur implements ActionListener {
    /**
     * On garde un pointeur vers le modèle, car le contrôleur doit
     * provoquer un appel de méthode du modèle.
     * Remarque : comme cette classe est interne, cette inscription
     * explicite du modèle est inutile. On pourrait se contenter de
     * faire directement référence au modèle enregistré pour la classe
     * englobante [Ile.VueCommandes].
     */
    Modele modele;
    public Controleur(Modele modele) { this.modele = modele; }
    /**
     * Action effectuée à réception d'un événement : appeler la
     * méthode [avance] du modèle.
     */
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd){
            case "finDeTour":
                modele.finDeTour();
                break;
            case "UP":
                modele.deplace(Joueur.direction.UP);
                break;
            case "DOWN":
                modele.deplace(Joueur.direction.DOWN);
                break;
            case "LEFT":
                modele.deplace(Joueur.direction.LEFT);
                break;
            case"RIGHT":
                modele.deplace(Joueur.direction.RIGHT);
                break;
        }
        modele.avance();
    }
}
/** Fin du contrôleur. */