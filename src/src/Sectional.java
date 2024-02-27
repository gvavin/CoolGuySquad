package src;


import java.util.ArrayList;

/**
 *
 * @author spockm
 */
public class Sectional 
{
    ArrayList<SchoolInfo> teams = new ArrayList<>();
    int host = 0; //which school is the host?
    
    public boolean canAddTeam() { return teams.size() < Algorithm.MAX_TEAMS_IN_SECTIONAL; }
    public boolean canTakeTeam() { return teams.size() > Algorithm.MIN_TEAMS_IN_SECTIONAL; }
    public SchoolInfo getHost() { return teams.get(host); }
    public void setHost(int h) { host = h; }
    
    public void chooseBestHost()
    {
        host = 0;
        for(int z=0; z<teams.size(); z++)
        {
            double d = this.totDistNewHost(z);
            if(d<this.totDistNewHost(host) )
                host = z;
        }
    }
    public void addSchool(SchoolInfo in)
    {
        teams.add(in);
        chooseBestHost();
    }
    
    public SchoolInfo takeRandomTeam()
    {
        int index = (int)(Math.random()*teams.size());
        SchoolInfo leaving = teams.remove(index);
        chooseBestHost(); //just incase the host leaves... 
        return leaving;
    }
    
    public SchoolInfo takeLastTeam()
    {
        SchoolInfo leaving = teams.remove(teams.size()-1);
        chooseBestHost(); //just incase the host leaves... 
        return leaving;
    }
    
    public ArrayList<SchoolInfo> getTeams() { return teams; }
    
    public void display()
    {
        for(SchoolInfo si : teams)
        {
            System.out.print(si.name+"  ");
        }
        System.out.println(" **");
        System.out.println("Average distance between schools = "+totalDistance());
        System.out.println("---------------------------------------------------");
    }
    
    /**
     * Calculates the total distance between all schools. 
     * Connects every point on the 'polygon' of schools and adds all those 
     * lengths. Then divides by size-1 to get an average distance a team would 
     * need to travel to a random sectional host. 
     * 
     * You may choose to CHANGE THIS METHOD as needed!!!
     * 
     * @return the average distance. 
     */
    public double totalDistance()
    {
        double totalDist = 0;
        int count = 0;
        for(int z=0; z<teams.size()-1; z++)
        {
            for(int q=z+1; q<teams.size(); q++)
            {
                totalDist += teams.get(z).distanceTo(teams.get(q));
                count++;
            }
        }
// When considering the host... Change the return
//        for(int z=0; z<teams.size(); z++)
//        {   
//            if(z != host)
//                totalDist += teams.get(z).distanceTo(teams.get(host));
//        }
        return totalDist/(teams.size()-1);
    }
    
/**
 * Calculates the total travel distance within this Sectional when 
 * team number 'h' is the host. 
 * @param h = index number of the host
 * @return the total travel distance. 
 */
    public double totDistNewHost(int h)
    {
        double totalDist = 0;
        int count = 0;
        for(int z=0; z<teams.size(); z++)
        {
            totalDist += teams.get(z).distanceTo(teams.get(h));
            count++;
        }
        return totalDist;///count;
    }
    
}
