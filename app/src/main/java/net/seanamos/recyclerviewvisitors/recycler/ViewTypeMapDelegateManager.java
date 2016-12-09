package net.seanamos.recyclerviewvisitors.recycler;

import android.support.annotation.NonNull;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.util.SparseArrayCompat;

class ViewTypeMapDelegateManager<T> extends AbstractViewTypeDelegateManager<T> {

    private final SimpleArrayMap<Class<? extends T>, Integer> viewTypeMap;

    public ViewTypeMapDelegateManager(SimpleArrayMap<Class<? extends T>, Integer> viewTypeMap, SparseArrayCompat<ViewTypeDelegate<T>> viewTypeDelegates) {
        super(viewTypeDelegates);
        this.viewTypeMap = viewTypeMap;
    }

    public int getItemViewType(@NonNull T item) {
        return viewTypeMap.get(item.getClass());
    }
}
