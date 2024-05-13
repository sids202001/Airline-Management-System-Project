package com.example.frequentflier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity5 extends AppCompatActivity {
String award;
String awardid;
String url;
String url1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Spinner spinner4 = findViewById(R.id.spinner4);
        TextView textView9 = findViewById(R.id.textView9);
        TextView textView10 = findViewById(R.id.textView10);
        TextView textView11 = findViewById(R.id.textView11);
        TextView textView12 = findViewById(R.id.textView12);
        TextView textView13 = findViewById(R.id.textView13);
        RequestQueue queue = Volley.newRequestQueue(this);
        ArrayList<String> list = new ArrayList<String>();
        Intent intent = getIntent();
        String passid = intent.getStringExtra("passid");
        url = "http://10.0.2.2:8080/frequentflier/AwardIds.jsp?pid="+passid;
        StringRequest request1 = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String result = s.trim();
                String[] ids = result.split("#");
                for(int i =0;i<ids.length;i++) {
                    list.add(ids[i]);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity5.this,com.google.android.material.R.layout.support_simple_spinner_dropdown_item,list);
                spinner4.setAdapter(adapter);
            }
        },null);
        queue.add(request1);
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                award = parent.getSelectedItem().toString();
                url1 = "http://10.0.2.2:8080/frequentflier/RedemptionDetails.jsp?awardid="+award+"&pid="+passid;
                StringRequest request1 = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        String Result = s.trim();
                        String[] res = Result.split(",");
                        String des = res[0];
                        String points = res[1];
                        String date = "";
                        String exc = "";

                        textView9.setText("Prize Desc.\n"+des+"\nPoints Needed\n"+points);
                        textView10.setText("Redemption Date");
                        textView11.setText("Exchange Center");
                        String[] rows = Result.split("#");
                        for(int i =0;i<rows.length;i++){
                            String[] cols = rows[i].split(",");
                            date+=cols[2]+"\n";
                            exc+=cols[3]+"\n";
                        }
                        textView12.setText(date);
                        textView13.setText(exc);
                    }
                },null);
                queue.add(request1);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


    }
}