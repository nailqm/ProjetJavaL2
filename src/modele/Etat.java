package modele;

public enum Etat {
    normale,
    inondee,
    submergee;

    @Override
    public String toString() {
        switch (this) {
            case normale:
                return "normale";
            case inondee:
                return "inondee";
            case submergee:
                return "submergee";
            default:
                return "normale";
        }
    }
}