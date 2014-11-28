package fr.herman.wizz.bytes.charset;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface CustomCharset {

    /**
     * Calculate the encoded byte size
     *
     * @param source
     *            the {@link CharSequence} to encode
     * @param charIndex
     *            offset to start
     * @param length
     *            the number of char to process
     * @return byte size
     */
    int computeSize(CharSequence source, int charIndex, int length);

    int maxBytesPerChar();

    int encode(CharSequence source, int charIndex, int length, byte[] buffer, int bufferIndex);

    /**
     * Decode a {@link ByteBuffer} into appendable chars
     *
     * @param source
     *            the input buffer
     * @param length
     *            the number of byte to read
     * @param dest
     * @return the number of char read
     * @throws IOException
     */
    int decode(ByteBuffer source, int length, Appendable dest) throws IOException;

}
