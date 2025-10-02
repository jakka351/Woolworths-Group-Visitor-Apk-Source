package com.maplibre.rctmln.components.styles.sources;

import android.content.Context;
import android.graphics.PointF;
import android.view.View;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.maplibre.rctmln.components.AbstractMapFeature;
import com.maplibre.rctmln.components.mapview.RCTMLNMapView;
import com.maplibre.rctmln.components.styles.layers.RCTLayer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.log.Logger;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.Style;
import org.maplibre.android.style.sources.Source;
import org.maplibre.geojson.Feature;

/* loaded from: classes3.dex */
public abstract class RCTSource<T extends Source> extends AbstractMapFeature {
    public static final double DEFAULT_HITBOX_HEIGHT = 44.0d;
    public static final double DEFAULT_HITBOX_WIDTH = 44.0d;
    public static final String DEFAULT_ID = "composite";
    public static final String LOG_TAG = "RCTSource";
    protected boolean mHasPressListener;
    protected String mID;
    protected List<RCTLayer> mLayers;
    protected MapLibreMap mMap;
    protected RCTMLNMapView mMapView;
    private List<RCTLayer> mQueuedLayers;
    protected T mSource;
    protected Map<String, Double> mTouchHitbox;

    public abstract T makeSource();

    public abstract void onPress(OnPressEvent onPressEvent);

    public RCTSource(Context context) {
        super(context);
        this.mLayers = new ArrayList();
        this.mQueuedLayers = new ArrayList();
    }

    public String getID() {
        return this.mID;
    }

