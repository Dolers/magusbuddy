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
public class NavigationSacralMagicUiTest {
    @Rule
    public ActivityTestRule<HomeActivity> activityRule
            = new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void navigateToSacralMagic() {
        onView(withId(R.id.navigation_codex)).perform(click());
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("SZAKRÁLIS MÁGIA"));
    }

    @Test
    public void canOpenCategoryNagyArkanum() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("NAGY ARKÁNUM"));
        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Égi szekér"));
        onView(withId(R.id.name_textview))
                .check(matches(withText("Égi szekér")));
    }

    @Test
    public void canOpenCategoryKisArkánum() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("KIS ARKÁNUM"));
        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Élelemteremtés"));
        onView(withId(R.id.name_textview))
                .check(matches(withText("Élelemteremtés")));
    }

    @Test
    public void canOpenCategoryArel() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("AREL"));
        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Csábítás"));
        onView(withId(R.id.name_textview))
                .check(matches(withText("Csábítás")));
    }

    @Test
    public void canOpenCategoryDarton() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("DARTON"));
        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Méregizzadás"));
        onView(withId(R.id.name_textview))
                .check(matches(withText("Méregizzadás")));
    }

    @Test
    public void canOpenCategoryDomvik() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("DOMVIK"));
        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Isteni fegyver"));
        onView(withId(R.id.name_textview))
                .check(matches(withText("Isteni fegyver")));
    }

    @Test
    public void canOpenCategoryDreina() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("DREINA"));
        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Földabrosz"));
        onView(withId(R.id.name_textview))
                .check(matches(withText("Földabrosz")));
    }

    @Test
    public void canOpenCategoryKrad() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("KRAD"));
        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Emlékezet"));
        onView(withId(R.id.name_textview))
                .check(matches(withText("Emlékezet")));
    }

    @Test
    public void canOpenCategoryRanagol() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("RANAGOL"));
        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Döbbenet"));
        onView(withId(R.id.name_textview))
                .check(matches(withText("Döbbenet")));
    }

    @Test
    public void canOpenCategorySogron() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("SOGRON"));
        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Kigyótűz"));
        onView(withId(R.id.name_textview))
                .check(matches(withText("Kigyótűz")));
    }

    @Test
    public void canOpenCategoryTharr() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("THARR"));
        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Istenalak"));
        onView(withId(R.id.name_textview))
                .check(matches(withText("Istenalak")));
    }

    @Test
    public void canOpenCategoryUwel() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("UWEL"));
        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Jutalom"));
        onView(withId(R.id.name_textview))
                .check(matches(withText("Jutalom")));
    }

}
