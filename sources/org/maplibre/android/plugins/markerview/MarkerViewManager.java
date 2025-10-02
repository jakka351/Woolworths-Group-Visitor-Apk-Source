package org.maplibre.android.plugins.markerview;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.MapView;

/* loaded from: classes2.dex */
public class MarkerViewManager implements MapView.OnCameraDidChangeListener, MapView.OnCameraIsChangingListener {
    private boolean initialised;
    private final MapView mapView;
    private final MapLibreMap maplibreMap;
    private final List<MarkerView> markers = new ArrayList();

    public MarkerViewManager(MapView mapView, MapLibreMap mapLibreMap) {
        this.mapView = mapView;
        this.maplibreMap = mapLibreMap;
    }

    public void onDestroy() {
        this.markers.clear();
        this.mapView.removeOnCameraDidChangeListener(this);
        this.mapView.removeOnCameraIsChangingListener(this);
        this.initialised = false;
    }

    public void addMarker(MarkerView markerView) {
        if (this.mapView.isDestroyed() || this.markers.contains(markerView)) {
            return;
        }
        if (!this.initialised) {
            this.initialised = true;
            this.mapView.addOnCameraDidChangeListener(this);
            this.mapView.addOnCameraIsChangingListener(this);
        }
        markerView.setProjection(this.maplibreMap.getProjection());
        this.mapView.addView(markerView.getView());
        this.markers.add(markerView);
        markerView.update();
    }

    public void removeMarker(MarkerView markerView) {
        if (this.mapView.isDestroyed() || !this.markers.contains(markerView)) {
            return;
        }
        this.mapView.removeView(markerView.getView());
        this.markers.remove(markerView);
    }

    private void update() {
        Iterator<MarkerView> it = this.markers.iterator();
        while (it.hasNext()) {
            it.next().update();
        }
    }

    @Override // org.maplibre.android.maps.MapView.OnCameraDidChangeListener
    public void onCameraDidChange(boolean z) {
        update();
    }

    @Override // org.maplibre.android.maps.MapView.OnCameraIsChangingListener
    public void onCameraIsChanging() {
        update();
    }
}
