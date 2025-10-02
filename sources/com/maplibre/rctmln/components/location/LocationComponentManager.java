package com.maplibre.rctmln.components.location;

import android.content.Context;
import com.maplibre.rctmln.R;
import com.maplibre.rctmln.components.mapview.RCTMLNMapView;
import com.maplibre.rctmln.location.LocationManager;
import org.maplibre.android.location.LocationComponent;
import org.maplibre.android.location.LocationComponentActivationOptions;
import org.maplibre.android.location.LocationComponentOptions;
import org.maplibre.android.location.OnCameraTrackingChangedListener;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.Style;

/* loaded from: classes3.dex */
public class LocationComponentManager {
    private Context mContext;
    private LocationManager mLocationManager;
    private MapLibreMap mMap;
    private RCTMLNMapView mMapView;
    private LocationComponent mLocationComponent = null;
    private int mRenderMode = 4;
    private boolean mShowUserLocation = false;
    private boolean mFollowUserLocation = false;
    private boolean mShowingUserLocation = false;

    public LocationComponentManager(RCTMLNMapView rCTMLNMapView, Context context) {
        this.mMap = null;
        this.mLocationManager = null;
        this.mContext = null;
        this.mMapView = rCTMLNMapView;
        this.mMap = rCTMLNMapView.getMapboxMap();
        this.mContext = context;
        this.mLocationManager = LocationManager.getInstance(context);
    }

    public void showUserLocation(boolean z) {
        this.mShowUserLocation = z;
        stateChanged();
    }

    public void setFollowUserLocation(boolean z) {
        this.mFollowUserLocation = z;
        stateChanged();
    }

    public void setCameraMode(int i) {
        this.mLocationComponent.setCameraMode(i);
    }

    public void setRenderMode(int i) {
        this.mRenderMode = i;
        if (this.mShowingUserLocation) {
            this.mLocationComponent.setRenderMode(i);
        }
    }

    public void setPreferredFramesPerSecond(int i) {
        LocationComponent locationComponent = this.mLocationComponent;
        if (locationComponent == null || i <= 0) {
            return;
        }
        locationComponent.setMaxAnimationFps(i);
    }

    public void addOnCameraTrackingChangedListener(OnCameraTrackingChangedListener onCameraTrackingChangedListener) {
        this.mLocationComponent.addOnCameraTrackingChangedListener(onCameraTrackingChangedListener);
    }

    private void stateChanged() {
        this.mLocationComponent.setLocationComponentEnabled(this.mFollowUserLocation || this.mShowUserLocation);
        boolean z = this.mShowingUserLocation;
        boolean z2 = this.mShowUserLocation;
        if (z != z2) {
            updateShowUserLocation(z2);
        }
        if (this.mFollowUserLocation) {
            if (!this.mShowUserLocation) {
                this.mLocationComponent.setRenderMode(8);
            } else {
                this.mLocationComponent.setRenderMode(this.mRenderMode);
            }
            this.mLocationComponent.onStart();
            return;
        }
        this.mLocationComponent.setCameraMode(8);
    }

    public boolean hasLocationComponent() {
        return this.mLocationComponent != null;
    }

    public void update(Style style) {
        update(this.mShowUserLocation, style);
    }

    public void update(boolean z, Style style) {
        Integer tintColor = this.mMapView.getTintColor();
        if (this.mLocationComponent == null || tintColor != null) {
            this.mLocationComponent = this.mMap.getLocationComponent();
            this.mLocationComponent.activateLocationComponent(LocationComponentActivationOptions.builder(this.mContext, style).locationComponentOptions(options(z)).build());
            this.mLocationComponent.setLocationEngine(this.mLocationManager.getEngine());
            this.mShowingUserLocation = z;
        }
        updateShowUserLocation(z);
    }

    private void updateShowUserLocation(boolean z) {
        if (this.mShowingUserLocation != z) {
            this.mLocationComponent.applyStyle(options(z));
            this.mShowingUserLocation = z;
        }
    }

    LocationComponentOptions options(boolean z) {
        LocationComponentOptions.Builder builder = LocationComponentOptions.builder(this.mContext);
        Integer tintColor = this.mMapView.getTintColor();
        if (!z) {
            builder = builder.padding(this.mMap.getPadding()).backgroundDrawable(R.drawable.empty).backgroundDrawableStale(R.drawable.empty).bearingDrawable(R.drawable.empty).foregroundDrawable(R.drawable.empty).foregroundDrawableStale(R.drawable.empty).gpsDrawable(R.drawable.empty).accuracyAlpha(0.0f);
        } else if (tintColor != null) {
            builder = builder.enableStaleState(false).bearingTintColor(tintColor).foregroundTintColor(tintColor).accuracyColor(tintColor.intValue());
        }
        return builder.build();
    }
}
