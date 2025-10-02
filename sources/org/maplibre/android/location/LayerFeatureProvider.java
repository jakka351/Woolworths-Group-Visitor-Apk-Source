package org.maplibre.android.location;

import org.maplibre.geojson.Feature;
import org.maplibre.geojson.Point;

/* loaded from: classes2.dex */
class LayerFeatureProvider {
    LayerFeatureProvider() {
    }

    Feature generateLocationFeature(Feature feature, boolean z) {
        if (feature != null) {
            return feature;
        }
        Feature featureFromGeometry = Feature.fromGeometry(Point.fromLngLat(0.0d, 0.0d));
        featureFromGeometry.addNumberProperty("mapbox-property-gps-bearing", Float.valueOf(0.0f));
        featureFromGeometry.addNumberProperty("mapbox-property-compass-bearing", Float.valueOf(0.0f));
        featureFromGeometry.addBooleanProperty("mapbox-property-location-stale", Boolean.valueOf(z));
        return featureFromGeometry;
    }
}
