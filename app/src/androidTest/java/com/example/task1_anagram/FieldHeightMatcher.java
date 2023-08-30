package com.example.task1_anagram;

import android.content.res.Resources;
import android.view.View;
import android.widget.EditText;

import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;

public class FieldHeightMatcher extends BoundedMatcher<View, EditText> {

    private final int expectedHeight;

    public FieldHeightMatcher(int expectedHeight) {
        super(EditText.class);
        this.expectedHeight = expectedHeight;
    }

    @Override
    protected boolean matchesSafely(EditText item) {
        if (item == null) {
            return false;
        }
        int actualHeight = (int)
                (item.getLayoutParams().height / Resources.getSystem().getDisplayMetrics().density);
        return actualHeight == expectedHeight;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("has gravity: ");
        description.appendValue(expectedHeight);
    }
}