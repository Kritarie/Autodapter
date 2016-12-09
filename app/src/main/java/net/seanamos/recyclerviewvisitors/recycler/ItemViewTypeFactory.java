package net.seanamos.recyclerviewvisitors.recycler;


public interface ItemViewTypeFactory<T> {
    int type(T item);
}
