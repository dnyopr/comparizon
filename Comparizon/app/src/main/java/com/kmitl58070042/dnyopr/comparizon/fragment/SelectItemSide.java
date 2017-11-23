package com.kmitl58070042.dnyopr.comparizon.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kmitl58070042.dnyopr.comparizon.MainActivity;
import com.kmitl58070042.dnyopr.comparizon.R;
import com.kmitl58070042.dnyopr.comparizon.adapter.ItemInfoRecyclerAdapter;
import com.kmitl58070042.dnyopr.comparizon.model.ItemInfo;

import java.util.List;

public class SelectItemSide extends Fragment  {

    private SelectItemSideListener listener;
    private LinearLayout itemL, itemR;
    private TextView itemLName, itemRName;
    private TextView itemLDetail, itemRDetail;
    private ImageView itemLImage, itemRimage;
    private List<ItemInfo> data;



    public interface SelectItemSideListener {
        void onItemSelected(String selectedSide);

    }

    public SelectItemSide() {
        // Required empty public constructor
    }

    public static SelectItemSide newInstance(ItemInfo itemInfo,int position) {

        Bundle args = new Bundle();
        args.putParcelable("itemInfo", itemInfo);
        SelectItemSide fragment = new SelectItemSide();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_item_selected, container, false);

        Log.wtf("where","set rootview already");
        listener = (MainActivity) getActivity();


        itemLName = rootView.findViewById(R.id.item_l_name);
        itemLDetail =rootView.findViewById(R.id.item_l_detail);
        itemLImage = rootView.findViewById(R.id.item_l_image);
        itemL = rootView.findViewById(R.id.item_left);
        itemL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemSelected("L");
                itemL.setBackgroundDrawable(getResources().getDrawable(R.drawable.layout_shadow));
                itemR.setBackgroundDrawable(getResources().getDrawable(R.drawable.layout_normal));
            }
        });

        itemR = rootView.findViewById(R.id.item_right);
        itemR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemSelected("R");
                itemR.setBackgroundDrawable(getResources().getDrawable(R.drawable.layout_shadow));
                itemL.setBackgroundDrawable(getResources().getDrawable(R.drawable.layout_normal));
            }
        });


        return rootView;
    }




}
