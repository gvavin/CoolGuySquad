package src;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author spockm
 */
public class Algorithm
{
    public static final int NUM_SECTIONALS = 16;
    public static final int MIN_TEAMS_IN_SECTIONAL = 4;
    public static final int MAX_TEAMS_IN_SECTIONAL = 8;
    public static final int STARTING_TEMP = 100;
    public static final double COOLING_RATE = 1.02;

    public double originalEnergy;

    public double newEnergy;

    public boolean on = true;
    public double deltaEnergy;

    public double p;

    public boolean keyPressed;

    ArrayList<SchoolInfo> allSchools = new ArrayList<>();
    File file = new File("src/src/SchoolsLoc.csv");
    Scanner stream;
    Sectional[] sectionals = new Sectional[NUM_SECTIONALS];
    Visualizer viz = new Visualizer();
    static double temp = STARTING_TEMP;



    

    public static void main(String[] args)
    {
        Algorithm bob = new Algorithm();
        bob.runTheSim();
    }
    
    public void runTheSim()
    {
        readFile();
        makeSectionals();
        displaySectionals();
        drawNewMap();

        //RUN THE SIMULATION HERE
        temp = STARTING_TEMP;
        while(on)
            swapSectionals();
    
        displaySectionals();
    }
    
    public void readFile()
    {
        try
        {
            stream = new Scanner(file);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found!!!  Yikes! " + file.getName());
        }
        
        while(stream.hasNextLine())
        {
            String currentLine = stream.nextLine();
            if(currentLine.startsWith("//")) break;
            allSchools.add(new SchoolInfo(currentLine));
        }
    }
    
    public void drawNewMap()
    {
        viz.fillMap(sectionals);
    }
    public void displaystuff()
    {
        for(SchoolInfo si : allSchools)
            si.display();
    }
    
    /**
     * This method just sequentially assigns teams to sectionals.
     * It creates a starting point for the simulated annealing.
     */
    public void makeSectionals()
    {
        for(int z=0; z<sectionals.length; z++)
        {
            sectionals[z] = new Sectional();
        }
        for(int z=0; z<allSchools.size(); z++)
        {
            sectionals[z%NUM_SECTIONALS].addSchool(allSchools.get(z));
        }
        
    }

    public void swapSectionals()
    {
        originalEnergy = evaluate();

        int firstSection = (int)(Math.random()*sectionals.length);
        int secondSection = (int)(Math.random()*sectionals.length);


        SchoolInfo teamone = sectionals[firstSection].takeRandomTeam();
        SchoolInfo teamtwo = sectionals[secondSection].takeRandomTeam();
        sectionals[secondSection].addSchool(teamone);
        sectionals[firstSection].addSchool(teamtwo);



        newEnergy = evaluate();
        deltaEnergy = newEnergy-originalEnergy;

        if(deltaEnergy<0){
            temp = temp / COOLING_RATE;
        }
        else{
            p= Math.exp(-deltaEnergy / temp);
            double rand = Math.random()+1;
            if(p>rand){
                temp = temp / COOLING_RATE;
            }
            else{
                SchoolInfo teamthree = sectionals[firstSection].takeLastTeam();
                SchoolInfo teamfour = sectionals[secondSection].takeLastTeam();
                sectionals[secondSection].addSchool(teamthree);
                sectionals[firstSection].addSchool(teamfour);

                temp = temp / COOLING_RATE;
            }

        }





    }
    
    public void displaySectionals()
    {
        for(Sectional s : sectionals)
            s.display();
        System.out.println("=========================================");
        

    }
    
    /**
     * You might want to write your own evaluate function. 
     * This one uses the totalDistance function from the Sectional class. 
     * DISTANCE in some way should be used. 
     * @return a numeric value related to travel distance. 
     */
    public double evaluate()
    {
        int out = 0;
        for(Sectional s : sectionals)
        {
            out += s.totalDistance();
        }
        return out;
    }
    
}
