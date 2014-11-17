package fr.herman.wizz.io.strings

import spock.lang.Specification
import spock.lang.Unroll

class NumberSpec extends Specification{

    @Unroll
    def "readwrite int #number"(){
        given:
        char[] buf = new char[NumberOutput.INT_MIN_BUFFER_SIZE]

        expect:
        int length = NumberOutput.outputInt(number, buf, 0)
        NumberInput.parseInt(buf, 0, length) == number

        where:
        number << [
            0,
            1,
            1985,
            -123456789,
            Integer.MAX_VALUE-1,
            Integer.MIN_VALUE+1,
            Integer.MAX_VALUE,
            Integer.MIN_VALUE
        ]
    }

    @Unroll
    def "readwrite long #number"(){
        given:
        char[] buf = new char[NumberOutput.LONG_MIN_BUFFER_SIZE]

        expect:
        int length = NumberOutput.outputLong(number, buf, 0)
        println length
        NumberInput.parseLong(buf, 0, length) == number

        where:
        number << [
            0,
            1,
            1985,
            -123456789,
            123456789012345678,
            -123456789012345678,
            Long.MAX_VALUE-1,
            Long.MIN_VALUE+1,
            Long.MAX_VALUE,
            Long.MIN_VALUE
        ]
    }
}
