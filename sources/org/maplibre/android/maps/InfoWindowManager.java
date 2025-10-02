package org.maplibre.android.maps;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.maplibre.android.annotations.InfoWindow;
import org.maplibre.android.annotations.Marker;
import org.maplibre.android.maps.MapLibreMap;

/* loaded from: classes2.dex */
class InfoWindowManager {
    private boolean allowConcurrentMultipleInfoWindows;
    private MapLibreMap.InfoWindowAdapter infoWindowAdapter;
    private final List<InfoWindow> infoWindows = new ArrayList();
    private MapLibreMap.OnInfoWindowClickListener onInfoWindowClickListener;
    private MapLibreMap.OnInfoWindowCloseListener onInfoWindowCloseListener;
    private MapLibreMap.OnInfoWindowLongClickListener onInfoWindowLongClickListener;

    InfoWindowManager() {
    }

    void update() {
        if (this.infoWindows.isEmpty()) {
            return;
        }
        Iterator<InfoWindow> it = this.infoWindows.iterator();
        while (it.hasNext()) {
            it.next().update();
        }
    }

    void setInfoWindowAdapter(MapLibreMap.InfoWindowAdapter infoWindowAdapter) {
        this.infoWindowAdapter = infoWindowAdapter;
    }

    MapLibreMap.InfoWindowAdapter getInfoWindowAdapter() {
        return this.infoWindowAdapter;
    }

    void setAllowConcurrentMultipleOpenInfoWindows(boolean z) {
        this.allowConcurrentMultipleInfoWindows = z;
    }

    boolean isAllowConcurrentMultipleOpenInfoWindows() {
        return this.allowConcurrentMultipleInfoWindows;
    }

    boolean isInfoWindowValidForMarker(Marker marker) {
        return (marker == null || (TextUtils.isEmpty(marker.getTitle()) && TextUtils.isEmpty(marker.getSnippet()))) ? false : true;
    }

    void setOnInfoWindowClickListener(MapLibreMap.OnInfoWindowClickListener onInfoWindowClickListener) {
        this.onInfoWindowClickListener = onInfoWindowClickListener;
    }

    MapLibreMap.OnInfoWindowClickListener getOnInfoWindowClickListener() {
        return this.onInfoWindowClickListener;
    }

    void setOnInfoWindowLongClickListener(MapLibreMap.OnInfoWindowLongClickListener onInfoWindowLongClickListener) {
        this.onInfoWindowLongClickListener = onInfoWindowLongClickListener;
    }

    MapLibreMap.OnInfoWindowLongClickListener getOnInfoWindowLongClickListener() {
        return this.onInfoWindowLongClickListener;
    }

    void setOnInfoWindowCloseListener(MapLibreMap.OnInfoWindowCloseListener onInfoWindowCloseListener) {
        this.onInfoWindowCloseListener = onInfoWindowCloseListener;
    }

    MapLibreMap.OnInfoWindowCloseListener getOnInfoWindowCloseListener() {
        return this.onInfoWindowCloseListener;
    }

    public void add(InfoWindow infoWindow) {
        this.infoWindows.add(infoWindow);
    }
}
