package fr.herman.wizz.serializer.numbers;

import java.math.BigDecimal;
import fr.herman.wizz.Serializer;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.SerializerReader;
import fr.herman.wizz.io.SerializerWriter;

public class BigDecimalStringSerializer implements Serializer<BigDecimal> {

    @Override
    public Class<BigDecimal> handleClass() {
        return BigDecimal.class;
    }

    @Override
    public BigDecimal deserialize(SerializerReader reader) throws SerializerException {
        String s = reader.readString();
        try {
            return new BigDecimal(s);
        } catch (Exception e) {
            throw new SerializerException(String.format("Cannot convert %s to BigDecimal", s), e);
        }
    }

    @Override
    public void serialize(SerializerWriter writer, BigDecimal object) throws SerializerException {
        writer.writeString(object.toPlainString());
    }

}
