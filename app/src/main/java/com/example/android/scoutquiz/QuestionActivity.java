package com.example.android.scoutquiz;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class QuestionActivity extends AppCompatActivity {

    MainFragment mainFragment;
    public static int questionNumber = 1;
    public static Button nextButton;
    public static EditText questionTwoText;
    boolean questionFourOne = false, questionFourTwo = false, questionFourThree = false, questionFourFour = false ;
    public static int idImageButton = 0;
    public static boolean answerOne = false, answerThree = false, answerFour = false, answerFive = false;
    public static boolean answerTwo = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_frame);

        nextButton = (Button)findViewById(R.id.next_btn);
        enableNextButtonState(false);

        if (savedInstanceState == null) {
            mainFragment = new MainFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.question_frame, mainFragment, "MainFragment");
            fragmentTransaction.commit();
        }

    }

    //navigate forward through the questions
    public void onNextClick(View view){

            if (++questionNumber <= 6) {
                //open alertdialog after every answer
                DialogFragment answerDialog = new AlertDialogFragment();
                answerDialog.show(getFragmentManager(), "answer");
            }
    }

    //Question 1
    public void onRadioButtonOneClick(View view){
        enableNextButtonState(true);
        if (view.getId() == R.id.question1_answer2) {
            answerOne = true;
            //Toast.makeText(this, "Right!", Toast.LENGTH_SHORT).show();
        }
        else
            answerOne = false;
    }

    //Question 3
    public void onImageButtonClick(View button) {

        //no button selected
        if (idImageButton == 0){
            button.setSelected(true);   //select
            idImageButton = button.getId();

            isAnswerRightThree(button);
            enableNextButtonState(true);
        }

        //one button already selected
        else{
            //if it's the already selected one -> deselect and reset id
            if (button.getId() == idImageButton){
                button.setSelected(false);
                idImageButton = 0;
                answerThree = false;
                enableNextButtonState(false);
            }
            //if it's another button
            else{
                button.setSelected(true);   //select new one
                int temp = button.getId();  //save new id
                ImageButton newButton = (ImageButton)findViewById(idImageButton);//set old button with old id
                newButton.setSelected(false);  //deselect old button
                idImageButton = temp;   //set new id

                answerThree = false;
                isAnswerRightThree(button);
                enableNextButtonState(true);
            }
        }
    }

    //check if the answer of question 3 is right
    public void isAnswerRightThree(View button){
        if (idImageButton == R.id.img_bipi){
            answerThree = true;
            //Toast.makeText(this, "Right!", Toast.LENGTH_SHORT).show();
        }
    }

    //Question 4
    public void onCheckboxClick(View box){
        boolean checked = ((CheckBox) box).isChecked();

        switch(box.getId()){
            case R.id.quest4_answer1:
                if (checked)
                    questionFourOne = true;
                else
                    questionFourOne = false;
                break;
            case R.id.quest4_answer2:
                if (checked)
                    questionFourTwo = true;
                else
                    questionFourTwo = false;
                break;
            case R.id.quest4_answer3:
                if (checked)
                    questionFourThree = true;
                else
                    questionFourThree = false;
                break;
            case R.id.quest4_answer4:
                if (checked)
                    questionFourFour = true;
                else
                    questionFourFour = false;
                break;
        }

        if (questionFourOne && questionFourFour && !questionFourTwo && !questionFourThree){
            answerFour = true;
            //Toast.makeText(this, "Right!", Toast.LENGTH_SHORT).show();
            enableNextButtonState(true);
        }
        else if (!questionFourOne && !questionFourFour && !questionFourTwo && !questionFourThree)
            enableNextButtonState(false);
        else{
            answerFour = false;
            //Toast.makeText(this, "False!", Toast.LENGTH_SHORT).show();
            enableNextButtonState(true);
        }

    }

    //Question 5
    public void onRadioButtonFiveClick(View view){
        enableNextButtonState(true);

        if (view.getId() == R.id.question5_answer3) {
            answerFive = true;
            //Toast.makeText(this, "Right!", Toast.LENGTH_SHORT).show();
        }
        else
            answerFive = false;
    }

    public static void enableNextButtonState(boolean state){
        nextButton.setEnabled(state);
    }
}
