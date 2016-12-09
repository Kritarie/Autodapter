package net.seanamos.recyclerviewvisitors.recycler;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;

class ViewTypeFactoryDelegateManager<T> extends AbstractViewTypeDelegateManager<T> {

    private final ItemViewTypeFactory<T> factory;

    public ViewTypeFactoryDelegateManager(ItemViewTypeFactory<T> factory, SparseArrayCompat<ViewTypeDelegate<T>> viewTypeDelegates) {
        super(viewTypeDelegates);
        this.factory = factory;
    }

    public int getItemViewType(@NonNull T item) {
        return factory.type(item);
    }
}
