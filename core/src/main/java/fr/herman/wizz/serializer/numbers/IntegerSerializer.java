package fr.herman.wizz.serializer.numbers;

import fr.herman.wizz.Serializer;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.Decoder;
import fr.herman.wizz.io.Encoder;

public class IntegerSerializer implements Serializer<Integer> {

    @Override
    public Class<Integer> handleClass() {
        return Integer.class;
    }


    @Override
    public Integer deserialize(Decoder reader) throws SerializerException {
        return Integer.valueOf(reader.readInt());
    }

    @Override
    public void serialize(Encoder writer, Integer object) throws SerializerException {
        writer.writeInt(object.intValue());
    }

}
