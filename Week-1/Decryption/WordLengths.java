
/**
 * Write a description of WordLengths here.
 * 
 * @author Arav Tewari
 * @version June 8, 2020
 */

import edu.duke.*;

public class WordLengths 
{
    private int indexOfMax(int[] counts)
    {
        int max = 0;
        for (int i = 0; i < counts.length; i++)
        {
            int val = counts[i];
            
            if (val > max)
            {
                max = val;
            }
        }
        return max;
    }
    
    private void countWordLengths(FileResource fr, int[] counts)
    {
        for (String word : fr.words())
        {
            int length = word.length();
            
            if (!Character.isLetter(word.charAt(0)) || !Character.isLetter(word.charAt(length-1)))
            {
                length--;
            }
            
            if (length > counts.length)
            {
                counts[counts.length-1] ++;
            }
            else
            {
                counts[length]++;
            }
        }
        
        for (int i = 0; i < counts.length; i++)
        {
            if (counts[i] != 0)
                System.out.println("number of words with length " + i + ": " + counts[i]);
        }
        
        int val = indexOfMax(counts);
        System.out.println("\n" + val);
    }
    
    public void testCountWordLengths()
    {
        FileResource fr = new FileResource();
        int[] counts = new int[50];
        
        countWordLengths(fr, counts);
    }
}
