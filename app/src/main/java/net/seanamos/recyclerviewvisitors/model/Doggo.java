package net.seanamos.recyclerviewvisitors.model;


public class Doggo extends Animal {
    @Override
    public <T> T accept(AnimalVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public int accept(IntAnimalVisitor visitor) {
        return visitor.visit(this);
    }
}
