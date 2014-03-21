/*
 * * Chapter #15 - Project 6   
   File Name:          LinkedlistHW.java  
   Programmer:         Jason Meyer
   Date Last Modified: April 19th, 2013
   * 
 Write an addSorted method for the generic linked list from Display 15.8 such
that the method adds a new node in the correct location so that the list remains
in sorted order. Note that this will require that the type parameter T extend the
Comparable interface. Write a suitable test program.
* 
* 
* 
* 
* This program will test the addsorted method by creating a linkedlist of 
* integers with 2 initial values.  Once that has been done it adds multiple 
* values to the linkedlist using the addSort method.
 */
package linkedlisthw;

public class LinkedlistHW {

  
    public static void main(String[] args) {
       //test it out
        
        LinkedList3<Integer> intlist = new LinkedList3<Integer>();
        intlist.addToStart(3);  //initialize a first node and link
        intlist.addToStart(1);
        
        intlist.addSorted(2);  //use the addsorted method to add integers
        intlist.addSorted(5);  //added out of order to demonstrate addSort
        intlist.addSorted(4);
        intlist.addSorted(8);
        intlist.addSorted(7);
        intlist.addSorted(6);
        
       intlist.outputList(); //output the list values
    }
}
