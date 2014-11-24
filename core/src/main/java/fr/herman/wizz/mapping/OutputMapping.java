package fr.herman.wizz.mapping;

import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.Encoder;

public interface OutputMapping<O> {

    void write(Encoder encoder, O source) throws SerializerException;
}
