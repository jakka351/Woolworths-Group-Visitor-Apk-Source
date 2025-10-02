package org.maplibre.android.annotations;

import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.List;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.maps.MapLibreMap;

@Deprecated
/* loaded from: classes2.dex */
public final class Polygon extends BasePointCollection {
    private int fillColor = ViewCompat.MEASURED_STATE_MASK;
    private int strokeColor = ViewCompat.MEASURED_STATE_MASK;
    private List<List<LatLng>> holes = new ArrayList();

    Polygon() {
    }

    public int getFillColor() {
        return this.fillColor;
    }

    public int getStrokeColor() {
        return this.strokeColor;
    }

    public List<List<LatLng>> getHoles() {
        return new ArrayList(this.holes);
    }

    public void setFillColor(int i) {
        this.fillColor = i;
        update();
    }

    public void setStrokeColor(int i) {
        this.strokeColor = i;
        update();
    }

    public void setHoles(List<? extends List<LatLng>> list) {
        this.holes = new ArrayList(list);
        update();
    }

    void addHole(List<LatLng> list) {
        this.holes.add(list);
        update();
    }

    @Override // org.maplibre.android.annotations.BasePointCollection
    void update() {
        MapLibreMap mapLibreMap = getMapLibreMap();
        if (mapLibreMap != null) {
            mapLibreMap.updatePolygon(this);
        }
    }
}
