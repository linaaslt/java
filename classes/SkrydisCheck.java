package classes;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File; 
import java.io.FileWriter;


public class SkrydisCheck {
			
	public static void main(String[] args) throws Exception {
	   
		String thisLine = null;
		Padetys padetys = new Padetys();
		Padetys res_kilimo; 
	   
			double[] skaiciai = new double [ 10000 ];
			int kiekis = 0;
			double suma = 0, vid = 0, v = 0, pagreitis = 0;		
			Double dt = 0.2, greitis = 0.0, v0 = 0.0;
			double aukstis = 0.0;
		try {
																												// open input stream test.txt for reading purpose.
			BufferedReader br = new BufferedReader( new FileReader( "duomenys.csv" ) );
			
			System.out.println ( "duomenu failo turinys:" );
			
			
			while ( ( thisLine = br.readLine() ) != null ) {
			 
				System.out.println( thisLine );
				String[] skaiciu_strs = thisLine.split ( "," );
				int poru = (int) (skaiciu_strs.length / 2 );
				
				for ( int i=0; i < poru * 2; i+=2 ) {
					
					padetys.papildyti ( new Padetis (
					
						Double.parseDouble (skaiciu_strs [ i ] ),
						0.0,
						Double.parseDouble ( skaiciu_strs [i + 1]) 
					));
									
				} 
				
				System.out.println( "Kiek poru " + poru );	
				
				for ( int i = 0; i < poru; i++ ) { 		//prad greit: Vi = Vf - (a * t); greitis, v = s/t
														//pagr a = dv/dt  a = (v gal - v prad) /dt
					greitis	= padetys.paiimtiItaji( i ).getH() / padetys.paiimtiItaji( i ).getT();
					pagreitis = ( greitis - v0 )  / dt;					
					aukstis = padetys.paiimtiItaji( i ).getH() * 0.97;			
					
					if ( padetys.paiimtiItaji( i ).getH() > aukstis ){
					
					v = padetys.paiimtiItaji( i ).getT() + dt /10;
					greitis	= padetys.paiimtiItaji( i ).getH() / v;
					System.out.println ( "banb" + v );															
					System.out.println ( "v01" + v0);						
					} else {
					
					 
					}
				
				v0 = greitis;
								
				System.out.println( "v0 " + v0 );
				System.out.println( "pagreitis " + pagreitis );
				}			
			}	
			
		} catch( IOException e ) {
			
			System.out.println( "Error Encountered getting user input:" + e.getMessage() );
			e.printStackTrace();
		}
	}
}