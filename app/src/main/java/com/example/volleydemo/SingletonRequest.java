package com.example.volleydemo;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SingletonRequest {

    private static SingletonRequest singletonDemo;
    private RequestQueue requestQueue;
    private Context context;


    //Constructor will take the context of the application as argument
    //and set RequestQueue from getRequestQueue() method
    private SingletonRequest(Context context){
        this.context = context;
        this.requestQueue = getRequestQueue();
    }

/* If the RequestQueue is not initialized before
        i.e it was not used/called before than we will have to initialize it
    else just return it */

    private RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized SingletonRequest getInstance(Context context){

        if(singletonDemo == null){
            singletonDemo = new SingletonRequest(context);
        }
        return singletonDemo;
    }

    /*
        Get the StringRequest to RequestQueue
     */
    public<T> void addToRequestQueue(Request<T> request){
        requestQueue.add(request);
    }

}
