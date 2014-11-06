package fr.herman.wizz.utils;

public class Nullables
{
    public static final <T> T defaultValue(T nullable, T defaultValue)
    {
        return nullable == null ? defaultValue : nullable;
    }
}
