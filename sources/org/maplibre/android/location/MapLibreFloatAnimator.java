package org.maplibre.android.location;

import android.animation.FloatEvaluator;
import android.animation.TypeEvaluator;
import org.maplibre.android.location.MapLibreAnimator;

/* loaded from: classes2.dex */
class MapLibreFloatAnimator extends MapLibreAnimator<Float> {
    MapLibreFloatAnimator(Float[] fArr, MapLibreAnimator.AnimationsValueChangeListener animationsValueChangeListener, int i) {
        super(fArr, animationsValueChangeListener, i);
    }

    @Override // org.maplibre.android.location.MapLibreAnimator
    TypeEvaluator provideEvaluator() {
        return new FloatEvaluator();
    }
}
