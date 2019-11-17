package com.lazyfools.magusbuddy;

import android.support.test.espresso.contrib.RecyclerViewActions;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class Utility {
    public static RecyclerViewActions.PositionableRecyclerViewAction clickOnRecycleViewElement(String text) {
        return RecyclerViewActions.actionOnItem(hasDescendant(withText(text)), click());
    }

}
