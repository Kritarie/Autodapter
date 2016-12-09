package net.seanamos.recyclerviewvisitors.model;

public interface AnimalVisitor<T> {
    //otterboxing
    T visit(Cat cat);
    T visit(Doggo doggo);
    T visit(Frog frog);
    T visit(Hippo hippo);
}
