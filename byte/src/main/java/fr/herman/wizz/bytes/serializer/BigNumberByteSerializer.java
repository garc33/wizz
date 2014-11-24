package fr.herman.wizz.bytes.serializer;

import fr.herman.wizz.bytes.ByteDecoder;
import fr.herman.wizz.bytes.ByteEncoder;
import fr.herman.wizz.exception.SerializerException;

public abstract class BigNumberByteSerializer<N> implements ByteSerializer<N> {

    protected void writeBytes(byte[] bytes, ByteEncoder encoder) throws SerializerException {
        encoder.writeInt(bytes.length);
        encoder.writeByteArray(bytes, 0, bytes.length);
    }

    protected byte[] readBytes(ByteDecoder decoder) throws SerializerException {
        byte[] b = new byte[decoder.readInt()];
        decoder.readByteArray(b, 0, b.length);
        return b;
    }
}
