package fr.herman.wizz.bytes.utils;

public class Number {

    public static final int INT_SIZE = 4;

    public static final void intToBytes(int value, byte[] bytes, int offset) {
        bytes[offset++] = (byte) (value >> 24);
        bytes[offset++] = (byte) (value >> 16);
        bytes[offset++] = (byte) (value >> 8);
        bytes[offset++] = (byte) value;
    }

    public static final void longToBytes(long value, byte[] bytes, int offset) {
        bytes[offset++] = (byte) (value >>> 56);
        bytes[offset++] = (byte) (value >>> 48);
        bytes[offset++] = (byte) (value >>> 40);
        bytes[offset++] = (byte) (value >>> 32);
        bytes[offset++] = (byte) (value >>> 24);
        bytes[offset++] = (byte) (value >>> 16);
        bytes[offset++] = (byte) (value >>> 8);
        bytes[offset++] = (byte) value;
    }

    public static final void doubleToBytes(double value, byte[] bytes, int offset) {
        longToBytes(Double.doubleToLongBits(value), bytes, offset);
    }

    public static final void floatToBytes(float value, byte[] bytes, int offset) {
        intToBytes(Float.floatToIntBits(value), bytes, offset);
    }

}
