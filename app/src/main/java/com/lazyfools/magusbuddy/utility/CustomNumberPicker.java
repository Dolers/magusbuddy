package com.lazyfools.magusbuddy.utility;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.lazyfools.magusbuddy.R;

import java.util.ArrayList;
import java.util.List;

public class CustomNumberPicker extends LinearLayout
{
    private EditText _etCurrentNumber;
    private int _min, _max;
    private List<OnValueChangeListener> _valueChangedListeners = new ArrayList<>();

    public CustomNumberPicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        parseAttributes(attrs);

        inflate(context, R.layout.custom_number_picker, this);

        _etCurrentNumber = findViewById(R.id.et_number);

        final Button btn_less = findViewById(R.id.btn_less);
        btn_less.setOnClickListener(new AddHandler(-1));

        final Button btn_more = findViewById(R.id.btn_more);
        btn_more.setOnClickListener(new AddHandler(1));
    }

    private void parseAttributes(AttributeSet attrs) {
        TypedArray attributes = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.CustomNumberPicker, 0, 0);

        try {
            _min = attributes.getInt(R.styleable.CustomNumberPicker_minValue, 0);
            _max = attributes.getInt(R.styleable.CustomNumberPicker_maxValue, 0);
            setValue(attributes.getInt(R.styleable.CustomNumberPicker_defaultValue, 0));
        } finally {
            attributes.recycle();
        }
    }

    public void setOnValueChangedListener(OnValueChangeListener item) {
        _valueChangedListeners.add(item);
    }

    /***
     * HANDLERS
     **/

    public interface OnValueChangeListener {
        void onValueChange(int newValue);
    }

    private class AddHandler implements OnClickListener {
        final int diff;

        public AddHandler(int diff) {
            this.diff = diff;
        }

        @Override
        public void onClick(View v) {
            setValue( getValue() + diff);
            for (OnValueChangeListener item : _valueChangedListeners) {
                item.onValueChange(getValue());
            }
        }
    }

    /***
     * GETTERS & SETTERS
     */

    public int getValue() {
        if (_etCurrentNumber != null) {
            try {
                final String value = _etCurrentNumber.getText().toString();
                return Integer.parseInt(value);
            } catch (NumberFormatException ex) {
                Log.e("CustomNumberPicker", ex.toString());
            }
        }
        return 0;
    }

    public void setValue(int value) {
        if (_etCurrentNumber != null) {
            if (value < _min) {
                value = _min;
            } else if (value > _max) {
                value = _max;
            }
            _etCurrentNumber.setText(String.valueOf(value));
        }
    }

    public int getMin() {
        return _min;
    }

    public void setMin(int min) {
        this._min = min;
    }

    public int getMax() {
        return _max;
    }

    public void setMax(int max) {
        this._max = max;
    }
}