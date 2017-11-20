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
import android.widget.Toast;

import com.kmitl58070042.dnyopr.comparizon.R;
import com.kmitl58070042.dnyopr.comparizon.database.ItemInfoDB;
import com.kmitl58070042.dnyopr.comparizon.model.ItemInfo;

public class AddItemFragment extends Fragment implements View.OnClickListener
{

    private ItemInfo itemInfo;
    private ItemInfoDB itemInfoDB;
    private EditText brand, detail, cost, size;
//    private Button btn_save;


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
                ItemInfoDB.class, "ItemInfo")
                .fallbackToDestructiveMigration()
                .build();

        brand = rootView.findViewById(R.id.edit_brand);
        detail = rootView.findViewById(R.id.edit_detail);
        cost = rootView.findViewById(R.id.edit_cost);
        size = rootView.findViewById(R.id.edit_size);

        Log.e("where","onCreateView");

        //save item data
        Button btn_save = rootView.findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("where","onClick");

//                if(R.id.btn_save == getId()){
                    Log.e("where","save onclick");

                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .remove(AddItemFragment.this)
                            .commit();
                    onSave();
//                }
            }
        });


        return rootView;
    }

    private void onSave() {
//        try {
            Log.e("where","try");
            new AsyncTask<Void, Void, ItemInfo>(){

                @Override
                protected ItemInfo doInBackground(Void... voids) {
                    String strBrand = brand.getText().toString();
Log.wtf("string",strBrand);
                    itemInfo.setBrand(strBrand);
                    itemInfo.setDetail(detail.getText()+"");
                    itemInfo.setCost(Float.valueOf(cost.getText()+""));
                    itemInfo.setSize(Float.valueOf(size.getText()+""));

                    itemInfoDB.itemInfoDAO().insert(itemInfo);
                    return null;
                }

//                @Override
//                protected void onPostExecute(ItemInfo itemInfo) {
//                    super.onPostExecute(itemInfo);
//                }
            }.execute();
//        }catch (Exception e){
//            Toast.makeText(getActivity(), "Please fill up this form.", Toast.LENGTH_LONG).show();
//        }


    }

    @Override
    public void onClick(View view) {


//        Log.e("where","onClick");
//
//        if(R.id.btn_save == getId()){
//            Log.e("where","save onclick");
//
//            getActivity().getSupportFragmentManager()
//                    .beginTransaction()
//                    .remove(AddItemFragment.this)
//                    .commit();
//            onSave();
//        }

    }
}
