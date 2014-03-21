/* Final Project    
   File Name:          ImageBrowser.java  
   Programmer:         Jason Meyer
   Date Last Modified: May 14, 2013
   * 
   * 
   * 
* This class defines a program called ImageBrowser that allows the user to
* create and browse a list of images via a GUI.  The user can add individual
* image files as well as add whole directories or save and load the current
* list of images.  Each image is stored in an array list of ImageIcons which
* the user cycles through with the use of Jbuttons.  Jfilechooser is used
* for file selection from the user for both individual files and directories.
* InputStream and output stream is used to take in the file that is selected
* via the JFilechooser.  To load an image the user can use the Jbutton at the
* bottom of the Jframe, as well as cycle through images that have been added.
* The user can also remove a single image from the arraylist via the JMenu.
* If the user chooses to save the current list of images as a file, 
* the JFilechooser is utilized as well as outputstream to write each object
* in the arraylist to a binary file.  When the user loads a previously saved
* list, inputstream takes in the binary file and reads each ImageIcon object
* from the file and adds them to a new arraylist of ImageIcons.  Jscrollpanel
* is used to allow images larger than the current window to be view via 
* scrollbars.
* 
*/





package imageviewer;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;




public class ImageBrowser extends JFrame implements Serializable{
    
    
    
    
    //define main components of the program, (arraylist, main jpanel/label
    //and scrollpane
    private JPanel browsepanel;
    private JLabel imagepane;
    
    //scrollpane displays imagepane
    private JScrollPane scrollpane = new JScrollPane(imagepane);
    private ArrayList<ImageIcon> imagelist = new ArrayList<>();
    
    //Counter is used to define the index of the list, counter is incremented
    //and decremented each time user moves foward or backward(respectively)
    //while browsing the arraylist
    private int counter = 0; 
    private JFileChooser chooser = new JFileChooser();
   
               
    //ImageBrowser extends JFrame and instantiates a GUI for image browsing
    //and saving
    
