package fr.herman.wizz.bytes.serializer;

import fr.herman.wizz.Serializer;
import fr.herman.wizz.bytes.ByteEncoder;
import fr.herman.wizz.bytes.charset.CustomCharset;
import fr.herman.wizz.bytes.charset.OneByte;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.Decoder;
import fr.herman.wizz.io.Encoder;

public class StringASCIISerializer implements Serializer<String> {

    private static final CustomCharset CHARSET = new OneByte();

    @Override
    public String deserialize(Decoder reader) throws SerializerException {
        return null;
    }

    @Override
    public void serialize(Encoder writer, String object) throws SerializerException {
        ((ByteEncoder) writer).writeString(object, CHARSET);
    }

    @Override
    public Class<String> handleClass() {
        return String.class;
    }

}
