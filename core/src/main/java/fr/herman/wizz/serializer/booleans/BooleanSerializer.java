package fr.herman.wizz.serializer.booleans;

import fr.herman.wizz.Serializer;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.SerializerReader;
import fr.herman.wizz.io.SerializerWriter;

public class BooleanSerializer implements Serializer<Boolean> {

    private final String trueValue, falseValue;

    public BooleanSerializer(String trueValue, String falseValue) {
        this.trueValue = trueValue;
        this.falseValue = falseValue;
    }

    @Override
    public Boolean deserialize(SerializerReader reader) throws SerializerException {
        return Boolean.valueOf(reader.readBoolean());
    }

    @Override
    public void serialize(SerializerWriter writer, Boolean object) throws SerializerException {
        writer.writeBoolean(object.booleanValue());
    }

    @Override
    public Class<Boolean> handleClass() {
        return Boolean.class;
    }

    @Override
    public String format() {
        return String.format("%s/%s", trueValue, falseValue);
    }

}
