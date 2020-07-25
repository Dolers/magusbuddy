package com.lazyfools.magusbuddy;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.lazyfools.magusbuddy.Utility.clickOnRecycleViewElement;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class NavigationQualificationUiTest {
    @Rule
    public ActivityTestRule<HomeActivity> activityRule
            = new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void navigateToQualification() {
        onView(withId(R.id.navigation_codex)).perform(click());
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("KÉPZETTSÉG"));
    }

    @Test
    public void canOpenCategoryHarci() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("HARCI"));
        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Fegyverhasználat"));
        onView(withId(R.id.title_value))
                .check(matches(withText("Fegyverhasználat")));
    }

    @Test
    public void canOpenCategoryVilagi() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("VILÁGI"));
        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Akrobatika"));
        onView(withId(R.id.title_value))
                .check(matches(withText("Akrobatika")));
    }

    @Test
    public void canOpenCategorySzocialis() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("SZOCIÁLIS"));
        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Lélektan"));
        onView(withId(R.id.title_value))
                .check(matches(withText("Lélektan")));
    }

    @Test
    public void canOpenCategoryAlvilagi() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("ALVILÁGI"));
        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Orvtámadás"));
        onView(withId(R.id.title_value))
                .check(matches(withText("Orvtámadás")));
    }

    @Test
    public void canOpenCategoryTudomanyos() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("TUDOMÁNYOS"));
        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Alkímia"));
        onView(withId(R.id.title_value))
                .check(matches(withText("Alkímia")));
    }

    @Test
    public void canOpenCategoryMisztikus() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("MISZTIKUS"));
        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Pszi"));
        onView(withId(R.id.title_value))
                .check(matches(withText("Pszi")));
    }
}
