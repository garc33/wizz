package fr.herman.wizz.bytes;

import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;

import fr.herman.wizz.exception.SerializerException;

public class ByteBufferIO implements ByteEncoder, ByteDecoder {
    private final ByteBuffer buffer;

    public ByteBufferIO(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void writeInt(int input) throws SerializerException {
        try {
            buffer.putInt(input);
        } catch (BufferOverflowException | ReadOnlyBufferException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public void writeLong(long input) throws SerializerException {
        try {
            buffer.putLong(input);
        } catch (BufferOverflowException | ReadOnlyBufferException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public void writeShort(short input) throws SerializerException {
        try {
            buffer.putShort(input);
        } catch (BufferOverflowException | ReadOnlyBufferException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public void writeDouble(double input) throws SerializerException {
        try {
            buffer.putDouble(input);
        } catch (BufferOverflowException | ReadOnlyBufferException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public void writeFloat(float input) throws SerializerException {
        try {
            buffer.putFloat(input);
        } catch (BufferOverflowException | ReadOnlyBufferException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public void writeBoolean(boolean input) throws SerializerException {
        try {
            buffer.put((byte) (input ? 1 : 0));
        } catch (BufferOverflowException | ReadOnlyBufferException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public void writeByte(byte input) throws SerializerException {
        try {
            buffer.put(input);
        } catch (BufferOverflowException | ReadOnlyBufferException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public void writeChar(char input) throws SerializerException {
        try {
            buffer.putChar(input);
        } catch (BufferOverflowException | ReadOnlyBufferException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public void writeString(String input) throws SerializerException {
        try {
            buffer.putInt(input.length());
            // TODO Find a faster way to do this!!!
            for (int i = 0; i < input.length(); i++) {
                buffer.putChar(input.charAt(i));
            }
        } catch (BufferOverflowException | ReadOnlyBufferException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public void writeNull() throws SerializerException {
        try {
            buffer.put((byte) 0);
        } catch (BufferOverflowException | ReadOnlyBufferException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public void writeNotNull() throws SerializerException {
        try {
            buffer.put((byte) 1);
        } catch (BufferOverflowException | ReadOnlyBufferException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public void writeByteArray(byte[] b, int offset, int length) throws SerializerException {
        try {
            buffer.put(b, offset, length);
        } catch (BufferOverflowException | ReadOnlyBufferException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public int readInt() throws SerializerException {
        try {
            return buffer.getInt();
        } catch (BufferOverflowException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public long readLong() throws SerializerException {
        try {
            return buffer.getLong();
        } catch (BufferOverflowException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public short readShort() throws SerializerException {
        try {
            return buffer.getShort();
        } catch (BufferOverflowException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public double readDouble() throws SerializerException {
        try {
            return buffer.getDouble();
        } catch (BufferOverflowException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public float readFloat() throws SerializerException {
        try {
            return buffer.getFloat();
        } catch (BufferOverflowException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public boolean readBoolean() throws SerializerException {
        try {
            return buffer.get() == 1;
        } catch (BufferOverflowException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public byte readByte() throws SerializerException {
        try {
            return buffer.get();
        } catch (BufferOverflowException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public char readChar() throws SerializerException {
        try {
            return buffer.getChar();
        } catch (BufferOverflowException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public String readString() throws SerializerException {
        try {
            char[] str = new char[buffer.getInt()];
            for (int i = 0; i < str.length; i++) {
                str[i] = buffer.getChar();
            }
            return new String(str);
        } catch (BufferOverflowException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public boolean readIsNull() throws SerializerException {
        try {
            return buffer.get() == 0;
        } catch (BufferOverflowException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public void readByteArray(byte[] dest, int offset, int length) throws SerializerException {
        try {
            buffer.get(dest, offset, length);
        } catch (BufferOverflowException e) {
            throw new SerializerException(e);
        }
    }

}
