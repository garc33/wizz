package fr.herman.wizz.serializer.datetime;

import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;
import fr.herman.wizz.Serializer;
import fr.herman.wizz.WithFormat;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.SerializerReader;
import fr.herman.wizz.io.SerializerWriter;

public abstract class TemporalAccessorSerializer<T extends TemporalAccessor> implements Serializer<T>, WithFormat
{

    private final String pattern;

    private final TemporalQuery<T> query;
    private final DateTimeFormatter formatter;

    public TemporalAccessorSerializer(String pattern, TemporalQuery<T> query) {
        this.pattern = pattern;
        this.query = query;
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public T deserialize(SerializerReader reader) throws SerializerException {
        try {
            return formatter.parse(reader.readString(), query);
        } catch (DateTimeParseException e) {
            throw new SerializerException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public void serialize(SerializerWriter writer, T object) throws SerializerException {
        try {
            writer.writeString(formatter.format(object));
        } catch (DateTimeException e) {
            throw new SerializerException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public String format() {
        return pattern;
    }

}
