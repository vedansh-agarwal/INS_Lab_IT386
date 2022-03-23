import java.util.Scanner;
public class INS_Lab02_B {
    private static final String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter plain text (only alphabets): ");
        String plainText = sc.nextLine().toUpperCase();
        System.out.print("Enter vigenere cipher key (only alphabets): ");
        String key = sc.nextLine().toUpperCase();

        String vigenereEncrypted = vigenereEncrypt(plainText, key);
        String vigenereDecrypted = vigenereDecrypt(vigenereEncrypted, key);
        System.out.println("\nVigenere Cipher: "+vigenereEncrypted+"\nVigenere Cipher Plain Text: "+vigenereDecrypted);
    }

    public static String vigenereEncrypt(String plainText, String key) {
        String encrypted = "";
        int n = plainText.length();
        int m = key.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if(plainText.charAt(i) == ' ') {
                count++;
                continue;
            }
            int indexPT = alphabets.indexOf(plainText.charAt(i));
            int indexK = alphabets.indexOf(key.charAt((i-count)%m));
            encrypted += Character.toString(alphabets.charAt((indexPT+indexK)%26));
        }
        return encrypted;
    }

    public static String vigenereDecrypt(String encrypted, String key) {
        String decrypted = "";
        int n = encrypted.length();
        int m = key.length();
        for (int i = 0; i < n; i++) {
            int indexE = alphabets.indexOf(encrypted.charAt(i));
            int indexK = alphabets.indexOf(key.charAt(i%m));
            int indexDEC = indexE-indexK;
            if(indexDEC < 0)
                indexDEC += 26;
            decrypted += Character.toString(alphabets.charAt(indexDEC));
        }
        return decrypted;
    }
}
