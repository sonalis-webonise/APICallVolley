package com.example.webonise.jsonparserdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class ActivityJson extends AppCompatActivity {

    private TextView textView;
    String url = "https://jsonplaceholder.typicode.com/comments";
    public final static String toastConnection = "No internet Connection, Please Connect to Network";
    StringRequest stringRequest;
    private ProgressDialog progress;

    private List<UserPost> postList;
    private RecyclerView recyclerView;
    private PostListAdapter postListAdapter;
    private boolean connected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_json);
        progress = new ProgressDialog(this);
        progress.setMessage("Loading");
        initView();
        addDataToView();

    }

    /**
     * API Call
     * Get the JSON from the URL and pass the response to JSonParser as param
     */
    private void addDataToView() {
        if (!isNetworkAvailable()) {
            Toast.makeText(getApplicationContext(), toastConnection, Toast.LENGTH_LONG).show();
        } else {
            progress.show();
            stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener() {
                @Override
                public void onResponse(Object response) {

//                progress.hide();
//                parseJson(response);
                    customJsonParser(response);
                    Log.d("Message", "Data loaded");
                    progress.hide();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), toastConnection, Toast.LENGTH_LONG);
                    Log.v("Message", "Error: " + error.getMessage());
                    progress.hide();
                }
            });
            makeAPICall();
        }
    }


    /**
     * @param response Get response object in param and get JSONArray contents and display it in TextView
     */
    private void customJsonParser(Object response) {
        try {
            JSONArray object = new JSONArray(response.toString());

            UserPost userPost;

            for (int i = 0; i < object.length(); i++) {
                userPost = new UserPost();
                userPost.setPostId(object.getJSONObject(i).getInt("postId"));
                userPost.setId(object.getJSONObject(i).getInt("id"));
                userPost.setName(object.getJSONObject(i).getString("name"));
                userPost.setEmail(object.getJSONObject(i).getString("email"));
                userPost.setBody(object.getJSONObject(i).getString("body"));
//                postList.addAll(post,id,name);

                postList.add(userPost);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        } else {
            connected = false;
        }
        return connected;
    }

    /**
     * Get Json file direct using Gson
     */
/*    private void parseJson(Object response) {
        Gson gson = new Gson();
        UserPost[] userPost = gson.fromJson(response.toString(), UserPost[].class);
        for (UserPost user:userPost){
            textView.append("\n " + user.getPostId() + "  " + user.getName());
        }
        textView.append(" ");
    }*/

    /**
     * Make an API call to add the request in queue in Volley Class
     */
    private void makeAPICall() {
        MyVolleyClass.getVolleyClass().addToRequestQueue(stringRequest);
        //stringRequest.setRetryPolicy(new DefaultRetryPolicy(2000, 1, 1));
    }

    private void initView() {
        postList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        postListAdapter = new PostListAdapter(postList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new SlideInLeftAnimator());
        recyclerView.setAdapter(postListAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
