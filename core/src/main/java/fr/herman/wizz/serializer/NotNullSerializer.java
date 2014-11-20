package fr.herman.wizz.serializer;

import fr.herman.wizz.Serializer;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.Decoder;
import fr.herman.wizz.io.Encoder;

public class NotNullSerializer<T> extends DelegateSerializer<T> {

    public NotNullSerializer(Serializer<T> delegate) {
        super(delegate);
    }

    @Override
    public T deserialize(Decoder reader) throws SerializerException {
        T t = super.deserialize(reader);
        if (t == null) {
            throw new SerializerException("Cannot read null value");
        }
        return t;
    }

    @Override
    public void serialize(Encoder writer, T object) throws SerializerException {
        if (object == null) {
            throw new SerializerException("Cannot write null value");
        }
        super.serialize(writer, object);
    }

}
