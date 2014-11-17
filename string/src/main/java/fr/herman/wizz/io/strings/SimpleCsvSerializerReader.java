package fr.herman.wizz.io.strings;

import java.io.Reader;

import fr.herman.wizz.exception.SerializerException;

public class SimpleCsvSerializerReader extends AbstractStringSerializerReader {

    private final char separator;

    public SimpleCsvSerializerReader(Reader reader, char separator) {
        super(reader);
        this.separator = separator;
    }

    @Override
    protected int tokenLength(char[] buffer, int offset, int count, int required) throws SerializerException {
        int capacity = offset + count;
        int i = offset;
        while (i < capacity) {
            // FIXME handle EOL
            if (separator == buffer[i]) {
                return i - offset - 1;
            }
            i++;
        }
        // FIXME handle EOF
        if (required > 0) {
            throw new SerializerException("token too long");
        }
        return -1;
    }

}
