package fr.herman.wizz.serializer.dates;

import java.util.Date;
import fr.herman.wizz.Serializer;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.Decoder;
import fr.herman.wizz.io.Encoder;

public class TimeStampSerializer implements Serializer<Date>
{

    @Override
    public Date deserialize(Decoder reader) throws SerializerException
    {
        return new Date(reader.readLong());
    }

    @Override
    public void serialize(Encoder writer, Date object) throws SerializerException
    {
        writer.writeLong(object.getTime());
    }

    @Override
    public Class<Date> handleClass()
    {
        return Date.class;
    }
}
