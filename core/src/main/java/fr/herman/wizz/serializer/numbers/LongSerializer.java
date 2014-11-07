package fr.herman.wizz.serializer.numbers;

import fr.herman.wizz.Serializer;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.SerializerReader;
import fr.herman.wizz.io.SerializerWriter;

public class LongSerializer implements Serializer<Long> {

    @Override
    public Class<Long> handleClass() {
        return Long.class;
    }

    @Override
    public Long deserialize(SerializerReader reader) throws SerializerException {
        return Long.valueOf(reader.readLong());
    }

    @Override
    public void serialize(SerializerWriter writer, Long object) throws SerializerException {
        writer.writeLong(object.longValue());
    }

}
