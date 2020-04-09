
	package classes;

	import java.io.*;

	class raketa4 {
		
		public static Padetys skaiciuotiRaketa4 ( Double prad_greitis, Double dti ) {
		
			Double dt = dti;
			Double greitis0 = prad_greitis;
			Double g = 9.8;
			Double laikas = 0.0;
			Double h_kilimo = 0.0;
			Double greitis = greitis0;
			Double h_kritimo = 0.0;
			int ct = 0;
			Double sklendimas = 0.0;

			
			Padetys padetys = new Padetys();
			
			System.out.println ( "  t   v   h " );
			
			do {
				
				padetys.papildyti ( new Padetis ( laikas, greitis, h_kilimo ) );
				
				ct++;				
				greitis = greitis0 - g * laikas;
				
				if ( greitis > 0 ) {
					
					laikas = laikas + dt;
					h_kilimo =  greitis0 * laikas - ( g * laikas * laikas ) / 2; 
					sklendimas = h_kilimo * 0.97;					
					
				} else {

					if ( ( greitis < 0 ) && ( h_kilimo > sklendimas ) ) {
												
						laikas = laikas + dt / 10;
						
						h_kritimo =  greitis0 * laikas - ( g * laikas * laikas ) / 2;
						
						h_kilimo = h_kritimo;
						
					} else {
						
						laikas = laikas + dt;
						
						h_kritimo = greitis0 * laikas - ( g * laikas * laikas ) / 2;
											
						h_kilimo = h_kritimo;		
					}
				}
				
			} while ( ( h_kilimo > 0 ) && ( ct < 500 ) );
						
			// System.out.println ( "Laikas: " + laikas + " greitis: " + greitis + "Aukstis: " + h_kilimo );
			padetys.papildyti ( new Padetis ( laikas, greitis, h_kilimo ) );
			return padetys;
		}			
		
		public static void main(String[] args) {
			
			BufferedReader br = new BufferedReader ( new InputStreamReader ( System.in ) );
			
			System.out.println ( "Raketos skridimo lentele" );

			Double greitis0;	
			Double dt ;
			Padetys res_kilimo;

			try {
				
				System.out.print ( "dt? " );				
				dt = Double.parseDouble ( br.readLine() );
				System.out.print ( "v0? " );
				greitis0 = Double.parseDouble ( br.readLine() );	
				br.close();
				res_kilimo = skaiciuotiRaketa4 ( greitis0, dt );
				
				File myObj = new File("duomenys.csv");
			
				if (myObj.createNewFile()) {
				  
					System.out.println("File created: " + myObj.getName());
				
				} else {
				  
					System.out.println("File already exists.");
				
				}
			 
				FileWriter myWriter = new FileWriter(myObj);
				
				String sep = "";
				
				System.out.println ( "padeciu: " + res_kilimo.n ); 
				
				for ( int i = 0; i < res_kilimo.n; i++ ) { 

					System.out.println (
					
						String.format("%.2f", res_kilimo.padetys [ i ].t ) 
						+ " " + String.format("%.2f", res_kilimo.padetys [ i ].v ) 
						+ " "+ String.format("%.2f", res_kilimo.padetys [ i ].h ) 
					);
					myWriter.write( sep + String.format("%.2f", res_kilimo.padetys [ i ].t ) + "," + String.format("%.2f", res_kilimo.padetys [ i ].h ) );				
					sep = ",";
				}
				
				myWriter.close();
				System.out.println("Successfully wrote to the file.");
				
			
			} catch   (Exception e) {
			
				System.out.println( "Error Encountered getting user input:" + e.getMessage() );
				// e.printStackTrace();
			}
		}
	}