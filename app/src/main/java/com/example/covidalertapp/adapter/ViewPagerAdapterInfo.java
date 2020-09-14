package com.example.covidalertapp.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.covidalertapp.activities.CollegesFragment;
import com.example.covidalertapp.activities.GovNotification;
import com.example.covidalertapp.activities.HelplineFragment;
import com.example.covidalertapp.activities.Hospitals;

public class ViewPagerAdapterInfo extends FragmentPagerAdapter {

    Context context;
    int totalTabs;
    public ViewPagerAdapterInfo(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                HelplineFragment helplineFragment = new HelplineFragment();
                return helplineFragment;
            case 1:
                GovNotification govNotification = new GovNotification();

                return govNotification;

            default:
                return new HelplineFragment();
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}
