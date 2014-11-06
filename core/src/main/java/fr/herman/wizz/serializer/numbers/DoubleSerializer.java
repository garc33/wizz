package fr.herman.wizz.serializer.numbers;

import fr.herman.wizz.Serializer;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.SerializerReader;
import fr.herman.wizz.io.SerializerWriter;

public class DoubleSerializer implements Serializer<Double> {

    @Override
    public Class<Double> handleClass() {
        return Double.class;
    }

    @Override
    public String format() {
        return "double";
    }

    @Override
    public Double deserialize(SerializerReader reader) throws SerializerException {
        return Double.valueOf(reader.readDouble());
    }

    @Override
    public void serialize(SerializerWriter writer, Double object) throws SerializerException {
        writer.writeDouble(object.doubleValue());
    }

}
