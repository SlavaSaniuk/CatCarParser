package by.bsac.core;

public class Asserts {

    public static void notNull(Object a_obj, String a_exception_message) throws RuntimeAssertionException{
        if (a_obj == null) throw new RuntimeAssertionException(a_exception_message);
    }

    public static class RuntimeAssertionException extends RuntimeException {

        public RuntimeAssertionException(String msg) {
            super(_message(msg));
        }

        private static String _message(String message) {
            if (message != null && !message.equals("")) return message;
            else return "Specified object is null.";
        }

    }

}

