public class Tester {
    public static void main(String[] args) {
        Encryptor e = new Encryptor(2,2);
        String message = "Hey";
        System.out.println(e.superEncryptMessage(message));
    }
}
