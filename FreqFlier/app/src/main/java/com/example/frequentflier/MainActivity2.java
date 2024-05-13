package com.example.frequentflier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        TextView textView3 = findViewById(R.id.textView3);
        TextView textView4 = findViewById(R.id.textView4);
        ImageView imageView = findViewById(R.id.imageView);
        Intent intent = getIntent();
        String passid = intent.getStringExtra("passid");
        RequestQueue queue = Volley.newRequestQueue(this);
//        what do you want to access on the server
//        10.0.2.2 -- local host ip address decided by google to launch wesite on their weboage
        String url1 = "http://10.0.2.2:8080/frequentflier/Info.jsp?passid=" + passid;
        //textView3.setText("Welcome"+passid);
        StringRequest request1 = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
            String result = s.trim();
            String[] content = result.split("#");
            String[] content1 = content[0].split(",");
            textView3.setText("Welcome back "+content1[0]);
            textView4.setText(content1[1]);
            }
        },null);
        queue.add(request1);
        String url2 = "http://10.0.2.2:8080/frequentflier/images/"+passid+".jpg";
        ImageRequest request2 = new ImageRequest(url2, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }
        },0,0,null,null);
        queue.add(request2);

        button2.setOnClickListener(new View.OnClickListener() {
//            button for flight details
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity2.this,MainActivity3.class);
                intent2.putExtra("passid",passid);
                startActivity(intent2);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
//            button for all flights
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(MainActivity2.this,MainActivity4.class);
                intent3.putExtra("passid",passid);
                startActivity(intent3);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
//            button for all awards
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(MainActivity2.this,MainActivity5.class);
                intent4.putExtra("passid",passid);
                startActivity(intent4);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(MainActivity2.this,MainActivity6.class);
                intent5.putExtra("passid",passid);
                startActivity(intent5);
            }
        });

    }

}