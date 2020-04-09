

	import java.io.*;

	class frl {
		
		
		public static void skaiciuotiFrl ( Double x_prad, Double x_pab ) {
		
			Double dx = ( x_pab - x_prad ) / 19;
			Double x = x_prad, y;
			
			Double y_max = 9 * x - x *x, x_max = x_prad; 
			
			while ( x < x_pab ) {
				
				y = 9 * x - x *x;
				
				if ( y > y_max ) {
					
					y_max = y;
					x_max = x;
				}
				
				System.out.println ( x + " " + y );
				x+=dx;
			}
			
			System.out.println ( "Funkcijos max. reiksme: " + y_max + " prie x reiksmes: " + x_max );			
			
		}
		
		
		public static void main(String[] args) {
			
			BufferedReader br = new BufferedReader ( new InputStreamReader ( System.in ) );
			
			System.out.println ( "Funkcijos reiksmiu lentele" );

			
			Double x_prad = 0.0;
			Double x_pab = 0.0;

			
			try {
				
				System.out.print ( "x_prad? " );				
				x_prad = Double.parseDouble ( br.readLine() );
				System.out.print ( "x_pab? " );
				x_pab = Double.parseDouble ( br.readLine() );				
				
				br.close();
				
				skaiciuotiFrl ( x_prad, x_pab );
				
			} catch   (Exception e) {
			
				System.out.println( "Error Encountered getting user input:" + e.getMessage() );
				// e.printStackTrace();
			}
			

		}
	}