package net.seanamos.recyclerviewvisitors.model;

public interface IntAnimalVisitor {
    int visit(Cat cat);
    int visit(Doggo dog);
    int visit(Frog frog);
    int visit(Hippo hippo);
}
