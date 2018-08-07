package com.example.shubham_pc.aptitudecracker;

/**
 * Created by Shubham_pc on 25/10/2017.
 */

// POGO Class for initializing each Question
public class Question {
    private int qid;
    private String question;
    private String option1; //Referes to the option provided by the user which is wrong
    private String answer;

    public Question(String question, String option1, String answer) {

        this.question = question;
        this.option1 = option1;
        this.answer = answer;
    }


    public String getOption1() {
        return option1;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
