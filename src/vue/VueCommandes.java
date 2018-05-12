package vue;

import javax.swing.*;
import modele.*;
import controleur.*;
/**
 * Une classe pour représenter la zone contenant le bouton.
 *
 * Cette zone n'aura pas à être mise à jour et ne sera donc pas un observateur.
 * En revanche, comme la zone précédente, celle-ci est un panneau [JPanel].
 */
class VueCommandes extends JPanel {
    /**
     * Pour que le bouton puisse transmettre ses ordres, on garde une
     * référence au modèle.
     */
    private Modele modele;

    /** Constructeur. */
    public VueCommandes(Modele modele) {
        this.modele = modele;
        /**
         * On crée un nouveau bouton, de classe [JButton], en précisant le
         * texte qui doit l'étiqueter.
         * Puis on ajoute ce bouton au panneau [this].
         */

        JButton finDeTour = new JButton("fin de tour");
        this.add(finDeTour);

        /**
         * Variante : une lambda-expression qui évite de créer une classe
         * spécifique pour un contrôleur simplissime.
         *
         JButton boutonAvance = new JButton(">");
         this.add(boutonAvance);
         boutonAvance.addActionListener(e -> { modele.avance(); });
         *
         */
        JButton HAUT = new JButton("↑");
        this.add(HAUT);
        JButton BAS = new JButton("↓");
        this.add(BAS);
        JButton GAUCHE = new JButton("←");
        this.add(GAUCHE);
        JButton DROITE = new JButton("→");
        this.add(DROITE);
        /**
         * Le bouton, lorsqu'il est cliqué par l'utilisateur, produit un
         * événement, de classe [ActionEvent].
         *
         * On a ici une variante du schéma observateur/observé : un objet
         * implémentant une interface [ActionListener] va s'inscrire pour
         * "écouter" les événements produits par le bouton, et recevoir
         * automatiquements des notifications.
         * D'autres variantes d'auditeurs pour des événements particuliers :
         * [MouseListener], [KeyboardListener], [WindowListener].
         *
         * Cet observateur va enrichir notre schéma Modèle-vue.Vue d'une couche
         * intermédiaire Contrôleur, dont l'objectif est de récupérer les
         * événements produits par la vue et de les traduire en instructions
         * pour le modèle.
         * Cette strate intermédiaire est potentiellement riche, et peut
         * notamment traduire les mêmes événements de différentes façons en
         * fonction d'un état de l'application.
         * Ici nous avons un seul bouton réalisant une seule action, notre
         * contrôleur sera donc particulièrement simple. Cela nécessite
         * néanmoins la création d'une classe dédiée.
         */
        Controleur ctrl = new Controleur(modele);
        /** Enregistrement du contrôleur comme auditeur du bouton. */

        finDeTour.addActionListener(ctrl);
        finDeTour.setActionCommand("finDeTour");

        HAUT.addActionListener(ctrl);
        HAUT.setActionCommand("HAUT");

        BAS.addActionListener(ctrl);
        BAS.setActionCommand("BAS");

        GAUCHE.addActionListener(ctrl);
        GAUCHE.setActionCommand("GAUCHE");

        DROITE.addActionListener(ctrl);
        DROITE.setActionCommand("DROITE");

    }
}
/** Fin de la vue. */