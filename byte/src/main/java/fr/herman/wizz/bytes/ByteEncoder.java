package fr.herman.wizz.bytes;

import fr.herman.wizz.bytes.charset.CustomCharset;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.Encoder;

public interface ByteEncoder extends Encoder {
    void writeByteArray(byte[] b, int offset, int length) throws SerializerException;

    void writeString(String input, CustomCharset charset) throws SerializerException;
}
