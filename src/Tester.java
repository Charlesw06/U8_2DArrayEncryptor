public class Tester {
    public static void main(String[] args) {
        Encryptor e = new Encryptor(6,3);
        String message = "Hello there! my favorite game is Clash";
        System.out.println(e.superEncryptMessage(message));
        System.out.println(e.superDecryptMessage(e.superEncryptMessage(message)));
    }
}
