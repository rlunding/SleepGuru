package org.lunding.sleepguru;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Lunding on 13/09/14.
 */
public class TestActivity extends Activity {
    private static final String TAG = TestActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null){
            TestFragment fragment = new TestFragment();
            getFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fragment,
                            fragment.getClass().getSimpleName())
                    .commit();
        }
        Log.d(TAG, "Test activity initialized");
    }
}
