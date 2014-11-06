package fr.herman.wizz.utils;

import java.util.Iterator;

public class Strings {

    public static final String join(Iterator<?> it) {
        if (it == null) {
            return null;
        }
        if (!it.hasNext()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(it.next());
        while (it.hasNext()) {
            sb.append(',').append(it.next());
        }
        return sb.toString();
    }

    public static final String join(Iterable<?> iterable) {
        if (iterable == null) {
            return null;
        }
        return join(iterable.iterator());
    }
}
