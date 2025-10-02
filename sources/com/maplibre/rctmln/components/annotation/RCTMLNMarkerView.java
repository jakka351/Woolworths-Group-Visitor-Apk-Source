package com.maplibre.rctmln.components.annotation;

import android.content.Context;
import android.graphics.PointF;
import android.view.View;
import com.maplibre.rctmln.components.AbstractMapFeature;
import com.maplibre.rctmln.components.mapview.RCTMLNMapView;
import com.maplibre.rctmln.utils.GeoJSONUtils;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.OnMapReadyCallback;
import org.maplibre.android.plugins.markerview.MarkerView;
import org.maplibre.geojson.Point;

/* loaded from: classes3.dex */
public class RCTMLNMarkerView extends AbstractMapFeature implements MarkerView.OnPositionUpdateListener, View.OnLayoutChangeListener {
    private Float[] mAnchor;
    private View mChildView;
    private Point mCoordinate;
    private RCTMLNMarkerViewManager mManager;
    private RCTMLNMapView mMapView;
    private MarkerView mMarkerView;
    private MarkerViewManager mMarkerViewManager;

    public RCTMLNMarkerView(Context context, RCTMLNMarkerViewManager rCTMLNMarkerViewManager) {
        super(context);
        this.mManager = rCTMLNMarkerViewManager;
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i) {
        this.mChildView = view;
    }

    public void setCoordinate(Point point) {
        this.mCoordinate = point;
        MarkerView markerView = this.mMarkerView;
        if (markerView != null) {
            markerView.setLatLng(GeoJSONUtils.toLatLng(point));
        }
    }

    public void setAnchor(float f, float f2) {
        this.mAnchor = new Float[]{Float.valueOf(f), Float.valueOf(f2)};
        refresh();
    }

    public void refresh() {
        MarkerView markerView = this.mMarkerView;
        if (markerView != null) {
            markerView.setLatLng(GeoJSONUtils.toLatLng(this.mCoordinate));
        }
    }

    @Override // com.maplibre.rctmln.components.AbstractMapFeature
    public void addToMap(RCTMLNMapView rCTMLNMapView) {
        this.mMapView = rCTMLNMapView;
        rCTMLNMapView.getMapAsync(new OnMapReadyCallback() { // from class: com.maplibre.rctmln.components.annotation.RCTMLNMarkerView.1
            @Override // org.maplibre.android.maps.OnMapReadyCallback
            public void onMapReady(MapLibreMap mapLibreMap) {
                RCTMLNMarkerView rCTMLNMarkerView = RCTMLNMarkerView.this;
                rCTMLNMarkerView.mMarkerViewManager = rCTMLNMarkerView.mMapView.getMarkerViewManager(mapLibreMap);
                if (RCTMLNMarkerView.this.mChildView != null) {
                    RCTMLNMarkerView.this.mMarkerView = new MarkerView(GeoJSONUtils.toLatLng(RCTMLNMarkerView.this.mCoordinate), RCTMLNMarkerView.this.mChildView);
                    RCTMLNMarkerView.this.mMarkerView.setOnPositionUpdateListener(this);
                    RCTMLNMarkerView.this.mChildView.addOnLayoutChangeListener(this);
                    RCTMLNMarkerView.this.mMarkerViewManager.addMarker(RCTMLNMarkerView.this.mMarkerView);
                }
            }
        });
    }

    @Override // org.maplibre.android.plugins.markerview.MarkerView.OnPositionUpdateListener
    public PointF onUpdate(PointF pointF) {
        return this.mAnchor != null ? new PointF(pointF.x - (this.mChildView.getWidth() * this.mAnchor[0].floatValue()), pointF.y - (this.mChildView.getHeight() * this.mAnchor[1].floatValue())) : pointF;
    }

    @Override // com.maplibre.rctmln.components.AbstractMapFeature
    public void removeFromMap(RCTMLNMapView rCTMLNMapView) {
        MarkerView markerView = this.mMarkerView;
        if (markerView != null) {
            this.mMarkerViewManager.removeMarker(markerView);
            this.mChildView.removeOnLayoutChangeListener(this);
            this.mMarkerView.setOnPositionUpdateListener(null);
            this.mMarkerView = null;
            this.mMarkerViewManager = null;
        }
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (i == i5 && i3 == i7 && i2 == i6 && i4 == i8) {
            return;
        }
        refresh();
    }
}
