package fr.herman.wizz.serializer.characters;

import fr.herman.wizz.Serializer;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.SerializerReader;
import fr.herman.wizz.io.SerializerWriter;

public class CharacterSerializer implements Serializer<Character> {

    @Override
    public Character deserialize(SerializerReader reader) throws SerializerException {
        return Character.valueOf(reader.readChar());
    }

    @Override
    public void serialize(SerializerWriter writer, Character object) throws SerializerException {
        writer.writeChar(object.charValue());
    }

    @Override
    public Class<Character> handleClass() {
        return Character.class;
    }

    @Override
    public String format() {
        return "character";
    }

}
