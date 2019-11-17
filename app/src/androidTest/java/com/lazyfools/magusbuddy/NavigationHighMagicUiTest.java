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
public class NavigationHighMagicUiTest {
    @Rule
    public ActivityTestRule<HomeActivity> activityRule
            = new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void navigateToHighMagic() {
        onView(withId(R.id.navigation_codex)).perform(click());
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("MAGASMÁGIA"));
    }

    @Test
    public void canOpenCategoryElemi(){

        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("ELEMI MÁGIA"));

        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Nyíl"));

        onView(withId(R.id.name_textview))
                .check(matches(withText("Nyíl")));
    }

    @Test
    public void canOpenCategoryTermeszeti() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("TERMÉSZETI ANYAGOK MÁGIÁJA"));

        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Teremtés"));

        onView(withId(R.id.name_textview))
                .check(matches(withText("Teremtés")));
    }

    @Test
    public void canOpenCategoryTer() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("TÉRMÁGIA"));

        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Teleport"));

        onView(withId(R.id.name_textview))
                .check(matches(withText("Teleport")));
    }

    @Test
    public void canOpenCategoryAsztral() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("ASZTRÁLMÁGIA"));

        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Semlegesség/Elvakultság"));

        onView(withId(R.id.name_textview))
                .check(matches(withText("Semlegesség/Elvakultság")));
    }

    @Test
    public void canOpenCategoryMental() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("MENTÁLMÁGIA"));

        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Tompítás (Kábítás)"));

        onView(withId(R.id.name_textview))
                .check(matches(withText("Tompítás (Kábítás)")));
    }

    @Test
    public void canOpenCategoryRuna() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("RÚNAMÁGIA"));

        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Szimpátia varázsjel"));

        onView(withId(R.id.name_textview))
                .check(matches(withText("Szimpátia varázsjel")));
    }

    @Test
    public void canOpenCategoryIdo() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("IDŐMÁGIA"));

        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Gyorsítás"));

        onView(withId(R.id.name_textview))
                .check(matches(withText("Gyorsítás")));
    }

    @Test
    public void canOpenCategoryNekromancia() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("NEKROMANCIA"));

        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Parancs"));

        onView(withId(R.id.name_textview))
                .check(matches(withText("Parancs")));
    }

    @Test
    public void canOpenCategoryDemonologia() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("DÉMONOLÓGIA"));

        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Démonidéző litánia"));

        onView(withId(R.id.name_textview))
                .check(matches(withText("Démonidéző litánia")));
    }

    @Test
    public void canOpenCategorySzimpatikus() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("SZIMPATIKUS MÁGIA"));

        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Tárgyszimpátia"));

        onView(withId(R.id.name_textview))
                .check(matches(withText("Tárgyszimpátia")));
    }

    @Test
    public void canOpenCategoryEgyeb() {
        onView(withId(R.id.card_list))
                .perform(clickOnRecycleViewElement("EGYÉB MÁGIKUS MÓDSZEREK"));

        onView(withId(R.id.name_list))
                .perform(clickOnRecycleViewElement("Testsúly csökkentés"));

        onView(withId(R.id.name_textview))
                .check(matches(withText("Testsúly csökkentés")));
    }

}
