package fr.herman.wizz.serializer.datetime;

import static java.util.Optional.ofNullable;

import java.time.LocalDate;

public class LocalDateConverter extends TemporalAccessorConverter<LocalDate> {

    public LocalDateConverter() {
        this(null);
    }

    public LocalDateConverter(String pattern) {
        super(ofNullable(pattern).orElse("yyyyMMdd"), LocalDate::from);
    }

    @Override
    public Class<LocalDate> handleClass() {
        return LocalDate.class;
    }

}
