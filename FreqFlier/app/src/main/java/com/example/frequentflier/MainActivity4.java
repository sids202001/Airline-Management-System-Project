package com.example.frequentflier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {
String url;
String flight;
String url1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        RequestQueue queue = Volley.newRequestQueue(this);
        ArrayList<String> list = new ArrayList<String>();
        Intent intent = getIntent();
        String passid = intent.getStringExtra("passid");
        Spinner spinner3 = findViewById(R.id.spinner3);
        TextView textView29 = findViewById(R.id.textView29);
        TextView textView30 = findViewById(R.id.textView30);
        TextView textView31 = findViewById(R.id.textView31);
        TextView textView32 = findViewById(R.id.textView32);
        TextView textView33 = findViewById(R.id.textView33);
        url = "http://10.0.2.2:8080/frequentflier/Flights.jsp?pid="+passid;
        StringRequest request1 = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String result = s.trim();
                String[] rows = result.split("#");
                for(int i =0;i<rows.length;i++) {
                    String[] cols = rows[i].split(",");
                    list.add(cols[0]);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity4.this,com.google.android.material.R.layout.support_simple_spinner_dropdown_item,list);
                spinner3.setAdapter(adapter);
            }
        },null);
        queue.add(request1);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                flight = parent.getSelectedItem().toString();
                url1 ="http://10.0.2.2:8080/frequentflier/FlightDetails.jsp?flightid="+flight;
                StringRequest request2 = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        String Result = s.trim();
                        String[] res = Result.split(",");
                        String dep = res[1];
                        String arr = res[2];
                        String mil = res[3];
                        String Tripid = "";
                        String Tripmil = "";

                        textView30.setText("Departure: "+dep+"\nArrival: "+arr+"\nMiles: "+mil);
                        textView29.setText("Trip Id");
                        textView31.setText("Trip Miles");
                        String[] rows = Result.split("#");
                        for(int i =0;i<rows.length;i++){
                            String[] cols = rows[i].split(",");
                            Tripid+=cols[4]+"\n";
                            Tripmil+=cols[5]+"\n";
                        }
                        textView32.setText(Tripid);
                        textView33.setText(Tripmil);
                    }
                },null);
                queue.add(request2);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

}