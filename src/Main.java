public class Main {
    public static void main(String[] args) {
        KeyTest keyTest = new KeyTest();
        byte[] sequence = keyTest.generateSequence(20000);
        System.out.println(keyTest.testRunner(sequence));
    }
}
