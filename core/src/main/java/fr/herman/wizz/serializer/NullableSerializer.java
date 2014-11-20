package fr.herman.wizz.serializer;

import fr.herman.wizz.Serializer;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.Decoder;
import fr.herman.wizz.io.Encoder;

public class NullableSerializer<T> extends DelegateSerializer<T> {

    public NullableSerializer(Serializer<T> delegate) {
        super(delegate);
    }

    @Override
    public T deserialize(Decoder reader) throws SerializerException {

        return reader.readIsNull() ? null : super.deserialize(reader);
    }

    @Override
    public void serialize(Encoder writer, T object) throws SerializerException {
        if (object != null) {
            writer.writeNotNull();
            super.serialize(writer, object);
        } else {
            writer.writeNull();
        }
    }

}
