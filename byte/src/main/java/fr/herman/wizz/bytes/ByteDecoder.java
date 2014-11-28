package fr.herman.wizz.bytes;

import fr.herman.wizz.bytes.charset.CustomCharset;
import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.io.Decoder;

public interface ByteDecoder extends Decoder {
    void readByteArray(byte[] dest, int offset, int length) throws SerializerException;

    String readString(CustomCharset charset) throws SerializerException;
}
