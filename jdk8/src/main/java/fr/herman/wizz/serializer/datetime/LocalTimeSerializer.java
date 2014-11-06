package fr.herman.wizz.serializer.datetime;

import java.time.LocalTime;
import java.util.Optional;

public class LocalTimeSerializer extends TemporalAccessorSerializer<LocalTime> {

    public LocalTimeSerializer() {
        this(null);
    }

    public LocalTimeSerializer(String pattern) {
        super(Optional.ofNullable(pattern).orElse("HHmmss"), LocalTime::from);
    }

    @Override
    public Class<LocalTime> handleClass() {
        return LocalTime.class;
    }

}
