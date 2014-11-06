package fr.herman.wizz.serializer.datetime;

import static java.util.Optional.ofNullable;

import java.time.LocalDate;

public class LocalDateSerializer extends TemporalAccessorSerializer<LocalDate> {

    public LocalDateSerializer() {
        this(null);
    }

    public LocalDateSerializer(String pattern) {
        super(ofNullable(pattern).orElse("yyyyMMdd"), LocalDate::from);
    }

    @Override
    public Class<LocalDate> handleClass() {
        return LocalDate.class;
    }

}
