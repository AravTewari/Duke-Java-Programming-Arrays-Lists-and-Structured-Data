
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author Arav Tewari 
 * @version June 11, 2020
 */

import java.util.*;
import edu.duke.*;
import java.text.*;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;

    public LogAnalyzer() 
    {
        records = new ArrayList<LogEntry>();
    }

    public void readFile() 
    {
        FileResource fr = new FileResource();
        for (String entry : fr.lines())
        {
            LogEntry le = WebLogParser.parseEntry(entry);
            records.add(le);
        }
    }

    public void printAll() 
    {
        for (LogEntry le : records) 
        {
            System.out.println(le);
        }
    }

    public int countUniqueIPs()
    {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records)
        {
            String currIP = le.getIpAddress();
            if (!uniqueIPs.contains(currIP))
            {
                uniqueIPs.add(currIP);
            }
        }
        return uniqueIPs.size();
    }
    
    public int countUniqueIPsInRange(int low, int high)
    {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records)
        {
            int currStatus = le.getStatusCode();
            String currIP = le.getIpAddress();
            if (currStatus >= low && currStatus <= high)
            {
                if (!uniqueIPs.contains(currIP))
                {
                    uniqueIPs.add(currIP);
                }
            }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num)
    {
        for (LogEntry le : records)
        {
            int currStatus = le.getStatusCode();
            if (currStatus > num)
            {
                System.out.println(le);
            }
        }
    }
    
    public ArrayList<String> uniqueIPVisitsOnDay(String date)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd", Locale.US);
        
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records)
        {
            Date unformattedDate = le.getAccessTime();
            String currDate = dateFormat.format(unformattedDate);
            
            String currIP = le.getIpAddress();
            if (currDate.equals(date) && !uniqueIPs.contains(currIP))
            {
                uniqueIPs.add(currIP);
            }
        }
        return uniqueIPs;
    }
    
    public ArrayList<String> ipVisitsOnDay(String date)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd", Locale.US);
        
        ArrayList<String> IPs = new ArrayList<String>();
        for (LogEntry le : records)
        {
            Date unformattedDate = le.getAccessTime();
            String currDate = dateFormat.format(unformattedDate);
            
            String currIP = le.getIpAddress();
            if (currDate.equals(date))
            {
                IPs.add(currIP);
            }
        }
        return IPs;
    }
    
    public HashMap<String, Integer> countVisitsPerIP()
    {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (LogEntry le : records)
        {
            String currIP = le.getIpAddress();
            if (!counts.keySet().contains(currIP))
            {
                counts.put(currIP, 1);
            }
            else
            {
                int currCount = counts.get(currIP);
                counts.put(currIP, currCount + 1);
            }
        }
        return counts;
    }
    
    public int mostNumberVisitsByIP()
    {
        HashMap<String, Integer> counts = countVisitsPerIP();
        int maxCount = 0;
        
        for (String ip : counts.keySet())
        {
            int currCount = counts.get(ip);
            if (currCount > maxCount)
            {
                maxCount = currCount;
            }
        }
        return maxCount;
    }
    
    public ArrayList<String> ipsMostVisits(HashMap<String, Integer> counts)
    {
        ArrayList<String> IPs = new ArrayList<String>();
        int maxCount = mostNumberVisitsByIP();
        
        for (String ip : counts.keySet())
        {
            int currCount = counts.get(ip);
            if (currCount == maxCount)
            {
                IPs.add(ip);
            }
        }
        return IPs;
    }
    
    public HashMap<String, ArrayList<String>> ipsForDays()
    {
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd", Locale.US);
        
        
        for (LogEntry le : records)
        {
            Date unformattedDate = le.getAccessTime();
            String currDate = dateFormat.format(unformattedDate);
            
            String currIP = le.getIpAddress();
            if (!map.keySet().contains(currDate))
            {
                ArrayList<String> IPs = ipVisitsOnDay(currDate);
                map.put(currDate, IPs);
            }
        }
        return map;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map)
    {
        String maxDate = "";
        int maxVisits = 0;
        
        for (String date : map.keySet())
        {
            int totalVisits = map.get(date).size();
            if (totalVisits > maxVisits)
            {
                maxVisits = totalVisits;
                maxDate = date;
            }
        }
        return maxDate;
    }
    
    public ArrayList<String> ipsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String date)
    {
        ArrayList<String> arr = map.get(date);
        Collections.sort(arr);
        return arr;
    }
}
