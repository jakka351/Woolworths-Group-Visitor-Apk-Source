package com.facebook.react.views.view;

import android.view.View;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import com.facebook.yoga.YogaMeasureMode;

/* loaded from: classes3.dex */
public class MeasureUtil {
    public static int getMeasureSpec(float f, YogaMeasureMode yogaMeasureMode) {
        if (yogaMeasureMode == YogaMeasureMode.EXACTLY) {
            return View.MeasureSpec.makeMeasureSpec((int) f, BasicMeasure.EXACTLY);
        }
        if (yogaMeasureMode == YogaMeasureMode.AT_MOST) {
            return View.MeasureSpec.makeMeasureSpec((int) f, Integer.MIN_VALUE);
        }
        return View.MeasureSpec.makeMeasureSpec(0, 0);
    }
}
