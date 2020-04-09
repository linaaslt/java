
	package classes;
	
	class Padetys {
		
		public Padetis[] padetys; 
		
		public int n;
		
		public Padetys() {
			
			padetys = new Padetis [ 1000 ];
			n = 0;
		}
		public void papildyti ( Padetis padetis ) {
			
			padetys [ n ] = padetis;
			n++;
		}
	}
