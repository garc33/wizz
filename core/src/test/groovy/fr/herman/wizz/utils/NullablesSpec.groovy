package fr.herman.wizz.utils

import spock.lang.Specification

class NullablesSpec extends Specification {

    def "return input value when input is not null"(){
        expect:
        Nullables.defaultValue('expected', 'default') == 'expected'
    }

    def "return the default value when input is null"(){
        expect:
        Nullables.defaultValue(null, 'default') == 'default'
    }
}
