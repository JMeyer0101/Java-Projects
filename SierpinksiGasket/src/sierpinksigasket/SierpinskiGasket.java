/*
 Write 
 * Chapter 18 - Project #1     
   File Name:          SierpinskiGasket.java  
   Programmer:         Jason Meyer
   Date Last Modified: May 9, 2013
   * 
   * 
   * 
   * 
   * 
   * 
   * 

* 
* 
* This program uses java.awt.point to handle each point of the triangle.
* A triangle is drawn from these 3 points (x, y, and z) and the point "current"
* is set to one of the points of the triangle.
* 
* A method called decidePoint uses a for loop to calculate and draw a rectangle
* at the midpoint of current and another point called "target" with the help
* of the findMidpoint method.  The findMidpoint method calculates the midpoint
* between two points by taking in two points as its parameter and adding the
* sum of the x and y coordinates for these points and dividing them by 2.
* A rectangle is then drawn at this location and another midpoint is then 
* calculated and the same code is run again for another rectangle 10000 times.
* 
* 
* 
 */
package sierpinksigasket;

import java.awt.Graphics;
import java.awt.Point;
import javax.swing.*;
public class SierpinskiGasket extends JFrame{

    
    ///define a jpanel where the images will be drawn
    private JPanel drawpanel = new JPanel();
   
    
    //create variables for each point needed to make the triangle
    private  Point x = new Point(75,700),
                   y = new Point(450,50),
                   z = new Point(950,700),
            
            //define points to be used in later calculations;
                   current = x,
                   target;
                    
    
   public SierpinskiGasket()
   {
       super("Sierpinski Gasket");//call to Jframe constructor
       
       
       //add the jlabel for graphics to illustrate on
       add(drawpanel);
       
       //set default window settings
       setSize(800,800);
      
       
       setVisible(true);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
   //findmidpoint takes two points as input and calculates the halfway point
   //between them (division by 2)
   public Point findMidpoint(Point one, Point two) {
    return new Point((Math.round(one.x+two.x)/2),
                     (Math.round(one.y+two.y)/2));
}
   
    @Override
   public void paint(Graphics g)
   {
    //draw an equilateral triangle using the point variables
    g.drawLine(x.x,x.y,y.x,y.y);
    g.drawLine(x.x,x.y,z.x,z.y);
    g.drawLine(z.x,z.y,y.x,y.y);
     
     
     
     //call decidepoint
     decidePoint();
   
     
   }
    
    //decidepoint method performs most of the operations to create
    //the sierpinski gasket.  decidepoint uses Math.random to create a random
    //number, the if else statements decide which point becomes current 
    //depending on the value of this random number;
    public void decidePoint()
    {
        Graphics pane = drawpanel.getGraphics();
        
        double random;
        for(int i = 0; i < 10000;i++)
        {
           random = Math.random();
           if(random < .33)
           {
               target = x;
           }
           else if(random > .33 && random < .66)
           {
               target = y;
           }
           else if(random > .66 && random < 1)
           {
               target = z;
           }
           current = findMidpoint(current,target);
           
           
           //draw a rectangle at the midpoint (now set to current)
            pane.drawRect(current.x,current.y,4,4);  
        }
       
    }
    


 

    

    public static void main(String[] args) {
        SierpinskiGasket demo = new SierpinskiGasket();//create a new
                                                        //object and run
    }
}
