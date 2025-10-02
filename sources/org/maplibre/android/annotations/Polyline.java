package org.maplibre.android.annotations;

import androidx.core.view.ViewCompat;
import org.maplibre.android.maps.MapLibreMap;

@Deprecated
/* loaded from: classes2.dex */
public final class Polyline extends BasePointCollection {
    private int color = ViewCompat.MEASURED_STATE_MASK;
    private float width = 10.0f;

    Polyline() {
    }

    public int getColor() {
        return this.color;
    }

    public float getWidth() {
        return this.width;
    }

    public void setColor(int i) {
        this.color = i;
        update();
    }

    public void setWidth(float f) {
        this.width = f;
        update();
    }

    @Override // org.maplibre.android.annotations.BasePointCollection
    void update() {
        MapLibreMap mapLibreMap = getMapLibreMap();
        if (mapLibreMap != null) {
            mapLibreMap.updatePolyline(this);
        }
    }
}
