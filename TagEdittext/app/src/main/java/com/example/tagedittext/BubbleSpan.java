package com.example.tagedittext;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;

public class BubbleSpan extends DynamicDrawableSpan {
    private Context c;

    public BubbleSpan(Context context) {
        super();
        c = context;
    }

    @Override
    public Drawable getDrawable() {
        Resources res = c.getResources();
        Drawable d = res.getDrawable(R.drawable.oval);
        d.setBounds(0, 0, 100, 20);
        return d;
    }
}
