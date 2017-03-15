package com.example.android.scoutquiz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import static com.example.android.scoutquiz.QuestionActivity.*;


public class AlertDialogFragment extends DialogFragment {

    String title, message;

    DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener(){
        @Override
        public void onClick(DialogInterface dialog, int value){
            //open next question
            getFragmentManager().beginTransaction().replace(R.id.question_frame, new MainFragment()).commit();
            enableNextButtonState(false);
        }
    };


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        switch(questionNumber){
            case 2: //quest 1
                if (answerOne) {
                    title = getResources().getString(R.string.right_answer);
                    message = getResources().getString(R.string.right_text);
                }
                else {
                    title = getResources().getString(R.string.wrong_answer);
                    message = getResources().getString(R.string.wrong_answer1_text);
                }
                    return new AlertDialog.Builder(getActivity())
                            .setTitle(title)
                            .setMessage(message)
                            .setPositiveButton("Next", onClickListener)
                            .create();

            case 3: //quest 2
                if (answerTwo) {
                    title = getResources().getString(R.string.right_answer);
                    message = getResources().getString(R.string.right_text);
                }
                else {
                    title = getResources().getString(R.string.wrong_answer);
                    message = getResources().getString(R.string.wrong_answer2_text);
                }
                return new AlertDialog.Builder(getActivity())
                        .setTitle(title)
                        .setMessage(message)
                        .setPositiveButton("Next", onClickListener)
                        .create();
            case 4: //quest 3
                if (answerThree) {
                    title = getResources().getString(R.string.right_answer);
                    message = getResources().getString(R.string.right_answer_3_text);
                }
                else {
                    title = getResources().getString(R.string.wrong_answer);
                    message = getResources().getString(R.string.wrong_answer3_text);
                }
                return new AlertDialog.Builder(getActivity())
                        .setTitle(title)
                        .setMessage(message)
                        .setPositiveButton("Next", onClickListener)
                        .create();
            case 5: //quest 4
                if (answerFour) {
                    title = getResources().getString(R.string.right_answer);
                    message = getResources().getString(R.string.right_text);
                }
                else {
                    title = getResources().getString(R.string.wrong_answer);
                    message = getResources().getString(R.string.wrong_answer4_text);
                }
                return new AlertDialog.Builder(getActivity())
                        .setTitle(title)
                        .setMessage(message)
                        .setPositiveButton("Next", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int value){
                                getFragmentManager().beginTransaction().replace(R.id.question_frame, new MainFragment()).commit();
                                //change text of nextButton for the last question
                                nextButton.setText(getResources().getString(R.string.submit_btn));
                                enableNextButtonState(false);
                            }
                        })
                        .create();
            case 6: //quest 5
                if (answerFive) {
                    title = getResources().getString(R.string.right_answer);
                    message = getResources().getString(R.string.right_text);
                }
                else {
                    title = getResources().getString(R.string.wrong_answer);
                    message = getResources().getString(R.string.wrong_answer5_text);
                }
                return new AlertDialog.Builder(getActivity())
                        .setTitle(title)
                        .setMessage(message)
                        .setPositiveButton("Next", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int value){
                                startActivity(new Intent(getActivity(), MainActivity.class));
                                getActivity().finish();
                            }
                        })
                        .create();
            case 1: //finish
                if (!answerOne || !answerTwo || !answerThree || !answerFour || !answerFive){
                    title = getResources().getString(R.string.well_done);
                    message = getResources().getString(R.string.well_done_text);
                }
                else{
                    title = getResources().getString(R.string.congrat);
                    message = getResources().getString(R.string.congrat_text);
                }
                return new AlertDialog.Builder(getActivity())
                        .setTitle(title)
                        .setMessage(message)
                        .setPositiveButton("CLOSE", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int value){
                                //close the app
                                questionNumber--;
                                getActivity().finish();
                            }
                        })
                        .create();
            default:
                return new AlertDialog.Builder(getActivity())
                        .setTitle("ERROR")
                        .setMessage("Ups, Something went wrong")
                        .setPositiveButton("CLOSE", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int value){
                                //close the app
                                getActivity().finish();
                            }
                        })
                        .create();

        }
    }

}
