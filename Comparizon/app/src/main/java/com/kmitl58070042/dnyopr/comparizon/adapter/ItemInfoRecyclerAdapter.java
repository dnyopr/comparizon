package com.kmitl58070042.dnyopr.comparizon.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kmitl58070042.dnyopr.comparizon.R;
import com.kmitl58070042.dnyopr.comparizon.model.ItemInfo;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ItemInfoRecyclerAdapter extends RecyclerView.Adapter<ItemInfoRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<ItemInfo> data;

    private ItemInfoRecyclerAdapterListener listener;

    public ItemInfoRecyclerAdapter(Context context, ItemInfoRecyclerAdapterListener listener) {
        this.context = context;
        this.data = new ArrayList<>();
        this.listener = listener;
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

        Bitmap bitmap = null;
        String imageUri = itemInfo.getImage();


        if (imageUri!=null){
            Log.wtf("where","maii null ja");
            Log.wtf("where",imageUri);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.parse(imageUri));
                Log.wtf("bitmap", bitmap.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            Log.wtf("where","null ja");
            Log.wtf("test null",itemInfo.getBrand());
        }

        holder.txt_brand.setText(itemInfo.getBrand());
        holder.txt_detail.setText(itemInfo.getDetail());
        holder.txt_cost.setText(Float.toString(itemInfo.getCost()));
        holder.txt_size.setText(Float.toString(itemInfo.getSize()));
//        Glide.with(context).load(position).into(holder.imageView);

         holder.imageView.setImageBitmap(bitmap);

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
        TextView txt_cost;
        TextView txt_size;
        ImageView imageView;


        public ViewHolder(View itemView) {
            super(itemView);

            txt_brand = itemView.findViewById(R.id.item_brand);
            txt_detail = itemView.findViewById(R.id.item_detail);
            txt_cost = itemView.findViewById(R.id.item_cost);
            txt_size = itemView.findViewById(R.id.item_size);
            imageView = itemView.findViewById(R.id.item_image);

        }
    }

    public interface ItemInfoRecyclerAdapterListener {
    }
}
