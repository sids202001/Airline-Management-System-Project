package com.example.frequentflier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity6 extends AppCompatActivity {
    String points;
    String dpassid;
    String dpass;
    private String passid;
    private String url2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        Button button = findViewById(R.id.button7);
        EditText editText1 = findViewById(R.id.editText1);
        Spinner spinner = findViewById(R.id.spinner);
        RequestQueue queue = Volley.newRequestQueue(this);
        ArrayList<String> list = new ArrayList<String>();
        Intent intent = getIntent();
        String passid = intent.getStringExtra("passid");
        TextView textView8 = findViewById(R.id.textView8);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                dpassid=parent.getSelectedItem().toString();
                dpass = dpassid;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                points = editText1.getText().toString();
                url2 = "http://10.0.2.2:8080/frequentflier/TransferPoints.jsp?spid="+passid+"&dpid="+dpass+"&npoints="+points;
                StringRequest request2 = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        String Result = s.trim();
                        Toast.makeText(MainActivity6.this, Result, Toast.LENGTH_SHORT).show();
                    }
                },null);
                queue.add(request2);
            }
        });
        String url1 = "http://10.0.2.2:8080/frequentflier/GetPassengerids.jsp?pid="+passid;
        StringRequest request1 = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String result = s.trim();
                String[] ids = result.split("#");
                for(int i =0;i<ids.length;i++) {
                    list.add(ids[i]);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity6.this,com.google.android.material.R.layout.support_simple_spinner_dropdown_item,list);
                spinner.setAdapter(adapter);
            }
        },null);
        queue.add(request1);
    }
}