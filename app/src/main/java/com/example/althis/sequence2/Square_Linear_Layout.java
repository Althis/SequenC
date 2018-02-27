package com.example.althis.sequence2;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;


public class Square_Linear_Layout extends LinearLayout {

    public Square_Linear_Layout(Context context) {
        super(context);
    }

    public Square_Linear_Layout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public Square_Linear_Layout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