    public String[] getLayerIDs() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mLayers.size(); i++) {
            arrayList.add(this.mLayers.get(i).getID());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public boolean hasPressListener() {
        return this.mHasPressListener;
    }

    public void setHasPressListener(boolean z) {
        this.mHasPressListener = z;
    }

    public void setHitbox(ReadableMap readableMap) {
        HashMap map = new HashMap();
        map.put("width", Double.valueOf(readableMap.getDouble("width")));
        map.put("height", Double.valueOf(readableMap.getDouble("height")));
        this.mTouchHitbox = map;
    }

    public void setID(String str) {
        this.mID = str;
    }

    public void setSource(T t) {
        this.mSource = t;
    }

    public Map<String, Double> getTouchHitbox() {
        if (!hasPressListener()) {
            return null;
        }
        Map<String, Double> map = this.mTouchHitbox;
        return map == null ? MapBuilder.builder().put("width", Double.valueOf(44.0d)).put("height", Double.valueOf(44.0d)).build() : map;
    }

    public int getLayerCount() {
        List<RCTLayer> list = this.mQueuedLayers;
        return (list != null ? list.size() : 0) + this.mLayers.size();
    }

    @Override // com.maplibre.rctmln.components.AbstractMapFeature
    public void addToMap(RCTMLNMapView rCTMLNMapView) {
        this.mMapView = rCTMLNMapView;
        MapLibreMap mapboxMap = rCTMLNMapView.getMapboxMap();
        this.mMap = mapboxMap;
        mapboxMap.getStyle(new Style.OnStyleLoaded() { // from class: com.maplibre.rctmln.components.styles.sources.RCTSource.1
            @Override // org.maplibre.android.maps.Style.OnStyleLoaded
            public void onStyleLoaded(Style style) {
                T t = (T) style.getSourceAs(RCTSource.this.mID);
                if (t != null) {
                    RCTSource.this.mSource = t;
                } else {
                    RCTSource rCTSource = RCTSource.this;
                    rCTSource.mSource = (T) rCTSource.makeSource();
                    style.addSource(RCTSource.this.mSource);
                }
                int i = 0;
                if (RCTSource.this.mQueuedLayers != null && RCTSource.this.mQueuedLayers.size() > 0) {
                    while (i < RCTSource.this.mQueuedLayers.size()) {
                        RCTSource rCTSource2 = RCTSource.this;
                        rCTSource2.addLayerToMap((RCTLayer) rCTSource2.mQueuedLayers.get(i), i);
                        i++;
                    }
                    RCTSource.this.mQueuedLayers = null;
                    return;
                }
                if (RCTSource.this.mLayers.size() > 0) {
                    while (i < RCTSource.this.mLayers.size()) {
                        RCTSource rCTSource3 = RCTSource.this;
                        rCTSource3.addLayerToMap(rCTSource3.mLayers.get(i), i);
                        i++;
                    }
                }
            }
        });
    }

    @Override // com.maplibre.rctmln.components.AbstractMapFeature
    public void removeFromMap(RCTMLNMapView rCTMLNMapView) {
        if (this.mLayers.size() > 0) {
            for (int i = 0; i < this.mLayers.size(); i++) {
                this.mLayers.get(i).removeFromMap(this.mMapView);
            }
        }
        List<RCTLayer> list = this.mQueuedLayers;
        if (list != null) {
            list.clear();
        }
        MapLibreMap mapLibreMap = this.mMap;
        if (mapLibreMap == null || this.mSource == null || mapLibreMap.getStyle() == null) {
            return;
        }
        try {
            this.mMap.getStyle().removeSource(this.mSource);
        } catch (Throwable th) {
            Logger.w(LOG_TAG, String.format("RCTSource.removeFromMap: %s - %s", this.mSource, th.getMessage()), th);
        }
    }

    public void addLayer(View view, int i) {
        if (view instanceof RCTLayer) {
            RCTLayer rCTLayer = (RCTLayer) view;
            if (this.mMap == null) {
                this.mQueuedLayers.add(i, rCTLayer);
            } else {
                addLayerToMap(rCTLayer, i);
            }
        }
    }

    public void removeLayer(int i) {
        RCTLayer rCTLayer;
        List<RCTLayer> list = this.mQueuedLayers;
        if (list != null && list.size() > 0) {
            rCTLayer = this.mQueuedLayers.get(i);
        } else {
            rCTLayer = this.mLayers.get(i);
        }
        removeLayerFromMap(rCTLayer, i);
    }

    public RCTLayer getLayerAt(int i) {
        List<RCTLayer> list = this.mQueuedLayers;
        if (list != null && list.size() > 0) {
            return this.mQueuedLayers.get(i);
        }
        return this.mLayers.get(i);
    }

    protected void addLayerToMap(RCTLayer rCTLayer, int i) {
        RCTMLNMapView rCTMLNMapView = this.mMapView;
        if (rCTMLNMapView == null || rCTLayer == null) {
            return;
        }
        rCTLayer.addToMap(rCTMLNMapView);
        if (this.mLayers.contains(rCTLayer)) {
            return;
        }
        this.mLayers.add(i, rCTLayer);
    }

    protected void removeLayerFromMap(RCTLayer rCTLayer, int i) {
        RCTMLNMapView rCTMLNMapView = this.mMapView;
        if (rCTMLNMapView != null && rCTLayer != null) {
            rCTLayer.removeFromMap(rCTMLNMapView);
        }
        List<RCTLayer> list = this.mQueuedLayers;
        if (list != null && list.size() > 0) {
            this.mQueuedLayers.remove(i);
        } else {
            this.mLayers.remove(i);
        }
    }

    public Style getStyle() {
        MapLibreMap mapLibreMap = this.mMap;
        if (mapLibreMap == null) {
            return null;
        }
        return mapLibreMap.getStyle();
    }

    public static class OnPressEvent {
        public List<Feature> features;
        public LatLng latLng;
        public PointF screenPoint;

        public OnPressEvent(List<Feature> list, LatLng latLng, PointF pointF) {
            this.features = list;
            this.latLng = latLng;
            this.screenPoint = pointF;
        }
    }

    public static boolean isDefaultSource(String str) {
        return DEFAULT_ID.equals(str);
    }
}
