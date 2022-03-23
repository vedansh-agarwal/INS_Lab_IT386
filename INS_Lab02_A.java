import java.util.Scanner;
public class INS_Lab02_A {
    private static final String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static int matrix[][];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter plain text (only alphabets): ");
        String plainText = sc.next().toUpperCase();
        System.out.print("Enter hill cipher key (only alphabets): ");
        String key = sc.next().toUpperCase();
        String hillEncrypted = hillCipherEncrypt(plainText, key);
        System.out.println("\nHill Cipher: "+hillEncrypted);
    }
    public static String hillCipherEncrypt(String plainText, String key) {
        String encrypted = "";
        int n = (int) Math.sqrt(key.length());
        int m = plainText.length();
        matrix = new int[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                matrix[i][j] = alphabets.indexOf(key.charAt(i*n+j));
        for(int i = 0; i < m; i += n)
            encrypted += matrixMultiply(plainText, i, Math.min(m,i+n));
        return encrypted;
    }

    public static String matrixMultiply(String pt, int start, int end) {
        String output = "";
        int n = end-start;
        int x;
        int arr[] = new int[n];
        for(int i = 0; i < n; i++)
            arr[i] = alphabets.indexOf(pt.charAt(start+i));
        for(int i = 0; i < n; i++) {
            x = 0;
            for(int j = 0; j < n; j++)
                 x += matrix[i][j] * arr[j];
            output += Character.toString(alphabets.charAt(x%26));
        }
        return output;
    }
}
