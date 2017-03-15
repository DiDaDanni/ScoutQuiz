package com.example.android.scoutquiz;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.android.scoutquiz.QuestionActivity.*;

public class MainFragment extends Fragment{

    boolean isTwoLoaded = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        switch (questionNumber ){
            case 1:
                return inflater.inflate(R.layout.question_one, container, false);
            case 2:
                isTwoLoaded = true;
                return inflater.inflate(R.layout.question_two, container, false);
            case 3:
                return inflater.inflate(R.layout.question_three, container, false);
            case 4:
                return inflater.inflate(R.layout.question_four, container, false);
            case 5:
                return inflater.inflate(R.layout.question_five, container, false);
            default:
                questionNumber = 1;
                return inflater.inflate(R.layout.question_one, container, false);
        }

    }

    public void onResume() {
        super.onResume();

        //if question 2 is active
        if (isTwoLoaded ) {
            //Toast.makeText(getActivity(), "reached", Toast.LENGTH_SHORT).show();
            isAnswerRightTwo();
        }
    }

    //Question 2
    public void isAnswerRightTwo(){
        questionTwoText = (EditText) getView().findViewById(R.id.quest2_editText);
        questionTwoText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0)
                    enableNextButtonState(true);
                else
                    enableNextButtonState(false);
            }

            @Override
            public void afterTextChanged(Editable s) {
                answerTwo = false;
                String answerTwoText = questionTwoText.getText().toString();
                //delete white spaces and convert to lower case
                answerTwoText = answerTwoText.replaceAll(" ","");
                answerTwoText = answerTwoText.toLowerCase();

                if (answerTwoText.equals(getResources().getString(R.string.quest2_answer))) {
                    answerTwo = true;
                    //Toast.makeText(getActivity(), "Right!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
