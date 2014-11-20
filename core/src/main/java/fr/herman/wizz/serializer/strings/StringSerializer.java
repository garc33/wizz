package fr.herman.wizz.serializer.strings;

import fr.herman.wizz.Serializer;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.Decoder;
import fr.herman.wizz.io.Encoder;

public class StringSerializer implements Serializer<String> {

    @Override
    public String deserialize(Decoder reader) throws SerializerException {
        return reader.readString();
    }

    @Override
    public void serialize(Encoder writer, String object) throws SerializerException {
        writer.writeString(object);
    }

    @Override
    public Class<String> handleClass() {
        return String.class;
    }
}
