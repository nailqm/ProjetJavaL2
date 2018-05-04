package Ile.modele;

public enum Evenement {
	no, heli, air, eau, terre, feu;
	
	public String getEvent(){
		switch (this){
			case no:
				return "N";
			case heli:
				return "H";
			case air:
				return "A";
			case eau:
				return "E";
			case terre:
				return "T";
			case feu:
				return "F";
			default:
				return "N";
		}
	}
	
	@Override
	public String toString(){
		switch (this){
			case no:
				return "N";
			case heli:
				return "H";
			case air:
				return "A";
			case eau:
				return "E";
			case terre:
				return "T";
			case feu:
				return "F";
			default:
				return "N";
		}
	}
}
