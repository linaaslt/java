
	import java.io.*;

	class raketa2 {
		
		public static String skaiciuotiRaketa2 ( Double prad_greitis, Double dti ) {
		
			Double dt = dti;
			Double greitis0 = prad_greitis;
			Double g = 9.8;
			Double laikas = 0.0;
			Double h_kilimo = 0.0;
			Double greitis = greitis0;
			Double h_kritimo = 0.0;
			int ct = 0;
			Double sklendimas = 0.0;
			String duomenys = "", sep="";
			
			
			System.out.println ( "  t   v   h " );
			
			do {
				
				ct++;
				
				System.out.println ( String.format("%.2f", laikas ) + " " + String.format("%.2f", greitis ) + " "+ String.format("%.2f", h_kilimo ) );
				duomenys += sep + String.format("%.2f", laikas ) + "," + String.format("%.2f", h_kilimo );
				sep = ",";
				
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
						
			System.out.println ( "Laikas: " + laikas + " greitis: " + greitis + "Aukstis: " + h_kilimo );
			return duomenys;
		}			
		
		public static void main(String[] args) {
			
			BufferedReader br = new BufferedReader ( new InputStreamReader ( System.in ) );
			
			System.out.println ( "Raketos skridimo lentele" );

			Double greitis0;	
			Double dt ;
			String res_kilimo;

			try {
				
				System.out.print ( "dt? " );				
				dt = Double.parseDouble ( br.readLine() );
				System.out.print ( "v0? " );
				greitis0 = Double.parseDouble ( br.readLine() );	
				br.close();
				res_kilimo = skaiciuotiRaketa2 ( greitis0, dt );
				
				File myObj = new File("duomenys.csv");
			
				if (myObj.createNewFile()) {
				  
					System.out.println("File created: " + myObj.getName());
				
				} else {
				  
					System.out.println("File already exists.");
				
				}
			 
				FileWriter myWriter = new FileWriter(myObj);
				myWriter.write( res_kilimo );
				myWriter.close();
				System.out.println("Successfully wrote to the file.");
				
			
			} catch   (Exception e) {
			
				System.out.println( "Error Encountered getting user input:" + e.getMessage() );
				// e.printStackTrace();
			}
		}
	}