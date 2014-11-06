package fr.herman.wizz.serializer.datetime

import static java.time.LocalTime.of
import java.time.LocalTime
import spock.lang.Specification
import spock.lang.Unroll
import fr.herman.wizz.exception.SerializerException
import fr.herman.wizz.io.SerializerReader
import fr.herman.wizz.io.SerializerWriter

class LocalTimeSerializerSpec extends Specification{

    SerializerReader reader = Mock(SerializerReader)
    SerializerWriter writer = Mock(SerializerWriter)

    @Unroll
    def "serialize Object #input with format #format to String #output"(){
        0* writer._
        1* writer.writeString(output)
        expect:
        new LocalTimeSerializer(format).serialize(writer,input)


        where:
        input           |format         ||output
        of(01, 35, 12)  |null           ||'013512'
        of(01, 35, 12)  |'HHmmss'       ||'013512'
        of(01, 35, 12)  |'HH mm ss'     ||'01 35 12'
        of(01, 35, 12)  |'HH:mm:ss'     ||'01:35:12'
    }

    @Unroll
    def "deserialize String #input from format #format to Object #output"(){
        0* reader._
        1* reader.readString() >> input
        expect:
        new LocalTimeSerializer(format).deserialize(reader) == output
        where:
        input           |format         ||output
        '013512'        |null           ||of(01, 35, 12)
        '013512'        |'HHmmss'       ||of(01, 35, 12)
        '01 35 12'      |'HH mm ss'     ||of(01, 35, 12)
        '01:35:12'      |'HH:mm:ss'     ||of(01, 35, 12)
    }

    def "default format is HHmmss"(){
        when:
        def converter = new LocalTimeSerializer()
        then:
        converter.format()=='HHmmss'
    }

    def "throw ConversionException when parsing fail"(){
        given:
        def converter = new LocalTimeSerializer()
        reader.readString() >> 'not a time'
        when:
        converter.deserialize(reader)
        then:
        thrown SerializerException
    }

    def "handle LocalTime class"(){
        when:
        def converter = new LocalTimeSerializer()
        then:
        converter.handleClass()==LocalTime
    }
}
