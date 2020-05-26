package klases;

public class Skaicius {
		
		public String [] simbo;
		public int kiek = 0;
		public int max_length = 36;
		
		
		public Skaicius () {
			
			simbo = new String [ max_length ];
			kiek = 0;
		}
		
		public void pridetiSimb ( String skaitmuo ) {
			
			if ( kiek < max_length - 1 ) {
			
				simbo [kiek] = skaitmuo;
				kiek++;
			}
		}
}