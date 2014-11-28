package fr.herman.wizz.bytes;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import fr.herman.wizz.bytes.charset.CustomCharset;
import fr.herman.wizz.exception.SerializerException;

public class FileChannelByteEncoder extends ByteBufferEncoder {

    private final FileChannel channel;

    public FileChannelByteEncoder(FileChannel channel, int bufferSize) {
        super();
        this.channel = channel;
        buffer = ByteBuffer.allocateDirect(bufferSize);
    }

    public FileChannelByteEncoder(FileChannel channel, int bufferSize, CustomCharset charset) {
        super(charset);
        this.channel = channel;
        buffer = ByteBuffer.allocateDirect(bufferSize);
    }

    @Override
    protected void require(int length) throws SerializerException {
        if (buffer.remaining() < length) {
            flush();
        }
    }

    public void flush() throws SerializerException {
        try {
            buffer.flip();
            channel.write(buffer);
            buffer.clear();
        } catch (IOException e) {
            throw new SerializerException(e);
        }

    }
}
