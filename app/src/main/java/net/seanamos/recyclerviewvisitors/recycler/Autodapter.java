package net.seanamos.recyclerviewvisitors.recycler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

public class Autodapter<T> extends RecyclerView.Adapter<ItemViewHolder<T>> {

    @NonNull
    private final ViewTypeDelegateManager<T> viewTypeDelegateManager;
    @Nullable
    private DiffHelper<T> diffHelper;
    @NonNull
    private List<T> items;

    private Autodapter(@NonNull List<T> items,
                       @NonNull ViewTypeDelegateManager<T> viewTypeDelegateManager,
                       @Nullable DiffHelper<T> diffHelper) {
        this.items = items;
        this.viewTypeDelegateManager = viewTypeDelegateManager;
        this.diffHelper = diffHelper;
    }

    @Override
    public ItemViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        return viewTypeDelegateManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder<T> holder, int position) {
        holder.setData(items.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return viewTypeDelegateManager.getItemViewType(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    public List<T> getItems() {
        return items;
    }

    public void updateItems(@NonNull List<T> listItems) {
        List<T> oldItems = this.items;
        this.items = listItems;
        if (diffHelper == null) {
            notifyDataSetChanged();
        } else {
            DiffUtil.calculateDiff(new SimpleDiffUtil<>(diffHelper, oldItems, listItems))
                    .dispatchUpdatesTo(this);
        }
    }

    private static class SimpleDiffUtil<T> extends DiffUtil.Callback {

        private final DiffHelper<T> diffHelper;
        private final List<T> oldItems;
        private final List<T> newItems;

        public SimpleDiffUtil(DiffHelper<T> diffHelper, List<T> oldItems, List<T> newItems) {
            this.diffHelper = diffHelper;
            this.oldItems = oldItems;
            this.newItems = newItems;
        }

        @Override
        public int getOldListSize() {
            return oldItems.size();
        }

        @Override
        public int getNewListSize() {
            return newItems.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return diffHelper.areItemsTheSame(oldItems.get(oldItemPosition), newItems.get(newItemPosition));
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return diffHelper.areContentsTheSame(oldItems.get(oldItemPosition), newItems.get(newItemPosition));
        }
    }

    public static class Builder<T> {

        private List<T> initialItems;
        private ItemViewTypeFactory<T> factory;
        private SimpleArrayMap<Class<? extends T>, Integer> map = new SimpleArrayMap<>();
        private SparseArrayCompat<ViewTypeDelegate<? extends T>> delegates = new SparseArrayCompat<>();
        private DiffHelper<T> diffHelper;

        public Builder() {
            this(Collections.emptyList());
        }

        public Builder(List<T> initialItems) {
            this.initialItems = initialItems;
        }

        public Builder<T> withItems(List<T> items) {
            this.initialItems = items;
            return this;
        }

        public <D extends T> Builder<T> withDelegate(int viewType, ViewTypeDelegate<D> delegate) {
            delegates.put(viewType, delegate);
            return this;
        }

        public <D extends T> Builder<T> withDelegate(int viewType, ViewTypeDelegate<D> delegate, Class<D> clazz) {
            map.put(clazz, viewType);
            delegates.put(viewType, delegate);
            return this;
        }

        public Builder<T> withFactory(ItemViewTypeFactory<T> factory) {
            this.factory = factory;
            return this;
        }

        public Builder<T> withAutomaticDiffing(DiffHelper<T> diffHelper) {
            this.diffHelper = diffHelper;
            return this;
        }

        @SuppressWarnings("unchecked")
        public Autodapter<T> create() {
            ViewTypeDelegateManager manager = factory == null ?
                    new ViewTypeMapDelegateManager(map, delegates) :
                    new ViewTypeFactoryDelegateManager(factory, delegates);

            return new Autodapter(initialItems, manager, diffHelper);
        }
    }
}
