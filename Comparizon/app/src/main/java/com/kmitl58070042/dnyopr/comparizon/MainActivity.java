package com.kmitl58070042.dnyopr.comparizon;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.kmitl58070042.dnyopr.comparizon.adapter.ItemInfoRecyclerAdapter;
import com.kmitl58070042.dnyopr.comparizon.database.ItemInfoDB;
import com.kmitl58070042.dnyopr.comparizon.fragment.AddItemFragment;
import com.kmitl58070042.dnyopr.comparizon.fragment.SelectItemSide;
import com.kmitl58070042.dnyopr.comparizon.model.Compare;
import com.kmitl58070042.dnyopr.comparizon.model.ItemInfo;

import java.util.List;


public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener
        , AddItemFragment.AddItemFragmentListener
        , SelectItemSide.SelectItemSideListener
        , ItemInfoRecyclerAdapter.ItemInfoRecyclerAdapterListener {

    private ItemInfoRecyclerAdapter adapter;
    private RecyclerView list;
    private ItemInfoDB itemInfoDB;
    private Compare compare;

    private String result, selectedSide="L" ;
    private int countSelectedItem = 0;

    private float costA, sizeA, costB, sizeB;


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemInfoDB = Room.databaseBuilder(this,
                ItemInfoDB.class, "ItemInfo")
                .fallbackToDestructiveMigration()
                .build();

        adapter = new ItemInfoRecyclerAdapter(this, this, selectedSide);


        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);


        if (savedInstanceState == null) {

            showItemList();
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
        } else {

        }


        Log.wtf("where", "fragment");
//
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.item_r_fragment, new ItemRightFragment())
//                .commit();

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
        switch (item.getItemId()) {
            case R.id.btn_add:
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.add_item_fragment, AddItemFragment.newInstance(new ItemInfo()))
                        .addToBackStack(null)
                        .commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(String selectedSide) {
        this.selectedSide = selectedSide;
        adapter.setSelectedSide(selectedSide);
        Log.wtf("what selected", this.selectedSide);
    }

    @Override
    public void onItemInfoSelected(float cost, float size) {
        compare = new Compare();
        countSelectedItem += 1;
        if (countSelectedItem == 1) {
            costA = cost;
            sizeA = size;
        } else if (countSelectedItem == 2) {
            costB = cost;
            sizeB = size;
            result = compare.findCheaperItem(costA, sizeA, costB, sizeB);
            countSelectedItem = 0;
            resultSet(result);
        }
    }

    @Override
    public void setItem(String brand, String detail, String image, String selectedSide) {



    }

    private void resultSet(String result) {
        TextView txtResult = findViewById(R.id.txt_result);
        txtResult.setText(result + " is cheaper!");
    }
}
