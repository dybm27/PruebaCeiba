package co.com.ceiba.mobile.pruebadeingreso.view

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.view.user.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class Calificador {
    @Rule
    val mActivityTestRule = ActivityScenarioRule(
        MainActivity::class.java
    )

    @Test
    fun emptyTest() {
        clickInputSearch()
        keypressInputSearch("empty")
        Espresso.onView(Matchers.allOf(ViewMatchers.withText("List is empty")))
            .check(ViewAssertions.matches(ViewMatchers.withText("List is empty")))
    }

    @Test
    fun ervinTest() {
        clickInputSearch()
        keypressInputSearch("Ervin")
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.name)))
            .check(ViewAssertions.matches(ViewMatchers.withText("Ervin Howell")))
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.phone)))
            .check(ViewAssertions.matches(ViewMatchers.withText("010-692-6593 x09125")))
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.email)))
            .check(ViewAssertions.matches(ViewMatchers.withText("Shanna@melissa.tv")))
    }

    @Test
    fun leanneTest() {
        clickInputSearch()
        keypressInputSearch("Leanne")
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.name)))
            .check(ViewAssertions.matches(ViewMatchers.withText("Leanne Graham")))
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.phone)))
            .check(ViewAssertions.matches(ViewMatchers.withText("1-770-736-8031 x56442")))
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.email)))
            .check(ViewAssertions.matches(ViewMatchers.withText("Sincere@april.biz")))
    }

    @Test
    @Throws(InterruptedException::class)
    fun leannePostClickVerPublicacionesTest() {
        clickInputSearch()
        keypressInputSearch("Leanne")
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.btn_view_post)))
            .perform(ViewActions.click())
        Thread.sleep(4000)
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.name))).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    Matchers.endsWith("Leanne Graham")
                )
            )
        )
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.phone)))
            .check(ViewAssertions.matches(ViewMatchers.withText("1-770-736-8031 x56442")))
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.title),
                ViewMatchers.withText("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")
            )
        )
            .check(ViewAssertions.matches(ViewMatchers.withText("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")))
    }

    private fun keypressInputSearch(valueToWrite: String) {
        val appCompatEditText2 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.editTextSearch),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withId(R.id.textInputLayoutSearch),
                        0
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
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
                ViewMatchers.withId(R.id.editTextSearch),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withId(R.id.textInputLayoutSearch),
                        0
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatEditText.perform(ViewActions.click())
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
    }
}