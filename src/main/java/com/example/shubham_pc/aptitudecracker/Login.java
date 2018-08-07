package com.example.shubham_pc.aptitudecracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    Button b1;
    TextView t1;
    EditText et_usr, et_pass;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#c5d5cb")));
        actionBar.setTitle(Html.fromHtml("<font color='#000000'>Apptitude Cracker</font>"));

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor("#c5d5cb"));

        }


        sharedPreferences = getSharedPreferences("MyPref", MODE_PRIVATE);
        String restoredText = sharedPreferences.getString("username", "no");
        if (!restoredText.equals("no")) {
            Bundle bundle = new Bundle();
            bundle.putString("username", restoredText);
            Intent intent = new Intent(Login.this, Main2Activity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

        Log.d("restored", restoredText);

        b1 = (Button) findViewById(R.id.b_login);
        et_usr = (EditText) findViewById(R.id.et_unme);
        et_pass = (EditText) findViewById(R.id.et_upass);

        t1 = (TextView) findViewById(R.id.textView);


        //*********************************** ALTERNATIVE**************************
        //  Intent intent = new Intent(Login.this, Main2Activity.class);
        // startActivity(intent);
        //***************************************************************************


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username, password;
                username = et_usr.getText().toString();
                password = et_pass.getText().toString();

                if (username.length() > 1 && password.length() > 1) {
                    String SEND_URL = "http://aptitudecracker.000webhostapp.com/login.php";

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, SEND_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("-1")) // Response codes sent via Server
                                Toast.makeText(Login.this, "Invalid Details", Toast.LENGTH_LONG).show();
                            else {
                                Toast.makeText(Login.this, "Welcome " + response, Toast.LENGTH_LONG).show();

//**********************************Session Management by Shared PReferences****************************************

                                sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.clear();
                                editor.putString("username", username);
                                editor.commit();
                                Bundle bundle = new Bundle();
                                bundle.putString("username", username);
                                Intent intent = new Intent(Login.this, Main2Activity.class);
                                intent.putExtras(bundle);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }


                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(Login.this, error.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("username", username);
                            params.put("password", password);

                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
                    requestQueue.add(stringRequest);

                }
            }
        });

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

    }
}
