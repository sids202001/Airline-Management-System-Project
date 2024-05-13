package com.example.frequentflier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
String url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        TextView textView18 = findViewById(R.id.textView18);
        TextView textView19 = findViewById(R.id.textView19);
        TextView textView20 = findViewById(R.id.textView20);
        RequestQueue queue = Volley.newRequestQueue(this);
        ArrayList<String> list = new ArrayList<String>();
        Intent intent = getIntent();
        String passid = intent.getStringExtra("passid");
        url = "http://10.0.2.2:8080/frequentflier/Flights.jsp?pid="+passid;
        StringRequest request1 = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String Result = s.trim();
                String output1 = "";
                String output2 = "";
                String output3 = "";
                String[] rows = Result.split("#");
                for(int i =0;i<rows.length;i++){
                    String[] cols = rows[i].split(",");
                    output1+=cols[0]+"\n";
                    output2+=cols[1]+"\n";
                    output3+=cols[2]+"\n";
                }
                textView18.setText(output1);
                textView19.setText(output2);
                textView20.setText(output3);
            }
        },null);
        queue.add(request1);
    }
}