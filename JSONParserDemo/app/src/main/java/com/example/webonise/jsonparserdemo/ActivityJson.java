package com.example.webonise.jsonparserdemo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityJson extends AppCompatActivity {

    private TextView textView;
    String url = "https://jsonplaceholder.typicode.com/comments";
    StringRequest stringRequest;
    private ProgressDialog progress;

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
     *API Call
     * Get the JSON from the URL and pass the response to JSonParser as param
     */
    private void addDataToView() {
        progress.show();

        stringRequest = new StringRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/comments", new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                Log.d("Message", "Data loaded");
//                progress.hide();
//                parseJson(response);
                customJsonParser(response);
                progress.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("Message", "Error: " + error.getMessage());
                progress.hide();
            }
        });
        makeAPICall();
    }

    /**
     *
     * @param response
     * Get response object in param and get JSONArray contents and display it in TextView
     */
    private void customJsonParser(Object response) {
        try {
            JSONArray object = new JSONArray(response.toString());

            for (int i = 0; i < object.length(); i++) {
                int post = object.getJSONObject(i).getInt("postId");
                int id = object.getJSONObject(i).getInt("id");
                String name = object.getJSONObject(i).getString("name");
                String email = object.getJSONObject(i).getString("email");
                String body = object.getJSONObject(i).getString("body");
                textView.append("\nPost Id: " + post + "\nId: " + id + "\nName: " + name + "\nEmail: " + email + "\nBody: " + body + "\n");

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(2000, 1, 1));
    }

    private void initView() {
        textView = (TextView) findViewById(R.id.textView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        progress.dismiss();
    }
}
