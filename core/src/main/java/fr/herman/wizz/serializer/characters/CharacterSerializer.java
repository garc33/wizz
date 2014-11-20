package fr.herman.wizz.serializer.characters;

import fr.herman.wizz.Serializer;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.Decoder;
import fr.herman.wizz.io.Encoder;

public class CharacterSerializer implements Serializer<Character> {

    @Override
    public Character deserialize(Decoder reader) throws SerializerException {
        return Character.valueOf(reader.readChar());
    }

    @Override
    public void serialize(Encoder writer, Character object) throws SerializerException {
        writer.writeChar(object.charValue());
    }

    @Override
    public Class<Character> handleClass() {
        return Character.class;
    }
}
