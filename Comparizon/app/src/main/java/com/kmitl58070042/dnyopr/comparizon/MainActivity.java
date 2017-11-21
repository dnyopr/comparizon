package com.kmitl58070042.dnyopr.comparizon;

import android.annotation.TargetApi;
import android.arch.persistence.room.Room;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.kmitl58070042.dnyopr.comparizon.adapter.ItemInfoRecyclerAdapter;
import com.kmitl58070042.dnyopr.comparizon.database.ItemInfoDB;
import com.kmitl58070042.dnyopr.comparizon.fragment.AddItemFragment;
import com.kmitl58070042.dnyopr.comparizon.fragment.ItemLeftFragment;
import com.kmitl58070042.dnyopr.comparizon.fragment.ItemRightFragment;
import com.kmitl58070042.dnyopr.comparizon.model.ItemInfo;

import java.util.List;


public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener
        , AddItemFragment.AddItemFragmentListener
        , ItemInfoRecyclerAdapter.ItemInfoRecyclerAdapterListener , ItemLeftFragment.ItemLeftFragmentListener{

    private ItemInfoRecyclerAdapter adapter;
    private RecyclerView list;
    private ItemInfoDB itemInfoDB;
    private String selectedFragment;



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

        adapter = new ItemInfoRecyclerAdapter(this, this);

        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        if (savedInstanceState == null) {
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


        Log.wtf("where","fragment");

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.item_r_fragment, new ItemRightFragment())
                .commit();

    }

    @Override
    public void onClick(View view) {

    }


    @Override
    public void onItemAdded() {
        getSupportFragmentManager()
                .popBackStack();

        showItemList();

    }

    private void showItemList() {
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


    //btn on action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_button, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_add : getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.add_item_fragment, AddItemFragment.newInstance(new ItemInfo()))
                    .addToBackStack(null)
                    .commit();
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onItemLSelected(FrameLayout frameLayout) {
        selectedFragment = "left";
        frameLayout.setBackground(getDrawable(R.drawable.layout_shadow));


    }
}
