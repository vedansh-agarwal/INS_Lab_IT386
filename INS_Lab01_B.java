package com.vedansh;
import java.util.Scanner;
public class INS_Lab01_B {
    private static String matrix;
    private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter plain text (only alphabets): ");
        String plainText = sc.next().toUpperCase();
        System.out.print("Enter Playfair key (only alphabets): ");
        String key = sc.next().toUpperCase();
        String playfairEncrypted = playfairCipherEncrypt(plainText,key);
        String playfairDecrypted = playfairCipherDecrypt(playfairEncrypted);
        System.out.println("\n\nPlayfair Cipher: "+playfairEncrypted);
        System.out.println("Playfair Cipher Plain Text: "+playfairDecrypted);
    }
    public static String playfairCipherEncrypt(String plainText, String key) {
        String encrypted = "";
        matrix = "";
        for(int i = 0; i < key.length(); i++)
            if(matrix.indexOf(key.charAt(i)) == -1)
                matrix += key.charAt(i);
        for(int i = 0; i < alphabet.length(); i++)
            if(matrix.indexOf(alphabet.charAt(i)) == -1)
                matrix += alphabet.charAt(i);
        plainText = plainText.replace("J", "I");
        matrix = matrix.replace("J", "I");
        matrix = matrix.substring(0, matrix.lastIndexOf("I")) + matrix.substring(matrix.lastIndexOf("I")+1);
        System.out.println("\nMatrix for Playfair Cipher: ");
        for(int i = 0; i < 25; i++) {
            if(i%5 == 0)
                System.out.println();
            System.out.print(matrix.charAt(i)+ " ");
        }
        char a,b;
        while(plainText.length() > 0) {
            if(plainText.length() == 1) {
                if(plainText.charAt(0) == 'Z') {
                    a = 'Z';
                    b = 'X';
                }
                else {
                    a = plainText.charAt(0);
                    b = 'Z';
                }
                plainText = "";
            }
            else {
                if(plainText.charAt(0) == plainText.charAt(1)) {
                    if(plainText.charAt(0) == 'X') {
                        a = 'X';
                        b = 'Z';
                    }
                    else {
                        a = plainText.charAt(0);
                        b = 'X';
                    }
                    plainText = plainText.substring(1);
                }
                else {
                    a = plainText.charAt(0);
                    b = plainText.charAt(1);
                    plainText = plainText.substring(2);
                }
            }
            int a_row = matrix.indexOf(a)/5;
            int a_col = matrix.indexOf(a)%5;
            int b_row = matrix.indexOf(b)/5;
            int b_col = matrix.indexOf(b)%5;
            if(a_row == b_row)
                encrypted += Character.toString(matrix.charAt(a_row*5+(a_col+1)%5)) + Character.toString(matrix.charAt(b_row*5+(b_col+1)%5));
            else if(a_col == b_col)
                encrypted += Character.toString(matrix.charAt((matrix.indexOf(a)+5)%25)) + Character.toString(matrix.charAt((matrix.indexOf(b)+5)%25));
            else
                encrypted += Character.toString(matrix.charAt(a_row*5+b_col)) + Character.toString(matrix.charAt(b_row*5+a_col));
        }
        return encrypted;
    }
    public static String playfairCipherDecrypt(String encrypted) {
        String decrypted = "";
        while(encrypted.length() > 0) {
            char a = encrypted.charAt(0);
            char b = encrypted.charAt(1);
            encrypted = encrypted.substring(2);
            int a_row = matrix.indexOf(a)/5;
            int a_col = matrix.indexOf(a)%5;
            int b_row = matrix.indexOf(b)/5;
            int b_col = matrix.indexOf(b)%5;
            if(a_row == b_row)
                decrypted += Character.toString(matrix.charAt(a_row*5+(4+a_col)%5)) + Character.toString(matrix.charAt(b_row*5+(4+b_col)%5));
            else if(a_col == b_col)
                decrypted += Character.toString(matrix.charAt(((4+a_row)%5)*5+a_col)) + Character.toString(matrix.charAt(((4+b_row)%5)*5+b_col));
            else
                decrypted += Character.toString(matrix.charAt(a_row*5+b_col)) + Character.toString(matrix.charAt(b_row*5+a_col));
        }
        return decrypted;
    }
}