package org.maplibre.android.location;

import org.maplibre.android.location.MapLibreAnimator;

/* loaded from: classes2.dex */
public class PulsingLocationCircleAnimator extends MapLibreFloatAnimator {
    public PulsingLocationCircleAnimator(MapLibreAnimator.AnimationsValueChangeListener animationsValueChangeListener, int i, float f) {
        super(new Float[]{Float.valueOf(0.0f), Float.valueOf(f)}, animationsValueChangeListener, i);
    }
}
