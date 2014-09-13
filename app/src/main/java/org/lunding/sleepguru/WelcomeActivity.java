package org.lunding.sleepguru;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class WelcomeActivity extends Activity {
    private static final String TAG = WelcomeActivity.class.getSimpleName();
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        startButton = (Button) findViewById(R.id.startTestButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "New test started");
                normalTest();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.benchmark_button:
                benchmarkTest();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private void normalTest(){
        Intent intent = new Intent(this, TestActivity.class);
        intent.putExtra("BENCHMARK", false);
        startActivity(intent);
    }

    private void benchmarkTest(){
        Intent intent = new Intent(this, TestActivity.class);
        intent.putExtra("BENCHMARK", true);
        intent.putExtra("TEST-METHOD", MathFragment.class.getSimpleName());
        intent.putExtra("AVERAGE", 0L);
        startActivity(intent);
    }
}