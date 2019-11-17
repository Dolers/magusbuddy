package com.lazyfools.magusbuddy;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
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
public class NavigationBardMagicUiTest {
    @Rule
    public ActivityTestRule<HomeActivity> activityRule
            = new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void navigateToHighMagic() {
        onView(withId(R.id.navigation_codex)).perform(click());
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("BÁRDMÁGIA"));
    }

    @Test
    public void canOpenCategoryDal() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("DALMÁGIA"));
        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Álom dala"));
        onView(withId(R.id.name_textview))
                .check(matches(withText("Álom dala")));
    }

    @Test
    public void canOpenCategoryFeny() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("FÉNYMÁGIA"));
        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Színvarázs"));
        onView(withId(R.id.name_textview))
                .check(matches(withText("Színvarázs")));
    }

    @Test
    public void canOpenCategoryHang() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("HANGMÁGIA"));
        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Zajkeltés"));
        onView(withId(R.id.name_textview))
                .check(matches(withText("Zajkeltés")));
    }

    @Test
    public void canOpenCategoryEgyeb() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("EGYÉB BÁRDMÁGIA"));
        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Tanulás"));
        onView(withId(R.id.name_textview))
                .check(matches(withText("Tanulás")));
    }


}
