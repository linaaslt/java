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
import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List; 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

// An AWT GUI program inherits the top-level container java.awt.Frame
public class Windo extends JFrame
      implements ActionListener, WindowListener {
      // This class acts as listener for ActionEvent and WindowEvent
      // A Java class can extend only one superclass, but it can implement multiple interfaces.
	private JTextArea taDisplay;
	private JTextField tfCount;  // Declare a TextField component
	private Button btnCount;    // Declare a Button component
	private int count = 0;
	public String skaicius = "";
	
	// Counter's value
		 
   // Constructor to setup the GUI components and event handlers
   public Windo() {
		
		
		setLayout(new FlowLayout()); // "super" Frame sets to FlowLayout

		add(new Label("Maks., sumos, kiekio, vid. skaiciavimas"));   // "super" Frame adds an anonymous Label
	 
		tfCount = new JTextField("0", 10); // Construct the TextField
		tfCount.setEditable(false);       // read-only
		add(tfCount);                     // "super" Frame adds TextField

		btnCount = new Button("Count");  // Construct the Button
		add(btnCount);   

		taDisplay = new JTextArea( 10, 40);
		taDisplay.setFont(new Font("Serif", Font.ITALIC, 16));
		taDisplay.setTabSize(3);
		taDisplay.setLineWrap(true);
		add(taDisplay);	 

		btnCount.addActionListener(this);
		// btnCount (source object) fires ActionEvent upon clicking
		// btnCount adds "this" object as an ActionEvent listener

		addWindowListener(this);
		// "super" Frame (source object) fires WindowEvent.
		// "super" Frame adds "this" object as a WindowEvent listener.

		setTitle("Maks., Suma, Kiekis, Vidurkis"); // "super" Frame sets title
		setSize(520, 550);            // "super" Frame sets initial size
		setVisible(true);             // "super" Frame shows
   }
 
   // The entry main() method
	private JTextArea Tekstas() {
	 JTextArea detailTA = new JTextArea();
	 detailTA.setFont(new Font("Monospaced", Font.PLAIN, 14));
	 detailTA.setTabSize(3);
	 detailTA.setLineWrap(true);
	 detailTA.setWrapStyleWord(false);
	 return (detailTA);
	}
 
   /* ActionEvent handler */
   @Override
   public void actionPerformed(ActionEvent evt) {

String thisLine = null;
		double[] skaiciai = new double [ 10000 ];
		int kiekis = 0;
		double suma = 0, vid = 0;	
		String skaic = "";
   
   try {
																												// open input stream test.txt for reading purpose.
			BufferedReader br = new BufferedReader( new FileReader( "duomenys.csv" ) );
			
			taDisplay.append ("Pradedam darba" + "\n");
			taDisplay.append ( "duomenu failo turinys:" + "\n");
			
			new JTextArea (); 			
			while ( ( thisLine = br.readLine() ) != null ) {
			 
				taDisplay.append( thisLine + "\n" );
				String[] skaiciu_strs = thisLine.split ( "," );
				
				for ( int i=0; i < skaiciu_strs .length; i++ ) {
				
					skaiciai [ kiekis ] = 
					
					Double.parseDouble (  skaiciu_strs [ i ] );
					
					if (kiekis > 10){
					
					skaiciai [ kiekis ] = 
					
					Double.parseDouble ((  skaiciu_strs [ i ] ) + "\n");
					
					} 
					
					skaic += ( skaiciai [ i ]) + " , ";
					kiekis++;
					//taDisplay.append( "Rastas skaicius  " + skaiciai [ i ] );
				} 
				
			}  
								  
		} catch( IOException e ) {
			
			System.out.println( "Error Encountered getting user input:" + e.getMessage() );
			e.printStackTrace();
		}
						
		taDisplay.append( "Nuskaityta " + kiekis + " skaiciu." + "\n");
			
		if ( kiekis > 0 ) {
		
			double max =  skaiciai [ 0 ];
			
			for ( int i = 0; i<kiekis; i++ ) {
				
				suma += skaiciai [ i ];
			
				if ( max < skaiciai [ i ] ) {
				
					max = skaiciai [ i ]; 
				}
			}
			
			taDisplay.append( "Maksimali reiksme skaiciu sekoje: " + max + "\n" );
			taDisplay.append( "Skaiciu suma: " + suma + "\n" );
			vid = suma / kiekis;
			taDisplay.append( "Vidurkis: " + vid + "\n" );

			taDisplay.append( "Skaiciai didesni uz vidurki: "  + "\n");
			
			double likusiu_suma = 0;
			
			for( int i = 0; i< kiekis; i++) {
			
				if ( skaiciai [ i ] > vid ) {
					
				
						taDisplay.append ( skaiciai [ i ] + ", " );
					
				} else {
				
					likusiu_suma += skaiciai [ i ]; 
				}
			}
			
			taDisplay.append("\n" + "likusiu skaiciu suma: " + likusiu_suma + "\n" );
			
		}
		
		if ( kiekis > 0 ) {
			
			double min = skaiciai [ 0 ];
			
			for ( int i = 0; i < kiekis; i++) {
				
				if ( min > skaiciai [ i ]) {
					min = skaiciai [ i ];
				}
			}
			taDisplay.append( "minimumas: " + suma + "\n" );
			
		}
		
     
		++count;
		tfCount.setText(count + ""); 
		//taDisplay.append( "Nuskaityta " + kiekis + " skaiciu." );
	
	  
	
   }
   
    public static void main(String[] args) throws Exception{			
	
		new Windo();
		
	  	
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
