package org.maplibre.android.gestures;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PointF;
import android.util.TypedValue;
import android.view.MotionEvent;

/* loaded from: classes2.dex */
public class Utils {
    public static PointF determineFocalPoint(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        float x = 0.0f;
        float y = 0.0f;
        for (int i = 0; i < pointerCount; i++) {
            x += motionEvent.getX(i);
            y += motionEvent.getY(i);
        }
        float f = pointerCount;
        return new PointF(x / f, y / f);
    }

    public static float getRawX(MotionEvent motionEvent, int i) {
        float rawX = motionEvent.getRawX() - motionEvent.getX();
        if (i < motionEvent.getPointerCount()) {
            return motionEvent.getX(i) + rawX;
        }
        return 0.0f;
    }

    public static float getRawY(MotionEvent motionEvent, int i) {
        float rawY = motionEvent.getRawY() - motionEvent.getY();
        if (i < motionEvent.getPointerCount()) {
            return motionEvent.getY(i) + rawY;
        }
        return 0.0f;
    }

    public static float dpToPx(float f) {
        return f * Resources.getSystem().getDisplayMetrics().density;
    }

    public static float pxToDp(float f) {
        return f / Resources.getSystem().getDisplayMetrics().density;
    }

    public static float pxToMm(float f, Context context) {
        return f / TypedValue.applyDimension(5, 1.0f, context.getResources().getDisplayMetrics());
    }
}
