package fr.herman.wizz.string.csv;

import java.io.Writer;

import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.string.StringEncoder;

public class CsvEncoder extends StringEncoder {

    private final char separator, eol;

    public CsvEncoder(Writer writer, char separator, char eol) {
        super(writer);
        this.separator = separator;
        this.eol = eol;
    }

    CsvEncoder(Writer writer, char separator, char eol, int bufferSize) {
        super(writer, bufferSize);
        this.separator = separator;
        this.eol = eol;
    }

    @Override
    public void writeNull() {
        // do nothing
    }

    @Override
    public void writeNotNull() {
        // do nothing
    }

    public void writeSeparator() throws SerializerException {
        writeChar(separator);
    }

    public void writeNewLine() throws SerializerException {
        writeChar(eol);
    }
}
