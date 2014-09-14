package org.lunding.sleepguru;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Collections;

/**
 * Created by Lunding on 13/09/14.
 */
public class ResultActivity extends Activity {

    private static final String TAG = ResultActivity.class.getSimpleName();
    private TextView helpText;
    private TextView resultText;
    private EditText inputTimeText;
    private Button calculateButton;

    private int selectedHour;
    private int selectedMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        helpText = (TextView) findViewById(R.id.help_text);
        resultText = (TextView) findViewById(R.id.result_text);
        inputTimeText= (EditText) findViewById(R.id.wake_up_time);
        calculateButton = (Button) findViewById(R.id.calculate_sleep_button);

        inputTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ResultActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        inputTimeText.setText("" + selectedHour + ":" + selectedMinute);
                        ResultActivity.this.selectedHour = selectedHour;
                        ResultActivity.this.selectedMinute = selectedMinute;
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });


        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputTimeText.getText().length() < 4){
                    Toast.makeText(ResultActivity.this, "Write a time", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ResultActivity.this);
                    int minimumMinutesToSleep = Integer.parseInt(prefs.getString("sleep_time", "8")) * 60;

                    Time currentTime = new Time();
                    currentTime.setToNow();
                    Time wakeupTime = new Time();
                    wakeupTime.setToNow();

                    wakeupTime.set(0, selectedMinute, selectedHour, currentTime.monthDay +1, currentTime.month, currentTime.year);

                    Log.d(TAG, "Current time: " + dateToString(currentTime));
                    Log.d(TAG, "Wakeup time: " + dateToString(wakeupTime));

                    long difference = wakeupTime.toMillis(true) - (minimumMinutesToSleep) - currentTime.toMillis(true);
                    difference = difference / (60 * 1000);
                    Log.d(TAG, "Difference: " + difference + " minutes... or hours: " + (difference/60));

                    int baselineScore = Integer.parseInt(prefs.getString("score", "1500"));

                    long [] scores = new long[6]; //get from database (20 random)
                    long avg = TestActivity.calculateAverage(scores);
                    int actualScore = 1000; //get last three entries from db.
                    int score = 0;
                    if(avg * 1.2f < actualScore){
                        score = 4;
                    } else if(avg < actualScore){
                        score = 3;
                    } else if (avg * 0.8f < actualScore){
                        score = 2;
                    } else {
                        score = 1;
                    }

                    if(difference < -90){
                        switch (score){
                            case 1: //Very good - study some more
                                setResult("You're quite alert! Feel free to study or work some more.");
                            case 2: //good - study some more, but go to bed soon
                                setResult("You're doing well. Work some more if you want, but plan on going to bed soon.");
                            case 3: //Get ready for bed
                                setResult("You should get ready for bed!");
                            case 4: //time to jump into bed
                                setResult("It's time to jump into bed.");
                            break;
                        }
                    } else if(difference > -90 && difference < 45){
                        switch (score){
                            case 1: //Complete what you are doing and go to bed
                                setResult("Wrap up your task and plan on going to bed!");
                            case 2: //start getting ready for bed
                                setResult("Start getting ready for bed.");
                            case 3: //Go to bed
                                setResult("Go to bed! Sweet dreams.");
                            case 4: //You should already be sleeping
                                setResult("Oh no! You should already be sleeping.");
                            break;
                        }
                    } else {
                        switch (score){
                            case 1:
                            case 2: //Go to bed (something about alcohol)
                                setResult("GO TO BED! Your state of mind is as if you are under the influence of x drinks.");
                            case 3:
                            case 4: //You are way to late (something about alcohol)
                                setResult("You are way too late. Your state of mind is as if you are under the influence of x drinks.");
                            break;
                        }
                    }

                    if(minimumMinutesToSleep > difference){
                        setResult("Go to sleep");
                    } else if (avg > baselineScore * 1.25){
                        setResult("You are tired, go to sleep");
                    } else {
                        setResult("Work some more");
                    }
                    Log.d(TAG, "Result provided, avg time was: " + avg);
                }
            }
        });

        Time currentTime = new Time();
        currentTime.setToNow();
        selectedHour = currentTime.hour;
        selectedMinute = currentTime.minute;
    }

    private void setResult(String s){
        resultText.setText(s);
        inputTimeText.setVisibility(View.GONE);
        calculateButton.setVisibility(View.GONE);
        helpText.setVisibility(View.GONE);
    }

    private String dateToString(Time t){
        StringBuilder sb = new StringBuilder();
        sb.append(t.year + "-");
        sb.append(t.month + "-");
        sb.append(t.monthDay + " ");
        sb.append(t.hour + ":");
        sb.append(t.minute + ":");
        sb.append(t.second);
        return sb.toString();
    }

}
