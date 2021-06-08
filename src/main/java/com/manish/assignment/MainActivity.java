package com.manish.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.manish.assignment.db.Appdatabase;
import com.manish.assignment.db.User;
import com.manish.assignment.pojo.MyListData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Appdatabase appdatabase = Appdatabase.getDbInstance(getApplicationContext());

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Button button = findViewById(R.id.button);

        String URL= "https://restcountries.eu/rest/v2/all";
        RequestQueue referenceQueue  = Volley.newRequestQueue(this);

        JsonArrayRequest objectRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                if (jsonObject.get("region").toString().equals("Asia")) {
                                    String name = jsonObject.getString("name");
                                    String capital = jsonObject.getString("capital");
                                    String region = jsonObject.getString("region");
                                    String subRegion = jsonObject.getString("subregion");
                                    String flag = jsonObject.getString("flag");
                                    int populations = jsonObject.getInt("population");
                                    JSONArray borderArray = jsonObject.getJSONArray("borders");
                                    LinkedList<String> bordersList = new LinkedList<>();
                                    for (int j = 0; j < borderArray.length(); j++) {
                                        String str = borderArray.getString(j);
                                        bordersList.add(str);
                                    }
                                    JSONArray langArray = jsonObject.getJSONArray("languages");
                                    LinkedList<String> languageList = new LinkedList<>();
                                    for (int j = 0; j < langArray.length(); j++) {
                                        JSONObject object = langArray.getJSONObject(j);
                                        languageList.add(object.getString("name"));
                                    }
                                    if (!appdatabase.userDao().isCountryNAmeExists(name)) {
                                        saveData(name, capital, region, subRegion, flag, languageList, populations, bordersList);
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        LinkedList<MyListData> myListData = new LinkedList<>();
                        for (User user : appdatabase.userDao().getAllUsers()) {
                            myListData.add(new MyListData(user.name, user.flag));
                        }
                        Adapter adapter = new Adapter(myListData, getApplicationContext());
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Api Error", error.toString());

                    }
                }
        );

        referenceQueue.add(objectRequest);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                appdatabase.userDao().deleteAll();
                appdatabase.clearAllTables();
            }
        });

    }

    void saveData(String name, String capital, String region, String subRegion, String flag, LinkedList<String> languages, int populations, LinkedList<String> borders) {
        Appdatabase appdatabase = Appdatabase.getDbInstance(this.getApplicationContext());

        User user = new User();
        user.name = name;
        user.capital = capital;
        user.region = region;
        user.subregion = subRegion;
        user.flag = flag;
        user.languages = languages.toString();
        user.population = populations;
        user.borders = borders.toString();

        appdatabase.userDao().insertUser(user);

    }


}