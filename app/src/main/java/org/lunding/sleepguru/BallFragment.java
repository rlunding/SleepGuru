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
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class BallFragment extends Fragment {
    private long startTime;
    private long times[] = new long[5];
    private int hits = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ball, container, false);
        final ImageButton ball = (ImageButton) view.findViewById(R.id.moving_ball);

        ball.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //difference between the start and current time is entered into array
                times[hits] = System.currentTimeMillis() - startTime;
                System.out.println("time stored: " + times[hits]);
                hits++;
                System.out.println("hits: " + hits);

                switch (view.getId()){
                    case (R.id.moving_ball):{
                        Random r = new Random();
                        int x = r.nextInt(480 + ball.getWidth());
                        int y = r.nextInt(900 + ball.getHeight());

                        ball.setX(x);
                        ball.setY(y);
                    }
                }
                //restart timer
                startTime = System.currentTimeMillis();
                System.out.println("start time 2:" + startTime);
            }
        });
        return view;
    }

}
