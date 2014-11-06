package fr.herman.wizz.serializer.datetime

import static java.time.LocalDateTime.of

import java.time.LocalDateTime

import spock.lang.Specification
import spock.lang.Unroll
import fr.herman.wizz.exception.SerializerException

class LocalDateTimeConverterSpec extends Specification{
    @Unroll
    def "convert Object #input with format #format to String #output"(){
        expect:
        new LocalDateTimeConverter(format).to(input) == output

        where:
        input           |format                         ||output
        of(1985, 01, 23,01,35,12)|null                  ||'19850123 013512'
        of(1985, 01, 23,01,35,12)|'yyyyMMddHHmmss'      ||'19850123013512'
        of(1985, 01, 23,01,35,12)|'yyyyMMdd HHmmss'     ||'19850123 013512'
        of(1985, 01, 23,01,35,12)|'yyyy-MM-dd HH:mm:ss' ||'1985-01-23 01:35:12'
        of(1985, 01, 23,01,35,12)|'dd MM yyyy HH:mm:ss' ||'23 01 1985 01:35:12'
    }

    @Unroll
    def "convert String #input from format #format to Object #output"(){
        expect:
        new LocalDateTimeConverter(format).from(input) == output

        where:
        input                   |format                ||output
        '19850123 013512'       |null                  ||of(1985, 01, 23,01,35,12)
        '19850123013512'        |'yyyyMMddHHmmss'      ||of(1985, 01, 23,01,35,12)
        '19850123 013512'       |'yyyyMMdd HHmmss'     ||of(1985, 01, 23,01,35,12)
        '1985-01-23 01:35:12'   |'yyyy-MM-dd HH:mm:ss' ||of(1985, 01, 23,01,35,12)
        '23 01 1985 01:35:12'   |'dd MM yyyy HH:mm:ss' ||of(1985, 01, 23,01,35,12)
    }

    def "default format is yyyyMMdd"(){
        when:
        def converter = new LocalDateTimeConverter()
        then:
        converter.format()=='yyyyMMdd HHmmss'
    }

    def "throw ConversionException when parsing fail"(){
        given:
        def converter = new LocalDateTimeConverter()
        when:
        converter.from('not a datetime')
        then:
        thrown SerializerException
    }

    def "handle LocalDate class"(){
        when:
        def converter = new LocalDateTimeConverter()
        then:
        converter.handleClass()==LocalDateTime
    }
}
