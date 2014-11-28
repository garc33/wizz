package fr.herman.wizz.bytes;

import java.io.IOException;
import java.nio.channels.FileChannel;

import fr.herman.wizz.bytes.charset.CustomCharset;
import fr.herman.wizz.exception.SerializerException;

public class MemoryMappedFileEncoder extends ByteBufferEncoder {

    public MemoryMappedFileEncoder(FileChannel channel, int fileSize) throws IOException {
        super();
        buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, fileSize);
    }

    public MemoryMappedFileEncoder(FileChannel channel, int fileSize, CustomCharset charset) throws IOException {
        super(charset);
        buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, fileSize);
    }

    @Override
    protected void require(int length) throws SerializerException {
        if (buffer.remaining() < length) {
            throw new SerializerException("OutOfMemory");
        }
    }
}
