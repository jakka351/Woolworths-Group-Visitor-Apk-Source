package org.maplibre.android.location;

import android.animation.TypeEvaluator;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PaddingEvaluator.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0013\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J$\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\u00022\b\b\u0001\u0010\n\u001a\u00020\u0002H\u0016R\u000e\u0010\u0005\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/maplibre/android/location/PaddingEvaluator;", "Landroid/animation/TypeEvaluator;", "", "<init>", "()V", ViewProps.PADDING, "evaluate", "fraction", "", "startValue", "endValue", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PaddingEvaluator implements TypeEvaluator<double[]> {
    private final double[] padding = new double[4];

    @Override // android.animation.TypeEvaluator
    public double[] evaluate(float fraction, double[] startValue, double[] endValue) {
        Intrinsics.checkNotNullParameter(startValue, "startValue");
        Intrinsics.checkNotNullParameter(endValue, "endValue");
        double[] dArr = this.padding;
        double d = startValue[0];
        double d2 = fraction;
        dArr[0] = d + ((endValue[0] - d) * d2);
        double d3 = startValue[1];
        dArr[1] = d3 + ((endValue[1] - d3) * d2);
        double d4 = startValue[2];
        dArr[2] = d4 + ((endValue[2] - d4) * d2);
        double d5 = startValue[3];
        dArr[3] = d5 + ((endValue[3] - d5) * d2);
        return dArr;
    }
}
