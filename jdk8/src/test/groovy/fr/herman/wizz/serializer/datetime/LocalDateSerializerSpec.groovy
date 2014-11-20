package fr.herman.wizz.serializer.datetime

import static java.time.LocalDate.of
import java.time.LocalDate
import spock.lang.Specification
import spock.lang.Unroll
import fr.herman.wizz.exception.SerializerException
import fr.herman.wizz.io.Decoder
import fr.herman.wizz.io.Encoder

class LocalDateSerializerSpec extends Specification{

    Decoder reader = Mock(Decoder)
    Encoder writer = Mock(Encoder)

    @Unroll
    def "convert Object #input with format #format to String #output"(){
        0* writer._
        1* writer.writeString(output)
        expect:
        new LocalDateSerializer(format).serialize(writer,input)

        where:
        input           |format         ||output
        of(1985, 01, 23)|null           ||'19850123'
        of(1985, 01, 23)|'yyyyMMdd'     ||'19850123'
        of(1985, 01, 23)|'yyyy MM dd'   ||'1985 01 23'
        of(1985, 01, 23)|'dd MM yyyy'   ||'23 01 1985'
    }

    @Unroll
    def "convert String #input from format #format to Object #output"(){
        0* reader._
        1* reader.readString() >> input
        expect:
        new LocalDateSerializer(format).deserialize(reader) == output

        where:
        input           |format         ||output
        '19850123'      |null           ||of(1985, 01, 23)
        '19850123'      |'yyyyMMdd'     ||of(1985, 01, 23)
        '1985 01 23'    |'yyyy MM dd'   ||of(1985, 01, 23)
        '23 01 1985'    |'dd MM yyyy'   ||of(1985, 01, 23)
    }

    def "default format is yyyyMMdd"(){
        when:
        def converter = new LocalDateSerializer()
        then:
        converter.format()=='yyyyMMdd'
    }

    def "throw ConversionException when parsing fail"(){
        given:
        def converter = new LocalDateSerializer()
        reader.readString() >> 'not a date'
        when:
        converter.deserialize(reader)
        then:
        thrown SerializerException
    }

    def "handle LocalDate class"(){
        when:
        def converter = new LocalDateSerializer()
        then:
        converter.handleClass()==LocalDate
    }
}
