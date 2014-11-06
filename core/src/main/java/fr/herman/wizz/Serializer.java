package fr.herman.wizz;

import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.SerializerReader;
import fr.herman.wizz.io.SerializerWriter;

public interface Serializer<TYPE> {
    TYPE deserialize(SerializerReader reader) throws SerializerException;

    void serialize(SerializerWriter writer, TYPE object) throws SerializerException;

    Class<TYPE> handleClass();

    String format();
}
