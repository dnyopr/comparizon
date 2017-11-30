package com.kmitl58070042.dnyopr.comparizon;


import android.os.SystemClock;
import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        Log.wtf("where","mainactivity");

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.btn_share),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.comparison_space),
                                        1),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());

        SystemClock.sleep(1000);

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.item_right),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                1),
                        isDisplayed()));
        linearLayout.perform(click());

        SystemClock.sleep(1000);

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.btn_share),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.comparison_space),
                                        1),
                                1),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        SystemClock.sleep(1000);

    }

    @Test
    public void testNoCompleteInfo() {
        Log.wtf("where","test no complete info");

        SystemClock.sleep(1000);

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.btn_add), withContentDescription("btn add"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        SystemClock.sleep(1000);

        ViewInteraction materialEditText = onView(
                allOf(withId(R.id.edit_brand),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        materialEditText.perform(scrollTo(), replaceText("middle item"), closeSoftKeyboard());

        SystemClock.sleep(1000);

        ViewInteraction materialEditText2 = onView(
                allOf(withId(R.id.edit_detail),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2)));
        materialEditText2.perform(scrollTo(), replaceText("this item is not completed"), closeSoftKeyboard());

        SystemClock.sleep(1000);

        ViewInteraction materialEditText3 = onView(
                allOf(withId(R.id.edit_cost),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                2)));
        materialEditText3.perform(scrollTo(), replaceText("55"), closeSoftKeyboard());

        SystemClock.sleep(1000);

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btn_save), withText("save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton.perform(scrollTo(), click());

        SystemClock.sleep(2000);

        ViewInteraction materialEditText4 = onView(
                allOf(withId(R.id.edit_size),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                0)));
        materialEditText4.perform(scrollTo(), replaceText("3"), closeSoftKeyboard());

        SystemClock.sleep(1000);

        ViewInteraction materialEditText5 = onView(
                allOf(withId(R.id.edit_detail), withText("this item is not completed"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2)));
        materialEditText5.perform(scrollTo(), replaceText(""));

        SystemClock.sleep(1000);

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btn_save), withText("save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton2.perform(scrollTo(), click());

        SystemClock.sleep(2000);

    }

    @Test
    public void testAddItem(){

        SystemClock.sleep(1000);

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.btn_add), withContentDescription("btn add"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        SystemClock.sleep(1000);

        ViewInteraction materialEditText = onView(
                allOf(withId(R.id.edit_brand),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        materialEditText.perform(scrollTo(), replaceText("middle price item"), closeSoftKeyboard());

        SystemClock.sleep(1000);


        ViewInteraction materialEditText4 = onView(
                allOf(withId(R.id.edit_detail),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2)));
        materialEditText4.perform(scrollTo(), replaceText("18.33 per ae"), closeSoftKeyboard());

        SystemClock.sleep(1000);


        ViewInteraction materialEditText5 = onView(
                allOf(withId(R.id.edit_size),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                0)));
        materialEditText5.perform(scrollTo(), replaceText("3"), closeSoftKeyboard());

        SystemClock.sleep(1000);


        ViewInteraction materialEditText6 = onView(
                allOf(withId(R.id.edit_cost),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                2)));
        materialEditText6.perform(scrollTo(), replaceText("55"), closeSoftKeyboard());

        SystemClock.sleep(1000);


        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btn_save), withText("save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton.perform(scrollTo(), click());

        SystemClock.sleep(3000);


        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.btn_add), withContentDescription("btn add"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView2.perform(click());

        SystemClock.sleep(1000);


        ViewInteraction materialEditText7 = onView(
                allOf(withId(R.id.edit_brand),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        materialEditText7.perform(scrollTo(), replaceText("cheapest item"), closeSoftKeyboard());

        SystemClock.sleep(1000);


        ViewInteraction materialEditText10 = onView(
                allOf(withId(R.id.edit_detail),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2)));
        materialEditText10.perform(scrollTo(), replaceText("15.43 per ae"), closeSoftKeyboard());

        SystemClock.sleep(1000);


        ViewInteraction materialEditText11 = onView(
                allOf(withId(R.id.edit_size),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                0)));
        materialEditText11.perform(scrollTo(), replaceText("12"), closeSoftKeyboard());

        SystemClock.sleep(1000);


        ViewInteraction materialEditText12 = onView(
                allOf(withId(R.id.edit_cost),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                2)));
        materialEditText12.perform(scrollTo(), replaceText("185.1"), closeSoftKeyboard());

        SystemClock.sleep(1000);


        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btn_save), withText("save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton2.perform(scrollTo(), click());

        SystemClock.sleep(3000);


        ViewInteraction actionMenuItemView3 = onView(
                allOf(withId(R.id.btn_add), withContentDescription("btn add"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView3.perform(click());

        SystemClock.sleep(1000);


        ViewInteraction materialEditText13 = onView(
                allOf(withId(R.id.edit_brand),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        materialEditText13.perform(scrollTo(), replaceText("expensive item"), closeSoftKeyboard());

        SystemClock.sleep(1000);


        ViewInteraction materialEditText14 = onView(
                allOf(withId(R.id.edit_detail),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2)));
        materialEditText14.perform(scrollTo(), replaceText("20.39 per ae"), closeSoftKeyboard());

        SystemClock.sleep(1000);


        ViewInteraction materialEditText15 = onView(
                allOf(withId(R.id.edit_size),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                0)));
        materialEditText15.perform(scrollTo(), replaceText("55"), closeSoftKeyboard());

        SystemClock.sleep(1000);


        ViewInteraction materialEditText16 = onView(
                allOf(withId(R.id.edit_cost),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                2)));
        materialEditText16.perform(scrollTo(), replaceText("1121.7"), closeSoftKeyboard());

        SystemClock.sleep(1000);


        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.btn_save), withText("save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton3.perform(scrollTo(), click());

        SystemClock.sleep(3000);



    }


    @Test
    public void testCompareItem() {

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.btn_add), withContentDescription("btn add"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction materialEditText = onView(
                allOf(withId(R.id.edit_brand),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        materialEditText.perform(scrollTo(), replaceText("Middle price Item"), closeSoftKeyboard());

        ViewInteraction materialEditText2 = onView(
                allOf(withId(R.id.edit_detail),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2)));
        materialEditText2.perform(scrollTo(), replaceText("18.33 per ae"), closeSoftKeyboard());

        ViewInteraction materialEditText3 = onView(
                allOf(withId(R.id.edit_size),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                0)));
        materialEditText3.perform(scrollTo(), replaceText("3"), closeSoftKeyboard());

        ViewInteraction materialEditText4 = onView(
                allOf(withId(R.id.edit_cost),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                2)));
        materialEditText4.perform(scrollTo(), replaceText("55"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btn_save), withText("save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.btn_add), withContentDescription("btn add"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView2.perform(click());

        ViewInteraction materialEditText5 = onView(
                allOf(withId(R.id.edit_brand),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        materialEditText5.perform(scrollTo(), replaceText("Cheapest item"), closeSoftKeyboard());

        ViewInteraction materialEditText6 = onView(
                allOf(withId(R.id.edit_detail),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2)));
        materialEditText6.perform(scrollTo(), replaceText("15.43 per ae"), closeSoftKeyboard());

        ViewInteraction materialEditText7 = onView(
                allOf(withId(R.id.edit_size),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                0)));
        materialEditText7.perform(scrollTo(), replaceText("12"), closeSoftKeyboard());

        ViewInteraction materialEditText8 = onView(
                allOf(withId(R.id.edit_cost),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                2)));
        materialEditText8.perform(scrollTo(), replaceText("185.1"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btn_save), withText("save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction actionMenuItemView3 = onView(
                allOf(withId(R.id.btn_add), withContentDescription("btn add"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView3.perform(click());

        ViewInteraction materialEditText9 = onView(
                allOf(withId(R.id.edit_brand),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        materialEditText9.perform(scrollTo(), replaceText("Expensive item"), closeSoftKeyboard());

        ViewInteraction materialEditText10 = onView(
                allOf(withId(R.id.edit_detail),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2)));
        materialEditText10.perform(scrollTo(), replaceText("20.39 per ae"), closeSoftKeyboard());

        ViewInteraction materialEditText11 = onView(
                allOf(withId(R.id.edit_size),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                0)));
        materialEditText11.perform(scrollTo(), replaceText("55"), closeSoftKeyboard());

        ViewInteraction materialEditText12 = onView(
                allOf(withId(R.id.edit_cost),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                2)));
        materialEditText12.perform(scrollTo(), replaceText("1121.7"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.btn_save), withText("save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton3.perform(scrollTo(), click());

        SystemClock.sleep(1000);


        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.list),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                2)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        SystemClock.sleep(1000);

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.list),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                2)));
        recyclerView2.perform(actionOnItemAtPosition(1, click()));

        SystemClock.sleep(1000);

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.item_right),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                1),
                        isDisplayed()));
        linearLayout.perform(click());

        SystemClock.sleep(1000);

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.list),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                2)));
        recyclerView3.perform(actionOnItemAtPosition(2, click()));

        SystemClock.sleep(1000);

        ViewInteraction recyclerView4 = onView(
                allOf(withId(R.id.list),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                2)));
        recyclerView4.perform(actionOnItemAtPosition(1, click()));

        SystemClock.sleep(1000);


    }

    @Test
    public void testShareItem() {

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.btn_share),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.comparison_space),
                                        1),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());

        SystemClock.sleep(1000);
    }

    @Test
    public void testDeleteItem(){

        ViewInteraction recyclerView6 = onView(
                allOf(withId(R.id.list),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                2)));
        recyclerView6.perform(actionOnItemAtPosition(2, longClick()));

        SystemClock.sleep(1000);

        DataInteraction appCompatTextView = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(0);
        appCompatTextView.perform(click());

        SystemClock.sleep(1000);

        ViewInteraction recyclerView7 = onView(
                allOf(withId(R.id.list),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                2)));
        recyclerView7.perform(actionOnItemAtPosition(0, longClick()));

        SystemClock.sleep(1000);

        DataInteraction appCompatTextView2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(0);
        appCompatTextView2.perform(click());

        SystemClock.sleep(1000);

        ViewInteraction recyclerView8 = onView(
                allOf(withId(R.id.list),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                2)));
        recyclerView8.perform(actionOnItemAtPosition(0, longClick()));

        SystemClock.sleep(1000);

        DataInteraction appCompatTextView3 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(0);
        appCompatTextView3.perform(click());

        SystemClock.sleep(1000);

    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
