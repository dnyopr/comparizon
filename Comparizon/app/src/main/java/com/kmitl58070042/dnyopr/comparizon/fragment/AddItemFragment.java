package com.kmitl58070042.dnyopr.comparizon.fragment;


import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.kmitl58070042.dnyopr.comparizon.R;
import com.kmitl58070042.dnyopr.comparizon.database.ItemInfoDB;
import com.kmitl58070042.dnyopr.comparizon.model.ItemInfo;

public class AddItemFragment extends Fragment {

    private ItemInfo itemInfo;
    private ItemInfoDB itemInfoDB;
    private EditText brand, detail, cost, size;
    private Button btn_save;


    public AddItemFragment() {
        // Required empty public constructor
    }

    public static AddItemFragment newInstance() {

        Bundle args = new Bundle();

        AddItemFragment fragment = new AddItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_item,container,false);

        itemInfoDB = Room.databaseBuilder(getActivity(),
                ItemInfoDB.class, "ITEM_INFO")
                .fallbackToDestructiveMigration()
                .build();

        brand = rootView.findViewById(R.id.edit_brand);
        detail = rootView.findViewById(R.id.edit_detail);
        cost = rootView.findViewById(R.id.edit_cost);
        size = rootView.findViewById(R.id.edit_size);

        //save item data
        btn_save = rootView.findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .remove(AddItemFragment.this)
                        .commit();
                onSave();
            }
        });


        return inflater.inflate(R.layout.fragment_add_item, container, false);
    }

    private void onSave() {
        try {
            Log.e("where","try");
            new AsyncTask<Void, Void, ItemInfo>(){


                @Override
                protected ItemInfo doInBackground(Void... voids) {

                    itemInfo.setBrand(brand.getText().toString());
                    itemInfo.setDetail(detail.getText().toString());
                    itemInfo.setCost(Float.valueOf(cost.getText().toString()));
                    itemInfo.setSize(Float.valueOf(size.getText().toString()));

                    itemInfoDB.itemInfoDAO().insert(itemInfo);
                    return null;
                }

                @Override
                protected void onPostExecute(ItemInfo itemInfo) {
                    super.onPostExecute(itemInfo);
                }
            }.execute();
        }catch (Exception e){
            Log.e("where","exception");
        }


    }

}