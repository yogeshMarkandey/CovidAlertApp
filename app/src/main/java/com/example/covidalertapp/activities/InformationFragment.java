package com.example.covidalertapp.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.covidalertapp.R;
import com.example.covidalertapp.adapter.ViewPagerAdapterInfo;
import com.google.android.material.tabs.TabLayout;

public class InformationFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_info, container, false);

        tabLayout = view.findViewById(R.id.tab_layout_info);
        viewPager = view.findViewById(R.id.viewPager_info);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        ViewPagerAdapterInfo adapterInfo = new ViewPagerAdapterInfo(getContext(),
                getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapterInfo);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }
}
