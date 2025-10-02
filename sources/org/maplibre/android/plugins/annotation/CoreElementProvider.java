package org.maplibre.android.plugins.annotation;

import org.maplibre.android.style.layers.Layer;
import org.maplibre.android.style.sources.GeoJsonOptions;
import org.maplibre.android.style.sources.GeoJsonSource;

/* loaded from: classes2.dex */
interface CoreElementProvider<L extends Layer> {
    L getLayer();

    String getLayerId();

    GeoJsonSource getSource(GeoJsonOptions geoJsonOptions);

    String getSourceId();
}
