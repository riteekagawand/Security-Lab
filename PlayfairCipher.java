import java.awt.Point;
import java.util.Scanner;

public class PlayfairCipher {
    // Length of digraph array
    private int length = 0;
    // Matrix for Playfair cipher
    private String[][] table;

    // Main method to test Playfair cipher
    public static void main(String args[]) {
        PlayfairCipher pf = new PlayfairCipher();
    }

    // Constructor of the class
    private PlayfairCipher() {
        // Prompts user for the keyword and creates the table
        System.out.print("Enter the key for Playfair cipher: ");
        Scanner sc = new Scanner(System.in);
        String key = parseString(sc);
        while (key.isEmpty()) {
            key = parseString(sc);
        }
        table = this.cipherTable(key);

        // Prompts user for the message to be encoded
        System.out.print("Enter the plaintext to be enciphered: ");
        String input = parseString(sc);
        while (input.isEmpty()) {
            input = parseString(sc);
        }

        // Encodes and then decodes the message
        String output = cipher(input);
        String decodedOutput = decode(output);

        // Outputs the results
        this.keyTable(table);
        this.printResults(output, decodedOutput);
        sc.close(); // Close the scanner
    }

    // Parses an input string, removes numbers, punctuation, replaces 'J' with 'I', and makes the string all uppercase
    private String parseString(Scanner sc) {
        String parse = sc.nextLine();
        parse = parse.toUpperCase();
        parse = parse.replaceAll("[^A-Z]", "");  // Keep only A-Z letters
        parse = parse.replace("J", "I");  // Replace J with I
        return parse;
    }

    // Creates the cipher table based on the input key
    private String[][] cipherTable(String key) {
        String[][] playfairTable = new String[5][5];
        String keyString = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();

        // Remove duplicates from keyString
        for (int i = 0; i < keyString.length(); i++) {
            char ch = keyString.charAt(i);
            if (sb.indexOf(String.valueOf(ch)) == -1) {
                sb.append(ch);
            }
        }

        // Fill the table with the key
        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                playfairTable[i][j] = String.valueOf(sb.charAt(index++));
            }
        }

        return playfairTable;
    }

    // Ciphers the input string, encodes it, and returns the output
    private String cipher(String in) {
        length = (in.length() / 2) + (in.length() % 2);  // Ensures even length
        in = fixDigraph(in);  // Inserts 'X' between duplicate letters and adjusts length

        String[] digraphs = new String[length];
        for (int i = 0; i < length; i++) {
            digraphs[i] = in.charAt(2 * i) + "" + in.charAt(2 * i + 1);
        }

        String[] encodedDigraphs = encodeDigraph(digraphs);
        StringBuilder out = new StringBuilder();
        for (String encodedDigraph : encodedDigraphs) {
            out.append(encodedDigraph);
        }

        return out.toString();
    }

    // Fixes digraph issues, adds 'X' where necessary
    private String fixDigraph(String in) {
        StringBuilder sb = new StringBuilder(in);
        for (int i = 0; i < sb.length(); i += 2) {
            if (i + 1 == sb.length() || sb.charAt(i) == sb.charAt(i + 1)) {
                sb.insert(i + 1, 'X');
            }
        }
        if (sb.length() % 2 != 0) {
            sb.append('X');
        }
        return sb.toString();
    }

    // Encodes digraph input using the cipher specifications
    private String[] encodeDigraph(String[] digraphs) {
        String[] encipher = new String[length];

        for (int i = 0; i < length; i++) {
            char a = digraphs[i].charAt(0);
            char b = digraphs[i].charAt(1);
            Point p1 = getPoint(a);
            Point p2 = getPoint(b);

            if (p1.x == p2.x) {  // Same row
                encipher[i] = table[p1.x][(p1.y + 1) % 5] + table[p2.x][(p2.y + 1) % 5];
            } else if (p1.y == p2.y) {  // Same column
                encipher[i] = table[(p1.x + 1) % 5][p1.y] + table[(p2.x + 1) % 5][p2.y];
            } else {  // Rectangle swap
                encipher[i] = table[p1.x][p2.y] + table[p2.x][p1.y];
            }
        }

        return encipher;
    }

    // Decodes the encoded message
    private String decode(String out) {
        StringBuilder decoded = new StringBuilder();

        for (int i = 0; i < out.length() / 2; i++) {
            char a = out.charAt(2 * i);
            char b = out.charAt(2 * i + 1);
            Point p1 = getPoint(a);
            Point p2 = getPoint(b);

            if (p1.x == p2.x) {  // Same row
                decoded.append(table[p1.x][(p1.y + 4) % 5]).append(table[p2.x][(p2.y + 4) % 5]);
            } else if (p1.y == p2.y) {  // Same column
                decoded.append(table[(p1.x + 4) % 5][p1.y]).append(table[(p2.x + 4) % 5][p2.y]);
            } else {  // Rectangle swap
                decoded.append(table[p1.x][p2.y]).append(table[p2.x][p1.y]);
            }
        }

        return decoded.toString();
    }

    // Returns a point containing the row and column of the letter
    private Point getPoint(char c) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (table[i][j].equals(String.valueOf(c))) {
                    return new Point(i, j);
                }
            }
        }
        return null;  // This should never happen if input is valid
    }

    // Prints the key table in matrix form for Playfair cipher
    private void keyTable(String[][] printTable) {
        System.out.println("Playfair Cipher Key Matrix:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(printTable[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Method that prints the encrypted and decrypted results
    private void printResults(String encipher, String dec) {
        System.out.println("Encrypted Message: " + encipher);
        System.out.println("Decrypted Message: " + dec);
    }
}