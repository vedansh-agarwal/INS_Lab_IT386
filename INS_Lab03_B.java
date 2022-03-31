import java.util.*;
public class INS_Lab03_B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter plain text: ");
        String plainText = sc.next();
        System.out.print("Enter value of n: ");
        int n = sc.nextInt();
        System.out.print("Enter value of m: ");
        int m = sc.nextInt();
        System.out.print("Enter row-col transposition cipher key (m unique alphabets): ");
        String key = sc.next().toLowerCase();
        String row_colEncrypted = row_colEncrypt(plainText, n, m, key);
        String row_colDecrypted = row_colDecrypt(row_colEncrypted, n, m, key);
        System.out.println(row_colEncrypted+"\n"+row_colDecrypted);
    }

    public static String row_colEncrypt(String plainText, int n, int m, String k) {
        char PTmatrix[][] = new char[n][m];
        int ptLen = plainText.length();
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                if(i*m+j < ptLen)
                    PTmatrix[i][j] = plainText.charAt(i*m+j);
                else
                    PTmatrix[i][j] = 0;

        List<Integer> key = convertKey(k, m);
        String encrypted = "";
        for(int i = 0; i < key.size(); i++) {
            int col = key.get(i);
            for(int row = 0; row < n; row++)
                if(PTmatrix[row][col] != 0)
                    encrypted += Character.toString(PTmatrix[row][col]);
        }
        return encrypted;
    }

    public static String row_colDecrypt(String cipherText, int n, int m, String k) {
        List<Integer> key = convertKey(k, m);
        char ctMatrix[][] = new char[n][m];
        int ctLen = cipherText.length();
        int count = 0;
        for(int i = 0; i < m; i++) {
            int col = key.get(i);
            for(int j = 0; j < n; j++)
                if(count < ctLen)
                    ctMatrix[j][col] = cipherText.charAt(count++);
                else
                    ctMatrix[j][col] = 0;
        }
        String decrypted = "";
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                if(ctMatrix[i][j] != 0)
                    decrypted += Character.toString(ctMatrix[i][j]);
        return decrypted;
    }

    public static List<Integer> convertKey(String k, int m) {
        List<Integer> key = new ArrayList<Integer>();
        String str = "abcdefghijklmnopqrstuvwxyz";
        for(int i = 0; i < 26 && key.size() < m; i++) {
            int x = k.indexOf(str.charAt(i))%m;
            if(x != -1 && !key.contains(x))
                key.add(x);
        }
        return key;
    }
}
