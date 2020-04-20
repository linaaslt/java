package classes;

	class Padetis {
		
		private Double t, v, h, a;
		
		
		public Padetis() {
		}
		
		public Padetis ( Double laikas, Double greitis, Double aukstis, Double pagreitis ) {
			
			this.t = laikas;
			this.v = greitis;
			this.h = aukstis;
			this.a = pagreitis;
		}
		
		public void setT ( Double laikas ) {
			
			this.t = laikas;
		}
		
		public void setV ( Double greitis ) {
			
			this.v = greitis;
		}

		public void setH ( Double aukstis) {
			
			this.h = aukstis;
		}
		
		public void setA ( Double pagreitis) {
			
			this.a = pagreitis;
		}
		
		public Double getT () {
			
			return this.t;
		}
		
		public Double getV () {
			
			return this.v;
		}

		public Double getH () {

			return this.h;
		}
		
		public Double getA () {

			return this.a;
		}
						
	}