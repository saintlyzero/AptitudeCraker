package com.example.shubham_pc.aptitudecracker;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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

public class Register extends AppCompatActivity {
    Button b1;
    EditText et_name, et_usr, et_pass, et_cpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final String SEND_URL = "http://<Your-Website>/register.php";

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e3e0cf")));
        actionBar.setTitle(Html.fromHtml("<font color='#3fb0ac'>Apptitude Cracker</font>"));

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor("#e3e0cf"));

        }

        b1 = (Button) findViewById(R.id.bt_register);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_name = (EditText) findViewById(R.id.et_name);
                et_usr = (EditText) findViewById(R.id.et_uname);
                et_pass = (EditText) findViewById(R.id.et_pass);
                et_cpass = (EditText) findViewById(R.id.et_confirmPass);


                final String name, username, pass, cpass;
                name = et_name.getText().toString();
                username = et_usr.getText().toString();
                pass = et_pass.getText().toString();
                cpass = et_cpass.getText().toString();
                if (pass.equals(cpass)) // Password & Confirm password match
                {


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, SEND_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(Register.this, response, Toast.LENGTH_LONG).show();

                            try {
                                //TODO: Display Message if User is properly registered

                            } catch (Exception e) {
                                Toast.makeText(Register.this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(Register.this, error.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("name", name);
                            params.put("username", username);
                            params.put("password", pass);
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(Register.this);
                    requestQueue.add(stringRequest);

                } else {
                    Toast.makeText(Register.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
