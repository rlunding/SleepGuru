package org.lunding.sleepguru;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import junit.framework.Test;

import java.awt.font.TextAttribute;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class XOFragment extends Fragment {
    private static final String TAG = XOFragment.class.getSimpleName();
    private long startTime;
    private long times[];
    private int hits = 0;
    private boolean benchmark;
    Random r = new Random();

    private ImageButton object1;
    private ImageButton object2;
    private ImageButton object3;
    private ImageButton object4;
    private ImageButton object5;
    private ImageButton objects[];


    public static XOFragment newInstance(int questions, boolean benchmark){
        XOFragment f = new XOFragment();
        Bundle args = new Bundle();
        args.putInt("questions", questions);
        args.putBoolean("benchmark", benchmark);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xo, container, false);
        object1 = (ImageButton) view.findViewById(R.id.object1);
        object2 = (ImageButton) view.findViewById(R.id.object2);
        object3 = (ImageButton) view.findViewById(R.id.object3);
        object4 = (ImageButton) view.findViewById(R.id.object4);
        object5 = (ImageButton) view.findViewById(R.id.object5);
        objects = new ImageButton[]{object1, object2, object3, object4, object5};
        times = new long[getArguments().getInt("questions", 5)];
        benchmark = getArguments().getBoolean("benchmark");
        startTime = System.currentTimeMillis();

        populateView(view);
        setTimer();

        Log.d(TAG, "New XOFragment created");
        return view;
    }

    private OnClickListener userInput(){
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
                times[hits] = System.currentTimeMillis() - startTime;
                System.out.println("time stored: " + times[hits]);
                hits++;
                System.out.println("hits: " + hits);

                if(hits==times.length){
                    if (benchmark) {
                        long avg = TestActivity.calculateAverage(times);
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                        SharedPreferences.Editor editpref = prefs.edit();
                        editpref.putString("score", String.valueOf(avg));
                        editpref.commit();
                        getActivity().finish();
                    } else {
                        Log.d(TAG, "Average time: " + TestActivity.calculateAverage(times));
                        Intent intent = new Intent(new Intent(v.getContext(), ResultActivity.class));
                        intent.putExtra("TIMES", times);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                else {
                    populateView(v);
                    setTimer();
                }
            }
        };
    }

    private void setTimer(){
        Handler handler = new Handler();
        handler.postDelayed((new Runnable() {
            @Override
            public void run() {
                ImageButton ib = objects[r.nextInt(objects.length)];
                ib.setBackgroundResource(R.drawable.reddot);
                ib.setOnClickListener(userInput());
                startTime = System.currentTimeMillis();
                Log.d(TAG, "Timer fired");
            }
        }), 2000);
        Log.d(TAG, "Timer set");
    }


    private void populateView(View v){
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        Log.d(TAG, "width: " + width + " height: " + height);

        for(ImageButton ib : objects){
            Random r = new Random();
            int x = r.nextInt((int) (width*0.6f));// - ib.getWidth() / 2;
            int y = r.nextInt((int) (height*0.6f));// - ib.getHeight() / 2;

            ib.setX(x + width*0.1f);
            ib.setY(y + height*0.1f);
            ib.setBackgroundResource(R.drawable.redcircle);
            ib.setScaleX(0.5f);
            ib.setScaleY(0.5f);
            Log.d(TAG, "Button: " + " at: " + x + ", y:" + y);
        }
    }
}
