package org.lunding.sleepguru;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Lunding on 13/09/14.
 */
public class TestFragment extends Fragment implements OnClickListener {
    private static final String TAG = TestFragment.class.getSimpleName();
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_test, container, false);

        button = (Button) view.findViewById(R.id.startTestButton);
        button.setOnClickListener(this);

        Log.d(TAG, "Test fragment initialized");
        return view;
    }

    @Override
    public void onClick(View view){
        Log.d(TAG, "Start test!");
    }
}
