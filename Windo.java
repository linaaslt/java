
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
import java.awt.event.*;  // Using AWT events classes and listener interfaces
 
 
// An AWT GUI program inherits the top-level container java.awt.Frame
public class Windo extends Frame
      implements ActionListener, WindowListener {
      // This class acts as listener for ActionEvent and WindowEvent
      // A Java class can extend only one superclass, but it can implement multiple interfaces.
	private TextArea taDisplay;
	private TextField tfCount;  // Declare a TextField component
	private Button btnCount;    // Declare a Button component
	private int count = 0;
	public String skaicius = "";
	public String du = "As ir tu ir mes kartu";
	// Counter's value
		 
   // Constructor to setup the GUI components and event handlers
   public Windo() {
		setLayout(new FlowLayout()); // "super" Frame sets to FlowLayout

		add(new Label("Spausk"));   // "super" Frame adds an anonymous Label
	 
		tfCount = new TextField("0", 10); // Construct the TextField
		tfCount.setEditable(false);       // read-only
		add(tfCount);                     // "super" Frame adds TextField

		btnCount = new Button("Count");  // Construct the Button
		add(btnCount);   

		taDisplay = new TextArea( 5, 40 );
		taDisplay.setFont(new Font("Serif", Font.ITALIC, 16));		// 5 rows, 40 columns
		add(taDisplay);	  // "super" Frame adds Button

		btnCount.addActionListener(this);
		// btnCount (source object) fires ActionEvent upon clicking
		// btnCount adds "this" object as an ActionEvent listener

		addWindowListener(this);
		// "super" Frame (source object) fires WindowEvent.
		// "super" Frame adds "this" object as a WindowEvent listener.

		setTitle("WindowEvent Demo"); // "super" Frame sets title
		setSize(520, 250);            // "super" Frame sets initial size
		setVisible(true);             // "super" Frame shows
   }
 
   // The entry main() method

 
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
			
			System.out.println ( "duomenu failo turinys:" );
						
			while ( ( thisLine = br.readLine() ) != null ) {
			 
				System.out.println( thisLine );
				String[] skaiciu_strs = thisLine.split ( "," );
				
				for ( int i=0; i < skaiciu_strs .length; i++ ) {
				
					skaiciai [ kiekis ] = 
					
					Double.parseDouble (  skaiciu_strs [ i ] );
					
					kiekis++;
					System.out.println( "Rastas skaicius  " + skaiciai [ i ] );
				} 
				
			}  
								  
		} catch( IOException e ) {
			
			System.out.println( "Error Encountered getting user input:" + e.getMessage() );
			e.printStackTrace();
		}
						
		System.out.println( "Nuskaityta " + kiekis + " skaiciu." );
			
		if ( kiekis > 0 ) {
		
			double max =  skaiciai [ 0 ];
			
			for ( int i = 0; i<kiekis; i++ ) {
				
				suma += skaiciai [ i ];
			
				if ( max < skaiciai [ i ] ) {
				
					max = skaiciai [ i ]; 
				}
			}
			
			System.out.println ( "Maksimali reiksme skaiciu sekoje: " + max );
			System.out.println ( "Skaiciu suma: " + suma );
			vid = suma / kiekis;
			System.out.println ( "Vidurkis: " + vid );

			System.out.println ( "Skaiciai didesni uz vidurki: " );
			
			double likusiu_suma = 0;
			
			for( int i = 0; i< kiekis; i++) {
			
				if ( skaiciai [ i ] > vid ) {
				
						System.out.print ( skaiciai [ i ] + ", " );
					
				} else {
				
					likusiu_suma += skaiciai [ i ]; 
				}
			}
			
			System.out.println( "likusiu skaiciu suma: " + likusiu_suma );
			
		}
		
		if ( kiekis > 0 ) {
			
			double min = skaiciai [ 0 ];
			
			for ( int i = 0; i < kiekis; i++) {
				
				if ( min > skaiciai [ i ]) {
					min = skaiciai [ i ];
				}
			}
			System.out.println( "minimumas: " + suma );
			
		}
		
     
	 ++count;
      tfCount.setText(count + ""); 
	  taDisplay.append("Pradedam darba" +  + "\n");
	  taDisplay.append( skaiciai + "\n");
	  
	
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
