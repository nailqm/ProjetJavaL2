package modele;

public enum Deplace {
    GAUCHE,
    DROITE,
    HAUT,
    BAS;

    @Override
    public String toString(){
        switch (this){
            case GAUCHE:
                return "GAUCHE";
            case DROITE:
                return "DROITE";
            case HAUT:
                return "HAUT";
            case BAS:
                return "BAS";
            default:
                return null;
        }
    }
}