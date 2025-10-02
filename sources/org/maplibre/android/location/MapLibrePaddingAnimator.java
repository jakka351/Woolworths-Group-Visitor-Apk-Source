package org.maplibre.android.location;

import android.animation.TypeEvaluator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.maplibre.android.location.MapLibreAnimator;
import org.maplibre.android.maps.MapLibreMap;

/* compiled from: MapLibrePaddingAnimator.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B1\b\u0000\u0012\u000e\b\u0001\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lorg/maplibre/android/location/MapLibrePaddingAnimator;", "Lorg/maplibre/android/location/MapLibreAnimator;", "", "values", "", "updateListener", "Lorg/maplibre/android/location/MapLibreAnimator$AnimationsValueChangeListener;", "cancelableCallback", "Lorg/maplibre/android/maps/MapLibreMap$CancelableCallback;", "<init>", "([[DLorg/maplibre/android/location/MapLibreAnimator$AnimationsValueChangeListener;Lorg/maplibre/android/maps/MapLibreMap$CancelableCallback;)V", "provideEvaluator", "Landroid/animation/TypeEvaluator;", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MapLibrePaddingAnimator extends MapLibreAnimator<double[]> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapLibrePaddingAnimator(double[][] values, MapLibreAnimator.AnimationsValueChangeListener<double[]> updateListener, MapLibreMap.CancelableCallback cancelableCallback) {
        super(values, updateListener, Integer.MAX_VALUE);
        Intrinsics.checkNotNullParameter(values, "values");
        Intrinsics.checkNotNullParameter(updateListener, "updateListener");
        addListener(new MapLibreAnimatorListener(cancelableCallback));
    }

    @Override // org.maplibre.android.location.MapLibreAnimator
    public TypeEvaluator<double[]> provideEvaluator() {
        return new PaddingEvaluator();
    }
}
