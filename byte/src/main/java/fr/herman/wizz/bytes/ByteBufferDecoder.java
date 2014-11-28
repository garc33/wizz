package fr.herman.wizz.bytes;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;

import fr.herman.wizz.bytes.charset.CustomCharset;
import fr.herman.wizz.exception.SerializerException;

public abstract class ByteBufferDecoder implements ByteDecoder {

    protected ByteBuffer buffer;

    protected abstract void require(int i);

    @Override
    public int readInt() throws SerializerException {
        require(4);
        try {
            return buffer.getInt();
        } catch (BufferOverflowException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public long readLong() throws SerializerException {
        require(8);
        try {
            return buffer.getLong();
        } catch (BufferOverflowException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public short readShort() throws SerializerException {
        require(2);
        try {
            return buffer.getShort();
        } catch (BufferOverflowException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public double readDouble() throws SerializerException {
        require(8);
        try {
            return buffer.getDouble();
        } catch (BufferOverflowException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public float readFloat() throws SerializerException {
        require(4);
        try {
            return buffer.getFloat();
        } catch (BufferOverflowException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public boolean readBoolean() throws SerializerException {
        require(1);
        try {
            return buffer.get() == 1;
        } catch (BufferOverflowException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public byte readByte() throws SerializerException {
        require(1);
        try {
            return buffer.get();
        } catch (BufferOverflowException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public char readChar() throws SerializerException {
        require(2);
        try {
            return buffer.getChar();
        } catch (BufferOverflowException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public String readString() throws SerializerException {
        return null;
    }

    @Override
    public boolean readIsNull() throws SerializerException {
        require(1);
        try {
            return buffer.get() == 0;
        } catch (BufferOverflowException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public void readByteArray(byte[] dest, int offset, int length) throws SerializerException {
        try {
            int limit = offset + length;
            while (offset < limit) {
                require(Math.min(buffer.capacity(), limit - offset));
                int batch = Math.min(limit - offset, buffer.remaining());
                buffer.get(dest, offset, batch);
                offset += batch;
            }
        } catch (BufferOverflowException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public String readString(CustomCharset charset) throws SerializerException {
        try {
            int size = readInt();
            require(size);
            StringBuilder sb = new StringBuilder(size);
            charset.decode(buffer, size, sb);
            return sb.toString();

        } catch (IOException e) {
            throw new SerializerException(e);
        }
    }
}
