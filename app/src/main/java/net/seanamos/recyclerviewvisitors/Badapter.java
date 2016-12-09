package net.seanamos.recyclerviewvisitors;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.seanamos.recyclerviewvisitors.model.Animal;
import net.seanamos.recyclerviewvisitors.model.Cat;
import net.seanamos.recyclerviewvisitors.model.Doggo;
import net.seanamos.recyclerviewvisitors.model.Frog;
import net.seanamos.recyclerviewvisitors.model.Hippo;

import java.util.List;

public class Badapter extends RecyclerView.Adapter {

    private static final int ITEM_CAT = 0;
    private static final int ITEM_DOG = 1;
    private static final int ITEM_FROG = 2;
    private static final int ITEM_HIPPO = 3;

    private List<Animal> animals;

    public Badapter(List<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // switch on viewType
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // more switch
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    @Override
    public int getItemViewType(int position) {
        Animal animal = animals.get(position);
        // ugh
        if (animal instanceof Cat) {
            return ITEM_CAT;
        } else if (animal instanceof Doggo) {
            return ITEM_DOG;
        } else if (animal instanceof Frog) {
            return ITEM_FROG;
        } else if (animal instanceof Hippo) {
            return ITEM_HIPPO;
        }
        throw new RuntimeException("send help");
    }

    private static class CatViewHolder extends RecyclerView.ViewHolder {

        private TextView text;

        public CatViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }

    // etc
}
