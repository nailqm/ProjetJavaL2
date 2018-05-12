package modele;

public enum Event {
    Eau, Feu, Terre, Air;

    @Override
    public String toString() {
        switch (this) {
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

    public String getEvent() {
        switch (this) {
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