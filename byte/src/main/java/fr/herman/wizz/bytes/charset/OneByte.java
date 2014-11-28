package fr.herman.wizz.bytes.charset;

import java.io.IOException;
import java.nio.ByteBuffer;

public class OneByte implements CustomCharset {

    @Override
    public int encode(CharSequence source, int charIndex, int length, byte[] buffer, int bufferIndex) {
        int limit = length + charIndex;
        for (; charIndex < limit; charIndex++) {
            buffer[bufferIndex++] = (byte) source.charAt(charIndex);
        }
        return bufferIndex;
    }

    @Override
    public int computeSize(CharSequence source, int charIndex, int length) {
        return length;
    }

    @Override
    public int maxBytesPerChar() {
        return 1;
    }

    @Override
    public int decode(ByteBuffer source, int length, Appendable dest) throws IOException {
        for (int i = 0; i < length; i++) {
            dest.append((char) source.get());
        }
        return length;
    }

}
