package com.chen.derek.journal.journal.dummy;

import com.chen.derek.journal.journal.model.NavigationDrawerDataInterface;
import com.chen.derek.journal.journal.model.NavigationItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by derek on 25/04/15.
 */
public class DummyNavigationData implements NavigationDrawerDataInterface{
    private static final String TAG = DummyNavigationData.class.getSimpleName();
    private List<NavigationItemModel> mNavigationItemList;

    public DummyNavigationData() {
        mNavigationItemList = new ArrayList<>();
    }

    public static NavigationDrawerDataInterface<NavigationItemModel> getData(){

        NavigationDrawerDataInterface data = new DummyNavigationData();
        for (int i = 1; i < 10; i ++){
            data.getDrawList().add(new NavigationItemModel("Title " + i, i));
        }
/*        data.getDrawList().add(new NavigationItemModel("Title 1", 1));
        data.getDrawList().add(new NavigationItemModel("Title 2", 2));
        data.getDrawList().add(new NavigationItemModel("Title 3", 3));
        data.getDrawList().add(new NavigationItemModel("Title 4", 4));
        data.getDrawList().add(new NavigationItemModel("Title 5", 5));*/

        return data;
    }

    @Override
    public List<NavigationItemModel> getDrawList() {
        return mNavigationItemList;
    }

    public void setDrawList(List<NavigationItemModel> list) {
        this.mNavigationItemList = list;
    }

    @Override
    public int getItemId(Object object) {
        return 0;
    }

    @Override
    public int Count() {
        return mNavigationItemList.size();
    }

    @Override
    public NavigationItemModel getItemAtIndex(int index) {
        return (NavigationItemModel)mNavigationItemList.get(index);
    }
}
