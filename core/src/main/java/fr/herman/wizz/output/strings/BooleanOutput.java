package fr.herman.wizz.output.strings;

public class BooleanOutput {
    public static final int MIN_BUFFER_SIZE = 5;

    public static int outputBoolean(boolean b, char[] buffer, int offset) {
        if (b) {
            buffer[offset++] = 't';
            buffer[offset++] = 'r';
            buffer[offset++] = 'u';
            buffer[offset++] = 'e';
        } else {
            buffer[offset++] = 'f';
            buffer[offset++] = 'a';
            buffer[offset++] = 'l';
            buffer[offset++] = 's';
            buffer[offset++] = 'e';
        }
        return offset;
    }
}
