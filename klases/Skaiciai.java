// package klases;

public class Skaiciai {
		
		public String [] simbo;
		public int kiek = 0;
		
		public Skaiciai () {
			
			simbo = new String [ 20 ];
			kiek = 0;
		}
		
		public void pridetiSimb (String skaitmuo) {
			
			simbo [kiek] = skaitmuo;
			kiek++;
		}
}