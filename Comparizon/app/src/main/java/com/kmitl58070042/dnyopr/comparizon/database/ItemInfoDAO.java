package com.kmitl58070042.dnyopr.comparizon.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.kmitl58070042.dnyopr.comparizon.model.ItemInfo;

import java.util.List;

@Dao
public interface ItemInfoDAO {

    @Query("SELECT * FROM ITEM_INFO")
    List<ItemInfo> getAll();

    @Insert
    void insert(ItemInfo itemInfo);
}
