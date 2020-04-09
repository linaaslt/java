package fileexamples;

import java.io.File;
import java.util.Scanner;

public static void main(String[] args) {
    Scanner input = null;
    try {
        input = new Scanner(new FileReader("skaiciai.csv"));
    } catch (Exception ex) {
        System.out.println("Can not open file.");
        System.exit(0);
    }

    int smallest = input.nextInt();
    int largest = smallest;

    while(input.hasNextInt()) {
        int number = input.nextInt();
        if(number < smallest)
            smallest = number;
        if(number > largest)
            largest = number;
    }

    System.out.println("The numbers in the file fall in the range from " + smallest + " to " + largest);
}