package com.kmitl58070042.dnyopr.comparizon.fragment;


import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kmitl58070042.dnyopr.comparizon.R;
import com.kmitl58070042.dnyopr.comparizon.database.ItemInfoDB;
import com.kmitl58070042.dnyopr.comparizon.model.ItemInfo;

public class AddItemFragment extends Fragment implements View.OnClickListener {

    private ItemInfo itemInfo;
    private ItemInfoDB itemInfoDB;
    private EditText brand, detail, cost, size;


    public AddItemFragment() {
        // Required empty public constructor
    }

    public static AddItemFragment newInstance(ItemInfo itemInfo) {

        Bundle args = new Bundle();
        args.putParcelable("itemInfo", itemInfo);
        AddItemFragment fragment = new AddItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemInfoDB = Room.databaseBuilder(getActivity(),
                ItemInfoDB.class, "ItemInfo")
                .fallbackToDestructiveMigration()
                .build();
        itemInfo = getArguments().getParcelable("itemInfo");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_item, container, false);



        brand = rootView.findViewById(R.id.edit_brand);
        detail = rootView.findViewById(R.id.edit_detail);
        cost = rootView.findViewById(R.id.edit_cost);
        size = rootView.findViewById(R.id.edit_size);

        Log.e("where", "onCreateView");

        //save item data
        Button btn_save = rootView.findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);

        return rootView;
    }

    private void onSave() {

        Log.e("where", "try");
        try{
            new AsyncTask<Void, Void, ItemInfo>() {

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
            e.printStackTrace();
            Toast.makeText(getActivity(), "Please enter description.", Toast.LENGTH_LONG).show();
            return;
        }




    }

    @Override
    public void onClick(View view) {

            if (R.id.btn_save == view.getId()) {
                Log.e("where", "onClick");

                getActivity().getSupportFragmentManager()
                        .popBackStack();
                onSave();
            }



    }

}
