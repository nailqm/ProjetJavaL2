package Ile.modele;

   public enum State {
	   normale, inondee, submergee;
	   
	   @Override
	   public String toString() {
		   switch(this) {
		   case normale:
			   return "N";
		   case inondee:
			   return "I";
		   case submergee:
			   return "S";
		   default:
			   return "N";
		   }
	   }
   }
