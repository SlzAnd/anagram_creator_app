package com.example.task1_anagram;

import android.view.View;
import android.widget.EditText;

import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;

public class GravityMatcher extends BoundedMatcher<View, EditText> {

    private final int expectedGravity;

    public GravityMatcher(int expectedGravity) {
        super(EditText.class);
        this.expectedGravity = expectedGravity;
    }

    @Override
    protected boolean matchesSafely(EditText item) {
        if (item == null) {
            return false;
        }
        int actualGravity = item.getGravity();
        return actualGravity == expectedGravity;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("has gravity: ");
        description.appendValue(expectedGravity);
    }
}
