package org.maplibre.android.annotations;

import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.MapView;

@Deprecated
/* loaded from: classes2.dex */
public abstract class Annotation implements Comparable<Annotation> {
    private long id = -1;
    protected MapView mapView;
    protected MapLibreMap maplibreMap;

    protected Annotation() {
    }

    public long getId() {
        return this.id;
    }

    public void remove() {
        MapLibreMap mapLibreMap = this.maplibreMap;
        if (mapLibreMap == null) {
            return;
        }
        mapLibreMap.removeAnnotation(this);
    }

    public void setId(long j) {
        this.id = j;
    }

    public void setMapLibreMap(MapLibreMap mapLibreMap) {
        this.maplibreMap = mapLibreMap;
    }

    protected MapLibreMap getMapLibreMap() {
        return this.maplibreMap;
    }

    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }

    protected MapView getMapView() {
        return this.mapView;
    }

    @Override // java.lang.Comparable
    public int compareTo(Annotation annotation) {
        if (this.id < annotation.getId()) {
            return 1;
        }
        return this.id > annotation.getId() ? -1 : 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && (obj instanceof Annotation) && this.id == ((Annotation) obj).getId();
    }

    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }
}
