package src;

/**
 *
 * @author spockm
 */
public class SchoolInfo 
{
    public String name;
    public double latitude; 
    public double longitude; 
    
    public SchoolInfo(String n, double lat, double lon)
    {
        name = n;
        latitude = lat;
        longitude = lon;
    }
    
    public SchoolInfo(String in)
    {
        String[] parts = in.split(",");
        name = parts[0];
        latitude = Double.valueOf(parts[1]);
        longitude = Double.valueOf(parts[2]);
    }
    
    public void display()
    {
//        System.out.println(name+" = "+latitude+", "+longitude+"  distance to CNHS "+distanceTo(in)+" miles.");
        System.out.println(name+" = "+latitude+", "+longitude);
    }
    
    public double distanceTo(SchoolInfo other)
    {
        double lat1 = toRadians(latitude);
        double lat2 = toRadians(other.latitude);
        double lon1 = toRadians(longitude);
        double lon2 = toRadians(other.longitude);
        
        return Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon2-lon1))*3958.8;
    }
    
    public double toRadians(double degrees)
    {
        return degrees*Math.PI/180;
    }
}
