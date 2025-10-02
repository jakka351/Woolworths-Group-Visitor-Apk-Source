package org.maplibre.android.location;

import org.maplibre.android.maps.Style;
import org.maplibre.android.style.layers.Layer;

/* loaded from: classes2.dex */
class LocationComponentPositionManager {
    private String layerAbove;
    private String layerBelow;
    private final Style style;

    LocationComponentPositionManager(Style style, String str, String str2) {
        this.style = style;
        this.layerAbove = str;
        this.layerBelow = str2;
    }

    boolean update(String str, String str2) {
        String str3;
        String str4 = this.layerAbove;
        boolean z = (str4 != str && (str4 == null || !str4.equals(str))) || ((str3 = this.layerBelow) != str2 && (str3 == null || !str3.equals(str2)));
        this.layerAbove = str;
        this.layerBelow = str2;
        return z;
    }

    void addLayerToMap(Layer layer) {
        String str = this.layerAbove;
        if (str != null) {
            this.style.addLayerAbove(layer, str);
            return;
        }
        String str2 = this.layerBelow;
        if (str2 != null) {
            this.style.addLayerBelow(layer, str2);
        } else {
            this.style.addLayer(layer);
        }
    }
}
