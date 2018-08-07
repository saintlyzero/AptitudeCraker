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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String SEND_URL1 = "http://aptitudecracker.000webhostapp.com/maths_getData.php";
    RecyclerView recyclerView;
    List<Question> questionList;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#bcd5d1")));
        actionBar.setTitle(Html.fromHtml("<font color='#ffffff'>Practise makes it perfect!</font>"));

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor("#bcd5d1"));

        }

        final int newMathsLevel, newVerbalLevel, newLogicalLevel;
        final int currentMathsLevel, currentVerbalLevel, currentLogicalLevel;
        final int mathsQid, verbalQid, logicalQid;

        Bundle bundle = getIntent().getExtras();
        ArrayList<String> question = bundle.getStringArrayList("wrongQuestion");
        ArrayList<String> uAns = bundle.getStringArrayList("correctAnswer");
        ArrayList<String> answer = bundle.getStringArrayList("userAnswer");
        final String username = bundle.getString("username");
        int score1 = bundle.getInt("score");
        final int mathsScore = bundle.getInt("mathsCorrect");
        final int verbalScore = bundle.getInt("verbalCorrect");
        final int logicalScore = bundle.getInt("logicalCorrect");

        mathsQid = bundle.getInt("mathsQid");
        verbalQid = bundle.getInt("verbalQid");
        logicalQid = bundle.getInt("logicalQid");

        currentMathsLevel = bundle.getInt("mathsLevel");
        currentVerbalLevel = bundle.getInt("verbalLevel");
        currentLogicalLevel = bundle.getInt("logicalLevel");


        tv1 = (TextView) findViewById(R.id.score);

        tv1.setText("Score: " + score1 + "/15");

        questionList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recylcerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i < question.size(); i++)
            questionList.add(new Question(question.get(i), uAns.get(i), answer.get(i)));


        QuestionAdapter adapter = new QuestionAdapter(MainActivity.this, questionList);
        recyclerView.setAdapter(adapter);

        final String timeStamp = DateFormat.getDateTimeInstance().format(new Date());
        Log.d("TimeStamp", timeStamp);

        newMathsLevel = levelGenerator(currentMathsLevel, mathsScore);
        newVerbalLevel = levelGenerator(currentVerbalLevel, verbalScore);
        newLogicalLevel = levelGenerator(currentLogicalLevel, logicalScore);


        String SEND_URL1 = "http://aptitudecracker.000webhostapp.com/updateAll.php";

        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, SEND_URL1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                Log.d("serverResponse", response);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("timestamp", timeStamp);

                params.put("newMathsLevel", newMathsLevel + "");
                params.put("newVerbalLevel", newVerbalLevel + "");
                params.put("newLogicalLevel", newLogicalLevel + "");

                params.put("mathsPointer", mathsQid + "");
                params.put("verbalPointer", verbalQid + "");
                params.put("logicalPointer", logicalQid + "");

                params.put("currentMathsLevel", currentMathsLevel + "");
                params.put("currentVerbalLevel", currentVerbalLevel + "");
                params.put("currentLogicalLevel", currentLogicalLevel + "");

                params.put("mathsCorrect", mathsScore + "");
                params.put("verbalCorrect", verbalScore + "");
                params.put("logicalCorrect", logicalScore + "");


                return params;
            }
        };
        RequestQueue requestQueue1 = Volley.newRequestQueue(MainActivity.this);
        requestQueue1.add(stringRequest1);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout:

                SharedPreferences sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(MainActivity.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

            case R.id.reload:
                Intent intent1 = new Intent(MainActivity.this, Login.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    // Logic for generating new Level for the user
    int levelGenerator(int currentLevel, int score) {
        int finalLevel = currentLevel;
        if (currentLevel == 1) {
            if (score < 3)
                finalLevel = 1;
            else if (score == 3)
                finalLevel = currentLevel;
            else if (score > 3)
                finalLevel = currentLevel + 1;
        } else if (currentLevel == 2) {
            if (score < 3)
                finalLevel = currentLevel - 1;
            else if (score == 3)
                finalLevel = currentLevel;
            else if (score > 3)
                finalLevel = currentLevel + 1;
        } else if (currentLevel == 3) {
            if (score < 3)
                finalLevel = currentLevel - 1;
            else
                finalLevel = currentLevel;

        }

        return finalLevel;
    }

}
