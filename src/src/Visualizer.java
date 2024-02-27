package src;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author spockm
 */
public class Visualizer 
{
    private DrawingFrame theFrame;
    Image indiana;
    
    final int FRAME_WIDTH = 500;
    final int FRAME_HEIGHT = 700;
    
    public Visualizer()
    {
        theFrame = new DrawingFrame(FRAME_WIDTH,FRAME_HEIGHT);
        initGraphics();
    }
    
    public void fillMap(Sectional[] sectionals)
    {
        Graphics g = theFrame.getPage();
        g.setColor(Color.BLACK);
        g.fillRect(0,0, FRAME_WIDTH, FRAME_HEIGHT);
//        g.drawImage(indiana, 0, 0,440,600, theFrame);

        g.setColor(Color.yellow);

        //TOP = 42, BOTTOM = 37.5
        //LEFT = 88, RIGHT = 84.5
        for(Sectional s : sectionals)
        {
            for(SchoolInfo si : s.getTeams())
            {
                int newX = (int)(-(si.longitude-88)*150);
                int newY = (int)(-(si.latitude-42)*150);
                for(SchoolInfo si2 : s.getTeams())
                {
                    int newX2 = (int)(-(si2.longitude-88)*150);
                    int newY2 = (int)(-(si2.latitude-42)*150);
                    g.setColor(Color.YELLOW);
                    g.drawLine(newX, newY, newX2, newY2);
                }
                g.setColor(Color.GREEN);
                g.fillRect(newX, newY, 5, 5);

            }
        }
        g.drawString("Total Value "+evaluate(sectionals), 20, 20);
        g.drawString("Temp= "+Algorithm.temp, 20, 40);
        theFrame.repaint();
    }
    
    
    public void initGraphics() 
    {      
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        
        indiana = toolkit.getImage("src/Indiana.png");
        
        System.out.println(indiana.getHeight(theFrame));
    }
    
    public double evaluate(Sectional[] sectionals)
    {
        int out = 0;
        for(Sectional s : sectionals)
        {
            out += s.totalDistance();
        }
        return out;
    }


}
