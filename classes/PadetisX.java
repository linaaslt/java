package classes;

	class PadetisX extends Padetis {
		
		private Double a;
		
		
		public PadetisX() {
		}
		
		public PadetisX ( Double laikas, Double greitis, Double aukstis, Double pagreitis ) {
			
			super ( laikas, greitis, aukstis );
			
			this.a = pagreitis;
		}
		

		
		public void setA ( Double pagreitis) {
			
			this.a = pagreitis;
		}
		

		
		public Double getA () {

			return this.a;
		}
						
	}