package org.maplibre.android.location;

import android.animation.TypeEvaluator;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.location.MapLibreAnimator;

/* loaded from: classes2.dex */
class MapLibreLatLngAnimator extends MapLibreAnimator<LatLng> {
    MapLibreLatLngAnimator(LatLng[] latLngArr, MapLibreAnimator.AnimationsValueChangeListener animationsValueChangeListener, int i) {
        super(latLngArr, animationsValueChangeListener, i);
    }

    @Override // org.maplibre.android.location.MapLibreAnimator
    TypeEvaluator provideEvaluator() {
        return new LatLngEvaluator();
    }
}
