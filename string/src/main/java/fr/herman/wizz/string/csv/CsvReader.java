package fr.herman.wizz.string.csv;

import java.io.Reader;

import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.string.AbstractStringSerializerReader;

public class CsvReader extends AbstractStringSerializerReader {

    private final char separator;

    private final char eol;

    private int previousCursor = -1;

    private int previousLength;

    public CsvReader(Reader reader, char separator, char eol) {
        super(reader);
        this.separator = separator;
        this.eol = eol;
    }

    CsvReader(Reader reader, char separator, char eol, int bufferSize) {
        super(reader, bufferSize);
        this.separator = separator;
        this.eol = eol;
    }

    @Override
    protected int tokenLength(boolean appendable) throws SerializerException {
        if (previousCursor == cursor) {
            return previousLength;
        }
        int computeLength = computeLength(appendable);
        previousCursor = cursor;
        previousLength = computeLength;
        return computeLength;
    }

    private int computeLength(boolean appendable) throws SerializerException {

        if (cursor > nextchar) {
            return -1;
        }
        int tokenLength;
        if (cursor < nextchar) {
            if ((tokenLength = findLength(cursor)) != -1) {
                return tokenLength;
            }
        }
        int required = Math.min(cursor + buffer.length - nextchar, buffer.length);
        if (required < 1) {
            throw new SerializerException("Buffer overflow");
        }
        int length = require(required);
        if (length == -1) {
            return nextchar - cursor;
        }
        tokenLength = findLength(cursor);
        return tokenLength;
    }

    private int findLength(int i) {
        while (i < nextchar) {
            char c = buffer[i];
            if (separator == c || eol == c) {
                return i - cursor;
            }
            i++;
        }
        return -1;
    }

    public boolean hasNext() throws SerializerException {
        return tokenLength(true) > -1;
    }

    public void skipToken() throws SerializerException {
        int length;
        while ((length = tokenLength(true)) == -2) {
            cursor = nextchar;
        }
        if (length > 0) {
            cursor += length;
        }
        cursor++;
    }


    @Override
    public int readInt() throws SerializerException {
        int readInt = super.readInt();
        cursor++;
        return readInt;
    }

    @Override
    public long readLong() throws SerializerException {
        long readLong = super.readLong();
        cursor++;
        return readLong;
    }

    @Override
    public short readShort() throws SerializerException {
        short readShort = super.readShort();
        cursor++;
        return readShort;
    }

    @Override
    public double readDouble() throws SerializerException {
        double readDouble = super.readDouble();
        cursor++;
        return readDouble;
    }

    @Override
    public float readFloat() throws SerializerException {
        float readFloat = super.readFloat();
        cursor++;
        return readFloat;
    }

    @Override
    public boolean readBoolean() throws SerializerException {
        boolean readBoolean = super.readBoolean();
        cursor++;
        return readBoolean;
    }

    @Override
    public byte readByte() throws SerializerException {
        byte readByte = super.readByte();
        cursor++;
        return readByte;
    }

    @Override
    public char readChar() throws SerializerException {
        char readChar = super.readChar();
        cursor++;
        return readChar;
    }

    @Override
    public String readString() throws SerializerException {
        String readString = super.readString();
        cursor++;
        return readString;
    }

    @Override
    public boolean readIsNull() throws SerializerException {
        boolean readIsNull = super.readIsNull();
        if (readIsNull) {
            cursor++;
        }
        return readIsNull;
    }

}
