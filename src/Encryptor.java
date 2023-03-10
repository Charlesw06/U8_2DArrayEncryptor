import java.util.Arrays;

public class Encryptor
{
    /** A two-dimensional array of single-character strings, instantiated in the constructor */
    private String[][] letterBlock;

    /** The number of rows of letterBlock, set by the constructor */
    private int numRows;

    /** The number of columns of letterBlock, set by the constructor */
    private int numCols;

    /** Constructor*/
    public Encryptor(int r, int c)
    {
        letterBlock = new String[r][c];
        numRows = r;
        numCols = c;
    }

    public String[][] getLetterBlock()
    {
        return letterBlock;
    }

    /** Places a string into letterBlock in row-major order.
     *
     *   @param str  the string to be processed
     *
     *   Post-condition:
     *     if str.length() < numRows * numCols, "A" in each unfilled cell
     *     if str.length() > numRows * numCols, trailing characters are ignored
     */
    public void fillBlock(String str)
    {
        int index = 0;
        for (int row = 0; row < letterBlock.length; row++) {
            for (int col = 0; col < letterBlock[0].length; col++) {
                if (index < str.length()) {
                    letterBlock[row][col] = str.substring(index, index+1);
                    index++;
                }
                else {
                    letterBlock[row][col] = "A";
                }
            }
        }

    }

    /** Extracts encrypted string from letterBlock in column-major order.
     *
     *   Precondition: letterBlock has been filled
     *
     *   @return the encrypted string from letterBlock
     */
    public String encryptBlock()
    {
        String encryptedStr = "";
        for (int col = 0; col < letterBlock[0].length; col++) {
            for (int row = 0; row < letterBlock.length; row++) {
                encryptedStr += letterBlock[row][col];
            }
        }
        return encryptedStr;

    }

    /** Encrypts a message.
     *
     *  @param message the string to be encrypted
     *
     *  @return the encrypted message; if message is the empty string, returns the empty string
     */
    public String encryptMessage(String message)
    {
        String encryptedMessage = "";
        int encryptNum = (message.length() / (numCols * numRows));
        for (int i = 0; i < encryptNum; i++) {
            fillBlock(message);
            encryptedMessage += encryptBlock();
            message = message.substring(numRows * numCols);
        }
        if (message.length() != 0) {
            fillBlock(message);
            encryptedMessage += encryptBlock();
        }
        return encryptedMessage;

    }

    /**  Decrypts an encrypted message. All filler 'A's that may have been
     *   added during encryption will be removed, so this assumes that the
     *   original message (BEFORE it was encrypted) did NOT end in a capital A!
     *
     *   NOTE! When you are decrypting an encrypted message,
     *         be sure that you have initialized your Encryptor object
     *         with the same row/column used to encrypted the message! (i.e.
     *         the “encryption key” that is necessary for successful decryption)
     *         This is outlined in the precondition below.
     *
     *   Precondition: the Encryptor object being used for decryption has been
     *                 initialized with the same number of rows and columns
     *                 as was used for the Encryptor object used for encryption.
     *
     *   @param encryptedMessage  the encrypted message to decrypt
     *
     *   @return  the decrypted, original message (which had been encrypted)
     *
     *   TIP: You are encouraged to create other helper methods as you see fit
     *        (e.g. a method to decrypt each section of the decrypted message,
     *         similar to how encryptBlock was used)
     */
    public String decryptMessage(String encryptedMessage)
    {
        String originalMessage = "";
        int loopTimes = encryptedMessage.length() / (numRows * numCols);
        if (loopTimes * (numCols * numRows) < encryptedMessage.length()) {
            loopTimes++;
        }
        for (int i = 0; i < loopTimes; i++) {
            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    int index = (col * numRows) + row;
                    if (index < encryptedMessage.length()) {
                        originalMessage += encryptedMessage.substring(index, index + 1);
                    }
                }
            }
            if (encryptedMessage.length() >= (numRows * numCols)) {
                encryptedMessage = encryptedMessage.substring(numRows * numCols);
            }
        }
        while (originalMessage.charAt(originalMessage.length()-1) == 'A') {
            originalMessage = originalMessage.substring(0, originalMessage.length()-1);
        }
        return originalMessage;
    }

    public String superEncryptMessage(String message) {
        String encryptedMessage = "";
        int encryptNum = (message.length() / (numCols * numRows));
        if (encryptNum * (numCols * numRows) < message.length()) {
            encryptNum++;
        }

        for (int i = 0; i < encryptNum; i++) {
            fillBlock(message);

            //Shift Rows down 1
            String[] lastRow = letterBlock[numRows-1];
            for (int row = numRows-1; row > 0; row--) {
                for (int col = 0; col < numCols; col++) {
                   letterBlock[row][col] = letterBlock[row-1][col];
                }
            }
            letterBlock[0] = lastRow;
            /*
            //Shift Columns right 1
            String[] tempCol = new String[numRows];
            for (int row = 0; row < numRows; row++) {
                tempCol[row] = letterBlock[row][0];
            }
            for (int col = 0; col < numRows; col++) {
                if (letterBlock[0].length > 1) {
                    tempRow = new String[numRows];
                    for (int row = 0; row < numRows; row++) {
                        tempCol[row] = letterBlock
                    }
                }
                if (col++ == numCols) {
                    int a = 0;
                    for (String letter : tempCol) {
                        letterBlock[a][0] = tempCol[a];
                    }
                }
                else {
                    int a = 0;
                    for (String letter : tempCol) {
                        letterBlock[a][col] = tempCol[a];
                    }
                }
            }
*/

            encryptedMessage += encryptBlock();
            if (message.length() > numRows * numCols) {
                message = message.substring(numRows * numCols);
            }
        }
        return encryptedMessage;

    }

    public String superDecryptMessage(String message) {
        return"";
    }
}