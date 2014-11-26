package fr.herman.wizz.bytes.charset;

public class UTF8 implements CustomCharset {

    @Override
    public int encode(CharSequence source, int charIndex, int length, byte[] buffer, int bufferIndex) {
        int limit = length + charIndex;
        for (; charIndex < limit; charIndex++) {
            int c = source.charAt(charIndex);
            if (c <= 0x007F) {
                buffer[bufferIndex++] = (byte) c;
            } else if (c > 0x07FF) {
                buffer[bufferIndex++] = (byte) (0xE0 | c >> 12 & 0x0F);
                buffer[bufferIndex++] = (byte) (0x80 | c >> 6 & 0x3F);
                buffer[bufferIndex++] = (byte) (0x80 | c & 0x3F);
            } else {
                buffer[bufferIndex++] = (byte) (0xC0 | c >> 6 & 0x1F);
                buffer[bufferIndex++] = (byte) (0x80 | c & 0x3F);
            }
        }
        return bufferIndex;
    }


    @Override
    public int computeSize(CharSequence source, int charIndex, int length) {
        int limit = length + charIndex;
        for (; charIndex < limit; charIndex++) {
            int c = source.charAt(charIndex);
            if (c > 0x007F) {
                if (c > 0x07FF) {
                    length++;
                }
                length += 2;
            }
        }
        return length;
    }

    @Override
    public int maxBytesPerChar() {
        return 3;
    }

}
