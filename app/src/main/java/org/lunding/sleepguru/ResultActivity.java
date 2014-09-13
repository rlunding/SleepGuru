package org.lunding.sleepguru;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Collections;

/**
 * Created by Lunding on 13/09/14.
 */
public class ResultActivity extends Activity {

    private static final String TAG = ResultActivity.class.getSimpleName();
    private TextView resultText;
    private EditText inputTimeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultText = (TextView) findViewById(R.id.result_text);
        inputTimeText= (EditText) findViewById((R.id.wake_up_time));
        Time inputTime = (Time) inputTimeText.getText(); //TO-DO
        Time currentTime = new Time();
        currentTime.setToNow();
        Time sleepTime = new Time();
        //long diff = inputTime.;






        long times[] = getIntent().getLongArrayExtra("TIMES");
        long avg = 0;
        for(int i = 0; i < times.length; i++){
            avg += times[i];
        }
        avg /= times.length;

        if(avg > 1000){
            resultText.setText("SLEEP!");
        } else {
            resultText.setText("WORK!");
        }

        Log.d(TAG, "Result provided, avg time was: " + avg);
    }
}
