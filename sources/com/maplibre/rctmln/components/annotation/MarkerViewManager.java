package com.maplibre.rctmln.components.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.MapView;

/* loaded from: classes3.dex */
public class MarkerViewManager extends org.maplibre.android.plugins.markerview.MarkerViewManager {
    private MapView mapView;
    private Method markerUpdate;
    private final List<MarkerView> markers;

    public MarkerViewManager(MapView mapView, MapLibreMap mapLibreMap) throws NoSuchMethodException, SecurityException {
        super(mapView, mapLibreMap);
        this.markers = new ArrayList();
        this.mapView = mapView;
        try {
            Method declaredMethod = MarkerView.class.getSuperclass().getDeclaredMethod("update", new Class[0]);
            this.markerUpdate = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            System.out.println(e.toString());
        }
    }

    public void addMarker(MarkerView markerView) {
        super.addMarker((org.maplibre.android.plugins.markerview.MarkerView) markerView);
        this.markers.add(markerView);
    }

    public void removeMarker(MarkerView markerView) {
        super.removeMarker((org.maplibre.android.plugins.markerview.MarkerView) markerView);
        this.markers.remove(markerView);
    }

    public void removeViews() {
        Iterator<MarkerView> it = this.markers.iterator();
        while (it.hasNext()) {
            this.mapView.removeView(it.next().getView());
        }
    }

    public void restoreViews() {
        Iterator<MarkerView> it = this.markers.iterator();
        while (it.hasNext()) {
            this.mapView.addView(it.next().getView());
        }
    }

    public void updateMarkers() {
        try {
            if (this.markerUpdate != null) {
                for (int i = 0; i < this.markers.size(); i++) {
                    this.markerUpdate.invoke(this.markers.get(i), new Object[0]);
                }
            }
        } catch (IllegalAccessException e) {
            System.out.println(e.toString());
        } catch (IllegalArgumentException e2) {
            System.out.println(e2.toString());
        } catch (InvocationTargetException e3) {
            System.out.println(e3.toString());
        }
    }
}
