/*
 Write a program that reads a file of numbers of type int and outputs all 
 * the numbers to another file, but without any duplicate numbers. 
 * Assume that the input file is sorted from smallest first to largest 
 * last. After the program is run, the new file will contain all the numbers in 
 * the original file, but no number will appear more than once in the file. 
 * The numbers in the output file should also be sorted from smallest to 
 * largest. Your program should obtain both file names from the user. 
 * Use a text file that stores one number per line.

* 1)create a variable for the filename defined by user input
* 2)use fileinputstream to read from the file defined by the user
* 3)create a sort algorithm to sort the numbers in the file
* 3)create a variable for the output filename defined by user input
* 4)use fileoutputstream to output to the user defined file
 */

package inputstreamhw;

import java.util.Arrays;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
public class InputStreamHW {

    
    public static void main(String[] args) {
        
        Scanner keyboard = new Scanner(System.in);
       System.out.println("Enter a filename for input: ");
        String userinput;
        String useroutput;
        FileOutputStream userout;
        PrintStream writeuser;
        
        
        int[] inputnums = new int[14];
        
        //setup input file and populate the int array
        try
        {
           
        userinput = keyboard.next();
        
        Scanner inputfile = new Scanner(new FileInputStream(userinput));
        
          
                    for(int i = 0; inputfile.hasNextInt(); i++)
                    {
                        
                     inputnums[i] = inputfile.nextInt();
                     
                     
                          //  if(inputfile.nextInt() == inputnums[i])
                          //  {
     //attempt at removing duplicate ints                           
                          //      inputnums[i] = inputfile.nextInt();
                          //  }
                        
                    }
                
            inputfile.close();
            Arrays.sort(inputnums);
            
        //setup output file
         System.out.println("Enter a filename for output: ");
         
                useroutput = keyboard.next();
         
                userout = new FileOutputStream(useroutput); 
       
                  writeuser = new PrintStream(userout);
       
                        for(int i = 0; i < inputnums.length; i++)
                        {
                             writeuser.println(inputnums[i]);
                        }
                        writeuser.close();
        }
        catch(FileNotFoundException e)
                {
                    System.out.println("file not found");
                }
        
        
    }
}
