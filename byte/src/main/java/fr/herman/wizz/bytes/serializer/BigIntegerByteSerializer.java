package fr.herman.wizz.bytes.serializer;

import java.math.BigInteger;

import fr.herman.wizz.bytes.ByteDecoder;
import fr.herman.wizz.bytes.ByteEncoder;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.Decoder;
import fr.herman.wizz.io.Encoder;

public class BigIntegerByteSerializer extends BigNumberByteSerializer<BigInteger> {

    @Override
    public BigInteger deserialize(Decoder reader) throws SerializerException {
        return new BigInteger(readBytes((ByteDecoder) reader));
    }

    @Override
    public void serialize(Encoder writer, BigInteger object) throws SerializerException {
        writeBytes(object.toByteArray(), (ByteEncoder) writer);
    }

    @Override
    public Class<BigInteger> handleClass() {
        return BigInteger.class;
    }

}
