package fr.herman.wizz.string.csv;

import java.io.Writer;
import java.util.List;

import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.mapping.OutputMapping;
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

    @Override
    public <T> void writeObject(List<OutputMapping<T>> mappings, T object) throws SerializerException {
        int size = mappings.size();
        if (size > 0) {
            mappings.get(0).write(this, object);
        }
        for (int i = 1; i < size; i++) {
            writeSeparator();
            mappings.get(i).write(this, object);
        }
        writeNewLine();
    }
}
