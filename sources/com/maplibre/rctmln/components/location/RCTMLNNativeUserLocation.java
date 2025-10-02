package com.maplibre.rctmln.components.location;

import android.content.Context;
import com.maplibre.rctmln.components.AbstractMapFeature;
import com.maplibre.rctmln.components.mapview.RCTMLNMapView;
import org.maplibre.android.location.permissions.PermissionsManager;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.OnMapReadyCallback;
import org.maplibre.android.maps.Style;

/* loaded from: classes3.dex */
public class RCTMLNNativeUserLocation extends AbstractMapFeature implements OnMapReadyCallback, Style.OnStyleLoaded {
    private boolean mEnabled;
    private MapLibreMap mMap;
    private RCTMLNMapView mMapView;
    private int mPreferredFramesPerSecond;
    private int mRenderMode;

    public RCTMLNNativeUserLocation(Context context) {
        super(context);
        this.mEnabled = true;
        this.mRenderMode = 4;
    }

    @Override // com.maplibre.rctmln.components.AbstractMapFeature
    public void addToMap(RCTMLNMapView rCTMLNMapView) {
        this.mEnabled = true;
        this.mMapView = rCTMLNMapView;
        rCTMLNMapView.getMapAsync(this);
        setRenderMode(this.mRenderMode);
        setPreferredFramesPerSecond(this.mPreferredFramesPerSecond);
    }

    @Override // com.maplibre.rctmln.components.AbstractMapFeature
    public void removeFromMap(RCTMLNMapView rCTMLNMapView) {
        this.mEnabled = false;
        MapLibreMap mapLibreMap = this.mMap;
        if (mapLibreMap != null) {
            mapLibreMap.getStyle(this);
        }
    }

    @Override // org.maplibre.android.maps.OnMapReadyCallback
    public void onMapReady(MapLibreMap mapLibreMap) {
        this.mMap = mapLibreMap;
        mapLibreMap.getStyle(this);
    }

    @Override // org.maplibre.android.maps.Style.OnStyleLoaded
    public void onStyleLoaded(Style style) {
        if (PermissionsManager.areLocationPermissionsGranted(getContext())) {
            LocationComponentManager locationComponentManager = this.mMapView.getLocationComponentManager();
            locationComponentManager.update(style);
            locationComponentManager.showUserLocation(this.mEnabled);
        }
    }

    public void setRenderMode(int i) {
        this.mRenderMode = i;
        RCTMLNMapView rCTMLNMapView = this.mMapView;
        if (rCTMLNMapView != null) {
            rCTMLNMapView.getLocationComponentManager().setRenderMode(i);
        }
    }

    public void setPreferredFramesPerSecond(int i) {
        this.mPreferredFramesPerSecond = i;
        RCTMLNMapView rCTMLNMapView = this.mMapView;
        if (rCTMLNMapView != null) {
            rCTMLNMapView.getLocationComponentManager().setPreferredFramesPerSecond(i);
        }
    }
}
