
package cryptopractical3;

import java.security.*;

public class CryptoPractical3 {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String password = ""; 
        System.out.println("Password: " + password + "\nSHA1: " + sha1(password)); 
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
}
