package fr.herman.wizz.io.strings;

import static fr.herman.wizz.io.strings.BooleanOutput.inputBoolean;
import static fr.herman.wizz.io.strings.ByteOutput.inputByte;
import static java.lang.String.format;

import java.io.IOException;
import java.io.Reader;

import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.SerializerReader;

public abstract class AbstractStringSerializerReader implements SerializerReader {

    protected Reader reader;

    protected char[] buffer;

    protected int cursor = 0;

    protected int end = 0;

    protected int require(int size) throws SerializerException {
        if (size > buffer.length) {
            throw new SerializerException(format("size %s is bigger than buffer size %s", size, buffer.length));
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
            fill();
        }
        return tokenLength();
    }

    protected void fill() throws SerializerException {
        try {
            int length = reader.read(buffer, end, buffer.length - end);
            end = end + length;
        } catch (IOException e) {
            throw new SerializerException(e.getLocalizedMessage(), e);
        }
    }

    protected abstract int tokenLength();

    @Override
    public int readInt() throws SerializerException {
        require(NumberOutput.INT_MIN_BUFFER_SIZE);
        return 0;
    }

    @Override
    public long readLong() throws SerializerException {
        require(NumberOutput.LONG_MIN_BUFFER_SIZE);
        return 0;
    }

    @Override
    public short readShort() throws SerializerException {
        return 0;
    }

    @Override
    public double readDouble() throws SerializerException {
        return 0;
    }

    @Override
    public float readFloat() throws SerializerException {
        return 0;
    }

    @Override
    public boolean readBoolean() throws SerializerException {
        int length = require(BooleanOutput.MIN_BUFFER_SIZE);
        return inputBoolean(buffer, cursor, length);
    }

    @Override
    public byte readByte() throws SerializerException {
        require(ByteOutput.MIN_BUFFER_SIZE);
        return inputByte(buffer, cursor++);
    }

    @Override
    public char readChar() throws SerializerException {
        require(1);
        return buffer[cursor++];
    }

    @Override
    public String readString() throws SerializerException {
        return null;
    }

    @Override
    public boolean readIsNull() throws SerializerException {
        int length = require(1);
        return length == 0;
    }

}
