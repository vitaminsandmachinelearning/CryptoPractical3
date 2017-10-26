
package cryptopractical3;

import java.security.*;
import java.util.ArrayList;

public class CryptoPractical3 {

    static char[] charset = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
        'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    //static char[] charset = {'a', 'b', 'c'};
    static int charmax = 6;

    
    public static void main(String[] args) throws NoSuchAlgorithmException 
    {
        String password = ""; 
        System.out.println("Password: " + password + "\nSHA1: " + sha1(password)); 
        bruteforce("");
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
    static String bruteforce(String hash) throws NoSuchAlgorithmException
    {
        ArrayList<String> passwords = new ArrayList<String>();
        passwords.add("");
        getallstrings(charset, charset.length, 6, "", passwords);
        for(String s : passwords)
            System.out.println("0:" + s);
        return "";
    }
    static void getallstrings(char[] set, int n, int length, String p, ArrayList<String> passwords) throws NoSuchAlgorithmException
    {
        if(length == 0)
        {
            passwords.add(sha1(p));
            return;
        }
        for(int i = 0; i < n; i++)
        {
            String np = p + set[i];
            getallstrings(set, n, length - 1, np, passwords);
        }
        
    }
}
