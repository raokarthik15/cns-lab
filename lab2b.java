
import java.util.Scanner;

public class lab2b {
   
    public static final String ALPHABET = "abcdefghiklmnopqrstuvwxyz";
   
    // Generate the key matrix from the provided key
    public static char[][] generateKeyMatrix(String key) {
        key = key.toLowerCase().replaceAll("[^a-z]", "");
        key += ALPHABET; // Append remaining alphabet characters
        key = key.replaceAll("j", "i"); // Replace 'j' with 'i'
        boolean[] visited = new boolean[26];
        char[][] keyMatrix = new char[5][5];
        int index = 0;
        for (char c : key.toCharArray()) {
            if (!visited[c - 'a']) {
                keyMatrix[index / 5][index % 5] = c;
                visited[c - 'a'] = true;
                index++;
            }
        }
        return keyMatrix;
    }
   
    // Encrypt plaintext using Playfair cipher
    public static String encryptData(String inputStr, String key) {
        char[][] keyMatrix = generateKeyMatrix(key);
        StringBuilder result = new StringBuilder();
        inputStr = inputStr.toLowerCase().replaceAll("[^a-z]", ""); // Convert input to lowercase and remove non-alphabetic characters
        inputStr = inputStr.replaceAll("j", "i"); // Replace 'j' with 'i'
        for (int i = 0; i < inputStr.length(); i += 2) {
            char a = inputStr.charAt(i);
            char b = (i + 1 < inputStr.length()) ? inputStr.charAt(i + 1) : 'x'; // Use 'x' for odd-length strings
            if (a == b) { // Handle double letters by inserting a filler character ('x')
                b = 'x';
                i--; // Repeat the same pair again
            }
            int rowA = 0, colA = 0, rowB = 0, colB = 0;
            // Find positions of letters in the key matrix
            outer: for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    if (keyMatrix[row][col] == a) {
                        rowA = row;
                        colA = col;
                    } else if (keyMatrix[row][col] == b) {
                        rowB = row;
                        colB = col;
                    }
                    if (rowA != 0 && rowB != 0) // Break when both letters are found
                        break outer;
                }
            }
            char encryptedA, encryptedB;
            // Same row
            if (rowA == rowB) {
                encryptedA = keyMatrix[rowA][(colA + 1) % 5];
                encryptedB = keyMatrix[rowB][(colB + 1) % 5];
            }
            // Same column
            else if (colA == colB) {
                encryptedA = keyMatrix[(rowA + 1) % 5][colA];
                encryptedB = keyMatrix[(rowB + 1) % 5][colB];
            }
            // Rectangle
            else {
                encryptedA = keyMatrix[rowA][colB];
                encryptedB = keyMatrix[rowB][colA];
            }
            result.append(encryptedA).append(encryptedB);
        }
        return result.toString();
    }
   
    // Decrypt ciphertext using Playfair cipher
    public static String decryptData(String inputStr, String key) {
        char[][] keyMatrix = generateKeyMatrix(key);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < inputStr.length(); i += 2) {
            char a = inputStr.charAt(i);
            char b = inputStr.charAt(i + 1);
            int rowA = 0, colA = 0, rowB = 0, colB = 0;
            // Find positions of letters in the key matrix
            outer: for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    if (keyMatrix[row][col] == a) {
                        rowA = row;
                        colA = col;
                    } else if (keyMatrix[row][col] == b) {
                        rowB = row;
                        colB = col;
                    }
                    if (rowA != 0 && rowB != 0) // Break when both letters are found
                        break outer;
                }
            }
            char decryptedA, decryptedB;
            // Same row
            if (rowA == rowB) {
                decryptedA = keyMatrix[rowA][(colA + 4) % 5]; // (colA - 1 + 5) % 5
                decryptedB = keyMatrix[rowB][(colB + 4) % 5]; // (colB - 1 + 5) % 5
            }
            // Same column
            else if (colA == colB) {
                decryptedA = keyMatrix[(rowA + 4) % 5][colA]; // (rowA - 1 + 5) % 5
                decryptedB = keyMatrix[(rowB + 4) % 5][colB]; // (rowB - 1 + 5) % 5
            }
            // Rectangle
            else {
                decryptedA = keyMatrix[rowA][colB];
                decryptedB = keyMatrix[rowB][colA];
            }
            result.append(decryptedA).append(decryptedB);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string for encryption using Playfair Cipher:");
        String inputStr = sc.nextLine();
        System.out.println("Enter the key for Playfair Cipher:");
        String key = sc.nextLine();
       
        String encryptedData = encryptData(inputStr, key);
        System.out.println("Encrypted Data: " + encryptedData);
        System.out.println("Decrypted Data: " + decryptData(encryptedData, key));
        sc.close();
    }
}