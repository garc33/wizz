package fr.herman.wizz.bytes.serializer;

import fr.herman.wizz.Serializer;
import fr.herman.wizz.bytes.ByteDecoder;
import fr.herman.wizz.bytes.ByteEncoder;

/**
 * Can be used only with {@link ByteEncoder}, {@link ByteDecoder}
 */
public interface ByteSerializer<TYPE> extends Serializer<TYPE> {

}
