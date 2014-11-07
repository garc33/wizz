package fr.herman.wizz.serializer.numbers;

import java.math.BigInteger;
import fr.herman.wizz.Serializer;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.SerializerReader;
import fr.herman.wizz.io.SerializerWriter;

public class BigIntegerStringSerializer implements Serializer<BigInteger> {

    @Override
    public Class<BigInteger> handleClass() {
        return BigInteger.class;
    }


    @Override
    public BigInteger deserialize(SerializerReader reader) throws SerializerException {
        String s = reader.readString();
        try {
        return new BigInteger(s);
        } catch (Exception e) {
            throw new SerializerException(String.format("Cannot convert %s to BigInteger", s), e);
        }
    }


    @Override
    public void serialize(SerializerWriter writer, BigInteger object) throws SerializerException {
        writer.writeString(object.toString());
    }

}
