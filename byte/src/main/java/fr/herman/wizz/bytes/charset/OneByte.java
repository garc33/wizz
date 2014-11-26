package fr.herman.wizz.bytes.charset;

public class OneByte implements CustomCharset {

    @Override
    public int computeSize(CharSequence source, int charIndex, int length) {
        return length;
    }

    @Override
    public int encode(CharSequence source, int charIndex, int length, byte[] buffer, int bufferIndex) {
        int limit = length + charIndex;
        for (; charIndex < limit; charIndex++) {
            buffer[bufferIndex++] = (byte) source.charAt(charIndex);
        }
        return bufferIndex;
    }

    @Override
    public int maxBytesPerChar() {
        return 1;
    }

}
