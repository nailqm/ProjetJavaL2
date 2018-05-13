package vue;

import javax.swing.*;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_COLOR_BURNPeer;
import com.sun.scenario.effect.impl.sw.java.JSWBoxBlurPeer;
import modele.*;
import controleur.*;

import java.awt.*;

/**
 * Une classe pour représenter la zone contenant le bouton.
 *
 * Cette zone n'aura pas à être mise à jour et ne sera donc pas un observateur.
 * En revanche, comme la zone précédente, celle-ci est un panneau [JPanel].
 */
public class VueCommandes extends JPanel {
    /**
     * Pour que le bouton puisse transmettre ses ordres, on garde une
     * référence au modèle.
     */
    private Modele modele;
    /** Initialise les button speciale */
    JButton HAUT_GAUCHE, HAUT_DROITE, BAS_GAUCHE, BAS_DROITE;
    JButton ASSEDBLE;

    /** Constructeur. */
    public VueCommandes(Modele modele) {
        this.modele = modele;
        /**
         * On crée un nouveau bouton, de classe [JButton], en précisant le
         * texte qui doit l'étiqueter.
         * Puis on ajoute ce bouton au panneau [this].
         */
        int[] joueurList;
        joueurList = new int[this.modele.idJoueur];
            for (int i = 0; i < joueurList.length; i++) {
                joueurList[i] = i+1;
            }

        /**
         * Variante : une lambda-expression qui évite de créer une classe
         * spécifique pour un contrôleur simplissime.
         *
         JButton boutonAvance = new JButton(">");
         this.add(boutonAvance);
         boutonAvance.addActionListener(e -> { modele.avance(); });
         *
         */
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3,3));

        /** Ini des bouton speciale */

        HAUT_GAUCHE = new JButton("↖");
        buttonPanel.add(HAUT_GAUCHE);

        JButton HAUT = new JButton("↑");
        buttonPanel.add(HAUT);

        HAUT_DROITE = new JButton("↗");
        buttonPanel.add(HAUT_DROITE);

        JButton GAUCHE = new JButton("←");
        buttonPanel.add(GAUCHE);

        JButton finDeTour = new JButton("●");
        buttonPanel.add(finDeTour);

        JButton DROITE = new JButton("→");
        buttonPanel.add(DROITE);

        BAS_GAUCHE = new JButton("↙");
        buttonPanel.add(BAS_GAUCHE);

        JButton BAS = new JButton("↓");
        buttonPanel.add(BAS);

        BAS_DROITE = new JButton("↘");
        buttonPanel.add(BAS_DROITE);

        this.add(buttonPanel);


        ASSEDBLE = new JButton("ASSEDBLE");

        JButton ASSECHER = new JButton("ASSECHER");
        JButton SEARCH = new JButton("SEARCH");

        Box rest = Box.createVerticalBox();
        rest.add(ASSECHER);
        rest.add(Box.createVerticalStrut(5));
        rest.add(ASSEDBLE);
        rest.add(Box.createVerticalStrut(5));
        rest.add(SEARCH);
        rest.add(Box.createVerticalStrut(5));
        this.add(rest);

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
         * pour le modèle.O
         * Cette strate intermédiaire est potentiellement riche, et peut
         * notamment traduire les mêmes événements de différentes façons en
         * fonction d'un état de l'application.
         * Ici nous avons un seul bouton réalisant une seule action, notre
         * contrôleur sera donc particulièrement simple. Cela nécessite
         * néanmoins la création d'une classe dédiée.
         */
        Controleur ctrl = new Controleur(modele,this);
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

        HAUT_GAUCHE.addActionListener(ctrl);
        HAUT_GAUCHE.setActionCommand("HAUT_GAUCHE");

        HAUT_DROITE.addActionListener(ctrl);
        HAUT_DROITE.setActionCommand("HAUT_DROITE");

        BAS_GAUCHE.addActionListener(ctrl);
        BAS_GAUCHE.setActionCommand("BAS_GAUCHE");

        BAS_DROITE.addActionListener(ctrl);
        BAS_DROITE.setActionCommand("BAS_DROITE");

        SEARCH.addActionListener(ctrl);
        SEARCH.setActionCommand("SEARCH");

        ASSECHER.addActionListener(ctrl);
        ASSECHER.setActionCommand("ASSECHER");

        ASSEDBLE.addActionListener(ctrl);
        ASSEDBLE.setActionCommand("ASSEDBLE");

        this.isIngenieur();
        this.isExplorateur();
    }

    public void isIngenieur(){
        if(this.modele.getJoueur(modele.tour).isInge()){
            ASSEDBLE.setEnabled(true);
        }else{
            ASSEDBLE.setEnabled(false);
        }
    }
    public void isExplorateur(){
        if(this.modele.getJoueur(modele.tour).isExplo()){
            HAUT_GAUCHE.setEnabled(true);
            HAUT_DROITE.setEnabled(true);
            BAS_GAUCHE.setEnabled(true);
            BAS_DROITE.setEnabled(true);
        }else{
            HAUT_GAUCHE.setEnabled(false);
            HAUT_DROITE.setEnabled(false);
            BAS_GAUCHE.setEnabled(false);
            BAS_DROITE.setEnabled(false);
        }
    }
}
/** Fin de la vue. */