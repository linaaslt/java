import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File; 
import java.io.FileWriter;


public class test {
    public static void main(String[] args) {
        String palindrome = "";
        int len = palindrome.length();
        char[] tempCharArray = new char[len];
        char[] charArray = new char[len];
        
        // put original string in an 
        // array of chars
        for (int i = 0; i < len; i++) {
            tempCharArray[i] = 
                palindrome.charAt(i);
        } 
        
        // reverse array of chars
        for (int j = 0; j < len; j++) {
            charArray[j] =
                tempCharArray[len - 1 - j];
        }
        
        String reversePalindrome =
            new String(charArray);
			
		try {	
			FileWriter myWriter = new FileWriter("duomenys.csv");
			  myWriter.write( palindrome );
			  myWriter.close();
			  System.out.println("Successfully wrote to the file.");
        System.out.println(reversePalindrome);
		} catch   (Exception e) {
			
				System.out.println( "Error Encountered getting user input:" + e.getMessage() );
				// e.printStackTrace();
			}
    }
}