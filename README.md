Projet Ile Interdite
---
**Description**

@binome **Kelun Chai  /  Mamadou Ndiaye**</br>
- [x] Partie I : On va se la couler douce
- [x] Partie II : Noooon, pas la trempette !
- [x] Partie III ： Sa palce est dans un musee</br>
**Initialiser les artefacts**</br>
Initialiser Manuelle et afficher dans la Command Line</br>
![Ini Artefact](https://github.com/nailqm/ProjetJavaL2/blob/master/artefact.PNG)</br>
```
// Main.java 

/** Initiale les artefacts */
        modele.iniEvent(modele.getCellule(1,3), Event.Air);
        modele.iniEvent(modele.getCellule(4,6),Event.Eau);
        modele.iniEvent(modele.getCellule(2,2),Event.Feu);
        modele.iniEvent(modele.getCellule(5,5),Event.Terre);
        
/** Position des artefacts */
        System.out.print("===Artefacts===\nAir(1,3)\nEau(4,6)\nFeu:(2,2)\nTerre:(5,5)\n");
```

**Initialiser les cles**</br>
Apres chaque "fin de tour", joueurs peuvent obtenir une cle aleatoirement.</br>
![Obtenir Cle](https://github.com/nailqm/ProjetJavaL2/blob/master/obtenir%20cle.PNG)</br>
```
// "Cle" est une liste booléenne, définie dans Joeur.java
// Les clés portées par le joueur,
// Air[0] - Eau[1] - Feu[2] - Terre[3]
    public boolean[] cle;

// Modele.java
// Obtenir une cle ou pas
public void rdmCle() {
        int rdCle = new Random().nextInt(5);
        if (rdCle < 4) {
            this.joueurs[tour].getCle(rdCle);
            System.out.println("Cle Obtenue: " + rdCle);
        } else {
            System.out.println("Pas de cle.");
        }
    }
    
// Joueur.java
// Renvoyer une cle
public void getCle(int i) {
        this.cle[i] = true;
    }
```

**Recuprer artefacts**</br>
<iframe height=498 width=510 src="https://github.com/nailqm/ProjetJavaL2/blob/master/recuprer.mp4" frameborder=0 allowfullscreen></iframe>
</br>
![](https://github.com/nailqm/ProjetJavaL2/blob/master/obtenir%20artefact.PNG)</br>
Dans la classe `Controleur` </br>
```
if (cmd.equals("RECUPERER")) {
            Cellule currentCellule = modele.getCellule(j.px + 1, j.py + 1);
            if (currentCellule.getEvent() == Event.NA) {
                System.out.println("Pas d'artefact.");
            } else {
                System.out.println("Artefact " + currentCellule.getEvent() + " trouve!");
                if (modele.hasArtefact(currentCellule.getEvent())) {
                    System.out.println("Obtenir l'artefact " + currentCellule.getEvent());
                } else {
                    System.out.println("Pas de Cle.");
                }
            }
        }
 ```       
 
- [x] Speciale : Actions speciales.</br>
**Assecher simple**</br>
<iframe 
    height=450 
    width=800 
    src="https://github.com/nailqm/ProjetJavaL2/blob/master/assecher-simple.mp4" 
    frameborder=0 
    allowfullscreen>
</iframe>
</br>
```
// Modele.java
public void assecher(Cellule c) {
        if (c.isInondee()) {
            cellules[c.getX()][c.getY()].setEtat(Etat.normale);
        }  else {
            System.out.println("Assecher impossible.");
        }
    }
```

**Assecher double**</br>
Si le rôle est `ingenieur`, activez le bouton `ASSEDBLE` et deux fenêtres apparaissent.</br>
<iframe 
    height=450 
    width=800 
    src="https://github.com/nailqm/ProjetJavaL2/blob/master/assecher-dble.mp4" 
    frameborder=0 
    allowfullscreen>
</iframe>
</br>
```
// Modele.java
public void assecherDble(Cellule c){
        if (c.isInondee()){
            cellules[c.getX()][c.getY()].setEtat(Etat.normale);
        }else if (c.isSubmergee()) {
            cellules[c.getX()][c.getY()].setEtat(Etat.inondee);
        }else {
            System.out.println("Assecher impossible.");
        }
    }
 ```   
 
- [x] Speciale : Personnages particuliers. </br>
**Ingenieur et Explorateur**</br>
Dans `Controleur`, détecter si c'est un rôle spécial, et faire un appel a `public void isIngenieur()` dans `VueControleur`.</br>
<iframe 
    height=450 
    width=800 
    src="https://github.com/nailqm/ProjetJavaL2/blob/master/charactere-speciale.mp4" 
    frameborder=0 
    allowfullscreen>
</iframe>
</br>
```
// Main.java

/** Initiale les joueurs */
        modele.joueurs[0] = new Joueur(modele, 1, 1, "j1", Character.Explorateur);
        modele.joueurs[1] = new Joueur(modele, 2, 2, "j2");
        modele.joueurs[2] = new Joueur(modele, 3, 3, "j3", Character.Ingenieur);
        modele.joueurs[3] = new Joueur(modele, 4, 4, "j4");
```

**Structure**</br>

Diagramme de classe
---
![](https://github.com/nailqm/ProjetJavaL2/blob/master/UML.png)

Programme Principe
---
![](https://github.com/nailqm/ProjetJavaL2/blob/master/1.PNG)

Pannel
---
![](https://github.com/nailqm/ProjetJavaL2/blob/master/2.PNG)

Pannel Double
---
![](https://github.com/nailqm/ProjetJavaL2/blob/master/3.PNG)
