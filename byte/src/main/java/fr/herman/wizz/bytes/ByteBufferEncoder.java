package fr.herman.wizz.bytes;

import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.util.List;

import fr.herman.wizz.bytes.charset.CustomCharset;
import fr.herman.wizz.bytes.charset.UTF8;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.mapping.OutputMapping;

public abstract class ByteBufferEncoder implements ByteEncoder {

    protected final CustomCharset charset;

    protected ByteBuffer buffer;

    private byte[] localBuffer = new byte[255];

    public ByteBufferEncoder() {
        this(new UTF8());
    }
    public ByteBufferEncoder(CustomCharset charset) {
        this.charset = charset;
    }

    protected abstract void require(int length) throws SerializerException;

    @Override
    public void writeInt(int input) throws SerializerException {
        try {
            require(4);
            buffer.putInt(input);
        } catch (BufferOverflowException | ReadOnlyBufferException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public void writeLong(long input) throws SerializerException {
        try {
            require(8);
            buffer.putLong(input);
        } catch (BufferOverflowException | ReadOnlyBufferException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public void writeShort(short input) throws SerializerException {
        try {
            require(2);
            buffer.putShort(input);
        } catch (BufferOverflowException | ReadOnlyBufferException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public void writeDouble(double input) throws SerializerException {
        try {
            require(8);
            buffer.putDouble(input);
        } catch (BufferOverflowException | ReadOnlyBufferException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public void writeFloat(float input) throws SerializerException {
        try {
            require(4);
            buffer.putFloat(input);
        } catch (BufferOverflowException | ReadOnlyBufferException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public void writeBoolean(boolean input) throws SerializerException {
        try {
            require(1);
            buffer.put((byte) (input ? 1 : 0));
        } catch (BufferOverflowException | ReadOnlyBufferException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public void writeByte(byte input) throws SerializerException {
        try {
            require(1);
            buffer.put(input);
        } catch (BufferOverflowException | ReadOnlyBufferException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public void writeChar(char input) throws SerializerException {
        try {
            require(2);
            buffer.putChar(input);
        } catch (BufferOverflowException | ReadOnlyBufferException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public void writeString(String input) throws SerializerException {
        writeString(input, charset);
    }


    @Override
    public void writeString(String input, CustomCharset charset) throws SerializerException {
        int length = input.length();
        int index = 0;
        writeInt(charset.computeSize(input, 0, length));
        while (index < length) {
            int estimated = (length - index) * charset.maxBytesPerChar();
            int batch = Math.min(localBuffer.length, estimated) / charset.maxBytesPerChar();
            int bytes = charset.encode(input, index, batch, localBuffer, 0);
            writeByteArray(localBuffer, 0, bytes);
            index += batch;
        }
    }

    @Override
    public void writeNull() throws SerializerException {
        try {
            require(1);
            buffer.put((byte) 0);
        } catch (BufferOverflowException | ReadOnlyBufferException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public void writeNotNull() throws SerializerException {
        try {
            require(1);
            buffer.put((byte) 1);
        } catch (BufferOverflowException | ReadOnlyBufferException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public void writeByteArray(byte[] b, int offset, int length) throws SerializerException {
        try {
            require(length);
            buffer.put(b, offset, length);
        } catch (BufferOverflowException | ReadOnlyBufferException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public <O> void writeObject(List<OutputMapping<O>> mappings, O object) throws SerializerException {
        for (OutputMapping<O> mapping : mappings) {
            mapping.write(this, object);
        }
    }

}
