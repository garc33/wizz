package fr.herman.wizz.serializer.dates;

import java.util.Date;
import fr.herman.wizz.Serializer;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.SerializerReader;
import fr.herman.wizz.io.SerializerWriter;

public class TimeStampSerializer implements Serializer<Date>
{

    @Override
    public Date deserialize(SerializerReader reader) throws SerializerException
    {
        return new Date(reader.readLong());
    }

    @Override
    public void serialize(SerializerWriter writer, Date object) throws SerializerException
    {
        writer.writeLong(object.getTime());
    }

    @Override
    public Class<Date> handleClass()
    {
        return Date.class;
    }

    @Override
    public String format()
    {
        return "timestamp";
    }

}
