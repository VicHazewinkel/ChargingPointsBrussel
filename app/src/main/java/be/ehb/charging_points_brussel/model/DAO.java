package be.ehb.charging_points_brussel.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

// 2.
@Dao
public interface DAO {
    @Insert
    void insertData(ChargingEntityTable f);

    @Query("SELECT * FROM ChargingEntityTable")                               // select all
    LiveData<List<ChargingEntityTable>> getAllData();

}
