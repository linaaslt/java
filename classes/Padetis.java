package classes;

	class Padetis {
		
		private Double t, v, h;
		
		
		public Padetis() {
		}
		
		public Padetis ( Double laikas, Double greitis, Double aukstis ) {
			
			this.t = laikas;
			this.v = greitis;
			this.h = aukstis;
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

		public Double getT () {
			
			return this.t;
		}
		
		public Double getV () {
			
			return this.v;
		}

		public Double getH () {

			return this.h;
		}		
	}