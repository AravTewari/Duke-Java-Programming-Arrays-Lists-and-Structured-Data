
/**
 * Character in Strings manipulation stuff
 * 
 * @author Arav Tewari 
 * @version June 8, 2020
 */
public class WordPlay 
{
    private boolean isVowel(char letter)
    {
        char ch = Character.toLowerCase(letter);
        
        return ch == 'a' || ch == 'e' || ch  == 'i' || ch == 'o' || ch == 'u';
        
    }
    
    public void testIsVowel()
    {
        boolean isVowel = isVowel('F');
        System.out.println(isVowel);
        
        isVowel = isVowel('a');
        System.out.println(isVowel);
        
        isVowel = isVowel('A');
        System.out.println(isVowel);
    }
    
    private String replaceVowels(String phrase, char ch)
    {
        StringBuilder sb = new StringBuilder(phrase);
        
        for (int i = 0; i < phrase.length(); i++)
        {
            char currChar = phrase.charAt(i);
            
            if (isVowel(currChar))
            {
                sb.setCharAt(i, ch);
            }
        }
        return sb.toString();
    }
    
    public void testReplaceVowels()
    {
        String s = replaceVowels("Hello World", '*');
        System.out.println(s);
    }
    
    private String emphasize(String phrase, char ch)
    {
        StringBuilder sb = new StringBuilder(phrase);
        
        for (int i = 0; i < phrase.length(); i++)
        {
            char currChar = Character.toLowerCase(phrase.charAt(i));
            
            if (currChar == ch)
            {
                if (i % 2 == 0)
                {
                    sb.setCharAt(i, '*');
                }
                else
                {
                    sb.setCharAt(i, '+');
                }
            }
        }
        return sb.toString();
    }
    
    public void testEmphasize()
    {
        String s = emphasize("dna ctgaaactga", 'a');
        System.out.println(s);
        
        s = emphasize("Mary Bella Abracadabra", 'a');
        System.out.println(s);
    }
    
}
