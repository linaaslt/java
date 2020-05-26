package klases;
import java.awt.EventQueue;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File; 
import java.io.FileWriter;
import java.awt.*;        // Using AWT containers and components
import java.awt.event.*;
import java.awt.Dimension;
import javax.swing.JSplitPane;  // Using AWT events classes and listener interfaces
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List; 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import java.text.SimpleDateFormat; 

public class WindoSTx extends JFrame
      implements ActionListener, WindowListener {
      
	private JTextArea taDisplay;
	private JTextField tfCount; 
    private Button btnNuskaitytiTeksta;
	private Button btnSkaitmenys;  
	private Button btnSveikiSkaiciai;
	private Button btnRealusSkaiciai;
	private Button btnSkaiciaiSuPrasme;
	private	JButton b1;
	private int count = 0;
	String simbolis = "";
	
	public static boolean yraSkaitmuo ( String simbolis ) {
		
		String[] skaitmenys = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		
		boolean yra_skaitmuo = false;
		
		for (int i = 0; i < skaitmenys.length; i++ ) {
		
			if ( simbolis.equals ( skaitmenys [ i ] ) ) {
			
				yra_skaitmuo = true;
				
			}
		}
		
		return yra_skaitmuo;
	
	}
	
	public static boolean yraRaide (String raides) {
		
		String [] raides2 = {  " " };
		
		boolean yra_raide = false;
		
		for (int t = 0; t < raides2.length; t++)
		
			if ( raides.equals ( raides2 [ t ] ) ) {
				
			yra_raide = true;
			
			}
		return yra_raide;
	}
	
	public WindoSTx() {
		
		setLayout(new FlowLayout()); 

		add(new Label("Maks., sumos, kiekio, vid. skaiciavimas" + "\n"));   		            

		btnNuskaitytiTeksta = new Button ("Nuskaitytis teksta");
		btnNuskaitytiTeksta.addActionListener (new ActionListener(){
			public void actionPerformed (ActionEvent event){
				
			nuskaitytiTeksta ();
			}
		});
		add(btnNuskaitytiTeksta);

		btnSkaitmenys = new Button("Skaiciai tekste"); 
		btnSkaitmenys.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent event){
			
			skaiciaiTekste ();	
			}
		});
		add(btnSkaitmenys);
	
		taDisplay = new JTextArea( 10, 40);
		taDisplay.setFont(new Font("Serif", Font.ITALIC, 16));
		taDisplay.setTabSize(3);
		taDisplay.setLineWrap(true);
		JScrollPane scrollV = new JScrollPane (taDisplay);
		scrollV.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(taDisplay);	 
        
		//btnCount.addActionListener(this);
		
		addWindowListener(this);
		
		setTitle("Maks., Suma, Kiekis, Vidurkis"); 
		setSize(520, 550);            
		setVisible(true);            
	}
		
	public void skaiciaiTekste() {
		String thisLine = null;
		String simbol = "";		
		
		Skaicius[] skaiciai = new Skaicius [ 500 ];
		int sk_kiek = 0;
		int k = 0;	
		boolean rastas_skaicius = false;
		
		
		try {	
						
			BufferedReader br = new BufferedReader( new FileReader( "tekstas.txt" ) );
			
			System.out.println ( "duomenu failo turinys:" );
									
			String simb;
			int kiekis_skaitmenu = 0;	

			
			while ( ( thisLine = br.readLine() ) != null ) {		
				
				String skaiciu_strs = thisLine;
								 
				skaiciu_strs = skaiciu_strs.replaceAll("[^0-9]+", ",");
								
				for (int i = 0; i < thisLine.length(); i++ ) {
					
					simb = thisLine.substring( i, i+1 );					
									 
					if ( yraSkaitmuo ( simb ) ) {
						
						if ( ! rastas_skaicius ) {
							
							skaiciai [ sk_kiek ] = new Skaicius();	
							rastas_skaicius = true;
						}
						
						skaiciai [ sk_kiek ].pridetiSimb( simb );					
					}
						
					if ( yraRaide ( simb ) && rastas_skaicius ) {
							
						rastas_skaicius = false;
						sk_kiek++;
					}
														
					if ( ( ! yraSkaitmuo ( simb ) )/*&& (yraRaide (simb))*/){
						
						//for (int i = 0, i < yraSkaitmuo.length(); i++ ){
						//simboliai += "\n ";

					}
				}	
				/*		 
				Pattern pattern = Pattern.compile("Publikuota:(.*)");
				Matcher matcher = pattern.matcher(thisLine);

				if(matcher.find()){
					
					String dateString = matcher.group(1);   //I'm using the Capturing groups to capture only the value.
					java.util.Date date = new SimpleDateFormat("yyyy-mm-dd").parse(dateString);
					taDisplay.append("Straipsnis publikuotas:" + date + "\n");
				}
				*/
			}
			//skaiciai [sk_kiek].pridetiSimb(simboliai);

						
		} catch( Exception e ) {
			
			e.printStackTrace();
		}
		
		taDisplay.append ("Rasta skaiciu " + sk_kiek + ":\n");		
		
		for ( k = 0 ; k < sk_kiek; k++ ) {
			
			taDisplay.append ( k + ". " + String.join ( "", skaiciai [ sk_kiek ].simbo ) + "\n");							
		}
	}


	public void nuskaitytiTeksta() {
		String thisLine = null;
		String simboliai = "";		
		String skaiciu_strs = "";	
		try {	
			
			BufferedReader br = new BufferedReader( new FileReader( "tekstas.txt" ) );
			
			System.out.println ( "duomenu failo turinys:" );
									
			String simb;
			int kiekis_skaitmenu = 0;	
			int k = 0;
			
			while ( ( thisLine = br.readLine() ) != null ) {		
												 
				//skaiciu_strs = skaiciu_strs.replaceAll("[^0-9]+", ",");
								
				for (int i = 0; i < thisLine.length(); i++ ) {
					
					simb =  thisLine.substring( i, i+1 );					
						
					skaiciu_strs += simb;
								 
									
				}	
				 								
			}
			
				taDisplay.append ("Rasti skaiciai " + "\n" + skaiciu_strs + "\n");
				
						
		} catch( Exception e ) {
			
			e.printStackTrace();
		}
    
	}

	private JTextArea Tekstas() {
	 JTextArea detailTA = new JTextArea();
	 detailTA.setFont(new Font("Monospaced", Font.PLAIN, 14));
	 detailTA.setTabSize(3);
	 detailTA.setLineWrap(true);
	 detailTA.setWrapStyleWord(false);
	 return (detailTA);
	}
 
   @Override
   public void actionPerformed(ActionEvent evt) {


   }
   
    public static void main(String[] args) throws Exception{			
	
	
		new WindoSTx();
		
	  	
   }
   /* WindowEvent handlers */
   // Called back upon clicking close-window button
   @Override
   public void windowClosing(WindowEvent evt) {
      System.exit(0);  // Terminate the program
   }
 
   // Not Used, BUT need to provide an empty body to compile.
   @Override public void windowOpened(WindowEvent evt) { }
   @Override public void windowClosed(WindowEvent evt) { }
   // For Debugging
   @Override public void windowIconified(WindowEvent evt) { System.out.println("Window Iconified"); }
   @Override public void windowDeiconified(WindowEvent evt) { System.out.println("Window Deiconified"); }
   @Override public void windowActivated(WindowEvent evt) { System.out.println("Window Activated"); }
   @Override public void windowDeactivated(WindowEvent evt) { System.out.println("Window Deactivated"); }
}
