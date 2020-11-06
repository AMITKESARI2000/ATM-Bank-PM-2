package com.AmitKesari;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import static com.AmitKesari.Main.bankNameIITT;
import static com.AmitKesari.Main.secretKeyAdmin;

public class PasswordSystem {
    private static SecretKeySpec secretKey;
    private static byte[] key;
    private static final String ALGORITHM = "AES";

    //Verifies the inputted password by checking three times
    boolean passwordVerifier(String encryptedCorrectPassword) {
        String correctPassword = decrypt(encryptedCorrectPassword, secretKeyAdmin);
        Scanner scanner = new Scanner(System.in);
        String inputPassword = new String();
        int attempts = 0;
        final int allowedAttempts = 3;
        while (attempts <= allowedAttempts) {
            if (attempts == 0) {
                System.out.println("Enter the password");
                inputPassword = scanner.next();
                if (inputPassword.equals(correctPassword)) {
                    System.out.println("✔ Verified ");
                    return true;
                }
                attempts++;
            } else if (attempts == 1 || attempts == 2) {
                System.out.println("❌ Wrong password ! Enter the password again ( " + (allowedAttempts - attempts) + " remaining )");
                inputPassword = scanner.next();
                if (inputPassword.equals(correctPassword)) {
                    System.out.println("✔ Verified ");
                    return true;
                }
                attempts++;
            } else {
                System.out.println("You have been blocked by system. Please visit your nearest " + bankNameIITT + " to reset the password.");
                return false;
            }
        }
        return false;
    }

    public void prepareSecreteKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String strToEncrypt, String secret) {
        try {
            prepareSecreteKey(secret);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public String decrypt(String strToDecrypt, String secret) {
        try {
            prepareSecreteKey(secret);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

}