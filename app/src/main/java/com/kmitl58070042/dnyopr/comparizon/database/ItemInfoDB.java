package com.kmitl58070042.dnyopr.comparizon.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.kmitl58070042.dnyopr.comparizon.model.ItemInfo;


@Database(entities = {ItemInfo.class}, version =2)
public abstract class ItemInfoDB extends RoomDatabase{
    public abstract ItemInfoDAO itemInfoDAO();
}
