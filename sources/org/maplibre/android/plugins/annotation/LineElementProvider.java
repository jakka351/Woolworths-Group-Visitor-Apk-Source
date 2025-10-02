package org.maplibre.android.plugins.annotation;

import java.util.concurrent.atomic.AtomicLong;
import org.maplibre.android.style.layers.LineLayer;
import org.maplibre.android.style.sources.GeoJsonOptions;
import org.maplibre.android.style.sources.GeoJsonSource;

/* loaded from: classes2.dex */
class LineElementProvider implements CoreElementProvider<LineLayer> {
    private static final AtomicLong ID_GENERATOR = new AtomicLong(0);
    private static final String ID_GEOJSON_LAYER = "mapbox-android-line-layer-%s";
    private static final String ID_GEOJSON_SOURCE = "mapbox-android-line-source-%s";
    private final String layerId;
    private final String sourceId;

    LineElementProvider() {
        long jIncrementAndGet = ID_GENERATOR.incrementAndGet();
        this.layerId = String.format(ID_GEOJSON_LAYER, Long.valueOf(jIncrementAndGet));
        this.sourceId = String.format(ID_GEOJSON_SOURCE, Long.valueOf(jIncrementAndGet));
    }

    @Override // org.maplibre.android.plugins.annotation.CoreElementProvider
    public String getLayerId() {
        return this.layerId;
    }

    @Override // org.maplibre.android.plugins.annotation.CoreElementProvider
    public String getSourceId() {
        return this.sourceId;
    }

    @Override // org.maplibre.android.plugins.annotation.CoreElementProvider
    public LineLayer getLayer() {
        return new LineLayer(this.layerId, this.sourceId);
    }

    @Override // org.maplibre.android.plugins.annotation.CoreElementProvider
    public GeoJsonSource getSource(GeoJsonOptions geoJsonOptions) {
        return new GeoJsonSource(this.sourceId, geoJsonOptions);
    }
}
