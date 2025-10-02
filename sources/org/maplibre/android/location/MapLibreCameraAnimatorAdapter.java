package org.maplibre.android.location;

import org.maplibre.android.location.MapLibreAnimator;
import org.maplibre.android.maps.MapLibreMap;

/* loaded from: classes2.dex */
class MapLibreCameraAnimatorAdapter extends MapLibreFloatAnimator {
    MapLibreCameraAnimatorAdapter(Float[] fArr, MapLibreAnimator.AnimationsValueChangeListener animationsValueChangeListener, MapLibreMap.CancelableCallback cancelableCallback) {
        super(fArr, animationsValueChangeListener, Integer.MAX_VALUE);
        addListener(new MapLibreAnimatorListener(cancelableCallback));
    }
}
