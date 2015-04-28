package com.chen.derek.journal.journal.model;

import android.support.v4.app.Fragment;

import com.chen.derek.journal.journal.common.logger.Log;
import com.chen.derek.journal.journal.ui.MainActivity;

/**
 * Created by derek on 25/04/15.
 */
public class NavigationItemModel {
    private static final String TAG = NavigationItemModel.class.getSimpleName();
    private String mTitle = "title";
    private Fragment mFragment = null;

    public NavigationItemModel() {
        Log.d(TAG, "Called constructor with no parameter");
//        mFragment = new MainActivity.PlaceholderFragment();
    }

    public NavigationItemModel(String mTitle) {
        super();
        Log.d(TAG, "Called constructor with parameter title:" + mTitle);
        this.mTitle = mTitle;
    }

    public NavigationItemModel(int sectionNumber) {
        super();
//        this.mFragment = MainActivity.PlaceholderFragment.newInstance(sectionNumber);
    }

    public NavigationItemModel(String mTitle, int sectionNumber) {
        this.mTitle = mTitle;
//        this.mFragment = MainActivity.PlaceholderFragment.newInstance(sectionNumber);
    }

    public Fragment getmFragment() {
        return mFragment;
    }

    public void setmFragment(Fragment mFragment) {
        this.mFragment = mFragment;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
