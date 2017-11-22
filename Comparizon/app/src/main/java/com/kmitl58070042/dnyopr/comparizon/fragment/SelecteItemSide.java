package com.kmitl58070042.dnyopr.comparizon.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.kmitl58070042.dnyopr.comparizon.MainActivity;
import com.kmitl58070042.dnyopr.comparizon.R;

public class SelecteItemSide extends Fragment {

    private ItemLeftFragmentListener listener;
    private LinearLayout itemL, itemR;


    public interface ItemLeftFragmentListener {
        void onItemSelected(String selectedSide);

    }

    public SelecteItemSide() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_item_selected, container, false);

        listener = (MainActivity) getActivity();

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
