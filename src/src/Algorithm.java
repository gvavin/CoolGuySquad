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

    public double originalTemp;

    public double newTemp;

    public double deltaTemp;

    public double p;
    
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
        originalTemp = evaluate();

        int firstSection = (int)(Math.random()*sectionals.length);
        int secondSection = (int)(Math.random()*sectionals.length);
        int firstTeam =(int)(Math.random()*sectionals[firstSection].getTeams().size());
        int secondTeam =(int)(Math.random()*sectionals[secondSection].getTeams().size());

        SchoolInfo teamOne = sectionals[firstSection].getTeams().get(firstTeam);
        SchoolInfo teamTwo = sectionals[firstSection].getTeams().get(secondTeam);

        SchoolInfo temp = teamOne;

        sectionals[firstSection].getTeams().remove(firstTeam);
        sectionals[firstSection].getTeams().add(teamTwo);
        sectionals[secondSection].getTeams().remove(teamTwo);
        sectionals[secondSection].getTeams().add(temp);


        newTemp = evaluate();
        deltaTemp = originalTemp-newTemp;

        if(deltaTemp<0){

        }
        else{
            p
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
