package net.seanamos.recyclerviewvisitors.recycler;


import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class ItemViewHolder<T> extends RecyclerView.ViewHolder {

    private T data;

    public ItemViewHolder(View itemView) {
        super(itemView);
        //ButterKnife.bind(itemView);
    }

    public final void setData(T data) {
        this.data = data;
        onDataSet(data);
    }

    protected void onDataSet(T data) {
        // subclass hook
    }

    public T getData() {
        return this.data;
    }
}
