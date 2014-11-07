package fr.herman.wizz.serializer.dates

import spock.lang.Specification
import fr.herman.wizz.io.SerializerReader
import fr.herman.wizz.io.SerializerWriter

class TimeStampSerializerSpec extends Specification {
    SerializerReader reader = Mock(SerializerReader)
    SerializerWriter writer = Mock(SerializerWriter)
    TimeStampSerializer serializer = new TimeStampSerializer()

    def "write a timestamp"(){
        when:
        serializer.serialize(writer, new Date(1415293611506L))
        then:
        0* writer._
        1* writer.writeLong(1415293611506L)
    }

    def "read a timestamp"(){
        when:
        Date result = serializer.deserialize(reader)
        then:
        0* reader._
        1* reader.readLong() >> 1415293611506L
        result == new Date(1415293611506L)
    }

    def "handle Date class"(){
        expect:
        serializer.handleClass() == Date
    }
}
