package com.olgazelenko.nycquiz;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private final String STATE_PLAYER_NAME = "PLAYER_NAME";
    private final String STATE_SCORE = "SCORE";
    private final String STATE_Q1 = "STATE_Q1";
    private final String STATE_Q2 = "STATE_Q2";
    private final String STATE_Q3 = "STATE_Q3";
    private final String STATE_Q4 = "STATE_Q4";
    private final String STATE_Q5 = "STATE_Q5";
    private final String STATE_Q6 = "STATE_Q6";
    private final String STATE_Q7 = "STATE_Q7";
    private final String STATE_Q8 = "STATE_Q8";
    private final String STATE_Q9 = "STATE_Q9";
    private final String STATE_Q10 = "STATE_Q10";
    private final String STATE_Q11 = "STATE_Q11";
    private final String STATE_Q12 = "STATE_Q12";
    private final String STATE_Q13 = "STATE_Q13";
    private final String STATE_Q14 = "STATE_Q14";
    private final String STATE_Q15 = "STATE_Q15";
    private RadioGroup q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, q15;
    private String playerName;
    private String[] correctAnswers;
    private int totalCorrect;
    private int questionNumber;

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //save checked radio buttons
        savedInstanceState.putString(STATE_PLAYER_NAME, playerName);
        savedInstanceState.putInt(STATE_Q1, q1.getCheckedRadioButtonId());
        savedInstanceState.putInt(STATE_Q2, q2.getCheckedRadioButtonId());
        savedInstanceState.putInt(STATE_Q3, q3.getCheckedRadioButtonId());
        savedInstanceState.putInt(STATE_Q4, q4.getCheckedRadioButtonId());
        savedInstanceState.putInt(STATE_Q5, q5.getCheckedRadioButtonId());
        savedInstanceState.putInt(STATE_Q6, q6.getCheckedRadioButtonId());
        savedInstanceState.putInt(STATE_Q7, q7.getCheckedRadioButtonId());
        savedInstanceState.putInt(STATE_Q8, q8.getCheckedRadioButtonId());
        savedInstanceState.putInt(STATE_Q9, q9.getCheckedRadioButtonId());
        savedInstanceState.putInt(STATE_Q10, q10.getCheckedRadioButtonId());
        savedInstanceState.putInt(STATE_Q11, q11.getCheckedRadioButtonId());
        savedInstanceState.putInt(STATE_Q12, q12.getCheckedRadioButtonId());
        savedInstanceState.putInt(STATE_Q13, q13.getCheckedRadioButtonId());
        savedInstanceState.putInt(STATE_Q14, q14.getCheckedRadioButtonId());
        savedInstanceState.putInt(STATE_Q15, q15.getCheckedRadioButtonId());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        playerName = savedInstanceState.getString(STATE_PLAYER_NAME);
        //restore checked radio buttons
        try {
            q1.check(savedInstanceState.getInt(STATE_Q1));
            q2.check(savedInstanceState.getInt(STATE_Q2));
            q3.check(savedInstanceState.getInt(STATE_Q3));
            q4.check(savedInstanceState.getInt(STATE_Q4));
            q5.check(savedInstanceState.getInt(STATE_Q5));
            q6.check(savedInstanceState.getInt(STATE_Q6));
            q7.check(savedInstanceState.getInt(STATE_Q7));
            q8.check(savedInstanceState.getInt(STATE_Q8));
            q9.check(savedInstanceState.getInt(STATE_Q9));
            q10.check(savedInstanceState.getInt(STATE_Q10));
            q11.check(savedInstanceState.getInt(STATE_Q11));
            q12.check(savedInstanceState.getInt(STATE_Q12));
            q13.check(savedInstanceState.getInt(STATE_Q13));
            q14.check(savedInstanceState.getInt(STATE_Q14));
            q15.check(savedInstanceState.getInt(STATE_Q15));

        } catch (Exception e) {
            Log.v(getString(R.string.quiz_activity_name), getString(R.string.LOG_ONRESTORE_ERROR));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = R.layout.activity_quiz;
        setContentView(layoutId);
        initialStates(getIntent());
    }

    /**
     * This method is called from the onCreate method and initial all the global variables.
     */
    private void initialStates(Intent intent) {
        playerName = intent.getStringExtra(STATE_PLAYER_NAME);
        totalCorrect = 0;
        questionNumber = 0;
        //RadioButton
        q1 = findViewById(R.id.q1);
        q2 = findViewById(R.id.q2);
        q3 = findViewById(R.id.q3);
        q4 = findViewById(R.id.q4);
        q5 = findViewById(R.id.q5);
        q6 = findViewById(R.id.q6);
        q7 = findViewById(R.id.q7);
        q8 = findViewById(R.id.q8);
        q9 = findViewById(R.id.q9);
        q10 = findViewById(R.id.q10);
        q11 = findViewById(R.id.q11);
        q12 = findViewById(R.id.q12);
        q13 = findViewById(R.id.q13);
        q14 = findViewById(R.id.q14);
        q15 = findViewById(R.id.q15);


        FillCorrectAnswersList();
    }

    private void FillCorrectAnswersList() {
        correctAnswers = getResources().getStringArray(R.array.correctAnswersArray);
    }


    public void submit(View view) {
        totalCorrect = getTotalCorrectAnswers();
        if (totalCorrect < 0)
            return;
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(STATE_PLAYER_NAME, playerName);
        intent.putExtra(STATE_SCORE, totalCorrect);
        startActivity(intent);
    }

    private int getTotalCorrectAnswers() {
        questionNumber = 0;
        try {
            checkRadioButtonAnswer(q1);
            checkRadioButtonAnswer(q2);
            checkRadioButtonAnswer(q3);
            checkRadioButtonAnswer(q4);
            checkRadioButtonAnswer(q5);
            checkRadioButtonAnswer(q6);
            checkRadioButtonAnswer(q7);
            checkRadioButtonAnswer(q8);
            checkRadioButtonAnswer(q9);
            checkRadioButtonAnswer(q10);
            checkRadioButtonAnswer(q11);
            checkRadioButtonAnswer(q12);
            checkRadioButtonAnswer(q13);
            checkRadioButtonAnswer(q14);
            checkRadioButtonAnswer(q15);


            return totalCorrect;

        } catch (Exception e) {
            Toast.makeText(this, R.string.validation_error, Toast.LENGTH_SHORT).show();
            return -1;
        }
    }

    private void checkRadioButtonAnswer(RadioGroup rg) {
        String answer = getCheckedRadioButtonId(rg).getText().toString();
        if (answer.equals(correctAnswers[questionNumber]))
            totalCorrect++;
        questionNumber++;
    }

    private RadioButton getCheckedRadioButtonId(RadioGroup rg) {
        return (RadioButton) findViewById(rg.getCheckedRadioButtonId());
    }

}
