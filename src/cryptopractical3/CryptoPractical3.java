
package cryptopractical3;

import java.security.*;

public class CryptoPractical3 {

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
    static String[] passwords = new String[hashes.length];
    static int count = 0;
    
    static char[] charset = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
        'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static int charmax = 6;
    
    static long[] times = new long[hashes.length];
    static long starttime = System.currentTimeMillis();
    
    public static void main(String[] args) throws NoSuchAlgorithmException 
    {
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
            toReturn += Integer.toString((bytes[i]&0xff) + 0x100, 16).substring(1);
        return toReturn;
    }

    static void nextString(char[] set, int n, int length, String p) throws NoSuchAlgorithmException
    {
        if(length == 0)
        {
            checkHash(p);
            return;
        }
        for(int i = 0; i < n; i++)
        {
            String np = p + set[i];
            nextString(set, n, length - 1, np);
        }
    }
    static void checkHash(String p) throws NoSuchAlgorithmException
    {
        String pw = p;
        p = sha1(p);
        for(int i = 0; i < hashes.length; i++)
            if(p.equals(hashes[i]))
            {
                times[i] = System.currentTimeMillis() - starttime;
                passwords[i] = pw;
                System.out.println(String.format("SHA1: %s Password: '%s' Cracked in: %dms", hashes[i], passwords[i], times[i]));
                count++;
            }
    }
}   