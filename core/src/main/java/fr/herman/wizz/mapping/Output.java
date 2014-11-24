package fr.herman.wizz.mapping;

public interface Output<S, V> {

    V getValue(S source);
}
