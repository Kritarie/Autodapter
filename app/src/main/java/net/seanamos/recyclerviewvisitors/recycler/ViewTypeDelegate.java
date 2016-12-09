package net.seanamos.recyclerviewvisitors.recycler;

import android.view.View;

public interface ViewTypeDelegate<T> {
    ItemViewHolder<T> onCreateViewHolder(View itemView);
}
