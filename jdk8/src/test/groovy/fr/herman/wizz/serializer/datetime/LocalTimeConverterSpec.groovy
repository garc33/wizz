package fr.herman.wizz.serializer.datetime

import static java.time.LocalTime.of

import java.time.LocalTime

import spock.lang.Specification
import spock.lang.Unroll
import fr.herman.wizz.exception.SerializerException

class LocalTimeConverterSpec extends Specification{

    @Unroll
    def "convert Object #input with format #format to String #output"(){
        expect:
        new LocalTimeConverter(format).to(input) == output

        where:
        input           |format         ||output
        of(01, 35, 12)  |null           ||'013512'
        of(01, 35, 12)  |'HHmmss'       ||'013512'
        of(01, 35, 12)  |'HH mm ss'     ||'01 35 12'
        of(01, 35, 12)  |'HH:mm:ss'     ||'01:35:12'
    }

    @Unroll
    def "convert String #input from format #format to Object #output"(){
        expect:
        new LocalTimeConverter(format).from(input) == output

        where:
        input           |format         ||output
        '013512'        |null           ||of(01, 35, 12)
        '013512'        |'HHmmss'       ||of(01, 35, 12)
        '01 35 12'      |'HH mm ss'     ||of(01, 35, 12)
        '01:35:12'      |'HH:mm:ss'     ||of(01, 35, 12)
    }

    def "default format is HHmmss"(){
        when:
        def converter = new LocalTimeConverter()
        then:
        converter.format()=='HHmmss'
    }

    def "throw ConversionException when parsing fail"(){
        given:
        def converter = new LocalTimeConverter()
        when:
        converter.from('not a time')
        then:
        thrown SerializerException
    }

    def "handle LocalTime class"(){
        when:
        def converter = new LocalTimeConverter()
        then:
        converter.handleClass()==LocalTime
    }
}
