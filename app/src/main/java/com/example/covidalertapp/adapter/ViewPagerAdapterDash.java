package com.example.covidalertapp.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.covidalertapp.activities.CollegesFragment;
import com.example.covidalertapp.activities.Hospitals;

public class ViewPagerAdapterDash  extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public ViewPagerAdapterDash(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Hospitals hospitals = new Hospitals();
                return hospitals;
            case 1:
                CollegesFragment collegesFragment = new CollegesFragment();

                return collegesFragment;

            default:
                return new Hospitals();
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}
