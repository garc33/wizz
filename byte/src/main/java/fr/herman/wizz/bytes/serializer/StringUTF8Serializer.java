package fr.herman.wizz.bytes.serializer;

import fr.herman.wizz.Serializer;
import fr.herman.wizz.bytes.ByteEncoder;
import fr.herman.wizz.bytes.charset.UTF8;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.Decoder;
import fr.herman.wizz.io.Encoder;

public class StringUTF8Serializer implements Serializer<String> {

    private static final UTF8 UTF8 = new UTF8();

    @Override
    public String deserialize(Decoder reader) throws SerializerException {
        return null;
    }

    @Override
    public void serialize(Encoder writer, String object) throws SerializerException {
        ((ByteEncoder) writer).writeString(object, UTF8);
    }

    @Override
    public Class<String> handleClass() {
        return String.class;
    }

}
