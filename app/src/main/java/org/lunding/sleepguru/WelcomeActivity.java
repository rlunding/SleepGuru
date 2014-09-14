package org.lunding.sleepguru;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


public class WelcomeActivity extends Activity {
    private static final String TAG = WelcomeActivity.class.getSimpleName();
    private SeekBar sb;
    private TextView slide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        slide = (TextView) findViewById(R.id.slide_to_continue);

        sb = (SeekBar) findViewById(R.id.seekbar);
        sb.setProgress(0);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (seekBar.getProgress() > 95) {

                } else {
                    seekBar.setThumb(getResources().getDrawable(R.drawable.snorlax));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                slide.setAlpha(0.2f);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (seekBar.getProgress() >95) {
                    Log.d(TAG, "New test started");
                    normalTest();
                    seekBar.setProgress(0);
                }
                else{
                    slide.setAlpha(0.6f);
                    seekBar.setProgress(0);
                }
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
            case R.id.highscore:
                printHighscore();
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

    private static final String[] ENTRY = { StatusContract.Column.USER,
            StatusContract.Column.SCORE, StatusContract.Column.CREATED_AT,
            StatusContract.Column.CREATED_AT };

    private void printHighscore(){
        Cursor cursor = this.getContentResolver().query(StatusContract.CONTENT_URI, null, null, null, StatusContract.DEFAULT_SORT);
        while(cursor.moveToNext()){
            Log.d(TAG,
                    cursor.getString(cursor.getColumnIndex(StatusContract.Column.USER))
                    + " " + cursor.getInt(cursor.getColumnIndex(StatusContract.Column.SCORE))
                    + " " + cursor.getInt(cursor.getColumnIndex(StatusContract.Column.CREATED_AT))
            );
        }

    }
}