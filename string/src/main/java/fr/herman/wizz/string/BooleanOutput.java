package fr.herman.wizz.string;

import fr.herman.wizz.exception.SerializerException;

public class BooleanOutput {
    public static final int MAX_BUFFER_SIZE = 5;

    public static final int MIN_BUFFER_SIZE = 4;

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

    public static boolean inputBoolean(char[] buffer, int offset, int length) throws SerializerException {
        if (length == 4) {
            if (is(buffer, offset, 't', 'r', 'u', 'e')) {
                return true;
            }
            throw new SerializerException("unreadable boolean");
        }
        if (length == 5) {
            if (is(buffer, offset, 'f', 'a', 'l', 's', 'e')) {
                return false;
            }
            throw new SerializerException("unreadable boolean");
        }
        throw new SerializerException("unreadable boolean");
    }

    private static boolean is(char[] buffer, int offset, char... chars) {
        for (int i = 0; i < chars.length; i++) {
            if (buffer[offset + i] != chars[i]) {
                return false;
            }
        }
        return true;
    }
}
