package com.kmitl58070042.dnyopr.comparizon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kmitl58070042.dnyopr.comparizon.R;
import com.kmitl58070042.dnyopr.comparizon.model.ItemInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 20/11/2017 AD.
 */

public class ItemInfoRecyclerAdapter extends RecyclerView.Adapter<ItemInfoRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<ItemInfo> data;

    private ItemInfoRecyclerAdapterListener listener;

    public ItemInfoRecyclerAdapter(Context context) {
        this.context = context;
        this.data = new ArrayList<>();
//        this.listener = (ItemInfoRecyclerAdapterListener) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.iteminfo_list, parent, false);

        ViewHolder holder = new ViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemInfo itemInfo = data.get(position);

        holder.txt_brand.setText(itemInfo.getBrand());
        holder.txt_detail.setText(itemInfo.getDetail());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public List<ItemInfo> getData() {
        return data;
    }

    public void setData(List<ItemInfo> data) {
        this.data = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_brand;
        TextView txt_detail;

        public ViewHolder(View itemView) {
            super(itemView);

            txt_brand = itemView.findViewById(R.id.item_brand);
            txt_detail = itemView.findViewById(R.id.item_detail);
        }
    }

    private interface ItemInfoRecyclerAdapterListener {
    }
}
