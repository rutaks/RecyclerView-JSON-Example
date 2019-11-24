package com.example.recyclerviewjsonexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.recyclerviewjsonexample.adapters.CardItemAdapter;
import com.example.recyclerviewjsonexample.models.CardItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CardItemAdapter cardItemAdapter;
    private ArrayList<CardItem> items;
    private RequestQueue requestQueue;
    private static final String API_KEY = "YOUR API KEY";
    private String url = "https://pixabay.com/api/?key=" + API_KEY + "&q=software&image_type=photo&pretty=true";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

        //Set Layout Details Of Our Recycler View
        setUpRecylerView();
        //Make JSON Request
        parseJSON(url, Request.Method.GET);
    }

    /**
     * Function To Set Size, View Layout & Layout Manager For the RecyclerView
     * @param null
     */
    public void setUpRecylerView(){
        recyclerView = findViewById(R.id.main_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Function To Make HTTP Request To The Pixabay API to easily obtain Images
     * & Their Details
     * @param url : String url of the request to be made
     * @param method : integer represent to Volley the request Type
     */
    public void parseJSON(String url, int method){
        JsonObjectRequest request = new JsonObjectRequest(method, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray arr = response.getJSONArray("hits");

                            for(int i = 0; i < arr.length(); i++){
                                JSONObject hit = arr.getJSONObject(i);

                                String creatorName = hit.getString("user");
                                String imgURL = hit.getString("webformatURL");
                                int likes = hit.getInt("likes");

                                items.add(new CardItem(imgURL,creatorName,likes));
                            }
                            cardItemAdapter = new CardItemAdapter(MainActivity.this, items);
                            recyclerView.setAdapter(cardItemAdapter);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        requestQueue.add(request);
    }
}
