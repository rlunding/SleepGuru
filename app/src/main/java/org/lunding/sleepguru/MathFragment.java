package org.lunding.sleepguru;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Random;
import java.util.Collection;

/**
 * Created by Lunding on 13/09/14.
 */
public class MathFragment extends Fragment {
    private static final String TAG = MathFragment.class.getSimpleName();
    private final int NUMBER_MAX_SIZE = 10;
    private Random r;
    private TextView question;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answerButtons[];
    private long startTime;
    private long times[];
    private boolean benchmark;

    private int answeredQuestions = 0;

    public static MathFragment newInstance(int questions, boolean benchmark){
        MathFragment f = new MathFragment();
        Bundle args = new Bundle();
        args.putInt("questions", questions);
        args.putBoolean("benchmark", benchmark);
        f.setArguments(args);
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_math, container, false);
        r = new Random();
        times = new long[getArguments().getInt("questions", TestActivity.DEFAULT_NUMBER_OF_QUESTIONS)];
        benchmark = getArguments().getBoolean("benchmark");

        question = (TextView) view.findViewById(R.id.math_question);
        answer1 = (Button) view.findViewById(R.id.answer1);
        answer2 = (Button) view.findViewById(R.id.answer2);
        answer3 = (Button) view.findViewById(R.id.answer3);
        answerButtons = new Button[]{answer1, answer2, answer3};
        for(int i = 0; i < answerButtons.length; i++){
            answerButtons[i].setOnClickListener(answerListener(i));
        }

        generateQuestion();

        Log.d(TAG, "Test fragment initialized");
        return view;
    }

    private OnClickListener answerListener(final int id){
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Answer: " + id + " time: " + (System.currentTimeMillis() - startTime) + " answered questions: " + answeredQuestions);
                times[answeredQuestions] = (System.currentTimeMillis() - startTime)/2;
                answeredQuestions++;
                if(answeredQuestions == times.length){
                    TestActivity.insertIntoDB(getActivity().getBaseContext(), times);

                    if(benchmark){
                        Intent intent = new Intent(v.getContext(), TestActivity.class);
                        intent.putExtra("BENCHMARK", true);
                        intent.putExtra("TEST-METHOD", XOFragment.class.getSimpleName());
                        startActivity(intent);
                        getActivity().finish();
                    } else {
                        Intent intent = new Intent(v.getContext(), ResultActivity.class);
                        intent.putExtra("TIMES", times);
                        startActivity(intent);
                        getActivity().finish();
                    }
                } else {
                    generateQuestion();
                }
            }
        };
    }

    private void generateQuestion(){
        int[] numbers = new int[6];
        for(int i = 0; i < 6; i++){
            numbers[i] = r.nextInt(NUMBER_MAX_SIZE)+1;
        }
        if(r.nextBoolean()){
            question.setText(numbers[0] + " + " + numbers[1]);
            ArrayList<String> answers = new ArrayList<String>();
            answers.add(String.valueOf(numbers[0] + numbers[1]));
            answers.add(String.valueOf(numbers[2] + numbers[3]));
            answers.add(String.valueOf(numbers[4] + numbers[5]));
            setAnswers(answers);
        } else {
            question.setText(String.valueOf(numbers[0] + numbers[1]));
            ArrayList<String> answers = new ArrayList<String>();
            answers.add(numbers[0] + " + " + numbers[1]);
            answers.add(numbers[2] + " + " + numbers[3]);
            answers.add(numbers[4] + " + " + numbers[5]);
            setAnswers(answers);
        }
        startTime = System.currentTimeMillis();
    }

    private void setAnswers(ArrayList<String> values){
        Collections.shuffle(values);
        for(int i = 0; i < answerButtons.length; i++){
            answerButtons[i].setText(values.get(i));
        }
    }
}
