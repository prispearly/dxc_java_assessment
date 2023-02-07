import java.util.Scanner;
import static java.lang.Math.abs;

public class Encoder
{
    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";

    public static String encode(String inputStr, char offsetChar)
    {
        String encodedText = "";
        int encryptPos;

        offsetChar = Character.toUpperCase(offsetChar);
        inputStr = inputStr.toUpperCase();

        int shiftKey = ALPHABET.indexOf(offsetChar);

        for (int i = 0; i < inputStr.length(); i++)
        {
            // skip spaces
            if (inputStr.charAt(i) == ' '){
                encodedText += inputStr.charAt(i);
                continue;
            }

            // encryption
            int pos = ALPHABET.indexOf(inputStr.charAt(i));
            int shiftDirection = (pos - shiftKey);
            if( shiftDirection <0) {
                encryptPos = ALPHABET.length() - abs(shiftDirection);
            } else {
                encryptPos = shiftDirection;
            }
            char encryptChar = ALPHABET.charAt(encryptPos);
            encodedText += encryptChar;
        }
        return offsetChar+encodedText;
    }

    public static String decode (String encodedText){
        String decryptStr = "";
        int decryptPos;

        int shiftKey = ALPHABET.indexOf(encodedText.charAt(0)); // remove first letter convert into shift key
        encodedText = encodedText.toUpperCase();

        for (int i = 0; i < encodedText.length(); i++)
        {
            // skip spaces
            if (encodedText.charAt(i) == ' '){
                decryptStr += encodedText.charAt(i);
                continue;
            }

            // decryption
            int pos = ALPHABET.indexOf(encodedText.charAt(i));
            int shiftDirection = (pos + shiftKey);
            if (shiftDirection > (ALPHABET.length()-1)){
                decryptPos = (shiftDirection % (ALPHABET.length()-1)) - 1;
            } else{decryptPos = shiftDirection;}
            char decryptChar = ALPHABET.charAt(decryptPos);
            decryptStr += decryptChar;
        }
        return decryptStr.substring(1);
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a string for encryption using Caesar Cipher: ");
        String inputStr = sc.nextLine();

        System.out.println("Enter the char offset by which each character in the plaintext message gets shifted: ");
        String shiftChar = sc.nextLine();
        char shiftKey = shiftChar.charAt(0);

        System.out.println("Encrypted Data ===> "+ encode(inputStr, shiftKey));
        System.out.println("Decrypted Data ===> "+decode(encode(inputStr, shiftKey)));

        sc.close();
    }
}