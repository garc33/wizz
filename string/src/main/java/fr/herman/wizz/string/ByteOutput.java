package fr.herman.wizz.string;

public class ByteOutput {
    public static final int MIN_BUFFER_SIZE = 1;

    public static int outputByte(byte b, char[] buffer, int offset) {
        buffer[offset++] = (char) ('0' + b);
        return offset;
    }

    public static byte inputByte(char[] buffer, int offset) {
        return (byte) (buffer[offset] - '0');
    }
}
