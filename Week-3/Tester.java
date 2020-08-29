 
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() 
    {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() 
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        
        la.printAll();
    }
    
    public void testUniqueIP()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " unique IPs");
    }
    
    public void testPrintAllHigherThanNum()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        ArrayList<String> uniqueIPs = la.uniqueIPVisitsOnDay("Sep 24");
        
        System.out.println("There were " + uniqueIPs.size() + " unique visits on that day");
    }
    
    public void testCountUniqueIPsInRange()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        
        int count = la.countUniqueIPsInRange(200, 299);
        System.out.println("There are "+ count + " unique IPs in that range.");
    }
    
    public void testCountVisitsPerIP()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        
        HashMap <String, Integer> counts = la.countVisitsPerIP();
        for (String ip : counts.keySet())
        {
            System.out.println(ip + " visited " + counts.get(ip) + " times.");
        }
    }
    
    public void testMostNumberVisitsByIPs()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        
        int maxVisits = la.mostNumberVisitsByIP();
        System.out.println("These IPs visited the site " + maxVisits + " times");
        
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        ArrayList<String> IPs = la.ipsMostVisits(counts);
        for (String ip : IPs)
        {
            System.out.println(ip);
        }
    }
    
    public void testIPsForDays()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        
        HashMap<String, ArrayList<String>> map = la.ipsForDays();
        for (String date : map.keySet())
        {
            System.out.println("\nIPs which visted on " + date + ":");
            for (String ip : map.get(date))
            {
                System.out.println(ip);
            }
        }
        
        String maxDay = la.dayWithMostIPVisits(map);
        System.out.println("\n\n" + maxDay + " had the most visits.");
        
        String date = "Sep 30";
        ArrayList<String> sortedIPs = la.ipsWithMostVisitsOnDay(map, date);
        System.out.println("\nSorted IPs which visited on " + date);
        for (String ip : sortedIPs)
        {
            System.out.println(ip);
        }
    }
}
