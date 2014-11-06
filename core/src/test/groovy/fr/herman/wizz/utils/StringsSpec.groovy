package fr.herman.wizz.utils

import spock.lang.Specification
import fr.herman.wizz.utils.Strings

class StringsSpec extends Specification{

    def "join strings"(){
        expect:
        Strings.join(input) == output
        where:
        input                   ||output
        []||''
        ['hello']||'hello'
        ['hello', 'world']||'hello,world'
    }

    def "join iterator"(){
        expect:
        Strings.join(['hello', 'world'].iterator()) == 'hello,world'
    }
}
