package com.kmitl58070042.dnyopr.comparizon.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;
import com.kmitl58070042.dnyopr.comparizon.MainActivity;
import com.kmitl58070042.dnyopr.comparizon.R;
import com.kmitl58070042.dnyopr.comparizon.model.ItemInfo;

public class SelectItemSide extends Fragment  {

    public static String TAG = SelectItemSide.class.getSimpleName();
    private SelectItemSideListener listener;
    private LinearLayout itemL, itemR;
    private TextView itemLName, itemRName;
    private TextView itemLDetail, itemRDetail;
    private TextView itemLCost, itemRCost;
    private TextView itemLSize, itemRSize;
    private ImageView itemLImage, itemRImage;

    private ItemInfo itemInfo;


    public interface SelectItemSideListener {
        void onItemSelected(String selectedSide);

    }

    public SelectItemSide() {
        // Required empty public constructor
    }

    public static SelectItemSide newInstance(ItemInfo itemInfo) {
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

        //set Id for left side
        itemLName = rootView.findViewById(R.id.item_l_name);
        itemLDetail =rootView.findViewById(R.id.item_l_detail);
        itemLImage = rootView.findViewById(R.id.item_l_image);
        itemLCost = rootView.findViewById(R.id.item_l_cost);
        itemLSize = rootView.findViewById(R.id.item_l_size);
        itemL = rootView.findViewById(R.id.item_left);
         //setId for right side
        itemRName = rootView.findViewById(R.id.item_r_name);
        itemRDetail =rootView.findViewById(R.id.item_r_detail);
        itemRImage = rootView.findViewById(R.id.item_r_image);
        itemRCost = rootView.findViewById(R.id.item_r_cost);
        itemRSize = rootView.findViewById(R.id.item_r_size);
        itemR = rootView.findViewById(R.id.item_right);

        itemInfo = getArguments().getParcelable("itemInfo");

        itemL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStyleSelectL();
            }
        });

        itemR = rootView.findViewById(R.id.item_right);
        itemR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStyleSelectR();
            }
        });
        return rootView;
    }

    public void setLValue(String brand, String detail, String image, String cost, String size) throws GlideException{
        if(image.equals("default")){
            itemLImage.setImageDrawable(getResources().getDrawable(R.drawable.default_product_image));
        }else {
            Glide.with(getActivity()).load(image).into(itemLImage);
        }
        itemLName.setText(brand);
        itemLDetail.setText(detail);
        itemLCost.setText(cost);
        itemLSize.setText(size);
        setStyleSelectR();
    }

    public void setRValue(String brand, String detail, String image, String cost, String size) throws GlideException{
        if(image.equals("default")){
            itemRImage.setImageDrawable(getResources().getDrawable(R.drawable.default_product_image));
        }else {
            Glide.with(getActivity()).load(image).into(itemRImage);
        }
        itemRName.setText(brand);
        itemRDetail.setText(detail);
        itemRCost.setText(cost);
        itemRSize.setText(size);
        setStyleSelectL();
    }

    private void setStyleSelectL(){
        listener.onItemSelected("L");

        itemL.setBackgroundDrawable(getResources().getDrawable(R.drawable.layout_shadow));
        itemR.setBackgroundDrawable(getResources().getDrawable(R.drawable.layout_normal));
    }
    private void setStyleSelectR(){
        listener.onItemSelected("R");
        itemR.setBackgroundDrawable(getResources().getDrawable(R.drawable.layout_shadow));
        itemL.setBackgroundDrawable(getResources().getDrawable(R.drawable.layout_normal));
    }



}
