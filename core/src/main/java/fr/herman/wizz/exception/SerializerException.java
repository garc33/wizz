package fr.herman.wizz.exception;

public class SerializerException extends Exception
{

    private static final long serialVersionUID = 3940499297559027106L;

    public SerializerException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public SerializerException(String message)
    {
        super(message);
    }

    public SerializerException(Exception cause) {
        super(cause);
    }

}
