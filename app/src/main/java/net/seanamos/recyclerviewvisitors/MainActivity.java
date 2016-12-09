package net.seanamos.recyclerviewvisitors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import net.seanamos.recyclerviewvisitors.model.Animal;
import net.seanamos.recyclerviewvisitors.model.Cat;
import net.seanamos.recyclerviewvisitors.model.Doggo;
import net.seanamos.recyclerviewvisitors.model.Frog;
import net.seanamos.recyclerviewvisitors.model.Hippo;
import net.seanamos.recyclerviewvisitors.model.IntAnimalVisitor;
import net.seanamos.recyclerviewvisitors.recycler.Autodapter;
import net.seanamos.recyclerviewvisitors.recycler.ItemViewHolder;
import net.seanamos.recyclerviewvisitors.recycler.ItemViewTypeFactory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        List<Animal> initialItems = new ArrayList<>();
        initialItems.add(new Cat());
        initialItems.add(new Doggo());
        initialItems.add(new Frog());
        initialItems.add(new Hippo());
        initialItems.add(new Cat());
        initialItems.add(new Cat());
        initialItems.add(new Doggo());
        initialItems.add(new Frog());
        initialItems.add(new Hippo());
        initialItems.add(new Cat());
        initialItems.add(new Cat());
        initialItems.add(new Doggo());
        initialItems.add(new Frog());
        initialItems.add(new Hippo());
        initialItems.add(new Cat());
        initialItems.add(new Cat());
        initialItems.add(new Doggo());
        initialItems.add(new Frog());
        initialItems.add(new Hippo());
        initialItems.add(new Cat());
        initialItems.add(new Cat());
        initialItems.add(new Doggo());
        initialItems.add(new Frog());
        initialItems.add(new Hippo());
        initialItems.add(new Cat());
        initialItems.add(new Cat());
        initialItems.add(new Doggo());
        initialItems.add(new Frog());
        initialItems.add(new Hippo());
        initialItems.add(new Cat());

        Autodapter<Animal> adapter = new Autodapter.Builder<>(initialItems)
                .withFactory(new AnimalFactory())
                .withDelegate(R.layout.item_cat, CatViewHolder::new)
                .withDelegate(R.layout.item_dog, DoggoViewHolder::new)
                .withDelegate(R.layout.item_frog, FrogViewHolder::new)
                .withDelegate(R.layout.item_hippo, HippoViewHolder::new)
                .create();
        // I made this API by mistake because I forgot java doesn't have reified generics
//        Autodapter<Animal> adapter = new Autodapter.Builder<>(initialItems)
//                .withDelegate(R.layout.item_cat, CatViewHolder::new, Cat.class)
//                .withDelegate(R.layout.item_dog, DoggoViewHolder::new, Doggo.class)
//                .withDelegate(R.layout.item_frog, FrogViewHolder::new, Frog.class)
//                .withDelegate(R.layout.item_hippo, HippoViewHolder::new, Hippo.class)
//                .create();
        recycler.setAdapter(adapter);
    }

    private class AnimalFactory implements ItemViewTypeFactory<Animal>, IntAnimalVisitor {

        @Override
        public int visit(Cat cat) {
            return R.layout.item_cat;
        }

        @Override
        public int visit(Doggo dog) {
            return R.layout.item_dog;
        }

        @Override
        public int visit(Frog frog) {
            return R.layout.item_frog;
        }

        @Override
        public int visit(Hippo hippo) {
            return R.layout.item_hippo;
        }

        @Override
        public int type(Animal item) {
            return item.accept(this);
        }
    }

    private class CatViewHolder extends ItemViewHolder<Cat> {

        private TextView tv;

        public CatViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.text);
        }

        @Override
        protected void onDataSet(Cat data) {
            tv.setText("meow");
        }
    }

    private class DoggoViewHolder extends ItemViewHolder<Doggo> {

        private TextView tv;

        public DoggoViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.text);
        }

        @Override
        protected void onDataSet(Doggo data) {
            tv.setText("woof");
        }
    }

    private class FrogViewHolder extends ItemViewHolder<Frog> {
        private TextView tv;

        public FrogViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.text);
        }

        @Override
        protected void onDataSet(Frog data) {
            tv.setText("ribbit");
        }
    }

    private class HippoViewHolder extends ItemViewHolder<Hippo> {
        private TextView tv;

        public HippoViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.text);
        }

        @Override
        protected void onDataSet(Hippo data) {
            tv.setText("I am so tired. I am so tired all of the time.");
        }
    }
}
