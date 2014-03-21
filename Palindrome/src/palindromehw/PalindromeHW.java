/*
 * Project handout #3 - Project 2   
   File Name:          PalindromeHW.java  
   Programmer:         Jason Meyer
   Date Last Modified: March 26th, 2013
   * 
   * 
Write a static recursive method definition for a method that takes one 
* parameter of type String and returns a boolean value. The method return 
* true if the argument is a palindrome and false otherwise. A palindrome is a 
* string that reads the same forward and backward, such as “radar”. Disregard 
* spaces and punctuation marks, and consider upper- and lowercase versions of 
* the same letter to be equal. For example, the following would be considered 
* a palindrome by your method.

* “Straw? No, too stupid a fad, I put soot on warts.”

Your method need not check that the string is correct English phrase or word. 
* The string “xyzczyx” will be considered a palindrome by your method. 
* Embed the method in a program and test it.

 */

package palindromehw;

import java.util.Scanner;
public class PalindromeHW {
//static method to check for palindrome
    public static boolean palindromeCheck(String input){
        if(input.length() == 0 || input.length() == 1)
                {
                    return true; //if length is 1 or 0 then true
                }
        else if (input.charAt(0) == input.charAt(input.length() - 1))
        {
            
           //if the characters are the same at each end, recurse through
            //the palindromecheck method checking characters
            
            return palindromeCheck(input.substring(1, input.length() -1));
        }
        return false;//if conditions not satisfied then false.
    }
    
    
    //main method demonstrates palindromeCheck()
    public static void main(String[] args) {
      
        Scanner keyboard = new Scanner(System.in);
        System.out.println("----Palindrome Detector----" + 
                "\n enter a string to be checked as a palindrome");
        String input = keyboard.nextLine();
        
        System.out.println(palindromeCheck(input));
    }
}
