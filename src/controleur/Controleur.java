package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modele.*;

/**
 * Classe pour notre contrôleur.
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
     * englobante [vue.VueCommandes].
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
            case "HAUT":
                modele.deplace(Deplace.HAUT);
                break;
            case "BAS":
                modele.deplace(Deplace.BAS);
                break;
            case "GAUCHE":
                modele.deplace(Deplace.GAUCHE);
                break;
            case "DROITE":
                modele.deplace(Deplace.DROITE);
                break;
        }
    }
}