
import java.awt.*;        // Using AWT containers and components
import java.awt.event.*;  // Using AWT events classes and listener interfaces
 
// An AWT GUI program inherits the top-level container java.awt.Frame
public class Window extends Frame
      implements ActionListener, WindowListener {
      // This class acts as listener for ActionEvent and WindowEvent
      // A Java class can extend only one superclass, but it can implement multiple interfaces.
 
	private TextField tfMano;
	private TextField tfCount;  // Declare a TextField component
	private Button btnCount; 
	private TextArea taDisplay;   // Declare a Button component
	private int count = 0;      // Counter's value
	private String b = "wsadasdas";
 
   // Constructor to setup the GUI components and event handlers
   public Window() {
      setLayout(new FlowLayout()); // "super" Frame sets to FlowLayout
 
      add(new Label(b));   // "super" Frame adds an anonymous Label
 
      tfCount = new TextField("0", 10); // Construct the TextField
      tfCount.setEditable(false);       // read-only
      add(tfCount); 

      btnCount = new Button("Count");  // Construct the Button
      add(btnCount); 
	taDisplay = new TextArea(5, 40); // 5 rows, 40 columns
      add(taDisplay);	  // "super" Frame adds Button

      btnCount.addActionListener(this);
        // btnCount (source object) fires ActionEvent upon clicking
        // btnCount adds "this" object as an ActionEvent listener
 
      addWindowListener(this);
        // "super" Frame (source object) fires WindowEvent.
        // "super" Frame adds "this" object as a WindowEvent listener.
 
      setTitle("WindowEvent Demo"); // "super" Frame sets title
      setSize(250, 100);            // "super" Frame sets initial size
      setVisible(true);             // "super" Frame shows
   }
 
   // The entry main() method
   public static void main(String[] args) {
      new Window();  // Let the construct do the job
   
   
   
   }
 
   /* ActionEvent handler */
   @Override
   public void actionPerformed(ActionEvent evt) {
	String bla = "";
      ++count;
	  bla += count;
      tfCount.setText(count + "");
	taDisplay.append("You have typed " + b + "\n");
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