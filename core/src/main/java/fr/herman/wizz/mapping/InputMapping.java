package fr.herman.wizz.mapping;

import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.Decoder;

public interface InputMapping<O> {

    void read(Decoder decoder, O dest) throws SerializerException;
}
