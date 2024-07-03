import java.math.*;
import java.util.*;

class lab7 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        // Taking user input for prime numbers p and q
        System.out.print("Enter the 1st prime number p: ");
        int p = sc.nextInt();
        System.out.print("Enter the 2nd prime number q: ");
        int q = sc.nextInt();
        // Taking user input for the message to be encrypted
        System.out.print("Enter the message to be encrypted (an integer): ");
        int msg = sc.nextInt();
        int n = p * q;
        int z = (p - 1) * (q - 1);
        int e = 0, d = 0;
        System.out.println("The value of n (p * q) = " + n);
        System.out.println("The value of z ((p-1)*(q-1)) = " + z);
        sc.close();
        // Finding e
        for (e = 2; e < z; e++)
            if (gcd(e, z) == 1)
                break;
        System.out.println("The value of e (public key exponent) = " + e);
        // Finding d
        for (int i = 0; i <= 9; i++) {
            int x = 1 + (i * z);
            if (x % e == 0) {
                d = x / e;
                break;
            }
        }
        System.out.println("The value of d (private key exponent) = " + d);
        // Encryption
        double c = (Math.pow(msg, e)) % n;
        System.out.println("Encrypted message is : " + (int) c);
        // Decryption
        BigInteger N = BigInteger.valueOf(n);
        BigInteger C = BigDecimal.valueOf(c).toBigInteger();
        BigInteger msgback = (C.pow(d)).mod(N);
        System.out.println("Decrypted message is : " + msgback);
    }

    static int gcd(int e, int z) {
        if (e == 0)
            return z;
        else
            return gcd(z % e, e);
    }
}
