import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat; 
import java.util.*; 
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class SkaiciaiTekste {
	
	String skaiciai = "";
	String simbolis = "";
	
	public static boolean yraSkaitmuo ( String simbolis ) {
		
		String[] skaitmenys = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		
		boolean yra_skaitmuo = false;
		
		for (int i = 0; i < 10; i++ ) {
		
			if ( simbolis.equals ( skaitmenys [ i ] ) ) {
			
				yra_skaitmuo = true;
				
			}
		}
		
	return yra_skaitmuo;
	
	}
	
	public static boolean yraRaide (String raides) {
		
		String [] raides2 = {  "," };
		
		boolean yra_raide = false;
		
		for (int t = 0; t < 1; t++)
		
		if ( raides.equals ( raides2 [ t ] ) ) {
			yra_raide = true;
		}
		return yra_raide;
	}
	
	

	
	public static void main(String[] args) throws FileNotFoundException {
	   
		String thisLine = null;
		String simboliai = "";		
		int [] kvadr = new int[1000];
		
		try {	
						// open input stream test.txt for reading purpose.
			BufferedReader br = new BufferedReader( new FileReader( "tekstas.txt" ) );
			
			System.out.println ( "duomenu failo turinys:" );
			
			//double [] kvadr = new double [ 1000 ];
			
			String simb;
			int kiekis_skaitmenu = 0;	
			
			while ( ( thisLine = br.readLine() ) != null ) {		
				
				String skaiciu_strs = thisLine;
								 
				skaiciu_strs = skaiciu_strs.replaceAll("[^0-9]+", ",");
								
				for (int i = 0; i < thisLine.length(); i++ ) {
					
					simb =  thisLine.substring( i, i + 1 );					
						
					simboliai += simb;
									 
					if (( yraSkaitmuo ( simb ))/* && (yraRaide (simb))*/) {
						
						kvadr[i]+= i;											
						kiekis_skaitmenu++;
						System.out.println ("Skaicius rastas pozicijoje " + kvadr[ i ]);
						
					}
				
				}	
						 
				Pattern pattern = Pattern.compile("Publikuota:(.*)");
				Matcher matcher = pattern.matcher(thisLine);

				if(matcher.find()){
					
					String dateString = matcher.group(1);   //I'm using the Capturing groups to capture only the value.
					java.util.Date date = new SimpleDateFormat("yyyy-mm-dd").parse(dateString);
					System.out.println("Straipsnis publikuotas:" + date);
				}
				
			}
			
				//System.out.println ( "skaiciai tekste:" + simboliai );		
				System.out.println ( "viso skaitmenu: " + kiekis_skaitmenu );
						
		} catch( Exception e ) {
			
			e.printStackTrace();
		}
			
	}
}