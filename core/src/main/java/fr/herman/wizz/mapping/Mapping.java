package fr.herman.wizz.mapping;

import fr.herman.wizz.Serializer;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.Decoder;
import fr.herman.wizz.io.Encoder;

public class Mapping<O, V> implements InputMapping<O>, OutputMapping<O> {

    private final Output<O, V> output;

    private final Input<O, V> input;

    private final Serializer<V> serializer;

    public Mapping(Input<O, V> input, Output<O, V> output, Serializer<V> serializer) {
        this.input = input;
        this.output = output;
        this.serializer = serializer;
    }

    @Override
    public void read(Decoder decoder, O dest) throws SerializerException {
        input.setValue(dest, serializer.deserialize(decoder));
    }

    @Override
    public void write(Encoder encoder, O source) throws SerializerException {
        serializer.serialize(encoder, output.getValue(source));
    }
}
