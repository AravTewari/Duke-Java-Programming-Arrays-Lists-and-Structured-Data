import edu.duke.*;
import java.util.*;

public class GladLib 
{
    private ArrayList<String> usedLabels;
    private ArrayList<String> usedWords;
    private HashMap<String, ArrayList<String>> map;

    private Random random;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "tests/data";

    public GladLib()
    {
        initializeFromSource(dataSourceDirectory);
        random = new Random();
    }

    public GladLib(String source)
    {
        initializeFromSource(source);
        random = new Random();
    }

    private void initializeFromSource(String source)
    {
        map = new HashMap<String, ArrayList<String>>();
        String[] labels = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
        
        for (String s : labels)
        {
            ArrayList<String> arr = readIt(source + "/" + s + ".txt");
            map.put(s, arr);
        }
        usedWords = new ArrayList<String>();
        usedLabels = new ArrayList<String>();
    }

    private String randomFrom(ArrayList<String> source)
    {
        int index = random.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) 
    {
        if(!usedLabels.contains(label))
        {
            usedLabels.add(label);
        }
        
        if (label.equals("number"))
        {
            return ""+random.nextInt(50)+5;
        }
        return randomFrom(map.get(label));
    }

    private String processWord(String w)
    {
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1)
        {
            return w;
        }
        
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while (usedWords.contains(sub))
        {
            sub = getSubstitute(w.substring(first+1, last));
        }
        usedWords.add(sub);
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth)
    {
        int charsWritten = 0;
        for (String w : s.split("\\s+"))
        {
            if (charsWritten + w.length() > lineWidth)
            {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source)
    {
        String story = "";
        if (source.startsWith("http")) 
        {
            URLResource resource = new URLResource(source);
            for (String word : resource.words())
            {
                story = story + processWord(word) + " ";
            }
        }
        else 
        {
            FileResource resource = new FileResource(source);
            for (String word : resource.words())
            {
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source)
    {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) 
        {
            URLResource resource = new URLResource(source);
            for (String line : resource.lines())
            {
                list.add(line);
            }
        }
        else 
        {
            FileResource resource = new FileResource(source);
            for (String line : resource.lines())
            {
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap()
    {
        int sum = 0;
        for (String s : map.keySet())
        {
            int length = map.get(s).size();
            sum += length;
        }
        return sum;
    }
    
    private int totalWordsConsidered()
    {
        int sum = 0;
        for (String s : usedLabels)
        {
            if (!s.equals("number"))
            {
                int length = map.get(s).size();
                sum += length;
            }
        }
        return sum;
    }

    public void makeStory()
    {
        usedWords.clear();
        
        System.out.println("\n");
        String story = fromTemplate("tests/data/madtemplate2.txt");
        printOut(story, 60);
        
        System.out.println("\n\nNumber of words replaced: " + usedWords.size());
        int totalWords = totalWordsInMap();
        System.out.println("Total number of words available to pick from: " + totalWords);
        int consideredWords = totalWordsConsidered();
        System.out.println("Total number of words considered: " + consideredWords);
        
    }

}
