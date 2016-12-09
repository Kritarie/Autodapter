package net.seanamos.recyclerviewvisitors.model;

public abstract class Animal {
    // autovalue extension?
    public abstract <T> T accept(AnimalVisitor<T> visitor);
    public abstract int accept(IntAnimalVisitor visitor);
}