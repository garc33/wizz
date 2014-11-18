package fr.herman.wizz.string.csv

import spock.lang.Specification

class CsvWriterSpec extends Specification{
    StringWriter sw = new StringWriter()
    CsvWriter writer = new CsvWriter(sw, ',' as char, '\n' as char)

    def "write separator"(){
        when:
        writer.writeSeparator()
        writer.flush()
        then:
        sw.toString() == ','
    }

    def "write new line"(){
        when:
        writer.writeNewLine()
        writer.flush()
        then:
        sw.toString() == '\n'
    }

    def "write int"(){
        when:
        writer.writeInt(2014)
        writer.writeSeparator()
        writer.writeInt(-15)
        writer.flush()
        then:
        sw.toString() == '2014,-15'
    }

    def "write short"(){
        when:
        writer.writeShort(2014 as short)
        writer.writeSeparator()
        writer.writeShort(-15 as short)
        writer.flush()
        then:
        sw.toString() == '2014,-15'
    }

    def "write long"(){
        when:
        writer.writeLong(2014)
        writer.writeSeparator()
        writer.writeLong(-15000000000000)
        writer.flush()
        then:
        sw.toString() == '2014,-15000000000000'
    }

    def "write double"(){
        when:
        writer.writeDouble(2014.75)
        writer.writeSeparator()
        writer.writeDouble(-150000.25)
        writer.flush()
        then:
        sw.toString() == '2014.75,-150000.25'
    }

    def "write float"(){
        when:
        writer.writeFloat(2014.75)
        writer.writeSeparator()
        writer.writeFloat(-150000.25)
        writer.flush()
        then:
        sw.toString() == '2014.75,-150000.25'
    }

    def "write boolean"(){
        when:
        writer.writeBoolean(true)
        writer.writeSeparator()
        writer.writeBoolean(false)
        writer.flush()
        then:
        sw.toString() == 'true,false'
    }

    def "write byte"(){
        when:
        writer.writeByte(0 as byte)
        writer.writeSeparator()
        writer.writeByte(1 as byte)
        writer.flush()
        then:
        sw.toString() == '0,1'
    }

    def "write char"(){
        when:
        writer.writeChar('a' as char)
        writer.writeSeparator()
        writer.writeChar('Z' as char)
        writer.flush()
        then:
        sw.toString() == 'a,Z'
    }

    def "write string"(){
        when:
        writer.writeString('Hello')
        writer.writeSeparator()
        writer.writeString('World')
        writer.flush()
        then:
        sw.toString() == 'Hello,World'
    }

    def "write string > to buffer size"(){
        given:
        Writer w = Mock(Writer)
        CsvWriter cw = new CsvWriter(w, ',' as char, '\n' as char,3)
        when:
        cw.writeString('Hello')
        cw.flush()
        then:
        1* w.write(['H', 'e', 'l'], 0, 3)
        1* w.write(['l', 'o', 'l'], 0, 2)
        2* w.flush()
    }

    def "auto flush when hit buffer size"(){
        given:
        Writer w = Mock(Writer)
        CsvWriter cw = new CsvWriter(w, ',' as char, '\n' as char,5)
        when:
        cw.writeChar('B' as char)
        cw.writeBoolean(true)
        cw.flush()
        then:
        1* w.write(_, 0, 1)
        1* w.write(_, 0, 4)
        2* w.flush()
    }
}
