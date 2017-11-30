package com.kmitl58070042.dnyopr.comparizon;

import android.Manifest;
import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.GlideException;
import com.kmitl58070042.dnyopr.comparizon.adapter.ItemInfoRecyclerAdapter;
import com.kmitl58070042.dnyopr.comparizon.database.ItemInfoDB;
import com.kmitl58070042.dnyopr.comparizon.fragment.AddItemFragment;
import com.kmitl58070042.dnyopr.comparizon.fragment.SelectItemSide;
import com.kmitl58070042.dnyopr.comparizon.model.Compare;
import com.kmitl58070042.dnyopr.comparizon.model.ItemInfo;
import com.kmitl58070042.dnyopr.comparizon.model.Screenshot;

import java.util.List;
import java.util.concurrent.LinkedTransferQueue;


public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener
        , AddItemFragment.AddItemFragmentListener
        , SelectItemSide.SelectItemSideListener
        , ItemInfoRecyclerAdapter.ItemInfoRecyclerAdapterListener {

    private ItemInfoRecyclerAdapter adapter;
    private RecyclerView list;
    private ImageView btn_share;
    private boolean isFragmenrAddOn =false;

    private ItemInfoDB itemInfoDB;
    private Compare compare;

    private LinearLayout comparizon_space;

    private String result, selectedSide = "L";
    private int selectedItem = 0;
    private final int WRITE_EXTERNAL_REQUEST_CODE = 2;


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

        comparizon_space = findViewById(R.id.comparison_space);

        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        btn_share = findViewById(R.id.btn_share);
        btn_share.setOnClickListener(this);

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
        if (R.id.btn_share == view.getId()) {
            if (selectedItem == 1) {
                onShare(comparizon_space);
            }else {
                Toast.makeText(this,"Please make some comparizon first.",Toast.LENGTH_SHORT).show();
            }

        }
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


    //------ btn on action bar ------//
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
                if(getSupportFragmentManager().getBackStackEntryCount()==0) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.add_item_fragment, AddItemFragment.newInstance(new ItemInfo()))
                            .addToBackStack("add")
                            .commit();
                }
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
        txtResult.setText(result);

        txtResult.setTextColor(Color.BLACK);
    }

    private void pleaseAddItems(int itemListSize) {
        TextView txtResult = findViewById(R.id.txt_result);
        Log.wtf("size", itemListSize + "");
        if (itemListSize == 0) {
            txtResult.setText("Please add an item.");
            txtResult.setTextColor(Color.RED);
        } else if (itemListSize == 1) {
            txtResult.setText("Please add another item.");
        }
        else {
            txtResult.setText("Please select 2 items.");

            txtResult.setTextColor(Color.RED);
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
                fragment.setLValue(brand, detail, image, String.valueOf(cost), String.valueOf(size));
            } catch (GlideException e) {
                e.printStackTrace();
            }
        } else {
            try {
                costB = cost;
                sizeB = size;
                fragment.setRValue(brand, detail, image, String.valueOf(cost), String.valueOf(size));
            } catch (GlideException e) {
                e.printStackTrace();
            }
        }

        Log.wtf("valuse chk", "" + costA + "" + costB + "" + sizeA + "" + sizeB);
        if (costA != 0.0 && costB != 0.0 && sizeA != 0.0 && sizeB != 0.0) {
            selectedItem = 1;
            result = compare.findCheaperItem(costA, sizeA, costB, sizeB);
            resultSet(result);
        } else {
            Log.wtf("0.0", "one is null");
        }

    }

    @Override
    public void onItemLongCilck(final ItemInfo itemInfo) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setItems(new CharSequence[]{"Delete"},
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                new AsyncTask<Void, Void, ItemInfo>() {

                                    @Override
                                    protected ItemInfo doInBackground(Void... voids) {
                                        itemInfoDB.itemInfoDAO().delete(itemInfo);
                                        showItemList();

                                        getSupportFragmentManager()
                                                .popBackStack();
                                        getSupportFragmentManager().findFragmentByTag(SelectItemSide.TAG);

                                        return null;
                                    }
                                }.execute();
                                break;

                        }
                    }
                });
        builder.show();
    }


    private void createShareIntent(Uri uriImage) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uriImage);
        try {
            startActivity(Intent.createChooser(shareIntent, " How do you want to share? "));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(MainActivity.this, "No App Available", Toast.LENGTH_SHORT).show();
        }
    }

    // ------ onshare ------//
    private void onShare(View imageView) {

        //Permission
        if (askPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, WRITE_EXTERNAL_REQUEST_CODE)) {
            //Screenshot
            Bitmap image = Screenshot.takescreenshotOfRootView(imageView);
            Uri screenshotUri = Screenshot.getImageUri(this.getApplicationContext(), image);

            //Share
            createShareIntent(screenshotUri);
        }
    }

    //------- Permission ------//
    private boolean askPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
            return false;
        } else {
//            Toast.makeText(this, "Permission is Already Granted", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case WRITE_EXTERNAL_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "WRITE_EXTERNAL Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "WRITE_EXTERNAL Permission Denied", Toast.LENGTH_SHORT).show();
                }
                onShare(comparizon_space);
                return;
        }
    }



}
