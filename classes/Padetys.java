package classes;
	
	class Padetys {
		
		private Padetis[] padetys; 
		
		private int n; //saugomas realiai esanciu elementu kiekis masyve
		
		public Padetys() {
			
			padetys = new Padetis [ 1000 ];
			n = 0;
		}
		
		public void papildyti ( Padetis padetis ) {
			
			padetys [ n ] = padetis;
			n++;
		}
		
		public void setN ( int kiekis ) {
			
			this.n = kiekis;
			
			if ( kiekis > 1000 ) {
			
				this.n = 1000;
			}
			
		}
		
		public int getN ( ) {
			
			return this.n;
		}

		public Padetis paiimtiItaji ( int i ) {
			
			int kuris = i;
			
			if ( i > n ) {
			
				kuris = n - 1;
			}
			if 	( i < 0 ) {
				
				kuris = 0;
			}
			return padetys [ kuris ];
		}
	}