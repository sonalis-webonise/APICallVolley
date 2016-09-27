package com.example.webonise.jsonparserdemo;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by webonise on 23/9/16.
 */
public class MyVolleyClass extends Application{

    private static MyVolleyClass volleyClass=null;
    private RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        volleyClass=this;
    }

    public static MyVolleyClass getVolleyClass(){
        return volleyClass;
    }

    public RequestQueue getRequestQueue(){
        if (requestQueue==null){
            requestQueue=Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request request){
        getRequestQueue().add(request);
    }
}
