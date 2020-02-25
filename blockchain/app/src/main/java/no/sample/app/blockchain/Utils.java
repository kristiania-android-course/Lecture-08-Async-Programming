package no.sample.app.blockchain;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {



    public static String generateHash(String content)
    {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(
                    content.getBytes("UTF-8"));

            return bytesToHex(encodedhash);

        }
        catch (NoSuchAlgorithmException e) {
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "";

    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String getDifficultyPrefix(int difficulty) {

        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < difficulty; i++) {
            sb.append('0');
        }
        return sb.toString();
    }
}