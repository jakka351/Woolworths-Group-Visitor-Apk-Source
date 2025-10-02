package org.maplibre.android.location;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.maplibre.android.maps.MapLibreMap;

/* compiled from: MapLibreAnimatorListener.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/maplibre/android/location/MapLibreAnimatorListener;", "Landroid/animation/AnimatorListenerAdapter;", "cancelableCallback", "Lorg/maplibre/android/maps/MapLibreMap$CancelableCallback;", "<init>", "(Lorg/maplibre/android/maps/MapLibreMap$CancelableCallback;)V", "onAnimationCancel", "", "animation", "Landroid/animation/Animator;", "onAnimationEnd", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MapLibreAnimatorListener extends AnimatorListenerAdapter {
    private final MapLibreMap.CancelableCallback cancelableCallback;

    public MapLibreAnimatorListener(MapLibreMap.CancelableCallback cancelableCallback) {
        this.cancelableCallback = cancelableCallback;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        MapLibreMap.CancelableCallback cancelableCallback = this.cancelableCallback;
        if (cancelableCallback != null) {
            cancelableCallback.onCancel();
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        MapLibreMap.CancelableCallback cancelableCallback = this.cancelableCallback;
        if (cancelableCallback != null) {
            cancelableCallback.onFinish();
        }
    }
}
