package fr.herman.wizz.string.csv

import fr.herman.wizz.string.csv.CsvReader;
import spock.lang.Specification

class CsvReaderSpec extends Specification {
    def "read int"(){
        given:
        def input = '123456,123,12\n34'
        when:
        def reader = new CsvReader(new StringReader(input), ',' as char)
        then:
        reader.hasNextInt() == true
        reader.readInt() == 123456
        reader.hasNextInt() == true
        reader.readInt() == 123
        reader.hasNextInt() == true
        reader.readInt() == 12
        reader.hasNextInt() == true
        reader.readInt() == 34
        reader.hasNextInt() == false
    }
}
