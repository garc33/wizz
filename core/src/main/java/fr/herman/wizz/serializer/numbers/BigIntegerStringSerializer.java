package fr.herman.wizz.serializer.numbers;

import java.math.BigInteger;
import fr.herman.wizz.Serializer;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.Decoder;
import fr.herman.wizz.io.Encoder;

public class BigIntegerStringSerializer implements Serializer<BigInteger> {

    @Override
    public Class<BigInteger> handleClass() {
        return BigInteger.class;
    }


    @Override
    public BigInteger deserialize(Decoder reader) throws SerializerException {
        String s = reader.readString();
        try {
        return new BigInteger(s);
        } catch (Exception e) {
            throw new SerializerException(String.format("Cannot convert %s to BigInteger", s), e);
        }
    }


    @Override
    public void serialize(Encoder writer, BigInteger object) throws SerializerException {
        writer.writeString(object.toString());
    }

}
