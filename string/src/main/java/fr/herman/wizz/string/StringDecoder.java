package fr.herman.wizz.string;

import static java.lang.String.format;

import java.io.IOException;
import java.io.Reader;

import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.Decoder;

public abstract class StringDecoder implements Decoder {

    private static final int DEFAULT_BUFFER_SIZE = 8192;

    protected final Reader reader;

    protected char[] buffer;

    protected int cursor = 0;

    protected int nextchar = 0;

    public StringDecoder(Reader reader) {
        this(reader, DEFAULT_BUFFER_SIZE);
    }
    public StringDecoder(Reader reader, int bufferSize) {
        this.reader = reader;
        this.buffer = new char[bufferSize];
    }

    protected int require(int size) throws SerializerException {
        if (size <= nextchar - cursor) {
            return nextchar - cursor;
        }
        if (size > buffer.length) {
            throw new SerializerException(format("Buffer overflow : size %s is bigger than buffer size %s", size, buffer.length));
        }
        if (size > buffer.length - cursor) {
            // move remaining chars to the beginning of the buffer
            System.arraycopy(buffer, cursor, buffer, 0, nextchar - cursor);
            nextchar -= cursor;
            cursor = 0;
        }
        if (size > nextchar - cursor) {
            int length = fill(buffer, nextchar, buffer.length - nextchar);
            if (length == -1) {
                return -1;
            }
            nextchar += length;
        }
        return nextchar - cursor;
    }

    protected int fill(char[] buffer, int offset, int count) throws SerializerException {
        try {
            int length;
            do {
                length = reader.read(buffer, offset, count);
            } while (length == 0);
            return length;
        } catch (IOException e) {
            throw new SerializerException(e.getLocalizedMessage(), e);
        }
    }

    protected abstract int tokenLength(boolean appendable) throws SerializerException;

    @Override
    public int readInt() throws SerializerException {

        int length = tokenLength(false);
        if (length < 1) {
            throw new SerializerException("length should be > 0");
        }
        int i = NumberInput.parseInt(buffer, cursor, length);
        cursor += length;
        return i;
    }

    @Override
    public long readLong() throws SerializerException {
        int length = tokenLength(false);
        if (length < 1) {
            throw new SerializerException("length should be > 0");
        }
        long l = NumberInput.parseLong(buffer, cursor, length);
        cursor += length;
        return l;
    }

    @Override
    public short readShort() throws SerializerException {
        int length = tokenLength(false);
        if (length < 1) {
            throw new SerializerException("length should be > 0");
        }
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
        int length = tokenLength(false);
        if (length < 1) {
            throw new SerializerException("length should be > 0");
        }
        boolean b = BooleanOutput.inputBoolean(buffer, cursor, length);
        cursor += length;
        return b;
    }

    @Override
    public byte readByte() throws SerializerException {
        int length = tokenLength(false);
        if (length < 1) {
            throw new SerializerException("length should be > 0");
        }
        return ByteOutput.inputByte(buffer, cursor++);
    }

    @Override
    public char readChar() throws SerializerException {
        int length = tokenLength(false);
        if (length < 1) {
            throw new SerializerException("length should be > 0");
        }
        return buffer[cursor++];
    }

    @Override
    public String readString() throws SerializerException {
        StringBuilder sb = new StringBuilder();
        int length;
        while ((length = tokenLength(true)) == -2) {
            sb.append(buffer, cursor, nextchar - cursor - 1);
            cursor = nextchar;
        }
        if (length > 0) {
            sb.append(buffer, cursor, length);
            cursor += length;
        }
        return sb.toString();
    }

    @Override
    public boolean readIsNull() throws SerializerException {
        return tokenLength(true) == 0;
    }

}
