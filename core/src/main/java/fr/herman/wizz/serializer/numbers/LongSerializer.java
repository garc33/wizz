package fr.herman.wizz.serializer.numbers;

import fr.herman.wizz.Serializer;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.Decoder;
import fr.herman.wizz.io.Encoder;

public class LongSerializer implements Serializer<Long> {

    @Override
    public Class<Long> handleClass() {
        return Long.class;
    }

    @Override
    public Long deserialize(Decoder reader) throws SerializerException {
        return Long.valueOf(reader.readLong());
    }

    @Override
    public void serialize(Encoder writer, Long object) throws SerializerException {
        writer.writeLong(object.longValue());
    }

}
