package com.example.shubham_pc.aptitudecracker;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends ActionBarActivity {
    TextView tv_qstn, tv_id;
    ProgressBar pb;
    CardView cv;
    Handler handler;
    RadioButton rb_1, rb_2, rb_3, rb_4, rb_5;
    RadioGroup radioGroup;
    Button btn_next, bt_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String SEND_URL1 = "http://<Your-Website>/maths_getData.php";
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e9ece5")));


        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor("#e9ece5"));

        }
        cv = (CardView) findViewById(R.id.cardView);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(View.VISIBLE);
        Bundle bundle = getIntent().getExtras();
        final String username = bundle.getString("username");
        actionBar.setTitle(Html.fromHtml("<font color='#000000'>  Welcome " + username + "</font>"));

        handler = new Handler();
        final Runnable r = new Runnable() {

            @Override
            public void run() {


            }

        };


        final ArrayList<String> questions = new ArrayList<String>();
        final ArrayList<String> questionID = new ArrayList<String>();
        final ArrayList<String> option1 = new ArrayList<String>();
        final ArrayList<String> option2 = new ArrayList<String>();
        final ArrayList<String> option3 = new ArrayList<String>();
        final ArrayList<String> option4 = new ArrayList<String>();
        final ArrayList<String> answer = new ArrayList<String>();
        final ArrayList<String> wrongQuestion = new ArrayList<String>();
        final ArrayList<String> correctAnswer = new ArrayList<String>();
        final ArrayList<String> userAnswer = new ArrayList<String>();
        final int[] mathsLevel = new int[1];
        final int[] logicalLevel = new int[1];
        final int[] verbalLevel = new int[1];
        final int[] mathsQuestionPointer = new int[1];
        final int[] logicalQuestionPointer = new int[1];
        final int[] verbalQuestionPointer = new int[1];


        tv_qstn = (TextView) findViewById(R.id.tv_question);
        tv_id = (TextView) findViewById(R.id.qno);
        rb_1 = (RadioButton) findViewById(R.id.rb1);
        rb_2 = (RadioButton) findViewById(R.id.rb2);
        rb_3 = (RadioButton) findViewById(R.id.rb3);
        rb_4 = (RadioButton) findViewById(R.id.rb4);
        radioGroup = (RadioGroup) findViewById(R.id.rg1);
        btn_next = (Button) findViewById(R.id.bt_next);
        bt_submit = (Button) findViewById(R.id.submit);

        final int[] count = {0};
        final int[] wrongCount = {0};
        final int[] correctCount = {0};
        final int[] arrayCounter = {0};
        final int[] mathsScore = {0};
        final int[] verbalScore = {0};
        final int[] logicalScore = {0};

        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, SEND_URL1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject serverData = array.getJSONObject(i);
                        questionID.add(serverData.getString("qid"));
                        questions.add(serverData.getString("question"));
                        option1.add(serverData.getString("option1"));
                        option2.add(serverData.getString("option2"));
                        option3.add(serverData.getString("option3"));
                        option4.add(serverData.getString("option4"));
                        answer.add(serverData.getString("answer"));
                        if (i == 4) {
                            mathsLevel[0] = Integer.parseInt(serverData.getString("level"));
                            mathsQuestionPointer[0] = Integer.parseInt(serverData.getString("qid"));
                        } else if (i == 9) {
                            verbalLevel[0] = Integer.parseInt(serverData.getString("level"));
                            verbalQuestionPointer[0] = Integer.parseInt(serverData.getString("qid"));
                        } else if (i == 14) {
                            logicalLevel[0] = Integer.parseInt(serverData.getString("level"));
                            logicalQuestionPointer[0] = Integer.parseInt(serverData.getString("qid"));
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Log.d("MathsLevel", mathsLevel[0] + "\n" + "MathsPointer" + mathsQuestionPointer[0] + "\n" + verbalLevel[0] + "\n" + "VerbalPointer" + verbalQuestionPointer[0] + "\n" + logicalLevel[0] + "\n" + "logicalPointer" + logicalQuestionPointer[0]);

                //Initializing the First Page of the quiz

                tv_id.setText(count[0] + 1 + "");
                tv_qstn.setText(questions.get(0));
                rb_1.setText(option1.get(0));
                rb_2.setText(option2.get(0));
                rb_3.setText(option3.get(0));
                rb_4.setText(option4.get(0));
                count[0]++;
                pb.setVisibility(View.INVISIBLE);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Main2Activity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);


                return params;
            }
        };
        RequestQueue requestQueue1 = Volley.newRequestQueue(Main2Activity.this);
        requestQueue1.add(stringRequest1);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count[0] < 15) {
                    int radioID = radioGroup.getCheckedRadioButtonId();
                    rb_5 = (RadioButton) findViewById(radioID);


                    if (!rb_5.getText().toString().equals(answer.get(arrayCounter[0]))) {

                        wrongQuestion.add(questions.get(arrayCounter[0]));
                        correctAnswer.add(answer.get(arrayCounter[0]));
                        userAnswer.add((String) rb_5.getText());
                        wrongCount[0]++;

                    } else {
                        correctCount[0]++;

                        if (arrayCounter[0] >= 0 && arrayCounter[0] <= 4)
                            mathsScore[0]++;
                        else if (arrayCounter[0] >= 5 && arrayCounter[0] <= 9)
                            verbalScore[0]++;
                        else if (arrayCounter[0] >= 10 && arrayCounter[0] <= 14)
                            logicalScore[0]++;

                    }
                    tv_id.setText(count[0] + 1 + "");
                    tv_qstn.setText(questions.get(count[0]));
                    rb_1.setText(option1.get(count[0]));
                    rb_2.setText(option2.get(count[0]));
                    rb_3.setText(option3.get(count[0]));
                    rb_4.setText(option4.get(count[0]));
                    count[0]++;
                    arrayCounter[0]++;

                } else if (count[0] == 15) {
                    int radioID = radioGroup.getCheckedRadioButtonId();
                    rb_5 = (RadioButton) findViewById(radioID);

                    if (!rb_5.getText().toString().equals(answer.get(arrayCounter[0]))) {

                        wrongQuestion.add(questions.get(arrayCounter[0]));
                        correctAnswer.add(answer.get(arrayCounter[0]));
                        userAnswer.add((String) rb_5.getText());

                        wrongCount[0]++;

                    } else {
                        correctCount[0]++;
                        logicalScore[0]++;

                    }
                    Toast.makeText(Main2Activity.this, "All Questions answered. Press Submit Button", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Main2Activity.this, "All Questions answered. Press Submit Button", Toast.LENGTH_LONG).show();
                }


            }
        });
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putInt("score", correctCount[0]);
                bundle.putStringArrayList("wrongQuestion", wrongQuestion);
                bundle.putStringArrayList("userAnswer", userAnswer);
                bundle.putStringArrayList("correctAnswer", correctAnswer);
                bundle.putInt("mathsCorrect", mathsScore[0]);
                bundle.putInt("verbalCorrect", verbalScore[0]);
                bundle.putInt("logicalCorrect", logicalScore[0]);
                bundle.putInt("mathsLevel", mathsLevel[0]);
                bundle.putInt("verbalLevel", verbalLevel[0]);
                bundle.putInt("logicalLevel", logicalLevel[0]);
                bundle.putInt("mathsQid", mathsQuestionPointer[0]);
                bundle.putInt("verbalQid", verbalQuestionPointer[0]);
                bundle.putInt("logicalQid", logicalQuestionPointer[0]);
                bundle.putString("username", username);


                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                intent.putExtras(bundle);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                Log.d("score", correctCount[0] + " ");
                Log.d("Maths", mathsScore[0] + " ");
                Log.d("Verbal", verbalScore[0] + " ");
                Log.d("Logical", logicalScore[0] + " ");

            }
        });

    }

}
