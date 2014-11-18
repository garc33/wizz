package fr.herman.wizz.string.csv;

import java.io.Reader;

import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.string.AbstractStringSerializerReader;
import fr.herman.wizz.string.BooleanOutput;
import fr.herman.wizz.string.ByteOutput;
import fr.herman.wizz.string.NumberInput;

public class CsvReader extends AbstractStringSerializerReader {

    private final char separator;

    private boolean skipLF = false;

    public CsvReader(Reader reader, char separator) {
        super(reader);
        this.separator = separator;
    }

    @Override
    protected int tokenLength(char[] buffer, int offset, int count, int required) throws SerializerException {
        if (count < 0) {
            throw new SerializerException("Buffer overflow");
        }
        int capacity = offset + count;
        int i = offset;
        while (i < capacity) {
            char c = buffer[i];
            if (separator == c || '\n' == c || '\r' == c) {
                if ('\n' == c) {
                    skipLF = true;
                } else if ('\r' == c && skipLF) {
                    cursor++;
                    offset++;
                    skipLF = false;
                }
                return i - offset;
            }
            i++;
        }
        if (required > 0) {
            throw new SerializerException("token too long");
        }
        return -1;
    }

    @Override
    protected int require(int size) throws SerializerException {
        return super.require(size + (skipLF ? 2 : 1));
    }

    protected boolean hasNext(int size) throws SerializerException {
        return require(size) >= size;
    }

    public boolean hasNextInt() throws SerializerException {
        return hasNext(NumberInput.MIN_BUFFER_SIZE);
    }

    public boolean hasNextLong() throws SerializerException {
        return hasNext(NumberInput.MIN_BUFFER_SIZE);
    }

    public boolean hasNextShort() throws SerializerException {
        return hasNext(NumberInput.MIN_BUFFER_SIZE);
    }

    public boolean hasNextDouble() throws SerializerException {
        return hasNext(NumberInput.MIN_BUFFER_SIZE);
    }

    public boolean hasNextFloat() throws SerializerException {
        return hasNext(NumberInput.MIN_BUFFER_SIZE);
    }

    public boolean hasNextBoolean() throws SerializerException {
        return hasNext(BooleanOutput.MIN_BUFFER_SIZE);
    }

    public boolean hasNextByte() throws SerializerException {
        return hasNext(ByteOutput.MIN_BUFFER_SIZE);
    }

    public boolean hasNextChar() throws SerializerException {
        return hasNext(1);
    }

    public boolean hasNextString() throws SerializerException {
        return hasNext(1);
    }

    @Override
    public int readInt() throws SerializerException {
        int i = super.readInt();
        cursor++;
        return i;
    }

    @Override
    public long readLong() throws SerializerException {
        long l = super.readLong();
        cursor++;
        return l;
    }

    @Override
    public short readShort() throws SerializerException {
        short s = super.readShort();
        cursor++;
        return s;
    }

    @Override
    public double readDouble() throws SerializerException {
        double d = super.readDouble();
        cursor++;
        return d;
    }

    @Override
    public float readFloat() throws SerializerException {
        float f = super.readFloat();
        cursor++;
        return f;
    }

    @Override
    public boolean readBoolean() throws SerializerException {
        boolean b = super.readBoolean();
        cursor++;
        return b;
    }

    @Override
    public byte readByte() throws SerializerException {
        byte b = super.readByte();
        cursor++;
        return b;
    }

    @Override
    public char readChar() throws SerializerException {
        char c = super.readChar();
        cursor++;
        return c;
    }

    @Override
    public String readString() throws SerializerException {
        String s = super.readString();
        cursor++;
        return s;
    }
}
