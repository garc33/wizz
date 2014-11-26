package fr.herman.wizz.io;

import java.util.List;

import fr.herman.wizz.exception.SerializerException;
import fr.herman.wizz.mapping.OutputMapping;

public interface Encoder {
    void writeInt(int input) throws SerializerException;

    void writeLong(long input) throws SerializerException;

    void writeShort(short input) throws SerializerException;

    void writeDouble(double input) throws SerializerException;

    void writeFloat(float input) throws SerializerException;

    void writeBoolean(boolean input) throws SerializerException;

    void writeByte(byte input) throws SerializerException;

    void writeChar(char input) throws SerializerException;

    void writeString(String input) throws SerializerException;

    void writeNull() throws SerializerException;

    void writeNotNull() throws SerializerException;

    <O> void writeObject(List<OutputMapping<O>> mappings, O object) throws SerializerException;
}
