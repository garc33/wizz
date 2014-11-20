package fr.herman.wizz.serializer.strings

import spock.lang.Specification
import fr.herman.wizz.io.Decoder
import fr.herman.wizz.io.Encoder

class StringSerializerSpec extends Specification {

    Decoder reader = Mock(Decoder)
    Encoder writer = Mock(Encoder)
    StringSerializer serializer = new StringSerializer()

    def "write a String"(){
        when:
        serializer.serialize(writer, 'hello world')
        then:
        0* writer._
        1* writer.writeString('hello world')
    }

    def "read a String"(){
        when:
        String result = serializer.deserialize(reader)
        then:
        0* reader._
        1* reader.readString()>>'hello world'
        result=='hello world'
    }

    def "handle String class"(){
        expect:
        serializer.handleClass() == String
    }
}
