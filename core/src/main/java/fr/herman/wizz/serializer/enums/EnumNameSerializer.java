package fr.herman.wizz.serializer.enums;

import java.util.ArrayList;
import java.util.List;
import fr.herman.wizz.Serializer;
import fr.herman.wizz.WithFormat;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.Decoder;
import fr.herman.wizz.io.Encoder;
import fr.herman.wizz.utils.Strings;

public class EnumNameSerializer<E extends Enum<E>> implements Serializer<E>, WithFormat
{

    private final Class<E> clazz;
    private final String   format;

    public EnumNameSerializer(Class<E> clazz) {
        this.clazz = clazz;
        this.format = initFormat(clazz.getEnumConstants());
    }

    private String initFormat(E[] enumerations)
    {
        List<String> names = new ArrayList<>(enumerations.length);
        for (E e : enumerations)
        {
            names.add(e.name());
        }
        return Strings.join(names);
    }

    @Override
    public E deserialize(Decoder reader) throws SerializerException {
        String s = reader.readString();
        try {
            return Enum.valueOf(clazz, s);
        } catch (Exception e) {
            throw new SerializerException(String.format("%s is not one of values %s", s, format()));
        }
    }

    @Override
    public void serialize(Encoder writer, E object) throws SerializerException {
        writer.writeString(object.name());
    }

    @Override
    public Class<E> handleClass() {
        return clazz;
    }

    @Override
    public String format() {
        return format;
    }

}
