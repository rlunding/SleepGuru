package org.lunding.sleepguru;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Lunding on 13/09/14.
 */
public class TestActivity extends Activity {

    public final static int DEFAULT_NUMBER_OF_QUESTIONS = 5;

    private static final String TAG = TestActivity.class.getSimpleName();
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        button = (Button) findViewById(R.id.startTestButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "New test started");
                button.setVisibility(View.GONE);

                boolean isBenchmark = getIntent().getBooleanExtra("BENCHMARK", false);
                Fragment fragment = null;
                if(isBenchmark){
                    String method = getIntent().getStringExtra("TEST-METHOD");
                    if(method.equals(MathFragment.class.getSimpleName())){
                        fragment = MathFragment.newInstance(DEFAULT_NUMBER_OF_QUESTIONS, true);
                    } else if(method.equals(BallFragment.class.getSimpleName())){
                        fragment = BallFragment.newInstance(DEFAULT_NUMBER_OF_QUESTIONS, true);
                    }
                } else {
                    int rand = new Random().nextInt(3);
                    switch (rand){
                        case 0:
                            fragment = MathFragment.newInstance(DEFAULT_NUMBER_OF_QUESTIONS, false);
                            break;
                        case 1:
                            fragment = BallFragment.newInstance(DEFAULT_NUMBER_OF_QUESTIONS, false);
                            break;
                        case 2:
                            fragment = XOFragment.newInstance(DEFAULT_NUMBER_OF_QUESTIONS, false);
                            break;
                        default:
                            fragment = null;
                            break;
                    }
                }
                if(fragment != null){
                    Log.d(TAG, fragment.getClass().getSimpleName() + "was created");
                    getFragmentManager()
                            .beginTransaction()
                            .add(android.R.id.content, fragment,
                                    fragment.getClass().getSimpleName())
                            .commit();
                }
            }
        });

        Log.d(TAG, "Test activity initialized");
    }

    public static long calculateAverage(long[] times){
        long avg = 0;
        for(int i = 0; i < times.length; i++){
            avg += times[i];
        }
        avg /= times.length;
        return avg;
    }
}
