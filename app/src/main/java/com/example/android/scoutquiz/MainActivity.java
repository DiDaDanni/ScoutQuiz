package com.example.android.scoutquiz;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static com.example.android.scoutquiz.QuestionActivity.answerThree;
import static com.example.android.scoutquiz.QuestionActivity.questionNumber;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartButtonClick(View view){

        startActivity(new Intent(this, QuestionActivity.class));
    }

    public void onResume(){
        super.onResume();
        if (questionNumber == 6){
            questionNumber = 1;

            DialogFragment finishDialog = new AlertDialogFragment();
            finishDialog.show(getFragmentManager(), "finish");
        }
        else if (questionNumber == 0) {
            answerThree = false;
            questionNumber = 1;
            finish();
        }
    }
}
