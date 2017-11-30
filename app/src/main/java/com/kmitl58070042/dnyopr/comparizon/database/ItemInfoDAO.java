package com.kmitl58070042.dnyopr.comparizon.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.content.ClipData;

import com.kmitl58070042.dnyopr.comparizon.model.ItemInfo;

import java.util.List;

@Dao
public interface ItemInfoDAO {

    @Query("SELECT * FROM ITEM_INFO")
    List<ItemInfo> getAll();

    @Insert
    void insert(ItemInfo itemInfo);

    @Delete
    void delete(ItemInfo itemInfo);
}
