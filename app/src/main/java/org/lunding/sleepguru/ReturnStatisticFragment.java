package org.lunding.sleepguru;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Lunding on 14/09/14.
 */
public class ReturnStatisticFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_statistic_return, container, false);

        Button exitButton = (Button) rootView.getRootView().findViewById(R.id.exit_button);
        exitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getActivity().finish();
            }
        });
        //setup view
        return rootView;
    }
}
