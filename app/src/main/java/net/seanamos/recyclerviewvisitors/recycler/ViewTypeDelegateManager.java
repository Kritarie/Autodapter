package net.seanamos.recyclerviewvisitors.recycler;


import android.support.annotation.NonNull;
import android.view.ViewGroup;

public interface ViewTypeDelegateManager<T> {
    int getItemViewType(@NonNull T item);
    ItemViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType);
}
