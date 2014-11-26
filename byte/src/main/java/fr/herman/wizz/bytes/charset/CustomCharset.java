package fr.herman.wizz.bytes.charset;

public interface CustomCharset {

    int computeSize(CharSequence source, int charIndex, int length);

    int maxBytesPerChar();

    int encode(CharSequence source, int charIndex, int length, byte[] buffer, int bufferIndex);

}
