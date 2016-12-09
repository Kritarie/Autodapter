package net.seanamos.recyclerviewvisitors.recycler;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class AbstractViewTypeDelegateManager<T> implements ViewTypeDelegateManager<T> {

    private final SparseArrayCompat<ViewTypeDelegate<T>> viewTypeDelegates;

    public AbstractViewTypeDelegateManager(SparseArrayCompat<ViewTypeDelegate<T>> viewTypeDelegates) {
        this.viewTypeDelegates = viewTypeDelegates;
    }

    //Find the ViewTypeDelegate for this viewtype and return its ViewHolder
    @NonNull
    public ItemViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewTypeDelegate<T> viewTypeHandler = viewTypeDelegates.get(viewType);
        if (viewTypeHandler == null)
            return createDefault(parent, viewType);

        View itemView = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        ItemViewHolder<T> viewHolder = viewTypeHandler.onCreateViewHolder(itemView);
        if (viewHolder == null) {
            throw new NullPointerException(
                    "ViewHolder returned from ViewTypeDelegate " + viewTypeHandler + " for ViewType =" + viewType + " is null");
        }
        return viewHolder;
    }

    private DefaultViewHolder<T> createDefault(@NonNull ViewGroup parent, int viewType) {
        if (parent.getResources().getResourceName(viewType) == null) {
            throw new RuntimeException("No delegate or layout specified for viewType " + viewType);
        }
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(viewType, parent, false);
        return new DefaultViewHolder<>(itemView);
    }
}
