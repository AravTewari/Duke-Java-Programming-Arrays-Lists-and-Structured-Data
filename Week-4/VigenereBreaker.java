import java.util.*;
import edu.duke.*;
import java.io.File;

public class VigenereBreaker 
{
    private String sliceString(String message, int whichSlice, int totalSlices) 
    {
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices)
        {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    private int[] tryKeyLength(String encrypted, int klength, char mostCommon) 
    {
        int[] key = new int[klength];
        CaesarCracker ccr = new CaesarCracker(mostCommon);
        
        for (int i = 0; i < klength; i++)
        {
            String s = sliceString(encrypted, i, klength);
            int a = ccr.getKey(s);
            key[i] = a;
        }
        return key;
    }
    
    private HashSet<String> readDictionary(FileResource fr)
    {
        HashSet<String> hss = new HashSet<String>();
        for (String s: fr.lines()){
            hss.add(s.toLowerCase());
        }
        return hss;
    }
    
    private int countWords(String message, HashSet<String> dictionary)
    {
        String[] sa = message.split("\\W+");
        int i = 0;
        
        for (String s: sa)
        {
            String slower = s.toLowerCase();
            if (dictionary.contains(slower))
            {
                i++;
            }
        }
        return i;
    }
    
    private String breakForLanguage(String encrypted, HashSet<String> dictionary)
    {
        int max = 0;
        char c = mostCommonCharIn(dictionary);
        
        for (int i = 1; i <= 100; i++)
        {
            int[] key = tryKeyLength(encrypted, i, c);
            VigenereCipher vc = new VigenereCipher(key);
            String s = vc.decrypt(encrypted);
            int a = countWords(s, dictionary);
            if (a > max)
            {
                max = a;
            }
        }
        
        for (int j = 1; j <= 100; j++)
        {
            int[] key = tryKeyLength(encrypted, j, c);
            VigenereCipher vc = new VigenereCipher(key);
            String s = vc.decrypt(encrypted);
            int a = countWords(s, dictionary);
            /*if (j == 38){
                System.out.println(j + "\n" + a);
            }*/
            if (a == max)
            {
                //System.out.println(j + "\n" + a);
                return s;
            }
        }
        return null;
    }
    
    public void breakVigenere() 
    {
        System.out.println("Chose file to decrypt");
        FileResource msgfr = new FileResource();
        String s = msgfr.asString();
        
        /*System.out.println("Choose dictionary file");
        FileResource dictfr = new FileResource();
        HashSet<String> hss = readDictionary(dictfr);
        String msg = breakForLanguage(s, hss);
        System.out.println(msg);*/
        
        System.out.println("Choose dictionary files");
        DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> hm = new HashMap<String, HashSet<String>>();
        for (File f: dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            hm.put(f.getName(), readDictionary(fr));
            System.out.println(f.getName() + " read.");
        }
        System.out.println();
        breakForAllLanguages(s, hm);
    }
    
    private char mostCommonCharIn(HashSet<String> dictionary)
    {
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        for (String s: dictionary)
        {
            String slower = s.toLowerCase();
            for (char c: slower.toCharArray())
            {
                if (hm.containsKey(c))
                {
                    hm.put(c, hm.get(c) + 1);
                }
                else
                {
                    hm.put(c, 1);
                }
            }
        }
        
        int max = 0;
        for (char c: hm.keySet())
        {
            if (hm.get(c) > max)
            {
                max = hm.get(c);
            }
        }
        
        for (char c: hm.keySet())
        {
            if (hm.get(c) == max)
            {
                return c;
            }
        }
        return 'n';
    }
    
    private void breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> languages)
    {
        int max = 0;
        for (String language: languages.keySet())
        {
            String s = breakForLanguage(encrypted, languages.get(language));
            int i = countWords(s, languages.get(language));
            
            if (i > max){
                max = i;
            }
        }
        
        for (String language: languages.keySet())
        {
            String s = breakForLanguage(encrypted, languages.get(language));
            int i = countWords(s, languages.get(language));
            
            if (i == max)
            {
                System.out.println(s + "\n" + language);
                System.out.println(i + " words");
            }
        }
    }
}