package com.kmitl58070042.dnyopr.comparizon.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HP_PC01 on 20/11/2560.
 */

@Entity(tableName = "ITEM_INFO")
public class ItemInfo implements Parcelable {

    public ItemInfo() {
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String brand;

    @ColumnInfo
    private String detail;

    @ColumnInfo
    private float cost;

    @ColumnInfo
    private float size;

    protected ItemInfo(Parcel in) {
        id = in.readInt();
        brand = in.readString();
        detail = in.toString();
        cost = in.readFloat();
        size = in.readFloat();
    }

    public static final Creator<ItemInfo> CREATOR = new Creator<ItemInfo>() {
        @Override
        public ItemInfo createFromParcel(Parcel in) {
            return new ItemInfo(in);
        }

        @Override
        public ItemInfo[] newArray(int size) {
            return new ItemInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(brand);
        parcel.writeString(detail);
        parcel.writeFloat(cost);
        parcel.writeFloat(size);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

}
