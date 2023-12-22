package be.ehb.charging_points_brussel.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import be.ehb.charging_points_brussel.model.ChargingDatabase;
import be.ehb.charging_points_brussel.model.ChargingEntityTable;

public class DataViewModel extends AndroidViewModel{
    private ExecutorService mExecutorService;
    private ChargingDatabase mDatabase;

    public DataViewModel(@NonNull Application application) {
        super(application);
        mExecutorService = Executors.newFixedThreadPool(2);
        mDatabase = ChargingDatabase.getINSTANCE(application);
    }

    // insert data
    public void insertData(ChargingEntityTable f){                                                  //--> insert a ChargingEntityTable object into the database
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.getDataDAO().insertData(f);
            }
        });
    }

    public LiveData<List<ChargingEntityTable>> getAllData(){                                        //--> returns a LiveData object containing a list of ChargingEntityTable objects
        return mDatabase.getDataDAO().getAllData();
    }
}
