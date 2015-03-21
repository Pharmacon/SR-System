package by.ostis.common.sctpclient.utils;

public class AssertionUtils {

    private static boolean ENABLE = true;

    public static <T> void notNull(T object) {

        if (ENABLE) {
            if (object == null) {
                throw new NullPointerException();
            }
        }
    }

    public static <T> void notNull(T... objects) {

        for (T t : objects) {
            notNull(t);
        }
    }

    public static void setENABLE(boolean eNABLE) {

        ENABLE = eNABLE;
    }

}
