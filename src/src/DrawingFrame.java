package src;

import java.awt.Graphics;
import java.awt.Image;

/**
 * Copied from TurtleWorld!
 * @author spockm
 */
public class DrawingFrame extends javax.swing.JFrame 
{
/** A TurtleWorld is a JFrame on which an Image object is drawn each time 
 *  the JFrame is repainted.  Each Turtle draws on that Image object. */

    private static final int EDGE = 3, TOP = 30;  // around the JFrame
    private Image itsPicture;
    private Graphics itsPage;

    public DrawingFrame (int width, int height)
    {    super ("Indiana Sectionals via Simulated Annealing");  // set the title for the frame
        setDefaultCloseOperation (EXIT_ON_CLOSE); // no WindowListener
        setSize (width + 2 * EDGE, height + TOP + EDGE);  
        toFront();  // put this frame in front of the BlueJ window
        setVisible (true);  // cause a call to paint
        begin (width, height);
    }    //======================


    public void begin (int width, int height)
    {    itsPicture = new java.awt.image.BufferedImage (width, height, 
                       java.awt.image.BufferedImage.TYPE_INT_RGB);
        itsPage = itsPicture.getGraphics();
//        itsPage.setColor (Color.white);
//        itsPage.fillRect (0, 0, width, height);
//        itsPage.setColor (Color.black);
    }    //======================


    public Graphics getPage()
    {    return itsPage; // itsPicture.getGraphics(); => NO COLORS
    }    //======================


    public void paint (Graphics g)
    {    if (itsPicture != null)
            g.drawImage (itsPicture, EDGE, TOP, this);
    }    //======================
}    

