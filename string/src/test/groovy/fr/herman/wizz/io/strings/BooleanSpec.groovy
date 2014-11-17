package fr.herman.wizz.io.strings

import spock.lang.Specification
import spock.lang.Unroll

class BooleanSpec extends Specification{

    @Unroll
    def "readwrite boolean #b"(){
        given:
        char[] buffer = new char[BooleanOutput.MIN_BUFFER_SIZE]
        expect:
        def length = BooleanOutput.outputBoolean(b, buffer, 0)
        BooleanOutput.inputBoolean(buffer, 0, length) == b
        where:
        b << [true, false]
    }

    def "write true"(){
        given:
        char[] buffer = new char[4]
        when:
        BooleanOutput.outputBoolean(true, buffer, 0)
        then:
        String.valueOf(buffer, 0, 4)=='true'
    }

    def "write false"(){
        given:
        char[] buffer = new char[5]
        when:
        BooleanOutput.outputBoolean(false, buffer, 0)
        then:
        String.valueOf(buffer, 0, 5)=='false'
    }
}
