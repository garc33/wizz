package fr.herman.wizz.serializer.numbers;

import fr.herman.wizz.Serializer;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.Decoder;
import fr.herman.wizz.io.Encoder;

public class DoubleSerializer implements Serializer<Double> {

    @Override
    public Class<Double> handleClass() {
        return Double.class;
    }

    @Override
    public Double deserialize(Decoder reader) throws SerializerException {
        return Double.valueOf(reader.readDouble());
    }

    @Override
    public void serialize(Encoder writer, Double object) throws SerializerException {
        writer.writeDouble(object.doubleValue());
    }

}
