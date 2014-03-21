/*
 * 
 * * * Chapter #15 - Project 2 
   File Name:          CalculatorGUI.java  
   Programmer:         Jason Meyer
   Date Last Modified: May 1st, 2013
   * 
   * This class demonstrated a GUI calculator created using JFrame components.
   * The calculator buttons are created using a gridlayout manager which is
   * added to the main panel.  the main panel uses a Borderlayout manager, so 
   * the calc buttons are located at center, and the textfields along with their
   * labels are placed in the north of the mainpanel.  The actionlisteners
   * on each button initlialize an if else statement to determine how to process
   * input.  the actioncommand for each button is stored as a string in a 
   * temp variable which is then output to the display panel.  In this way,
   * whatever the user presses will be displayed.  When the "=" button is 
   * pressed, the user created string will be processed as a formula with the 
   * help of Scriptmanager and Scriptengine.  The processed input is then 
   * converted back into a string and output to the results panel and the 
   * user input panel (operandpanel) is reset to be blank.
   * 
   * 
 Design and code a Swing GUI calculator. You can use Display 17.19 as a starting
point, but your calculator will be more sophisticated. Your calculator will have
two text fields that the user cannot change: One labeled "Result" will contain 
result of performing the operation, and the other labeled "Operand" will be for
the user to enter a number to be added, subtracted, and so forth from the
result. The user enters the number for the "Operand" text field by clicking 
labeled with the digits 0 through 9 and a decimal point, just as in a real calc.
Allow the operations of addition, subtraction, multiplication, and division. Use 
GridLayout manager to produce a button pad that looks similar to the keyboard
on a real calculator.
 */
