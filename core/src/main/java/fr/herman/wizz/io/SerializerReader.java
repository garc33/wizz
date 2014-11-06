package fr.herman.wizz.io;

import fr.herman.wizz.exception.SerializerException;

public interface SerializerReader {
    int readInt() throws SerializerException;

    long readLong() throws SerializerException;

    short readSort() throws SerializerException;

    double readDouble() throws SerializerException;

    float readFloat() throws SerializerException;

    boolean readBoolean() throws SerializerException;

    byte readByte() throws SerializerException;

    char readChar() throws SerializerException;

    String readString() throws SerializerException;

    boolean readIsNull() throws SerializerException;

}
