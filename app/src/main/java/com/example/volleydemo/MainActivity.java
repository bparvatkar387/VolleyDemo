package com.example.volleydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import myutils.VolleyHelper;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;
/*
WARNING: When using local ip make sure that you run it on an EMULATOR
        The local ip of PC/Laptop wont work on an android device and will give
        TimeoutError

IMPORTANT: To resolve errors regarding ClearText HTTP traffic request
            Add the network_security_config file in res/xml
            add android:networkSecurityConfig="@xml/network_security_config" in manifest file

*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context;

/*
    RequestQueue takes following arguments:
        Context

    StringRequest takes the following arguments:
        1. Request method type(Post, get):
            Request.Method.POST

        2. URL of the php file

        3. Listener when the response is fetched successfully
            new Response.Listener<String>{
                @Override
                public void onResponse(String response) {

                }
            }

        4. ErrorListener when the response fetch failed
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }

*/
                final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
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
                                error.printStackTrace();
                                requestQueue.stop();

                            }
                        });

//Adding the stringRequest to requestQueue
                requestQueue.add(stringRequest);
            }
        });

    }
}
