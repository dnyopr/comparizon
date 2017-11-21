package com.kmitl58070042.dnyopr.comparizon;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.kmitl58070042.dnyopr.comparizon.adapter.ItemInfoRecyclerAdapter;
import com.kmitl58070042.dnyopr.comparizon.database.ItemInfoDB;
import com.kmitl58070042.dnyopr.comparizon.fragment.AddItemFragment;
import com.kmitl58070042.dnyopr.comparizon.fragment.ItemLeftFragment;
import com.kmitl58070042.dnyopr.comparizon.fragment.ItemRightFragment;
import com.kmitl58070042.dnyopr.comparizon.model.ItemInfo;

import java.util.List;
import java.util.ListIterator;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, AddItemFragment.AddItemFragmentListener {

    private ItemInfoRecyclerAdapter adapter;
    private RecyclerView list;
    private ItemInfoDB itemInfoDB;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemInfoDB = Room.databaseBuilder(this,
                ItemInfoDB.class, "ItemInfo")
                .fallbackToDestructiveMigration()
                .build();

        adapter = new ItemInfoRecyclerAdapter(this);

        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        if (savedInstanceState == null) {
        }


        Log.wtf("where","fragment");
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.item_l_fragment, new ItemLeftFragment())
                .commit();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.item_r_fragment, new ItemRightFragment())
                .commit();

    }

    @Override
    public void onClick(View view) {
        if(R.id.btn_add == view.getId()){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.add_item_fragment, AddItemFragment.newInstance(new ItemInfo()))
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onItemAdded() {
        getSupportFragmentManager()
                .popBackStack();

        new AsyncTask<Void, Void, List<ItemInfo>>() {

            @Override
            protected List<ItemInfo> doInBackground(Void... voids) {
                return itemInfoDB.itemInfoDAO().getAll();
            }

            @Override
            protected void onPostExecute(List<ItemInfo> itemInfos) {
                adapter.setData(itemInfos);
                adapter.notifyDataSetChanged();
            }
        }.execute();

    }
}
