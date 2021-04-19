package co.com.ceiba.mobile.pruebadeingreso.view

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.view.post.PostAdapter
import co.com.ceiba.mobile.pruebadeingreso.view.user.MainActivity
import co.com.ceiba.mobile.pruebadeingreso.view.user.UserAdapter
import org.hamcrest.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class Calificador {
    @get:Rule
    val mActivityTestRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(
        MainActivity::class.java
    )

    @Test
    fun emptyTest() {
        clickInputSearch()
        keypressInputSearch("empty")
        Espresso.onView(Matchers.allOf(withText("List is empty")))
            .check(matches(withText("List is empty")))
    }

    @Test
    fun ervinTest() {
        clickInputSearch()
        keypressInputSearch("Ervin")
        Espresso.onView(Matchers.allOf(withId(R.id.name)))
            .check(matches(withText("Ervin Howell")))
        Espresso.onView(Matchers.allOf(withId(R.id.phone)))
            .check(matches(withText("010-692-6593 x09125")))
        Espresso.onView(Matchers.allOf(withId(R.id.email)))
            .check(matches(withText("Shanna@melissa.tv")))
    }

    @Test
    fun leanneTest() {
        clickInputSearch()
        keypressInputSearch("Leanne")
        Espresso.onView(Matchers.allOf(withId(R.id.name)))
            .check(matches(withText("Leanne Graham")))
        Espresso.onView(Matchers.allOf(withId(R.id.phone)))
            .check(matches(withText("1-770-736-8031 x56442")))
        Espresso.onView(Matchers.allOf(withId(R.id.email)))
            .check(matches(withText("Sincere@april.biz")))
    }

    @Test
    @Throws(InterruptedException::class)
    fun leannePostClickVerPublicacionesTest() {
        clickInputSearch()
        keypressInputSearch("Leanne")
        Espresso.onView(Matchers.allOf(withId(R.id.btn_view_post)))
            .perform(click())
        Thread.sleep(4000)
        Espresso.onView(Matchers.allOf(withId(R.id.name))).check(
            matches(
                withText(
                    Matchers.endsWith("Leanne Graham")
                )
            )
        )
        Espresso.onView(Matchers.allOf(withId(R.id.phone)))
            .check(matches(withText("1-770-736-8031 x56442")))
        Espresso.onView(
            Matchers.allOf(
                withId(R.id.title),
                withText("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")
            )
        )
            .check(matches(withText("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")))
    }

    private fun keypressInputSearch(valueToWrite: String) {
        val appCompatEditText2 = Espresso.onView(
            Matchers.allOf(
                withId(R.id.editTextSearch),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textInputLayoutSearch),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(
            ViewActions.replaceText(valueToWrite),
            ViewActions.closeSoftKeyboard()
        )
    }

    private fun clickInputSearch() {
        val appCompatEditText = Espresso.onView(
            Matchers.allOf(
                withId(R.id.editTextSearch),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textInputLayoutSearch),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(click())
    }

    companion object {
        private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int
        ): Matcher<View> {
            return object : TypeSafeMatcher<View>() {
                override fun describeTo(description: Description) {
                    description.appendText("Child at position $position in parent ")
                    parentMatcher.describeTo(description)
                }

                public override fun matchesSafely(view: View): Boolean {
                    val parent = view.parent
                    return (parent is ViewGroup && parentMatcher.matches(parent)
                            && view == parent.getChildAt(position))
                }
            }
        }

        private fun customActionClick(id: Int): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View>? {
                    return null
                }

                override fun getDescription(): String {
                    return "Action Description"
                }

                override fun perform(uiController: UiController, view: View) {
                    val v = view.findViewById<View>(id) as View
                    v.performClick()
                }
            }
        }
    }

    @Test
    fun lastUserPostTest() {
        Espresso.closeSoftKeyboard()
        Thread.sleep(2000)
        Espresso.onView(withId(R.id.recyclerViewSearchResults))
            .perform(
                RecyclerViewActions.scrollToPosition<UserAdapter.UserHolder>(Constants.LAST_POSITION),
                RecyclerViewActions.actionOnItemAtPosition<UserAdapter.UserHolder>(
                    Constants.LAST_POSITION,
                    customActionClick(R.id.btn_view_post)
                )
            )
        Thread.sleep(4000)
        Espresso.onView(withId(R.id.recyclerViewPostsResults))
            .perform(
                RecyclerViewActions.scrollToPosition<PostAdapter.PostHolder>(Constants.LAST_POSITION),
            )
    }
}