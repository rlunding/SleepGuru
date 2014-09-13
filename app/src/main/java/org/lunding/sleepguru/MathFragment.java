package org.lunding.sleepguru;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Collection;

/**
 * Created by Lunding on 13/09/14.
 */
public class MathFragment extends Fragment {
    private static final String TAG = MathFragment.class.getSimpleName();
    private Random r;
    private TextView question;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private long startTime;
    private long times[] = new long[5];

    private int answeredQuestions = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_math, container, false);
        r = new Random();

        question = (TextView) view.findViewById(R.id.math_question);
        answer1 = (Button) view.findViewById(R.id.answer1);
        answer1.setOnClickListener(answerListener(1));
        answer2 = (Button) view.findViewById(R.id.answer2);
        answer2.setOnClickListener(answerListener(2));
        answer3 = (Button) view.findViewById(R.id.answer3);
        answer3.setOnClickListener(answerListener(3));

        generateQuestion();

        Log.d(TAG, "Test fragment initialized");
        return view;
    }

    private OnClickListener answerListener(final int id){
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Answer: " + id + " time: " + (System.currentTimeMillis() - startTime) + " answered questions: " + answeredQuestions);
                times[answeredQuestions] = System.currentTimeMillis() - startTime;
                answeredQuestions++;
                if(answeredQuestions == 5){
                    Intent intent = new Intent(new Intent(v.getContext(), ResultActivity.class));
                    intent.putExtra("TIMES", times);
                    startActivity(intent);
                } else {
                    generateQuestion();
                }
            }
        };
    }

    private void generateQuestion(){
        int[] numbers = new int[6];
        for(int i = 0; i < 6; i++){
            numbers[i] = r.nextInt(20)+1;
        }
        if(r.nextInt(10) < 5){
            question.setText(numbers[0] + " + " + numbers[1]);
            ArrayList<String> answers = new ArrayList<String>();
            answers.add(String.valueOf(numbers[0] + numbers[1]));
            answers.add(String.valueOf(numbers[2] + numbers[3]));
            answers.add(String.valueOf(numbers[4] + numbers[5]));
            Collections.shuffle(answers);
            answer1.setText(answers.get(0));
            answer2.setText(answers.get(1));
            answer3.setText(answers.get(2));
        } else {
            question.setText(String.valueOf(numbers[0] + numbers[1]));
            ArrayList<String> answers = new ArrayList<String>();
            answers.add(numbers[0] + " + " + numbers[1]);
            answers.add(numbers[2] + " + " + numbers[3]);
            answers.add(numbers[4] + " + " + numbers[5]);
            Collections.shuffle(answers);
            answer1.setText(answers.get(0));
            answer2.setText(answers.get(1));
            answer3.setText(answers.get(2));
        }
        startTime = System.currentTimeMillis();
    }
}
