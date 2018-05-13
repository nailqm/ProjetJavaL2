package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.*;
import vue.*;

import javax.swing.*;

/**
 * Classe pour notre contrôleur.
 * <p>
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
    VueCommandes vueCommandes;

    public Controleur(Modele modele, VueCommandes v) {
        this.modele = modele;
        this.vueCommandes = v;
    }

    /**
     * Action effectuée à réception d'un événement : appeler la
     * méthode [avance] du modèle.
     */
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        Joueur j = this.modele.getJoueur(modele.tour);
        int px = this.modele.getJoueur(modele.tour).px + 1;
        int py = this.modele.getJoueur(modele.tour).py+1;

        if (cmd.equals("finDeTour")) {
            modele.finDeTour();
            vueCommandes.isExplorateur();
        }

        if (this.modele.moveRest == 0) {
            System.out.println("Deplacer impossible (MAX 3).");
            return;
        }

        if (cmd.equals("HAUT")) {
            if(j.isValide(Deplace.HAUT)) {
                modele.actionRest();
            }
        }
        if (cmd.equals("BAS")) {
            if (j.isValide(Deplace.BAS)) {
                modele.actionRest();
            }
        }
        if (cmd.equals("GAUCHE")) {
            if (j.isValide(Deplace.GAUCHE)) {
                modele.actionRest();
            }
        }
        if (cmd.equals("DROITE")) {
            if (j.isValide(Deplace.DROITE)) {
                modele.actionRest();
            }
        }
        if (cmd.equals("HAUT_GAUCHE")){
            if(j.isValide(Deplace.HAUT_GAUCHE)){
                modele.actionRest();
            }
        }
        if(cmd.equals("HAUT_DROITE")){
            if(j.isValide(Deplace.HAUT_DROITE)){
                modele.actionRest();
            }
        }
        if(cmd.equals("BAS_GAUCHE")){
            if(j.isValide(Deplace.BAS_GAUCHE)){
                modele.actionRest();
            }
        }
        if(cmd.equals("BAS_DROITE")){
            if(j.isValide(Deplace.BAS_DROITE)){
                modele.actionRest();
            }
        }
        if(cmd.equals("ASSECHER")) {
            // direction d'assecher
            fenetre();
        }

        if(cmd.equals("SEARCH")){
            Cellule currentCellule = modele.getCellule(j.px+1,j.py+1);
            if(currentCellule.getEvent() == Event.NA){
                System.out.println("Pas d'artefact.");
            }
            else {
                System.out.println("Artefact "+currentCellule.getEvent()+" trouve!");
                if(modele.hasArtefact(currentCellule.getEvent())){
                    System.out.println("Obtenir l'artefact "+currentCellule.getEvent());
                } else{
                    System.out.println("Pas de Cle.");
                }
            }
        }

        if (cmd.equals("C")) {
            modele.assecher(modele.getCellule(px,py));
        }

        if (cmd.equals("G")) {
            if (j.px - 1 >= 1) {
                modele.assecher(modele.getCellule(px-1,py));
            }
        }

        if (cmd.equals("H")) {
            if (j.py - 1 >= 1) {
                modele.assecher(modele.getCellule(px,py-1));
            }
        }

        if (cmd.equals("B")) {
            if (j.py + 1 <= this.modele.HAUTEUR) {
                modele.assecher(modele.getCellule(px,py+1));
            }
        }

        if (cmd.equals("D")) {
            if (j.px + 1 <= this.modele.LARGEUR) {
                modele.assecher(modele.getCellule(px+1,py));
            }
        }
    }

    public void fenetre(){
        JFrame frame = new JFrame("ASSECHER");
        // Position de la fenêtre
        frame.setLocation(100, 50);
        // Taille du la fenêtre
        frame.setSize(400, 400);

        frame.setVisible(true);
        frame.setLayout(null);

        JButton H = new JButton("↑");
        JButton B = new JButton("↓");
        JButton G = new JButton("←");
        JButton D = new JButton("→");
        JButton C = new JButton("●");

        C.setSize(60, 60);
        C.setLocation(150, 150);

        H.setSize(60, 60);
        H.setLocation(150, 50);

        B.setSize(60, 60);
        B.setLocation(150, 250);

        G.setSize(60, 60);
        G.setLocation(50, 150);

        D.setSize(60, 60);
        D.setLocation(250, 150);

        JPanel panel = new JPanel();

        frame.add(H);
        frame.add(C);
        frame.add(G);
        frame.add(B);
        frame.add(D);

        H.addActionListener(this);
        B.addActionListener(this);
        G.addActionListener(this);
        D.addActionListener(this);
        C.addActionListener(this);

        H.setActionCommand("H");
        B.setActionCommand("B");
        G.setActionCommand("G");
        D.setActionCommand("D");
        C.setActionCommand("C");
    }
}