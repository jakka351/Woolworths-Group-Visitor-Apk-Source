package org.maplibre.android.location;

import android.view.animation.Interpolator;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.location.MapLibreAnimator;
import org.maplibre.android.maps.MapLibreMap;

/* loaded from: classes2.dex */
final class MapLibreAnimatorProvider {
    private static MapLibreAnimatorProvider INSTANCE;

    private MapLibreAnimatorProvider() {
    }

    public static MapLibreAnimatorProvider getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MapLibreAnimatorProvider();
        }
        return INSTANCE;
    }

    MapLibreLatLngAnimator latLngAnimator(LatLng[] latLngArr, MapLibreAnimator.AnimationsValueChangeListener animationsValueChangeListener, int i) {
        return new MapLibreLatLngAnimator(latLngArr, animationsValueChangeListener, i);
    }

    MapLibreFloatAnimator floatAnimator(Float[] fArr, MapLibreAnimator.AnimationsValueChangeListener animationsValueChangeListener, int i) {
        return new MapLibreFloatAnimator(fArr, animationsValueChangeListener, i);
    }

    MapLibreCameraAnimatorAdapter cameraAnimator(Float[] fArr, MapLibreAnimator.AnimationsValueChangeListener animationsValueChangeListener, MapLibreMap.CancelableCallback cancelableCallback) {
        return new MapLibreCameraAnimatorAdapter(fArr, animationsValueChangeListener, cancelableCallback);
    }

    MapLibrePaddingAnimator paddingAnimator(double[][] dArr, MapLibreAnimator.AnimationsValueChangeListener<double[]> animationsValueChangeListener, MapLibreMap.CancelableCallback cancelableCallback) {
        return new MapLibrePaddingAnimator(dArr, animationsValueChangeListener, cancelableCallback);
    }

    PulsingLocationCircleAnimator pulsingCircleAnimator(MapLibreAnimator.AnimationsValueChangeListener animationsValueChangeListener, int i, float f, float f2, Interpolator interpolator) {
        PulsingLocationCircleAnimator pulsingLocationCircleAnimator = new PulsingLocationCircleAnimator(animationsValueChangeListener, i, f2);
        pulsingLocationCircleAnimator.setDuration((long) f);
        pulsingLocationCircleAnimator.setRepeatMode(1);
        pulsingLocationCircleAnimator.setRepeatCount(-1);
        pulsingLocationCircleAnimator.setInterpolator(interpolator);
        return pulsingLocationCircleAnimator;
    }
}
