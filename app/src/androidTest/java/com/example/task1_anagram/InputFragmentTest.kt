package com.example.task1_anagram

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.view.Gravity
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasFocus
import androidx.test.espresso.matcher.ViewMatchers.hasTextColor
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@SuppressLint("CheckResult")
@RunWith(AndroidJUnit4::class)
class InputFragmentTest {

    private lateinit var scenario: FragmentScenario<InputFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_Task1_anagram)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun test_initiallyDisplaysHintTextInAnagramTextView() {
        assertThat(onView(withId(R.id.tv_anagram)).check(matches(withText(R.string.anagram_hint))))
        assertThat(onView(withId(R.id.tv_anagram)).check(matches(hasFontSize(16f))))
    }
    @Test
    fun test_initiallyDisplaysHintTextInTheCenterOfInputField() {
        assertThat(onView(withId(R.id.et_input_text)).check(matches(withHint(R.string.input_text_hint))))
        assertThat(onView(withId(R.id.et_input_text)).check(matches(hasFontSize(14f))))
        assertThat(onView(withId(R.id.et_input_text)).check(matches(hasGravity(Gravity.CENTER))))
    }

    @Test
    fun test_initiallyDisplaysHintTextInTheCenterOfFilterField() {
        assertThat(onView(withId(R.id.et_filter_text)).check(matches(withHint(R.string.input_filter_hint))))
        assertThat(onView(withId(R.id.et_filter_text)).check(matches(hasFontSize(14f))))
        assertThat(onView(withId(R.id.et_input_text)).check(matches(hasGravity(Gravity.CENTER))))
    }

    @Test
    fun test_enteredEmptyInputAndNotEmptyFilterDisplaysNothing() {
        onView(withId(R.id.et_input_text)).perform(typeText(""))
        onView(withId(R.id.et_filter_text)).perform(typeText("hello"))
        assertThat(onView(withId(R.id.tv_anagram)).check(matches(withText(""))))
    }

    @Test
    fun test_automaticallyAnagramCreation() {
        val firstInputText = "party pooper"
        val secondInputText = "Kilimanjaro"
        val filterText = "type"
        val unfilteredExpectedResult = "ytrap repoop"
        val firstExpectedResult = "praty propeo"
        val secondExpectedResult = "orajnamiliK"

        onView(withId(R.id.et_input_text)).perform(typeText(firstInputText))
        onView(withId(R.id.et_filter_text)).perform(typeText(filterText))
        onView(withId(R.id.et_filter_text)).perform(closeSoftKeyboard())
        assertThat(onView(withId(R.id.tv_anagram)).check(matches(withText(firstExpectedResult))))

        onView(withId(R.id.et_filter_text)).perform(clearText())
        assertThat(onView(withId(R.id.tv_anagram)).check(matches(withText(unfilteredExpectedResult))))

        onView(withId(R.id.et_input_text)).perform(replaceText(secondInputText))
        assertThat(onView(withId(R.id.tv_anagram)).check(matches(withText(secondExpectedResult))))

        onView(withId(R.id.et_input_text)).perform(clearText())
        assertThat(onView(withId(R.id.tv_anagram)).check(matches(withText(""))))
    }

    @Test
    fun test_createdAnagramTextViewHasAnotherColorAndBiggerSize() {
        val inputText = "party pooper"
        val filterText = "type"
        val expectedColorId = R.color.web_orange
        onView(withId(R.id.et_input_text)).perform(typeText(inputText))
        onView(withId(R.id.et_filter_text)).perform(typeText(filterText))
        assertThat(onView(withId(R.id.tv_anagram)).check(matches(hasTextColor(expectedColorId))))
        assertThat(onView(withId(R.id.tv_anagram)).check(matches(hasFontSize(24f))))
    }

    @Test
    fun test_notEmptyInputFieldChangesFieldTextStyle() {
        onView(withId(R.id.et_input_text)).perform(typeText("hello"))
        assertThat(onView(withId(R.id.et_input_text)).check(matches(hasTextColor(R.color.black))))
        assertThat(onView(withId(R.id.et_input_text)).check(matches(hasFontSize(20f))))
        assertThat(onView(withId(R.id.et_input_text)).check(matches(withHint(R.string.input_text_hint))))
    }

    @Test
    fun test_focusingInputFieldChangesGravity() {
        onView(withId(R.id.et_input_text)).perform(click())
        assertThat(onView(withId(R.id.et_input_text)).check(matches(hasFocus())))
        assertThat(onView(withId(R.id.et_input_text)).check(matches(hasGravity(Gravity.START + Gravity.CENTER_VERTICAL))))

        // change focus to another field should change gravity in the previous one
        onView(withId(R.id.et_filter_text)).perform(click())
        assertThat(onView(withId(R.id.et_filter_text)).check(matches(hasFocus())))
        assertThat(onView(withId(R.id.et_input_text)).check(matches(hasGravity(Gravity.CENTER))))
    }

    @Test
    fun test_UIChangesAfterScreenRotation() {
        // Portrait
        assertThat(onView(withId(R.id.et_input_text)).check(matches(hasHeight(98))))
        assertThat(onView(withId(R.id.et_filter_text)).check(matches(hasHeight(98))))
        // Rotate to Landscape
        scenario.onFragment{
            it.activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        // Check after rotation
        assertThat(onView(withId(R.id.et_input_text)).check(matches(hasHeight(64))))
        assertThat(onView(withId(R.id.et_filter_text)).check(matches(hasHeight(64))))
    }


    private fun hasFontSize(fontSize: Float): FontSizeMatcher {
        return FontSizeMatcher(fontSize)
    }

    private fun hasGravity(gravity: Int): GravityMatcher {
        return GravityMatcher(gravity)
    }

    private fun hasHeight(height: Int): FieldHeightMatcher {
        return FieldHeightMatcher(height)
    }


}