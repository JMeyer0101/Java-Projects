/*
 * 
 * Chapter No. 11 - Exercise No. 1   
   File Name:          RecursionHW.java  
   Programmer:         Jason Meyer
   Date Last Modified: March 16th, 2013
   
  
   * 
   * 
There are n people in a room, where n is an integer greater than or equal 
to 1. 
Each person shakes hands once with every other person. 
What is the total number, h(n) ,
of handshakes? Write a recursive function to solve this problem. 
To get you started, if there are only one or two people in the room, then
handshake(1) = 0
handshake(2) = 1
If a third person enters the room, he or she must shake hands with each of the
two people already there. This is two handshakes in addition to the number
of handshakes that would be made in a room of two people, or a total of three
handshakes.
If a fourth person enters the room, he or she must shake hands with each of the
three people already present. This is three handshakes in addition to the number
of handshakes that would be made in a room of three people, or six handshakes.
If you can generalize this to n handshakes, then it should help you write the
recursive solution.
   * 
   * 1)create a recursive method to calculate the handshakes
   * 2)create a main method to test the recursive method
   * 3)println the results
 

 */
package recursionhw;

import java.util.Scanner;

public class RecursionHW {
//recursive method calculates handshakes
    
   public static int Handshake(int numpeople){
    
       
     if(numpeople>2) 
     {
         System.out.println("recursion");//for illustrative purposes
         //recursive method adds previous number of handshakes to the base value
         return Handshake(numpeople - 1) + (numpeople - 1);//
     }
       else if(numpeople == 2)
    {
        return 1;           //if 2 people, 1 handshake, if one, none.
    }
    return 0;
    
} 
    public static void main(String[] args) {
        //this code will demonstrate the recursive method
        Scanner keyboard = new Scanner(System.in);
        int numpeople;
       System.out.println("How many people?"); //scanner input for numpeople
        numpeople = keyboard.nextInt();
        keyboard.nextLine();
        //print out the results of the recursive method
        System.out.println("Number of handshakes: " + Handshake(numpeople));
    }
    
    
}
