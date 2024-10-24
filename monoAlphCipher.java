import java.util.Scanner;

public class monoAlphCipher {
    public static String temp = ""; 
    public static int k;

    // Encrypts text using a shift cipher
    public static StringBuffer encrypt(String m, int k) {
        StringBuffer encrypto = new StringBuffer();
        k = k % 26;  // Ensure key is within range

        for (int i = 0; i < m.length(); i++) {
            char ch = m.charAt(i);
            if (Character.isUpperCase(ch)) {
                char encryptedChar = (char) (((int) ch + k - 65) % 26 + 65);
                encrypto.append(encryptedChar);
            } else if (Character.isLowerCase(ch)) {
                char encryptedChar = (char) (((int) ch + k - 97) % 26 + 97);
                encrypto.append(encryptedChar);
            } else {
                encrypto.append(ch);  // Preserve spaces and punctuation
            }
        }

        temp = encrypto.toString(); 
        return encrypto;
    }

    // Decrypts text using a shift cipher
    public static StringBuffer decrypt(String temp, int k) {
        StringBuffer decrypto = new StringBuffer();
        k = k % 26;  // Ensure key is within range

        for (int i = 0; i < temp.length(); i++) {
            char ch = temp.charAt(i);
            if (Character.isUpperCase(ch)) {
                char decryptedChar = (char) (((int) ch - k - 65 + 26) % 26 + 65);
                decrypto.append(decryptedChar);
            } else if (Character.isLowerCase(ch)) {
                char decryptedChar = (char) (((int) ch - k - 97 + 26) % 26 + 97);
                decrypto.append(decryptedChar);
            } else {
                decrypto.append(ch);  // Preserve spaces and punctuation
            }
        }

        return decrypto;
    }

    // main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a key: ");
        int k = sc.nextInt();
        sc.nextLine();  // Consume the newline character

        System.out.println("Enter the text: ");
        String m = sc.nextLine();

        System.out.println("Text: " + m);
        System.out.println("Key: " + k);
        System.out.println("Cipher: " + encrypt(m, k));
        System.out.println("Decrypted: " + decrypt(temp, k));

        sc.close();
    }
}
