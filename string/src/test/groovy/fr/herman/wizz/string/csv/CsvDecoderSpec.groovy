package fr.herman.wizz.string.csv

import spock.lang.Specification

class CsvDecoderSpec extends Specification {
    def "read int"(){
        given:
        def input = '123456,123,12\n34\n'
        when:
        def reader = new CsvDecoder(new StringReader(input), ',' as char,'\n' as char,7)
        then:
        reader.readInt() == 123456
        reader.readInt() == 123
        reader.readInt() == 12
        reader.readInt() == 34
    }

    def "read string"(){
        given:
        def input = 'salut,Hello World!!!,ola\n'
        when:
        def reader = new CsvDecoder(new StringReader(input), ',' as char,'\n' as char,7)
        then:
        reader.readString() == 'salut'
        reader.readString() == 'Hello World!!!'
        reader.readString() == 'ola'
    }
}
