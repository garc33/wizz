package fr.herman.wizz.mapping;

import java.util.ArrayList;
import java.util.List;

import fr.herman.wizz.Serializer;

public class ObjectMappingBuilder<O> {
    private List<Mapping<O, ?>> mappings = new ArrayList<>();

    private ObjectFactory<O> factory;

    public ObjectMappingBuilder(ObjectFactory<O> factory) {
        this.factory = factory;
    }

    public <V> ObjectMappingBuilder<O> map(Input<O, V> input, Output<O, V> output, Serializer<V> serializer) {
        mappings.add(new Mapping<>(input, output, serializer));
        return this;
    }

    public <V, IO extends Input<O, V> & Output<O, V>> ObjectMappingBuilder<O> map(IO accessor, Serializer<V> serializer) {
        return map(accessor, accessor, serializer);
    }

    public ObjectMapping<O> build() {
        return new ObjectMapping<>(new ArrayList<>(mappings), factory);
    }
}