package calculatorgui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CalculatorGUI extends JFrame implements ActionListener { 

    
    ///scriptengine is used to process user input as a math formula
    private ScriptEngineManager mgr = new ScriptEngineManager();
    private ScriptEngine engine = mgr.getEngineByName("JavaScript");
    
    
    private double finalresult= 0.0;
    private JPanel mainpanel = new JPanel();
    private JPanel calcbuttonpanel = new JPanel();
    
    
    
    private JPanel top = new JPanel();
    private JPanel textpanel = new JPanel();
    private JPanel inputpanel = new JPanel();
    
    
    
    private JTextField displaypanel = new JTextField();
    private JTextField operandpanel = new JTextField();
    private JLabel result = new JLabel("Result");
    private JLabel operand = new JLabel("Operand");
    
    
    ///one JButton for each button on the calculator
    private JButton one, two, three, four, five, six, seven, eight, nine,
                    plus, minus, multiply, divide, decimal, zero, equals;
    
    
    
    public CalculatorGUI()
    {
        super("Calculator");    //call to jframe constructor
        
        //initiliaze all buttons
        one = new JButton("1");
        two = new JButton("2");
        three = new JButton("3");
        four = new JButton("4");
        five = new JButton("5");
        six = new JButton("6");
        seven = new JButton("7");
        eight = new JButton("8");
        nine = new JButton("9");        
        plus = new JButton("+");
        minus = new JButton("-");
        multiply = new JButton("*");
        divide = new JButton("/");
        decimal = new JButton(".");
        zero = new JButton("0");
        equals = new JButton("=");
        
        //setup the text area panels including Jlabels for each
        inputpanel.setLayout(new BoxLayout(inputpanel, BoxLayout.PAGE_AXIS));
        inputpanel.add(displaypanel);
        inputpanel.add(operandpanel);
        inputpanel.setPreferredSize(new Dimension(200,45));
        
        
        
        
        displaypanel.setEditable(false);   //set each textfield uneditable
        operandpanel.setEditable(false);
        
        
        
        
        //textpanel contains the jlabels for result and operand
        textpanel.setLayout(new BoxLayout(textpanel, BoxLayout.PAGE_AXIS));
        textpanel.add(result);
        textpanel.add(Box.createVerticalGlue());  //used for spacing
        textpanel.add(operand);
        
        
        
        
       ///apply inputpanel and textpanel to the top panel which will be placed
        //at the top of the mainpanel
        top.setLayout(new BorderLayout());
        top.add(inputpanel, BorderLayout.EAST);
                top.add(textpanel, BorderLayout.WEST);
        
        
                
        //create the calculator layout by adding all the buttons
        //to the calcbutton panel
        calcbuttonpanel.setLayout(new GridLayout(4,4));
        calcbuttonpanel.add(one);
        calcbuttonpanel.add(two);
        calcbuttonpanel.add(three);
        calcbuttonpanel.add(four);
        calcbuttonpanel.add(five);
        calcbuttonpanel.add(six);
        calcbuttonpanel.add(seven);
        calcbuttonpanel.add(eight);
        calcbuttonpanel.add(nine);
        calcbuttonpanel.add(zero);
        calcbuttonpanel.add(plus);
        calcbuttonpanel.add(minus);
        calcbuttonpanel.add(divide);
        calcbuttonpanel.add(decimal);
        calcbuttonpanel.add(multiply);
        calcbuttonpanel.add(equals);
        
        //add actionlisteners to each button
        one.addActionListener(this);
        two.addActionListener(this);
        three.addActionListener(this);
        four.addActionListener(this);
        five.addActionListener(this);
        six.addActionListener(this);
        seven.addActionListener(this);
        eight.addActionListener(this);
        nine.addActionListener(this);
        zero.addActionListener(this);
       plus.addActionListener(this);
        minus.addActionListener(this);
        equals.addActionListener(this);
        one.addActionListener(this);
        multiply.addActionListener(this);
        divide.addActionListener(this);
        decimal.addActionListener(this);
        
        calcbuttonpanel.setPreferredSize(new Dimension(300, 300));
        
        
        
         
         
         
         
         
         
        mainpanel.setLayout(new BorderLayout());
        
        //add calculator buttons to the center
        mainpanel.add(calcbuttonpanel, BorderLayout.CENTER);
        
        //add textfields and labels to the top
        mainpanel.add(top, BorderLayout.NORTH);
      
        mainpanel.setPreferredSize(new Dimension(200,100));
        
        //add main panel to the jframe
        add(mainpanel);
        
        
        
       //set default settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 700);
     
        setVisible(true);
    }
    
    private static double stringToDouble(String stringObject)
    {
 
        return Double.parseDouble(stringObject.trim());
    }
    
    
    /////here the actionperformed method has been modified to utilize
    /////scriptengine. one advantage of 
    /////scriptengine along with scriptengine manager is that it allows
    //// for a string to be evaluated as a mathmatical formula
    
    ///in this case the user input is taken as a string and evaluated
    ///as a formula using the eval method
  
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
        String actionCommand = e.getActionCommand();//default action command
                                                    // is the Jbutton label
       
        if(actionCommand.equalsIgnoreCase("="))
        {   
            try {
                
                String zerotemp = operandpanel.getText(); //user input stored
                                                            //as string
                
                
                
                Object temp = engine.eval(zerotemp); ///evaluate user input
                                                     //as a formula, store in 
                                                     //temp variable
                
                
                String temp2 = temp.toString(); ///convert back to string
                
                
                 displaypanel.setText(temp2);  //display in the result textfield
                 
                 
                 operandpanel.setText("");  //reset the operand field
            } catch (ScriptException ex) {
                displaypanel.setText("Error");   //sends an error message
                //in special cases ( mainly used for division by zero error)
                operandpanel.setText("");//reset the operand panel
            }
        }
        else{
            
            //set the operand panel to display what the user has clicked,
            //this appends and adds to previous input
     operandpanel.setText(operandpanel.getText() + actionCommand);
        }                   //previous input//   +  //new input//
       }
     
    
    public static void main(String[] args) {
        
        CalculatorGUI demo = new CalculatorGUI();
        
    }
}
