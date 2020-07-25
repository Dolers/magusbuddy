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
public class NavigationPsziUiTest {
    @Rule
    public ActivityTestRule<HomeActivity> activityRule
            = new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void navigateToPszi() {
        onView(withId(R.id.navigation_codex)).perform(click());
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("PSZI"));
    }

    @Test
    public void canOpenCategoryPyarroni(){

        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("PYARRONI MÓDSZER"));

        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Abszolút látás"));

        onView(withId(R.id.title_value))
                .check(matches(withText("Abszolút látás")));
    }

    @Test
    public void canOpenCategorySlan(){

        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("SLAN ÚTJA"));

        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Aranyharang"));

        onView(withId(R.id.title_value))
                .check(matches(withText("Aranyharang")));
    }


    @Test
    public void canOpenCategoryKyr(){

        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("KYR METÓDUS"));

        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Asztrálszem"));

        onView(withId(R.id.title_value))
                .check(matches(withText("Asztrálszem")));
    }

    @Test
    public void canOpenCategoryGodoni(){

        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("GODONI ÖRÖKSÉG"));

        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Felfedés"));

        onView(withId(R.id.title_value))
                .check(matches(withText("Felfedés")));
    }
}
