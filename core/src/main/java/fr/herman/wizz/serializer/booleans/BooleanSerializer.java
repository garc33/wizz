package fr.herman.wizz.serializer.booleans;

import fr.herman.wizz.Serializer;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.Decoder;
import fr.herman.wizz.io.Encoder;

public class BooleanSerializer implements Serializer<Boolean> {

    public BooleanSerializer(String trueValue, String falseValue) {
    }

    @Override
    public Boolean deserialize(Decoder reader) throws SerializerException {
        return Boolean.valueOf(reader.readBoolean());
    }

    @Override
    public void serialize(Encoder writer, Boolean object) throws SerializerException {
        writer.writeBoolean(object.booleanValue());
    }

    @Override
    public Class<Boolean> handleClass() {
        return Boolean.class;
    }
}
