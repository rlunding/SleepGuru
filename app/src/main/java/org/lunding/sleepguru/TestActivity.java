package org.lunding.sleepguru;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Lunding on 13/09/14.
 */
public class TestActivity extends Activity {
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

               MathFragment fragment = MathFragment.newInstance(5);
                getFragmentManager()
                        .beginTransaction()
                        .add(android.R.id.content, fragment,
                                fragment.getClass().getSimpleName())
                        .commit();
            }
        });

        Log.d(TAG, "Test activity initialized");
    }
}
