
/**
 * Write a description of CaesarCipher here.
 * 
 * @author Arav Tewari
 * @version June 8, 2020
 */
import edu.duke.*;

public class CaesarCipher
{
    public String encrypt(String input, int key) 
    {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
                                 
        for(int i = 0; i < encrypted.length(); i++) 
        {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            boolean isLowerCase = Character.isLowerCase(currChar);
            
            if(isLowerCase)
            
                idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if(idx != -1)
            {
                char newChar = shiftedAlphabet.charAt(idx);
                if (isLowerCase)
                
                    newChar = Character.toLowerCase(shiftedAlphabet.charAt(idx));
                
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }

    private void testCaesar() 
    {
        int key = 15;
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2)
    {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < input.length(); i++)
        {
            String currChar = Character.toString(input.charAt(i));
            
            if (i % 2 == 0)
            {
                sb.append(encrypt(currChar, key1));
            }
            else
            {
                sb.append(encrypt(currChar, key2));
            }
        }
        return sb.toString();
    }
    
    public void testEncryptTwoKeys()
    {
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        int key1 = 21;
        int key2 = 8;
        
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println(encrypted);  
        
        String decrypted = encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
        System.out.println(decrypted);
    }
}
