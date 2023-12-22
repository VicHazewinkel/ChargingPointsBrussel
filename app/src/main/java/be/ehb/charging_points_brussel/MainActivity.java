package be.ehb.charging_points_brussel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import be.ehb.charging_points_brussel.model.ChargingEntityTable;
import be.ehb.charging_points_brussel.viewmodel.DataViewModel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = findViewById(R.id.toolbar);                                              //--> application's app bar. Specified in activity_main.xml
        setSupportActionBar(mToolbar);


        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


        DataViewModel viewModel = new ViewModelProvider(this).get(DataViewModel.class);
        Thread thisWillRunInTheBackground = new Thread(new Runnable() {                             //--> background thread
            @Override
            public void run() {
                try {
                    OkHttpClient mClient = new OkHttpClient();                                      //--> GET request to the specified URL and retrieves the response body as a string

                    // https://guides.codepath.com/android/Using-OkHttp
                    // Picasso picasso = new Picasso.Builder(context).downloader(new OkHttp3Downloader(client)).build();

                    Request mRequest = new Request.Builder()
                            .url("https://opendata.bruxelles.be/api/explore/v2.1/catalog/datasets/bornes-de-recharge-pour-voitures-electriques/records?limit=50")               //--> GET request to the specified URL and retrieves the response body as a string
                            .get()
                            .build();

                    Response mResponse = mClient.newCall(mRequest).execute();                       //-->

                    String responseText = mResponse.body().string();                                //--> JSON response, extracts the "results" array, and iterates over its elements.

                    //responseText is een string omzetten naar een JSONobject
                    JSONObject object = new JSONObject(responseText);

                    // start the thread
                    JSONArray postsJSONArray = object.getJSONArray("results"); // responseText is een string
                    // en die wordt in postsJSONArray gestoken
                    int postJSONArrayLenth = postsJSONArray.length();   // postsJSONArray.length() is een int


                    for(int i = 0; i < postJSONArrayLenth; i++) {
                        JSONObject temp = postsJSONArray.getJSONObject(i);

                        ChargingEntityTable currentPost = new ChargingEntityTable(
                                //temp.getInt("id"),   //--> The parsed JSON data is used to create 'ChargingEntityTable' objects
                                temp.getString("typedut"),
                                temp.getString("gemeente"),
                                temp.getLong("cp"),
                                temp.getString("rue"),
                                temp.getLong("nr")
                        );

                        //Todo: controleren of de data in de database zit
                        // if (currentPost.getId() == 0) continue;                                     //--> If the 'id' field is 0, the object is skipped (this is the case for the first object in the array

                        viewModel.insertData(currentPost); //insertData van DAO                     //--> The 'ChargingEntityTable' objects are inserted into the database (inserted into a ViewModel)
                    }
                    //parse uw date


                } catch (Exception e) {                                                             //--> If anny error occurs, the stack trace is printed to the console
                    e.printStackTrace();
                }
            }
        });
        thisWillRunInTheBackground.start();



        //
        // show overview_fragment.xml in activity_main.xml
        // 'DataBass' #Fish kijken mogenlijke oplossing
        //

        }

        /*
            public boolean onSupportNavigateUp() {
                return NavigationUI.navigateUp(navController, appBarConfiguration)
                    || super.onSupportNavigateUp();
            }
         */
    }
