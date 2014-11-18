package fr.herman.wizz.string

import fr.herman.wizz.string.ByteOutput;
import spock.lang.Specification

class ByteSpec extends Specification {

    def "write 1"(){
        given:
        char[] buf = new char[1]
        when:
        ByteOutput.outputByte(1 as byte, buf, 0)
        then:
        String.valueOf(buf) == '1'
    }

    def "write 0"(){
        given:
        char[] buf = new char[1]
        when:
        ByteOutput.outputByte(0 as byte, buf, 0)
        then:
        String.valueOf(buf) == '0'
    }

    def "read 1"(){
        given:
        char[] buf = new char[1]
        when:
        buf[0] = '1' as char
        then:
        ByteOutput.inputByte(buf, 0) == 1 as byte
    }

    def "read 0"(){
        given:
        char[] buf = new char[1]
        when:
        buf[0] = '0' as char
        then:
        ByteOutput.inputByte(buf, 0) == 0 as byte
    }
}
