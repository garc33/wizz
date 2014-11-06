package fr.herman.wizz.serializer.datetime;

import java.time.LocalDateTime;
import java.util.Optional;

public class LocalDateTimeSerializer extends TemporalAccessorSerializer<LocalDateTime> {

    public LocalDateTimeSerializer() {
        this(null);
    }

    public LocalDateTimeSerializer(String pattern) {
        super(Optional.ofNullable(pattern).orElse("yyyyMMdd HHmmss"), LocalDateTime::from);
    }

    @Override
    public Class<LocalDateTime> handleClass() {
        return LocalDateTime.class;
    }
}
