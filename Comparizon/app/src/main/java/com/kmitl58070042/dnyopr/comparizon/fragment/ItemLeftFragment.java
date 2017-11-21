package com.kmitl58070042.dnyopr.comparizon.fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kmitl58070042.dnyopr.comparizon.MainActivity;
import com.kmitl58070042.dnyopr.comparizon.R;

public class ItemLeftFragment extends Fragment {

    private ItemLeftFragmentListener listener;
    private FrameLayout frameLayout;


    public interface ItemLeftFragmentListener {
        void onItemLSelected(FrameLayout frameLayout);
    }
    public ItemLeftFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_item_left, container, false);

        listener = (MainActivity) getActivity();

        frameLayout = rootView.findViewById(R.id.fragment_l);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemLSelected(frameLayout);
            }
        });

        return rootView;
    }


}
