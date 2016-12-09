package net.seanamos.recyclerviewvisitors.recycler;

public interface DiffHelper<T> {
    boolean areItemsTheSame(T oldItem, T newItem);
    boolean areContentsTheSame(T oldItem, T newItem);
}
