package fr.herman.wizz.mapping;

import java.util.List;

import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.Decoder;
import fr.herman.wizz.io.Encoder;

public class ObjectMapping<O> {

    private final List<Mapping<O, ?>> mappings;
    private final ObjectFactory<O> factory;

    public ObjectMapping(List<Mapping<O, ?>> mappings, ObjectFactory<O> factory) {
        this.mappings = mappings;
        this.factory = factory;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void write(Encoder encoder, O object) throws SerializerException {
        encoder.writeObject((List) mappings, object);
    }

    public void read(Decoder decoder, O object) throws SerializerException {
        for (Mapping<O, ?> mapping : mappings) {
            mapping.read(decoder, object);
        }
    }

    public O read(Decoder decoder) throws SerializerException {
        if (factory == null) {
            throw new SerializerException("no factory to create object");
        }
        O object = factory.create();
        read(decoder, object);
        return object;
    }
}
