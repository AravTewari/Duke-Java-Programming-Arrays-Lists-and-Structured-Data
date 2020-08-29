
/**
 * Write a description of WordsInFiles here.
 * 
 * @author Arav Tewari 
 * @version June 10, 2020
 */

import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles 
{
    HashMap<String, ArrayList<String>> map;
    
    public WordsInFiles()
    {
        map = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f)
    {
        FileResource fr = new FileResource(f);
        String fileName = f.getName();
        
        for (String word : fr.words())
        {
            if (map.keySet().contains(word))
            {
                ArrayList<String> arr = map.get(word);
                int index = arr.indexOf(fileName);
                
                if (index == -1)
                {
                    arr.add(fileName);
                }
            }
            else
            {
                ArrayList<String> arr = new ArrayList<String>();
                arr.add(fileName);
                map.put(word, arr);
            }
        }
    }
    
    private void buildWordFileMap()
    {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles())
        {
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber()
    {
        int maxFreq = 0;        
        for (String word : map.keySet())
        {
            int freq = map.get(word).size();
            if (freq > maxFreq)
            {
                maxFreq = freq;
            }
        }
        return maxFreq;
    }
    
    private ArrayList<String> wordsInNumFiles(int number)
    {
        ArrayList<String> words = new ArrayList<String>();
        
        for (String word : map.keySet())
        {
            int size = map.get(word).size();
            if (size == number)
            {
                words.add(word);
            }
        }
        return words;
    }
    
    private void printFilesIn(String word)
    {
        ArrayList<String> arr = map.get(word);
        
        for (String file : arr)
        {
            System.out.println(file);
        }
    }
    
    public void tester()
    {
        buildWordFileMap();
        int maxFreq = 4;
        ArrayList<String> arr = wordsInNumFiles(maxFreq);
        
        System.out.println("\nThe greatest number of files a word appears in is " + maxFreq + " and there are " + arr.size() + " such words: ");
        for (String word : arr)
        {
            System.out.println("\n\"" + word + "\" appears in the files: ");
            printFilesIn(word);
        }
        
    }
    
    public void wordFinder()
    {
        FileResource fr = new FileResource();
        for (String s : fr.words())
        {
            if (s.equals("tree")) 
            {
                System.out.println(s);
            }
        }
    }
}
