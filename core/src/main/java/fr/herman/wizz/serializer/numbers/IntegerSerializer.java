package fr.herman.wizz.serializer.numbers;

import fr.herman.wizz.Serializer;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.SerializerReader;
import fr.herman.wizz.io.SerializerWriter;

public class IntegerSerializer implements Serializer<Integer> {

    @Override
    public Class<Integer> handleClass() {
        return Integer.class;
    }

    @Override
    public String format() {
        return "integer";
    }

    @Override
    public Integer deserialize(SerializerReader reader) throws SerializerException {
        return Integer.valueOf(reader.readInt());
    }

    @Override
    public void serialize(SerializerWriter writer, Integer object) throws SerializerException {
        writer.writeInt(object.intValue());
    }

}
