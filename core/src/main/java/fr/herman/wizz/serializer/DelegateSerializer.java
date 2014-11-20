package fr.herman.wizz.serializer;

import fr.herman.wizz.Serializer;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.Decoder;
import fr.herman.wizz.io.Encoder;

public class DelegateSerializer<T> implements Serializer<T> {

    private final Serializer<T> delegate;

    public DelegateSerializer(Serializer<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public T deserialize(Decoder reader) throws SerializerException {
        return delegate.deserialize(reader);
    }

    @Override
    public void serialize(Encoder writer, T object) throws SerializerException {
        delegate.serialize(writer, object);
    }

    @Override
    public Class<T> handleClass() {
        return delegate.handleClass();
    }
}
