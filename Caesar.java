import java.util.Scanner;
import java.io.*;

// #24
public class Caesar {
    public static String temp = "";
    public static int k;

    // Encrypts text using a shift cipher
    public static StringBuffer encrypt(String m, int k) {
        StringBuffer encrypto = new StringBuffer();
        for (int i = 0; i < m.length(); i++) {
            if (Character.isUpperCase(m.charAt(i))) {
                char ch = (char) (((int) m.charAt(i) + k - 65) % 26 + 65);
                encrypto.append(ch);
            } else {
                char ch = (char) (((int) m.charAt(i) + k - 97) % 26 + 97);
                encrypto.append(ch);
            }
        }

        temp = encrypto.toString();
        return encrypto;
    }

    // Decrypts the text
    public static StringBuffer decrypt(String temp, int k) {
        StringBuffer decrypto = new StringBuffer();

        for (int i = 0; i < temp.length(); i++) {
            if (Character.isUpperCase(temp.charAt(i))) {
                char ch = (char) (((int) temp.charAt(i) - k - 65) % 26 + 65);
                decrypto.append(ch);
            } else {
                char ch = (char) (((int) temp.charAt(i) - k - 97) % 26 + 97);
                decrypto.append(ch);
            }
        }
        return decrypto;
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a key:");
        int k = sc.nextInt();
        System.out.println("Enter the text:");
        String m = sc.next();

        System.out.println("Text : " + m);
        System.out.println("Key : " + k);
        System.out.println("Cipher: " + encrypt(m, k));
        System.out.println("Decrypted: " + decrypt(temp, k));
    }
}
