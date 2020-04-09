import java.util.Arrays; 
   
  
public class students 
{ 
    public static void main (String[] args) 
    { 
        Student [] arr1 = {new Student(111, "bbbb", "london"), 
                           new Student(131, "aaaa", "nyc"), 
                           new Student(121, "cccc", "jaipur")}; 
          
        Student [] arr2 = {new Student(111, "bbbb", "london"), 
                           new Student(131, "aaaa", "nyc"), 
                           new Student(121, "cccc", "jaipur")}; 
          
        Student [] arr3 = {new Student(111, "bbbb", "london"), 
                           new Student(121, "dddd", "jaipur"), 
                           new Student(131, "aaaa", "nyc"), 
                           }; 
          
        System.out.println("is arr1 equals to arr2 : " + 
                                    Arrays.equals(arr1, arr2)); 
        System.out.println("is arr1 equals to arr3 : " + 
                                    Arrays.equals(arr1, arr3));     
    }     
} 
   
// A class to represent a student. 
class Student 
{ 
    int rollno; 
    String name, address; 
   
    // Constructor 
    public Student(int rollno, String name, 
                               String address) 
    { 
        this.rollno = rollno; 
        this.name = name; 
        this.address = address; 
    } 
      
    @Override
    public boolean equals(Object obj) { 
          
        // typecast obj to Student so that we can compare students 
        Student s = (Student) obj; 
          
        return this.rollno == s.rollno && this.name.equals(s.name) 
                                && this.address.equals(s.address); 
    } 
} 