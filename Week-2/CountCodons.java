
/**
 * Write a description of CountCodons here.
 * 
 * @author Arav Tewari
 * @version June 10, 2020
 */

import edu.duke.*;
import java.util.*;

public class CountCodons 
{   
    private HashMap<String, Integer> map;

    public CountCodons()
    {
        map = new HashMap<String, Integer>();
    }

    private void buildCodonMap(int start, String dna)
    {
        map.clear();
        while (true)
        {
            int end = start + 3;
            if (end > dna.length())
            {
                break;
            }
            String codon = dna.substring(start, start + 3);

            if (map.keySet().contains(codon))
            {
                map.put(codon, map.get(codon) + 1);
            }
            else
            {
                map.put(codon, 1);
            }
            start += 3;
        }
    }

    private String getMostCommonCodon()
    {
        int maxFreq = 0;
        String maxCodon = "";
        for (String codon : map.keySet())
        {
            int currFreq = map.get(codon);
            if (currFreq > maxFreq)
            {
                maxFreq = currFreq;
                maxCodon = codon;
            }
        }
        return maxCodon;
    }

    private void printCodonCounts(int start, int end)
    {
        for (String codon : map.keySet())
        {
            int currFreq = map.get(codon);
            if (currFreq >= start && currFreq <= end)
            {
                System.out.println(codon + " " + currFreq);
            }
        }
    }

    public void tester()
    {
        FileResource fr = new FileResource();
        String dna = fr.asString().trim()   ;
        int start = 7;
        int stop = 7;

        for (int i = 0; i < 3; i++)
        {
            buildCodonMap(i, dna);
            System.out.println("\n\nReading frame starting with " + i + " results in " + map.size() + " unique codons");

            String maxCodon = getMostCommonCodon();
            System.out.println("and most common codon is " + maxCodon + " with count " + map.get(maxCodon) + "\n");

            System.out.println("Counts of codon between " + start + " and " + stop + " inclusive are:");
            printCodonCounts(start, stop);
        }
    }
}
