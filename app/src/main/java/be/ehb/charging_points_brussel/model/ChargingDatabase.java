package be.ehb.charging_points_brussel.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// 3.
@Database(version = 1, entities = {ChargingEntityTable.class})
public abstract class ChargingDatabase extends RoomDatabase {
    private static ChargingDatabase INSTANCE;

    public static ChargingDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            // maak database verbinding
            INSTANCE = Room.databaseBuilder(context,
                    ChargingDatabase.class,
                    "database")         // moet dezelfde naam zijn als in de DAO zo ja welke?
                    .build();
        }
        return INSTANCE;
    }
    public abstract DAO getDataDAO();
}
