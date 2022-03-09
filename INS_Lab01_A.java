package com.vedansh;
import java.util.Scanner;
public class INS_Lab01_A {
    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter plain text (only alphabets): ");
        String plainText = sc.next().toLowerCase();
        System.out.print("Enter caeser key: ");
        int key = sc.nextInt();
        String caeserEncrypted = caeserCipherEncrypt(plainText,key);
        String caeserDecrypted = caeserCipherDecrypt(caeserEncrypted, key);
        System.out.println("\nCaeser Cipher: "+caeserEncrypted);
        System.out.println("Caeser Cipher Plain Text: "+caeserDecrypted);
    }
    public static String caeserCipherEncrypt(String plainText, int key) {
        String encrypted = "";
        for(char ch: plainText.toCharArray())
            encrypted += alphabet.charAt((alphabet.indexOf(ch) + key)%26);
        return encrypted;
    }
    public static String caeserCipherDecrypt(String encrypted, int key) {
        String decrypted = "";
        for(char ch: encrypted.toCharArray())
            decrypted += alphabet.charAt((alphabet.indexOf(ch) - key + 26)%26);
        return decrypted;
    }
}