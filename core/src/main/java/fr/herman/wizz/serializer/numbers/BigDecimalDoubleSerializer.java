package fr.herman.wizz.serializer.numbers;

import java.math.BigDecimal;

import fr.herman.wizz.Serializer;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.SerializerReader;
import fr.herman.wizz.io.SerializerWriter;

public class BigDecimalDoubleSerializer implements Serializer<BigDecimal> {

    @Override
    public Class<BigDecimal> handleClass() {
        return BigDecimal.class;
    }

    @Override
    public String format() {
        return "decimal";
    }

    @Override
    public BigDecimal deserialize(SerializerReader reader) throws SerializerException {
        double d = reader.readDouble();
        try {
            return BigDecimal.valueOf(d);
        } catch (Exception e) {
            throw new SerializerException(String.format("Cannot convert %s to BigDecimal", d), e);
        }
    }

    @Override
    public void serialize(SerializerWriter writer, BigDecimal object) throws SerializerException {
        writer.writeDouble(object.doubleValue());
    }

}
