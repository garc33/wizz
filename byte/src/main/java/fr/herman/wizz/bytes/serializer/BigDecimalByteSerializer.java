package fr.herman.wizz.bytes.serializer;

import java.math.BigDecimal;
import java.math.BigInteger;

import fr.herman.wizz.bytes.ByteDecoder;
import fr.herman.wizz.bytes.ByteEncoder;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.Decoder;
import fr.herman.wizz.io.Encoder;

public class BigDecimalByteSerializer extends BigNumberByteSerializer<BigDecimal> {

    @Override
    public BigDecimal deserialize(Decoder reader) throws SerializerException {
        return new BigDecimal(new BigInteger(readBytes((ByteDecoder) reader)), reader.readInt());
    }

    @Override
    public void serialize(Encoder writer, BigDecimal object) throws SerializerException {
        writeBytes(object.unscaledValue().toByteArray(), (ByteEncoder) writer);
        writer.writeInt(object.scale());
    }

    @Override
    public Class<BigDecimal> handleClass() {
        return BigDecimal.class;
    }

}
