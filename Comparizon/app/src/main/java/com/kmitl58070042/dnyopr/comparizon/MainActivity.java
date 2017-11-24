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
import android.widget.Toast;

import com.bumptech.glide.load.engine.GlideException;
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

    private String result, selectedSide = "L";
    private int countSelectedItem = 0;


    float costA = (float) 0.0, sizeA = (float) 0.0, costB = (float) 0.0, sizeB = (float) 0.0;


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

        adapter = new ItemInfoRecyclerAdapter(this, this);


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
                    pleaseAddItems(itemInfos.size());

                }
            }.execute();
        } else {

        }

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.item_fragment, SelectItemSide.newInstance(new ItemInfo()), SelectItemSide.TAG)
                .commit();


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
                pleaseAddItems(itemInfos.size());
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
        Log.wtf("what selected", this.selectedSide);
    }

//    @Override
//    public void onItemInfoSelected(float cost, float size) {
//        compare = new Compare();
//        countSelectedItem += 1;
//        if (countSelectedItem == 1) {
//            costA = cost;
//            sizeA = size;
//        } else if (countSelectedItem == 2) {
//            costB = cost;
//            sizeB = size;
//            result = compare.findCheaperItem(costA, sizeA, costB, sizeB);
//            countSelectedItem = 0;
//            resultSet(result);
//        }
//    }

//    @Override
//    public void setItem(int position) {
//        getSupportFragmentManager()
//                .popBackStack();
//
//        SelectItemSide fragment = (SelectItemSide) getSupportFragmentManager().findFragmentByTag(SelectItemSide.TAG);
//
//        if (selectedSide == "L") {
//            fragment.setLValue(position);
//        }
//    }

    private void resultSet(String result) {
        TextView txtResult = findViewById(R.id.txt_result);
        txtResult.setText(result + " is cheaper!");
    }

    private void pleaseAddItems(int itemListSize) {
        TextView txtResult = findViewById(R.id.txt_result);
        Log.wtf("size", itemListSize + "");
        if (itemListSize == 0) {
            txtResult.setText("Please add an item");
        } else if (itemListSize == 1) {
            txtResult.setText("Please add another items");
        }
    }

    @Override
    public void setItem(String brand, String detail, String image, float cost, float size) {
        compare = new Compare();

        getSupportFragmentManager()
                .popBackStack();

        SelectItemSide fragment = (SelectItemSide) getSupportFragmentManager().findFragmentByTag(SelectItemSide.TAG);

        if (selectedSide == "L") {
            try {
                costA = cost;
                sizeA = size;
                fragment.setLValue(brand, detail, image);
            } catch (GlideException e) {
                e.printStackTrace();
            }
        } else {
            try {
                costB = cost;
                sizeB = size;
                fragment.setRValue(brand, detail, image);
            } catch (GlideException e) {
                e.printStackTrace();
            }
        }

        Log.wtf("valuse chk", "" + costA + "" + costB + "" + sizeA + "" + sizeB);
        if (costA != 0.0 && costB != 0.0 && sizeA != 0.0 && sizeB != 0.0) {
            Log.wtf("0.0", "not null");
            result = compare.findCheaperItem(costA, sizeA, costB, sizeB);
            resultSet(result);
        }else {
            Log.wtf("0.0", "all is null");
        }

    }
}
