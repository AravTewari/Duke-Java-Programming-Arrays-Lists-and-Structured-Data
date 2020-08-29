
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author Arav Tewari
 * @version June 8, 2020
 */
import edu.duke.*;

public class CaesarBreaker 
{
    private int[] countLetters(String message)
    {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        
        for (int i = 0; i < message.length(); i++)
        {
            char ch = Character.toLowerCase(message.charAt(i));
            int dex = alph.indexOf(ch);
            
            if (dex != -1)
            {
                counts[dex]++;
            }
        }
        return counts;
    }
    
    private int maxIndex(int[] counts)
    {
        int maxDex = 0;
        for (int i = 0; i < counts.length; i++)
        {
            if (counts[i] > counts[maxDex])
            {
                maxDex = i;
            }
        }
        return maxDex;
    }
    
    private String decrypt(String encrypted)
    {
        CaesarCipher cc = new CaesarCipher();
        int[] counts = countLetters(encrypted);
        int maxDex = maxIndex(counts);
        int dKey = maxDex - 4;
        
        if (maxDex < 4)
        {
            dKey = 26 - (4 - maxDex);
        }
        
        return cc.encrypt(encrypted, 26 - dKey);
    }
    
    public void testDecrypt()
    {
        String encrypted = "Yjhi p ithi higxcv lxiw adih du ttttttttttttttttth";
        String decrypted = decrypt(encrypted);
        System.out.println(decrypted);
    }
    
    private String halfOfString(String message, int start)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < message.length(); i += 2)
        {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }
    
    public void testHalfOfString()
    {
        String message = "Qbkm Zgis";
        
        String half1 = halfOfString(message, 0);
        String half2 = halfOfString(message, 1);
        
        System.out.println(half1);
        System.out.println(half2);
    }
    
    private int getKey(String s)
    {
        int[] counts = countLetters(s);
        int maxDex = maxIndex(counts);
        int dKey = maxDex - 4;
        
        if (maxDex < 4)
        {
            dKey = 26 - (4 - maxDex);
        }
        
        return dKey;
    }
    
    public void testGetKey()
    {
        String s = "Hsqr y rcqr qrpgle ugrf jmrq md cccccccccccccccccq";
        int key = getKey(s);
        
        System.out.println(key);
    }
    
    private String decryptTwoKeys(String encrypted)
    {
        String s1 = halfOfString(encrypted, 0);
        String s2 = halfOfString(encrypted, 1);
        
        int key1 = getKey(s1);
        int key2 = getKey(s2);
        System.out.println("Keys are " + key1 + ", " + key2);
        
        CaesarCipher cc = new CaesarCipher();
        String decrypted = cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
        
        return decrypted;
    }
    
    private String decryptTwoKeys(String encrypted, int key1, int key2)
    {
        System.out.println("Keys are " + key1 + ", " + key2);
        
        CaesarCipher cc = new CaesarCipher();
        String decrypted = cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
        
        return decrypted;
    }
    
    public void testDecryptTwoKeys()
    {
        //FileResource fr = new FileResource();
        //String encrypted = fr.asString(); 
        String encrypted = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        String decrypted = decryptTwoKeys(encrypted, 14, 24);
        
        System.out.println(decrypted);
    }
}
