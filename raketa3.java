

	import java.io.*;

	class raketa3 {
		
		
		public static void skaiciuotiRaketa3 ( Double prad_greitis, Double dti ) {
		
			Double dt = dti;
			Double v0 = prad_greitis;
			Double g = 9.8;
			Double t = 1.0;
			Double h = 0.0;
			Double v = 0.0;
						
			while ( t < 10 ) {
				
				t = t + dt;
				v = v0 - g * t;
				h =  v0 * t - ( g * t * t ) / 2;
				
				
					if ( v < 0 ){
					
						dt = dt / 10;	
						t = t + dt;
						h =  v0 * t - ( g * t * t ) / 2;
					
					}
						
				System.out.println ( t + " " + v +" "+ h );
				
				t += dt;
					
			
			
			}
					
			System.out.println ( "Laikas: " + t + " greitis: " + v + "Aukstis: " + h );
			
		}
		
		
		public static void main(String[] args) {
			
			BufferedReader br = new BufferedReader ( new InputStreamReader ( System.in ) );
			
			System.out.println ( "Raketos skridimo lentele" );

				Double v0;	
				Double dt ;

			
			try {
				
				System.out.print ( "dt? " );				
				dt = Double.parseDouble ( br.readLine() );
				System.out.print ( "v0? " );
				v0 = Double.parseDouble ( br.readLine() );	
			
								
				
				br.close();
				
				skaiciuotiRaketa3 ( v0, dt );
				
			} catch   (Exception e) {
			
				System.out.println( "Error Encountered getting user input:" + e.getMessage() );
				// e.printStackTrace();
			}
			

		}
	}