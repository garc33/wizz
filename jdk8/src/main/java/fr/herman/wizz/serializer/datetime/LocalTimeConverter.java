package fr.herman.wizz.serializer.datetime;

import java.time.LocalTime;
import java.util.Optional;

public class LocalTimeConverter extends TemporalAccessorConverter<LocalTime> {

    public LocalTimeConverter() {
        this(null);
    }

    public LocalTimeConverter(String pattern) {
        super(Optional.ofNullable(pattern).orElse("HHmmss"), LocalTime::from);
    }

    @Override
    public Class<LocalTime> handleClass() {
        return LocalTime.class;
    }

}
