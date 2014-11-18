package fr.herman.wizz.string;

import static java.lang.String.format;

import java.io.IOException;
import java.io.Reader;

import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.SerializerReader;

public abstract class AbstractStringSerializerReader implements SerializerReader {

    private static final int DEFAULT_BUFFER_SIZE = 8192;

    protected final Reader reader;

    protected char[] buffer;

    protected int cursor = 0;

    protected int end = 0;

    public AbstractStringSerializerReader(Reader reader) {
        this(reader, DEFAULT_BUFFER_SIZE);
    }
    public AbstractStringSerializerReader(Reader reader, int bufferSize) {
        this.reader = reader;
        this.buffer = new char[bufferSize];
    }

    protected int require(int size) throws SerializerException {
        if (size <= end - cursor) {
            return end - cursor;
        }
        if (size > buffer.length) {
            throw new SerializerException(format("Buffer overflow : size %s is bigger than buffer size %s", size, buffer.length));
        }
        if (size > buffer.length - cursor) {
            // move remaining chars to the beginning of the buffer
            for (int i = 0; i < end - cursor; i++) {
                buffer[i] = buffer[cursor + i];
            }
            end -= cursor;
            cursor = 0;
        }
        if (size > end - cursor) {
            int length = fill(buffer, end, buffer.length - end);
            if (length == -1) {
                return -1;
            }
            end = end + length;
        }
        return end - cursor;
    }

    protected int fill(char[] buffer, int offset, int count) throws SerializerException {
        try {
            return reader.read(buffer, offset, count);
        } catch (IOException e) {
            throw new SerializerException(e.getLocalizedMessage(), e);
        }
    }

    protected abstract int tokenLength(char[] buffer, int offset, int count, int required) throws SerializerException;

    @Override
    public int readInt() throws SerializerException {
        System.out.println(cursor);
        int length = require(NumberOutput.INT_MIN_BUFFER_SIZE);
        length = tokenLength(buffer, cursor, length, NumberOutput.INT_MIN_BUFFER_SIZE);
        int i = NumberInput.parseInt(buffer, cursor, length);
        cursor += length;
        return i;
    }

    @Override
    public long readLong() throws SerializerException {
        int length = require(NumberOutput.LONG_MIN_BUFFER_SIZE);
        length = tokenLength(buffer, cursor, length, NumberOutput.LONG_MIN_BUFFER_SIZE);
        long l = NumberInput.parseLong(buffer, cursor, length);
        cursor += length;
        return l;
    }

    @Override
    public short readShort() throws SerializerException {
        int length = require(NumberOutput.SHORT_MIN_BUFFER_SIZE);
        length = tokenLength(buffer, cursor, length, NumberOutput.SHORT_MIN_BUFFER_SIZE);
        short s = (short) NumberInput.parseInt(buffer, cursor, length);
        cursor += length;
        return s;
    }

    @Override
    public double readDouble() throws SerializerException {
        // find a faster way to parse double
        return Double.parseDouble(readString());
    }

    @Override
    public float readFloat() throws SerializerException {
        return Float.parseFloat(readString());
    }

    @Override
    public boolean readBoolean() throws SerializerException {
        int length = require(BooleanOutput.MAX_BUFFER_SIZE);
        length = tokenLength(buffer, cursor, length, BooleanOutput.MAX_BUFFER_SIZE);
        boolean b = BooleanOutput.inputBoolean(buffer, cursor, length);
        cursor += length;
        return b;
    }

    @Override
    public byte readByte() throws SerializerException {
        require(ByteOutput.MIN_BUFFER_SIZE);
        return ByteOutput.inputByte(buffer, cursor++);
    }

    @Override
    public char readChar() throws SerializerException {
        require(1);
        return buffer[cursor++];
    }

    @Override
    public String readString() throws SerializerException {
        StringBuilder sb = new StringBuilder();
        int length = require(end - cursor);
        while ((length = tokenLength(buffer, cursor, length, -1)) != -1) {
            sb.append(buffer, cursor, length);
            cursor += length;
            length = require(buffer.length);
        }
        return sb.toString();
    }

    @Override
    public boolean readIsNull() throws SerializerException {
        int length = require(1);
        return tokenLength(buffer, cursor, length, 1) == 0;
    }

}