package com.example.myapplication;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication.CarrerObjective;
import com.example.myapplication.PersonalInformation;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int numberOfTabs;

    public PagerAdapter(FragmentManager fm , int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                PersonalInformation personalInformation = new PersonalInformation();
                return personalInformation;
            case 1:
                CarrerObjective carrerObjective = new CarrerObjective();
                return carrerObjective;
            }
        return null;
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
