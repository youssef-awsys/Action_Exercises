package sample;

public final class JavaApp {
    private JavaApp() {
        super();
    }

    /**
     *
     * @param args
     */
    public static void main(final String[] args) {
        StringBuilder a = new StringBuilder("abc");
        StringBuilder b = a.append("de");
        b = b.append("f").append("g");
        System.out.println(a);
        System.out.println(b.toString().toUpperCase());
        // modifyArray(arrayName);
        // System.out.println(arrayName[0]);
    }

    /**
     *
     * @param arrN
     */
    public static void modifyArray(final int[] arrN) {
        final int x = 10;
        arrN[0] = x;
    }
}
