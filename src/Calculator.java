public class Calculator {

    public static void main(String[] args) {
        int a = 10;
        int b = 5;

        // Bug 1: dividing by a value that is always zero
        int zero = 0;
        int bad = a / zero;

        // Bug 2: using a variable that is always null
        String text = null;
        int len = text.length();

        // Bug 3: comparing a value with itself
        if (a == a) {
            bad++;
        }
    }
}