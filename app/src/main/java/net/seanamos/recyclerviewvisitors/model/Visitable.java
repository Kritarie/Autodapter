package net.seanamos.recyclerviewvisitors.model;

public interface Visitable<T> {
    <D> D accept(T visitor);
}
