package org.lunding.sleepguru;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Lunding on 14/09/14.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int index){
        switch (index){
            case 0:
                return new CircleStatisticFragment();
            case 1:
                return new GraphStatisticFragment();
            case 2:
                return new ReturnStatisticFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount(){
        return 3;
    }
}
