import java.util.Scanner;
public class INS_Lab03_A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter plain text: ");
        String plainText = sc.nextLine();
        System.out.print("Enter rail fence cipher key: ");
        int key = sc.nextInt();
        String railEncrypted = railFenceEncrypt(plainText, key);
        System.out.println("\nRail-Fence Cipher: "+railEncrypted);
        String railDecrypted = railFenceDecrypt(railEncrypted, key);
        System.out.println("\nRail-Fence Plain Text: "+railDecrypted);
    }

    public static String railFenceEncrypt(String plainText, int key) {
        int n = plainText.length();
        if(key >= n)
            return plainText;

        String encrypted = "";
        for(int i = 0; i < key; i++)
            for(int j = i; j < n; j += key)
                encrypted += plainText.charAt(j);
        return encrypted;
    }

    public static String railFenceDecrypt(String encrypted, int key) {
        int n = encrypted.length();
        int step = (int)Math.ceil((double)n/key);
        if(key >= n)
            return encrypted;

        String decrypted = "";
        for(int i = 0; i < step; i++)
            for(int j = i; j < n; j += step)
                decrypted += encrypted.charAt(j);
        return decrypted;
    }
}
