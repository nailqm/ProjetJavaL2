package modele;

public enum Evenement {
    Null, Eau, Feu, Terre, Air;

    @Override
    public String toString() {
        switch (this) {
            case Null:
                return "N";
            case Eau:
                return "E";
            case Feu:
                return "F";
            case Terre:
                return "T";
            case Air:
                return "A";
            default:
                return "null";
        }
    }

    public String getEven() {
        switch (this) {
            case Null:
                return "N";
            case Eau:
                return "E";
            case Feu:
                return "F";
            case Terre:
                return "T";
            case Air:
                return "A";
            default:
                return "null";
        }
    }
}