package fr.herman.wizz.string;

import static fr.herman.wizz.string.BooleanOutput.outputBoolean;
import static fr.herman.wizz.string.ByteOutput.outputByte;
import static fr.herman.wizz.string.NumberOutput.outputInt;
import static fr.herman.wizz.string.NumberOutput.outputLong;
import static java.lang.Math.min;

import java.io.IOException;
import java.io.Writer;

import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.Encoder;

public abstract class StringEncoder implements Encoder {

    private static final int DEFAULT_BUFFER_SIZE = 8192;

    protected final Writer writer;

    protected char[] buffer;

    protected int cursor;

    public StringEncoder(Writer writer) {
        this(writer, DEFAULT_BUFFER_SIZE);
    }
    public StringEncoder(Writer writer, int bufferSize) {
        this.writer = writer;
        this.buffer = new char[bufferSize];
    }

    protected void require(int size) throws SerializerException {
        if (size > buffer.length - cursor) {
            flush();
        }
    }

    public void flush() throws SerializerException {
        try {
            writer.write(buffer, 0, cursor);
            writer.flush();
            cursor = 0;
        } catch (IOException e) {
            throw new SerializerException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public void writeInt(int input) throws SerializerException {
        require(NumberOutput.INT_MIN_BUFFER_SIZE);
        cursor = outputInt(input, buffer, cursor);
    }

    @Override
    public void writeLong(long input) throws SerializerException {
        require(NumberOutput.LONG_MIN_BUFFER_SIZE);
        cursor = outputLong(input, buffer, cursor);
    }

    @Override
    public void writeShort(short input) throws SerializerException {
        require(NumberOutput.SHORT_MIN_BUFFER_SIZE);
        cursor = outputInt(input, buffer, cursor);
    }

    @Override
    public void writeDouble(double input) throws SerializerException {
        // TODO Try write it faster
        writeString(Double.toString(input));
    }

    @Override
    public void writeFloat(float input) throws SerializerException {
        writeString(Float.toString(input));
    }

    @Override
    public void writeBoolean(boolean input) throws SerializerException {
        require(BooleanOutput.MAX_BUFFER_SIZE);
        cursor = outputBoolean(input, buffer, cursor);
    }

    @Override
    public void writeByte(byte input) throws SerializerException {
        require(ByteOutput.MIN_BUFFER_SIZE);
        cursor = outputByte(input, buffer, cursor);
    }

    @Override
    public void writeChar(char input) throws SerializerException {
        require(1);
        buffer[cursor++] = input;
    }

    @Override
    public void writeString(String input) throws SerializerException {
        int length = input.length();
        int offset = 0;
        while (offset < length) {
            int d = min(buffer.length - cursor, length - offset);
            input.getChars(offset, offset + d, buffer, cursor);
            offset += d;
            cursor += d;
            if (cursor >= buffer.length) {
                flush();
            }
        }
    }

}
