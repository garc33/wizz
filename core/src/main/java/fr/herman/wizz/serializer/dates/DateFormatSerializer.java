package fr.herman.wizz.serializer.dates;

import static fr.herman.wizz.utils.Nullables.defaultValue;
import java.text.SimpleDateFormat;
import java.util.Date;
import fr.herman.wizz.Serializer;
import fr.herman.wizz.WithFormat;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.SerializerReader;
import fr.herman.wizz.io.SerializerWriter;

public class DateFormatSerializer implements Serializer<Date>, WithFormat
{

    private final String pattern;

    public DateFormatSerializer(String pattern) {
        this.pattern = defaultValue(pattern, "yyyyMMdd HHmmss");
    }

    @Override
    public Date deserialize(SerializerReader reader) throws SerializerException {
        String s = reader.readString();
        try {
            return new SimpleDateFormat(pattern).parse(s);
        } catch (Exception e) {
            throw new SerializerException(String.format("Cannot parse date %s with pattern %s", s, pattern), e);
        }
    }

    @Override
    public void serialize(SerializerWriter writer, Date object) throws SerializerException {
        try {
            String formatedDate = new SimpleDateFormat(pattern).format(object);
            writer.writeString(formatedDate);
        } catch (Exception e) {
            throw new SerializerException(String.format("Cannot format date %s with pattern %s", object, pattern), e);
        }
    }

    @Override
    public Class<Date> handleClass() {
        return Date.class;
    }

    @Override
    public String format() {
        return pattern;
    }

}
