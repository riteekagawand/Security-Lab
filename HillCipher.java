public class HillCipher {
    // 3x3 key matrix for 3 characters at once
    public static int[][] keymat = {
        {1, 2, 1},
        {2, 3, 2},
        {2, 2, 1}
    };

    // Inverse matrix for decryption (pre-computed)
    public static int[][] invkeymat = {
        {-1, 0, 1},
        {2, -1, 0},
        {1, -2, 2}
    };

    // Alphabet string
    public static String key = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Encode method
    private static String encode(char a, char b, char c) {
        int posa = (int) a - 65; // Get position of char a
        int posb = (int) b - 65; // Get position of char b
        int posc = (int) c - 65; // Get position of char c

        // Calculate new positions using the key matrix
        int x = (posa * keymat[0][0] + posb * keymat[0][1] + posc * keymat[0][2]) % 26;
        int y = (posa * keymat[1][0] + posb * keymat[1][1] + posc * keymat[1][2]) % 26;
        int z = (posa * keymat[2][0] + posb * keymat[2][1] + posc * keymat[2][2]) % 26;

        // Construct the encoded string
        return "" + key.charAt(x) + key.charAt(y) + key.charAt(z);
    }

    // Decode method
    private static String decode(char a, char b, char c) {
        int posa = (int) a - 65; // Get position of char a
        int posb = (int) b - 65; // Get position of char b
        int posc = (int) c - 65; // Get position of char c

        // Calculate new positions using the inverse key matrix
        int x = (posa * invkeymat[0][0] + posb * invkeymat[0][1] + posc * invkeymat[0][2]) % 26;
        int y = (posa * invkeymat[1][0] + posb * invkeymat[1][1] + posc * invkeymat[1][2]) % 26;
        int z = (posa * invkeymat[2][0] + posb * invkeymat[2][1] + posc * invkeymat[2][2]) % 26;

        // Ensure non-negative results for modulo
        x = (x + 26) % 26;
        y = (y + 26) % 26;
        z = (z + 26) % 26;

        // Construct the decoded string
        return "" + key.charAt(x) + key.charAt(y) + key.charAt(z);
    }

    // Main method to test encoding and decoding
    public static void main(String[] args) {
        String msg = "Vidyavardhini Vasai Road";
        System.out.println("Simulation of Hill Cipher");

        // Prepare message: remove spaces and convert to uppercase
        msg = msg.toUpperCase().replaceAll("\\s", "");

        // Pad the message if it's not a multiple of 3
        int n = msg.length() % 3;
        if (n != 0) {
            for (int i = 0; i < 3 - n; i++) {
                msg += 'X'; // Add padding character 'X'
            }
            System.out.println("Padded message: " + msg);
        }

        // Encode the message
        String enc = "";
        char[] pdchars = msg.toCharArray();
        for (int i = 0; i < pdchars.length; i += 3) {
            enc += encode(pdchars[i], pdchars[i + 1], pdchars[i + 2]);
        }
        System.out.println("Encoded message: " + enc);

        // Decode the message
        String dec = "";
        char[] dechars = enc.toCharArray();
        for (int i = 0; i < dechars.length; i += 3) {
            dec += decode(dechars[i], dechars[i + 1], dechars[i + 2]);
        }
        System.out.println("Decoded message: " + dec);
    }
}
