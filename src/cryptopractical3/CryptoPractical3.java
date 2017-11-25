
package cryptopractical3;

import java.security.*;

public class CryptoPractical3 {
    //the list of hashes to be cracked
    static String[] hashes = {
        "c2543fff3bfa6f144c2f06a7de6cd10c0b650cae",
        "b47f363e2b430c0647f14deea3eced9b0ef300ce",
        "e74295bfc2ed0b52d40073e8ebad555100df1380",
        "0f7d0d088b6ea936fb25b477722d734706fe8b40",
        "77cfc481d3e76b543daf39e7f9bf86be2e664959",
        "5cc48a1da13ad8cef1f5fad70ead8362aabc68a1",
        "4bcc3a95bdd9a11b28883290b03086e82af90212",
        "7302ba343c5ef19004df7489794a0adaee68d285",
        "21e7133508c40bbdf2be8a7bdc35b7de0b618ae4",
        "6ef80072f39071d4118a6e7890e209d4dd07e504",
        "02285af8f969dc5c7b12be72fbce858997afe80a",
        "57864da96344366865dd7cade69467d811a7961b"
    };
    //store plaintexts of passwords that have been cracked
    static String[] passwords = new String[hashes.length];
    static int count = 0;
    
    static char[] charset = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
        'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static int charmax = 6;
    //store the amount of time taken for each password to be cracked
    static long[] times = new long[hashes.length];
    static long starttime = System.currentTimeMillis();
    
    public static void main(String[] args) throws NoSuchAlgorithmException 
    {
        //loop through every character combination using charset 
        for(int i = 1; i < charmax + 1; i++)
        {
            nextString(charset, charset.length, i, "");
        }
    }
    static String sha1(String password) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        byte[] bytes = md.digest(password.getBytes());
        String toReturn = "";
        for(int i = 0; i < bytes.length; i++)
            toReturn += Integer.toString((bytes[i]&0xff) + 0x100, 16).substring(1); //output to string as hex instead of decimal 
        return toReturn;
    }

    static void nextString(char[] set, int n, int length, String variant) throws NoSuchAlgorithmException
    {
        if(length == 0) //once length is 0, a final string of a set length has been generated
        {
            checkHash(variant); //call the check hash function on the passed string
            return; //whether the hash was found or not, this function is no longer needed so return 
        }
        //recursively call this function to find every combination of characters from a set
        for(int i = 0; i < n; i++)
        {
            String newvariant = variant + set[i]; //append next character in charset to passed string parameter
            nextString(set, n, length - 1, newvariant); //call this function using a length of - 1 and using the new string
        }
    }
    static void checkHash(String p) throws NoSuchAlgorithmException
    {
        String pw = p;
        p = sha1(p); //hash the passed plaintext string
        for(int i = 0; i < hashes.length; i++) //check each hash in the hashes array
            if(p.equals(hashes[i])) //if a hash matches record the plaintext and timestamp then output it to the user
            {
                times[i] = System.currentTimeMillis() - starttime;
                passwords[i] = pw;
                System.out.println(String.format("SHA1: %s Password: '%s' Cracked in: %dms", hashes[i], passwords[i], times[i]));
                count++;
            }
    }
}   