    public ImageBrowser(String Header)
    {
        super(Header);
        setLayout(new BorderLayout());
        
        //menubar
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        
        JMenuItem adddir = new JMenuItem("Add Directory..");
        JMenuItem savelist = new JMenuItem("Save Imagelist..");
        JMenuItem loadlist = new JMenuItem("Load Imagelist..");
        JMenuItem newlist = new JMenuItem("Clear Imagelist");
        JMenuItem remove = new JMenuItem("Remove Image..");
        
        //add actionlisteners to menu items
        newlist.addActionListener(new listbuttonListener());
        adddir.addActionListener(new addDirListener());
        savelist.addActionListener(new saveimageListener());
        loadlist.addActionListener(new loadimageListener());
        remove.addActionListener(new listbuttonListener());
        
        //add items to appropriate JMenu
        file.add(adddir);
        file.add(savelist);
        file.add(loadlist);
        edit.add(remove);
        edit.add(newlist);
        
        menubar.add(file);
        menubar.add(edit);
        setJMenuBar(menubar);
        
        
        
        //defines the scrollpane settings and which panel is shown via 
        //the scrollpane (in this case, imagepane)
        
        scrollpane.setPreferredSize(new Dimension(400, 400));
        imagepane = new JLabel();
        imagepane.setPreferredSize(new Dimension(400, 400));
        imagepane.setBorder(BorderFactory.createEtchedBorder());
        
        
        
        //add panels to jframe and set default operations for jframe
        add(scrollpane, BorderLayout.CENTER);
        add(browsePanel(), BorderLayout.WEST);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,600);
        setVisible(true);
        
        
    }
  
    //browsepanel returns a jpanel that can be added to the Jframe
    public final JPanel browsePanel()
    {
        browsepanel = new JPanel();
        browsepanel.setLayout(new BorderLayout());
        JButton addfilebutton = new JButton("Add File(s)");
        JButton next = new JButton(">");
        JButton prev = new JButton("<");    //browsepanel contains jbuttons
        JPanel prevnextlabl = new JPanel(); //for the user to add single files
        prevnextlabl.add(prev);             // and to cycle through the array
        prevnextlabl.add(next);             
        prevnextlabl.add(addfilebutton);
        prev.addActionListener(new listbuttonListener());
                next.addActionListener(new listbuttonListener());

        addfilebutton.addActionListener(new filebuttonListener());
        browsepanel.add(prevnextlabl, BorderLayout.SOUTH);  
        browsepanel.setPreferredSize(new Dimension(200,600));
       
        
        return browsepanel;
        
    }
    
    
    
    ///writeimagelist writes each object in the arraylist to a binary file
    ///that is chosen and named by the user via JFilechooser.
    
    
    public void writeImagelist() throws IOException 
    {
         FileOutputStream tempstream = null;
                ObjectOutputStream objectOut = null;
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        Object[] temparray = imagelist.toArray();
        int returnVal3 = chooser.showSaveDialog(browsepanel);
        if (returnVal3 == JFileChooser.APPROVE_OPTION)
        {
            try{
            
            
           tempstream = new FileOutputStream(chooser.getSelectedFile());
           objectOut = new ObjectOutputStream( tempstream );
      for(int i = 0; (i<imagelist.size()); i++)
      {
        objectOut.writeUnshared( temparray[i] );
          
            objectOut.reset(); //allows garbage collection on prev. image data
                               //otherwise outofmemory errors may appear
                               //when writing numerous large image files.
      }
        
            }catch (FileNotFoundException e)
    {
        System.out.println("File Not Found");
    }
            tempstream.close();   ///close streams
        objectOut.close();
        }
    }
    
    
    
    
    
    
    
    //Readimagelist allows the user to select a binary file of previously
    //saved images. once selected the file is read via inputstream and each
    //ImageIcon object in the file is then added to the arraylist.
    
    public void readImagelist() throws ClassNotFoundException 
    {
        
       int returnVal4 = chooser.showOpenDialog(browsepanel);
        
        if(returnVal4 == JFileChooser.APPROVE_OPTION)
            {
                imagelist.clear(); 
       try
            {      
                
                //create an inputstream that can take
                //the file selected by user via the JFilechooser, 
                //read images from that file and add to arraylist
     FileInputStream fileIn = new FileInputStream(chooser.getSelectedFile());
            //display filepath in console
         System.out.println(chooser.getSelectedFile().toString());
         ObjectInputStream in = new ObjectInputStream(fileIn);
             
         
           for(int i = 0;i < 25;i++)//limit to 25 images, else out of memory
                {                   //error may occur
                    Object tempicon = (ImageIcon)in.readObject();
                    
                    imagelist.add((ImageIcon)tempicon);
                    
                    
                }
                fileIn.close();   //close streams
                in.close();
                } catch (IOException exc)
                    {
         
        
                        System.out.println("End of File");
       
                    } //close catch  
            
            }//close if statment   
            
    }//end readimagelist()    
    
    
    
    
    
    
    //addDir method allows user to add an entire directory of images (filtered
    //by filename extention) to the arraylist, and display them to the 
    //jscrollpane
    
    
    public void addDir()
    {
        
        
        FileNameExtensionFilter filter2 = new FileNameExtensionFilter(
        "Image Folders", "jpg", "gif");
        chooser.setFileFilter(filter2);
        
        
        //starts the JFilechooser dialog
        int returnVal2 = chooser.showOpenDialog(browsepanel);
        
        //when user selects file or directory
        if(returnVal2 == JFileChooser.APPROVE_OPTION)
        {
            scrollpane.setViewportView(new JLabel("loading.."));
            File directory = chooser.getSelectedFile();
            String[] list = directory.list();
            for(String s: list)
            {
                
             if(s.endsWith(".jpg") || s.endsWith(".png")|| s.endsWith(".gif"))
                {
                String tempstring = (directory.getPath() + "\\" + s);
                imagelist.add(new ImageIcon(tempstring));
                System.out.println("Loading " + tempstring);
                }
                
            }
        }
        System.out.println("ImageList size : " + imagelist.size());
        
        if(imagelist.size()>0)  //as long as images exist in arraylist
        {                       //set jscrollpane to display image
        scrollpane.setViewportView(new JLabel(imagelist.get(counter)));
        }
    }
    
    
    
    
    
    
    
    
    

    ///listbuttonListener handles actionperformed for the back and forward
    //buttons that cycle through the arraylist, as well as handling the action
    //for removing current image from the list and clearing the imagelist.
    
    private class listbuttonListener implements ActionListener
    {
        @Override
     public void actionPerformed(ActionEvent e)
     {
         if (e.getActionCommand().equalsIgnoreCase("<") )////back button
         {
           if(imagelist.size()>0)
           {
           counter--;
          
           try{
           scrollpane.setViewportView(new JLabel(imagelist.get(counter)));
           
           }catch(ArrayIndexOutOfBoundsException a)
               
   // Exception handling for indexoutofbounds exception when counter is on the 
   //less than arraylist size is dealt with by setting the counter to the
   //index of the last object in the array.
   //this allows the user to cycle through the entire array
    //from either direction
           {
               counter = (imagelist.size()-1);
               scrollpane.setViewportView(new JLabel(imagelist.get(counter)));
           }
           
           }
           
       }
    else if (e.getActionCommand().equalsIgnoreCase(">") )//forward button
       {
          if(imagelist.size()>0)
          {
           counter++;
           
            try{
          scrollpane.setViewportView(new JLabel(imagelist.get(counter)));
            }catch(IndexOutOfBoundsException b)
            {
              counter= 0;
              scrollpane.setViewportView(new JLabel(imagelist.get(counter)));
            }
          }
           
          
       }
    ///defines actionperformed for removing image
    else if (e.getActionCommand().equalsIgnoreCase("Remove Image.."))
    {
        if(imagelist.size()>1)   
        {
        imagelist.remove(counter);
                
                
               if(counter>0)
               {
               counter--;  //make sure counter does not go smaller than
                            //smallest arraylist index
               }
                
                //set scrollpanel view
            scrollpane.setViewportView(new JLabel(imagelist.get(counter)));
                
        }
        else if(imagelist.size() == 1) //if arraylist has only one image
                {                     //and that image is removed
                                      //set scrollpane to display blank label
                   imagelist.remove(counter); 
                   scrollpane.setViewportView(new JLabel(""));
                }
        
      }
    else if(e.getActionCommand().equalsIgnoreCase("Clear Imagelist"))
    {
        imagelist.clear();
        scrollpane.setViewportView(new JLabel(""));
    }
   }
}
    
    
    //adddirListener defines actionperformed for adding directory
    
     private class addDirListener implements ActionListener
    {
       public void actionPerformed(ActionEvent e)
       {
           if(e.getActionCommand().equalsIgnoreCase("Add Directory.."))
           {
               
               chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
               addDir();
               
               
           }
           
       }
     }
     
     
     
     
   //saveimageListener defines actionpeformed when user selects Save imagelist
     //from the JMenu
     private class saveimageListener implements ActionListener
     {
         public void actionPerformed(ActionEvent e)
       {
           
           if(e.getActionCommand().equalsIgnoreCase("Save Imagelist.."))
           {
               chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
               try {
                   writeImagelist();
               } catch (IOException ex) {
                   System.out.println("IO exception");
               }
           }
       }
     }
     
     
     
   //loadimageListener defines actionpeformed when user selects load imagelist
   //from the JMenu
     private class loadimageListener implements ActionListener
     {
         @Override
         public void actionPerformed(ActionEvent e)
       {
           if(e.getActionCommand().equalsIgnoreCase("Load Imagelist.."))
           {
               chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
               try {           
                   
                   readImagelist(); ///call to readimagelist method
                   
                   
               } catch (ClassNotFoundException ex) {
                   System.out.println("Classnotfound exception");
               }
                   System.out.println("ImageList size: " + imagelist.size());
              
              //if imagelist is not empty, display the first image in the list
               if(imagelist.size()>0)
               {
                   scrollpane.setViewportView(new JLabel(imagelist.get(0)));
               }
            counter = 0; //reset counter to 0;
               
           }
       }
     }
     
     
     
     
     //fielbuttonListener defines actionperformed when user selects 
     //the addfilebutton in the browserpanel
     
    private class filebuttonListener implements ActionListener
    {
        @Override
       public void actionPerformed(ActionEvent e)
       {
           if(e.getActionCommand().equalsIgnoreCase("Add File(s)"))
           {
              chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); 
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                 "JPG & GIF Images", "jpg", "gif");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(browsepanel);
                if(returnVal == JFileChooser.APPROVE_OPTION) 
                {
                   System.out.println("Loading Image: " +
                   chooser.getSelectedFile().getName());
                   
                   //create a string with the full pathname of the user
                   //selected file. create a temp ImageIcon defined by this
                   //pathname, and add the imageicon to the arraylist
                   
                   
                   String dirfile  = chooser.getSelectedFile().getPath();
                   ImageIcon temp = new ImageIcon(dirfile);
      
       
       
                    imagelist.add(temp);
                        
                    
                    
                    //Display the temp icon in the scrollpanel
                    scrollpane.setViewportView(new JLabel(temp));

                    counter = imagelist.size() - 1; //set counter to index
                                                 //of new image (end of list)
                    System.out.println(imagelist.size());
                }
    
           }
       }
    }
    
  
}

