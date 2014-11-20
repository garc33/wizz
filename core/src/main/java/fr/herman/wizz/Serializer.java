package fr.herman.wizz;

import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.Decoder;
import fr.herman.wizz.io.Encoder;

public interface Serializer<TYPE> {
    TYPE deserialize(Decoder reader) throws SerializerException;

    void serialize(Encoder writer, TYPE object) throws SerializerException;

    Class<TYPE> handleClass();
}
