import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

public class lab6 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the text to be encrypted using AES:");
        String originalText = scanner.nextLine();
        // Generate or input AES key
        SecretKey secretKey;
        System.out.println("Do you want to generate a new AES key? (yes/no)");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            secretKey = generateAESKey();
            System.out.println("Generated AES Key (Base64): " +
                    Base64.getEncoder().encodeToString(secretKey.getEncoded()));
        } else {
            System.out.println("Enter the existing AES key (Base64):");
            String keyBase64 = scanner.nextLine();
            byte[] decodedKey = Base64.getDecoder().decode(keyBase64);
            secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        }
        // Encrypt the text
        byte[] encryptedText = encrypt(originalText, secretKey);
        // Decrypt the text
        String decryptedText = decrypt(encryptedText, secretKey);
        System.out.println("Original Text: " + originalText);
        System.out.println("Encrypted Text: " +
                Base64.getEncoder().encodeToString(encryptedText));
        System.out.println("Decrypted Text: " + decryptedText);
        scanner.close();
    }

    private static SecretKey generateAESKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256, new SecureRandom());
        return keyGen.generateKey();
    }

    private static byte[] encrypt(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(plainText.getBytes());
    }

    private static String decrypt(byte[] cipherText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(cipherText);
        return new String(decryptedBytes);
    }
}
