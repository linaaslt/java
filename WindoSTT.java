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
import java.awt.*;       
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

public class WindoSTT extends JFrame
      implements ActionListener, WindowListener {
      
	private JTextArea taDisplay;
	private JTextField tfCount; 
    private Button Hexi;
	private Button Decimal;
	private Button Bina;
	private TextField tfInput;
	
	public WindoSTT() {
		
		setLayout(new FlowLayout()); 
		
		tfInput = new TextField(10);
		add(tfInput);

		add(new Label("I kokia skaiciavimo sistema versti skaiciu" + "\n"));   		            

		Hexi = new Button ("Versti i Hexi");
		Hexi.addActionListener (new ActionListener(){
			public void actionPerformed (ActionEvent event){
				versti_hex ();
			}
		});
		add(Hexi);
		
		Bina = new Button ("Versti i Bina");
		Bina.addActionListener (new ActionListener(){
			public void actionPerformed (ActionEvent event){
				versti_BinDeci ();
			}
		});
		add(Bina);

		Decimal = new Button("Versti i Deci"); 
		Decimal.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent event){
				versti_deci ();	
			}
		});
		add(Decimal);
	
		taDisplay = new JTextArea( 10, 40);
		taDisplay.setFont(new Font("Serif", Font.ITALIC, 16));
		taDisplay.setTabSize(3);
		taDisplay.setLineWrap(true);
		JScrollPane scrollV = new JScrollPane (taDisplay);
		scrollV.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(taDisplay);	 
        
		//btnCount.addActionListener(this);
		
		addWindowListener(this);
		
		setTitle("Skaiciu konvertavimas"); 
		setSize(520, 550);            
		setVisible(true);            
	}
		
	public void versti_deci() {
	
	int skaicius = Integer.parseInt(tfInput.getText());
    String dvejet_skaiciai = "";
	int skaiciai = 0;
    skaiciai = skaicius;
 
		while( skaiciai > 0){
		
			if( skaiciai % 2 == 0){
				
				dvejet_skaiciai = "0" + dvejet_skaiciai;
				
			} else {
				
				dvejet_skaiciai = "1" + dvejet_skaiciai;
			}

			skaiciai = skaiciai / 2;
		}

		taDisplay.append(dvejet_skaiciai + " ");

	}
	
	public void versti_BinDeci() {
	
	long skaicius = Long.parseLong(tfInput.getText());
    long rasti_skaiciai = 0;
	long skaiciai = 0;
    int daliklis = 10;
	long kiekis = 0;
 
		while( skaicius > 0){
		
			skaiciai = skaicius % daliklis;
		
			if( skaiciai == 0 || skaiciai == 1){
				
				rasti_skaiciai = (long)(rasti_skaiciai + skaiciai * Math.pow(2, kiekis));
				skaicius  = skaicius / daliklis;
			
			} else {
			
				taDisplay.append ("Neteisingai ivestas skaicius ");
				break;			
			}

			kiekis++;
		}
		
		taDisplay.append(rasti_skaiciai + " ");

	}

	public void versti_hex() {
			
		String simboliai = "0123456789ABCDEF";	
		String skaiciai = "";
		int daliklis = 16;
		int skaicius = Integer.parseInt(tfInput.getText());
		
		while ( skaicius > 0 ){
	
			int simb = skaicius % daliklis;
			skaiciai = simboliai.charAt(simb) + skaiciai;
			skaicius = skaicius / daliklis;
		}
		
		taDisplay.append(skaiciai + " ");
	
	}
	 
   @Override
   public void actionPerformed(ActionEvent evt) {

   }
   
    public static void main(String[] args) throws Exception{			
	
		new WindoSTT();
  	
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
