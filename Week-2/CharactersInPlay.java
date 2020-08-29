
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author Arav Tewari 
 * @version June 10, 2020
 */

import java.util.*;
import edu.duke.*;

public class CharactersInPlay 
{
    private ArrayList<String> characters;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay()
    {
        characters = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    
    public void update (String person)
    {
        int index = characters.indexOf(person);
        if (index == -1)
        {
            characters.add(person);
            counts.add(1);
        }
        else
        {
            int val = counts.get(index);
            counts.set(index, val+1);
        }
    }
    
    public void findAllCharacters()
    {
        characters.clear();
        counts.clear();
        
        FileResource fr = new FileResource();
        for (String line : fr.lines())
        {
            int index = line.indexOf(".");
            if (index != -1)
            {
                String person = line.substring(0, index).trim();
                update(person);
            }
        }
    }
    
    public void charactersWithNumParts(int num1, int num2)
    {
        System.out.println("\nCharacters with " + num1 + "-" + num2 + " lines:");
        for (int i = 0; i < characters.size(); i++)
        {
            int count = counts.get(i);
            
            if (count >= num1 && count <= num2)
            {
                System.out.println(characters.get(i) + " " + count);
            }
        }
    }
    
    public void tester()
    {
        findAllCharacters();
        for (int i = 0; i < characters.size(); i++)
        {
            int count = counts.get(i);
            
            if (count > 1)
            {
                System.out.println(characters.get(i) + " " + count);
            }
        }
        charactersWithNumParts(7, 7);
    }
}
