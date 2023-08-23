package com.example.task1_anagram;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.task1_anagram.databinding.FragmentInputBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class InputFragment extends Fragment {

    private FragmentInputBinding binding = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInputBinding.inflate(inflater, container, false);

        changeStyleFromFocusChanges(binding.etInputText, binding.tilInputText);
        changeStyleFromFocusChanges(binding.etFilterText, binding.tilFilterText);

        return binding.getRoot();
    }

    @SuppressLint("ResourceType")
    private void changeStyleFromFocusChanges(TextInputEditText editText, TextInputLayout layout) {
        changeStyleFromTextChanges(editText);

        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                layout.setGravity(Gravity.CENTER_VERTICAL);
                editText.setGravity(Gravity.CENTER_VERTICAL);
            } else {
                if (editText.getEditableText().length() == 0) {
                    layout.setGravity(Gravity.CENTER);
                    editText.setGravity(Gravity.CENTER);
                }
            }
        });
    }
    private void changeStyleFromTextChanges(TextInputEditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    // nothing entered -> hint is visible -> smaller size
                    editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

                } else {
                    // something entered -> hint moves to the top -> bigger size and black color
                    editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                    editText.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
