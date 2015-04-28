package com.chen.derek.journal.journal.model;

import java.util.List;

/**
 * Created by derek on 25/04/15.
 */
public interface NavigationDrawerDataInterface<E> {

    public List<E> getDrawList();
    public int Count();
    public E getItemAtIndex(int index);
    public int getItemId(E object);

}
