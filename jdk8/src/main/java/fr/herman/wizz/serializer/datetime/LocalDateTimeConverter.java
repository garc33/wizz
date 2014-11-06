package fr.herman.wizz.serializer.datetime;

import java.time.LocalDateTime;
import java.util.Optional;

public class LocalDateTimeConverter extends TemporalAccessorConverter<LocalDateTime> {

    public LocalDateTimeConverter() {
        this(null);
    }

    public LocalDateTimeConverter(String pattern) {
        super(Optional.ofNullable(pattern).orElse("yyyyMMdd HHmmss"), LocalDateTime::from);
    }

    @Override
    public Class<LocalDateTime> handleClass() {
        return LocalDateTime.class;
    }
}
