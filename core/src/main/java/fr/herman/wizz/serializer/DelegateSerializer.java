package fr.herman.wizz.serializer;

import fr.herman.wizz.Serializer;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.SerializerReader;
import fr.herman.wizz.io.SerializerWriter;

public class DelegateSerializer<T> implements Serializer<T> {

    private final Serializer<T> delegate;

    public DelegateSerializer(Serializer<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public T deserialize(SerializerReader reader) throws SerializerException {
        return delegate.deserialize(reader);
    }

    @Override
    public void serialize(SerializerWriter writer, T object) throws SerializerException {
        delegate.serialize(writer, object);
    }

    @Override
    public Class<T> handleClass() {
        return delegate.handleClass();
    }

    @Override
    public String format() {
        return delegate.format();
    }

}
