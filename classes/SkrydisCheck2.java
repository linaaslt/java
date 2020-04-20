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

/**
*@author Linas
*/

public class SkrydisCheck2 {
	
		/**
		*Metodas Analizuoti atliekami
		*pirminiai skaiciavimai, 
		*reikalingi programai.
		*Greitis saugomas V elemente.
		*@param klases Padetys, padetys elementas
		*/
	public static void Analizuoti( Padetys padetys ) {
			
		double padetis = 0.0, pad_skirtumas = 0.0, greitis = 0.0;
		double laiko_skirtumas = 0.0, laikas = 0.0, pagreitis = 0.0;
		double prad_greitis = 0.0;	
		
		for ( int i = 1; i < padetys.getN(); i++ ) { 			
		
			pad_skirtumas = padetys.paiimtiItaji( i ).getH() - padetis;
			laiko_skirtumas = padetys.paiimtiItaji( i ).getT() - laikas;
			greitis = pad_skirtumas / laiko_skirtumas;
			padetis = padetys.paiimtiItaji( i ).getH();
			laikas = padetys.paiimtiItaji( i ).getT();										
			padetys.paiimtiItaji( i ).setV ( greitis );
			
			System.out.println ( "greitis" + " " + greitis );
		}
	}	
		/**Metodas apskaičiuoti pagreitį,
		*informacija imama iš Analizuoti
		*metodo. Gautoji reikšmė saugoma
		*dukterinės klasės A elemente.
		*/
		
	public static void Pagreicio_rad( Padetys padetys ) {
	
		for ( int i = 2; i < padetys.getN(); i++ ){
						
			pagreitis = 
					( 
							padetys.paiimtiItaji( i ).getV() 
						- 
							padetys.paiimtiItaji( i - 1 ).getV() 
					) 
				/ 
					laiko_skirtumas
			;
			
			( ( PadetisX ) padetys.paiimtiItaji ( i ) ).setA ( pagreitis );								 
		}
		
		
		}
		/**
		*Metodas apskaičiuoti pag_skirtumus,
		*bei prad_greitį.
		*Tikrinama ar pag_skirtumas atitinka salygą.
		*Skaičiavimai išvedami į ekraną.
		*/
		
	public static void Skaiciavimai( Padetys padetys ) {
		
		double pag_skirtumas = 0.0;
		boolean reiksmes_teisingos = true;		
		
		
		
		for ( int i = 2; i < padetys.getN() -1; i++ ){
						
			pag_skirtumas = 
					( ( PadetisX ) padetys.paiimtiItaji ( i ) ).getA() 
				- 
					( ( PadetisX ) padetys.paiimtiItaji( i + 1  ) ).getA()
			;
			
			if ( Math.abs ( pag_skirtumas ) > 0.001 ) {
			
				reiksmes_teisingos = false;
				System.out.println ( "Pagreiciai neatitinka: " );
				System.out.println ( "padeties nr .:" + i );
				System.out.println ( "pag skirt" + " " + pag_skirtumas );
			}					
		}
						
		prad_greitis = padetys.paiimtiItaji( 1 ).getV() + ( pag_skirtumas * padetys.paiimtiItaji( 1 ).getT() );
		System.out.println ( "prad greitis" + " " + prad_greitis );	
	}
				
	public static void main(String[] args) throws Exception {
	   
		String thisLine = null;
		Padetys padetys = new Padetys();
		/** 
	    *Masyvui thisLine prikiriama
		*null reikšmė. null reikšmė
		*pasikeis kai bus pirmą kartą
		*panaudota. 
		*Sukuriamas naujas elementas padetys,
		*aprašytas Padetys klasėje.
		*/
		/**
		*Sukuriama nauji kintamieji
		*kurie bus naudojami skaičiavime.
		*/
		double[] skaiciai = new double [ 10000 ];
		int kiekis = 0;
		double suma = 0, vid = 0, v = 0;		
		Double dt = 0.2, greitis = 0.0, v0 = 0.0;
		double aukstis = 0.0;
		
		
		
		try {
																												// open input stream test.txt for reading purpose.
			BufferedReader br = new BufferedReader( new FileReader( "duomenys.csv" ) );
			
			System.out.println ( "duomenu failo turinys:" );
			
			while ( ( thisLine = br.readLine() ) != null ) {
			 
				System.out.println( thisLine );
				String[] skaiciu_strs = thisLine.split ( "," );
				int poru = (int) ( skaiciu_strs.length / 2 );
				
				for ( int i = 0; i < poru * 2; i += 2 ) {
					
					padetys.papildyti ( new PadetisX (
					
						Double.parseDouble ( skaiciu_strs [ i ] ),
						0.0,
						Double.parseDouble ( skaiciu_strs [ i + 1 ] ),
						0.0
					));
									
				} 
				
				System.out.println( "Kiek poru " + poru );
			}
			
		/**
		*Skaityti informaciją iš failo iškviečiame 
		*BufferedReader'į, kuris skaitys informaciją
		*iš duomenys.csv failo.
		*Sukuriamas metodas kuris truks iki
		*paskutinės masyvo thisLine reikšmės.
		*Masyvo elementai yra išskirstomi juos 
		*atskiriant "," (kableliu). 
		*Surandama kiek bus porų, kurios
		*atvaizduojamos metodo pabaigoje.
		*Ciklas sukuriamas surasti elementų
		*reikšmes, kurios bus naudojamos 
		*programoje atlikti skaičiavimus.
		*/
			
			Analizuoti ( padetys );
			Pagreicio_rad ( padetys );
			Skaiciavimai ( padetys );
			
		} catch( IOException e ) {
			
			System.out.println( "Error Encountered getting user input:" + e.getMessage() );
			e.printStackTrace();
		}
	}
}