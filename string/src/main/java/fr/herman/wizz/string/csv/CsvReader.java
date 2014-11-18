package fr.herman.wizz.string.csv;

import java.io.Reader;

import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.string.AbstractStringSerializerReader;

public class CsvReader extends AbstractStringSerializerReader {

    private final char separator;

    private final char eol;

    private boolean shift = false;

    public CsvReader(Reader reader, char separator, char eol) {
        super(reader);
        this.separator = separator;
        this.eol = eol;
    }

    CsvReader(Reader reader, char separator, char eol, int bufferSize) {
        super(reader);
        this.separator = separator;
        this.eol = eol;
    }

    @Override
    protected int tokenLength(boolean appendable) throws SerializerException {
        if (shift) {
            // skip previous separator
            cursor++;
            shift = false;
        }
        int i = cursor;
        int tokenLength = findLength(i);
        if (tokenLength != -1) {
            return tokenLength;
        }
        if (appendable && end - cursor > 0) {
            // finish to read values in cache and fill it entirely
            return -2;
        }
        int required = cursor + buffer.length - end;
        if (required < 1) {
            throw new SerializerException("Buffer overflow");
        }
        int length = require(required);
        if (length == -1) {
            return end - cursor;
        }
        tokenLength = findLength(i);
        if (appendable && tokenLength == -1) {
            return -2;
        }
        return tokenLength;
    }

    private int findLength(int i) {
        while (i < end) {
            char c = buffer[i];
            if (separator == c || eol == c) {
                shift = true;
                return i - cursor;
            }
            i++;
        }
        return -1;
    }

    public boolean hasNext() throws SerializerException {
        return tokenLength(false) > -1;
    }

    public void skipToken() throws SerializerException {
        int length;
        while ((length = tokenLength(true)) == -2) {
            cursor = end;
        }
        if (length > 0) {
            cursor += length;
        }
    }
}
