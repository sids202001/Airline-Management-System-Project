package com.example.frequentflier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
//        editText for username
        EditText editText = findViewById(R.id.editText1);
//        editText for password
        EditText editText2 = findViewById(R.id.editText2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String user = editText.getText().toString();
                String pass = editText2.getText().toString();
//                http://localhost:8080/frequentflier/login?username=user1&password=user@01
                String url = "http://10.0.2.2:8080/frequentflier/login?username=" + user + "&password=" + pass;
                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        String result = s.trim();
                        if(result.contains("Yes")){
                            Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                            String[] auth = s.split(": ");
                            String pid = auth[1];
                            intent.putExtra("passid",pid);
                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity.this,"incorrect username or password",Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
                queue.add(request);
            }
        });
    }
}