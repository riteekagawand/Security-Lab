import java.math.BigInteger;

public class RSA {
    public static void main(String[] args) {
        int p = 3; // 1st prime number
        int q = 11; // 2nd prime number
        int n = p * q; // Calculate n
        int z = (p - 1) * (q - 1); // Calculate z
        System.out.println("The value of z = " + z);

        int e = 0; // Public key exponent
        for (e = 2; e < z; e++) {
            // Check if e is coprime with z
            if (gcd(e, z) == 1) {
                break;
            }
        }
        System.out.println("The value of e = " + e);

        int d = 0; // Private key exponent
        for (int i = 1; i < 10; i++) {
            int x = 1 + (i * z);
            // Calculate d
            if (x % e == 0) {
                d = x / e;
                break;
            }
        }
        System.out.println("The value of d = " + d);

        int msg = 12; // Message to be encrypted
        BigInteger c = BigInteger.valueOf(msg).modPow(BigInteger.valueOf(e), BigInteger.valueOf(n)); // Encrypt the message
        System.out.println("Encrypted message is: " + c);

        // Decrypt the message
        BigInteger msgback = c.modPow(BigInteger.valueOf(d), BigInteger.valueOf(n));
        System.out.println("Decrypted message is: " + msgback);
    }

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
