package com.example.volleydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

import myutils.VolleyHelper;

public class VolleyCache extends AppCompatActivity {
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_cache);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

/*
    Cache takes 2 arguments:
        1. The directory of cache       ->      getCacheDir()
        2. Maximum cache size in Bytes  ->      1024*1024 = 1MB
 */
        Cache cache = new DiskBasedCache(getCacheDir(), 1024*1024);
        Network network = new BasicNetwork(new HurlStack());

/*
    Using RequestQueue of Volley which takes 2 arguments:
        1. The cache directory      -> declared above
        2. The network              -> declared above

 */
        final RequestQueue requestQueue = new RequestQueue(cache, network);
        requestQueue.start();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, VolleyHelper.URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                textView.setText(response);
                                requestQueue.stop();

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                textView.setText(R.string.error_occurred);
                                requestQueue.stop();
                                error.printStackTrace();
                            }
                        });

                requestQueue.add(stringRequest);
            }
        });

    }
}
