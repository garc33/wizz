package fr.herman.wizz.serializer.datetime

import static java.time.LocalDate.of

import java.time.LocalDate

import spock.lang.Specification
import spock.lang.Unroll
import fr.herman.wizz.exception.SerializerException

class LocalDateConverterSpec extends Specification{
    @Unroll
    def "convert Object #input with format #format to String #output"(){
        expect:
        new LocalDateConverter(format).to(input) == output

        where:
        input           |format         ||output
        of(1985, 01, 23)|null           ||'19850123'
        of(1985, 01, 23)|'yyyyMMdd'     ||'19850123'
        of(1985, 01, 23)|'yyyy MM dd'   ||'1985 01 23'
        of(1985, 01, 23)|'dd MM yyyy'   ||'23 01 1985'
    }

    @Unroll
    def "convert String #input from format #format to Object #output"(){
        expect:
        new LocalDateConverter(format).from(input) == output

        where:
        input           |format         ||output
        '19850123'      |null           ||of(1985, 01, 23)
        '19850123'      |'yyyyMMdd'     ||of(1985, 01, 23)
        '1985 01 23'    |'yyyy MM dd'   ||of(1985, 01, 23)
        '23 01 1985'    |'dd MM yyyy'   ||of(1985, 01, 23)
    }

    def "default format is yyyyMMdd"(){
        when:
        def converter = new LocalDateConverter()
        then:
        converter.format()=='yyyyMMdd'
    }

    def "throw ConversionException when parsing fail"(){
        given:
        def converter = new LocalDateConverter()
        when:
        converter.from('not a date')
        then:
        thrown SerializerException
    }

    def "handle LocalDate class"(){
        when:
        def converter = new LocalDateConverter()
        then:
        converter.handleClass()==LocalDate
    }
}
