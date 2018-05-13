package modele;

public enum Event {
    Air, Eau, Terre, Feu,NA;

    @Override
    public String toString() {
        switch (this) {
            case Eau:
                return "EAU";
            case Feu:
                return "FEU";
            case Terre:
                return "TERRE";
            case Air:
                return "AIR";
            default:
                return "NA";
        }
    }
}