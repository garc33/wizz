package fr.herman.wizz.serializer.strings;

import fr.herman.wizz.Serializer;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.SerializerReader;
import fr.herman.wizz.io.SerializerWriter;

public class StringSerializer implements Serializer<String> {

    @Override
    public String deserialize(SerializerReader reader) throws SerializerException {
        return reader.readString();
    }

    @Override
    public void serialize(SerializerWriter writer, String object) throws SerializerException {
        writer.writeString(object);
    }

    @Override
    public Class<String> handleClass() {
        return String.class;
    }
}
