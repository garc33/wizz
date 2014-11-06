package fr.herman.wizz.serializer.numbers;

import java.math.BigInteger;

import fr.herman.wizz.Serializer;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.SerializerReader;
import fr.herman.wizz.io.SerializerWriter;

public class BigIntegerLongSerializer implements Serializer<BigInteger> {

    @Override
    public Class<BigInteger> handleClass() {
        return BigInteger.class;
    }


    @Override
    public String format() {
        return "integer";
    }

    @Override
    public BigInteger deserialize(SerializerReader reader) throws SerializerException {
        long l = reader.readLong();
        try {
            return BigInteger.valueOf(l);
        } catch (Exception e) {
            throw new SerializerException(String.format("Cannot convert %s to BigInteger", l), e);
        }
    }


    @Override
    public void serialize(SerializerWriter writer, BigInteger object) throws SerializerException {
        writer.writeLong(object.longValue());
    }

}
