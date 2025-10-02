package org.maplibre.android.location;

import android.content.Context;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Looper;
import android.os.SystemClock;
import android.view.WindowManager;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.maplibre.android.R;
import org.maplibre.android.camera.CameraPosition;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.location.engine.LocationEngine;
import org.maplibre.android.location.engine.LocationEngineCallback;
import org.maplibre.android.location.engine.LocationEngineDefault;
import org.maplibre.android.location.engine.LocationEngineRequest;
import org.maplibre.android.location.engine.LocationEngineResult;
import org.maplibre.android.log.Logger;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.Style;
import org.maplibre.android.maps.Transform;

/* loaded from: classes2.dex */
public final class LocationComponent {
    private static final String TAG = "Mbgl-LocationComponent";
    OnCameraTrackingChangedListener cameraTrackingChangedListener;
    private CompassEngine compassEngine;
    private CompassListener compassListener;
    private LocationEngineCallback<LocationEngineResult> currentLocationEngineListener;
    private final MapLibreMap.OnDeveloperAnimationListener developerAnimationListener;
    private long fastestInterval;
    private boolean isComponentInitialized;
    private boolean isComponentStarted;
    private boolean isEnabled;
    private boolean isLayerReady;
    private boolean isListeningToCompass;
    private CameraPosition lastCameraPosition;
    private Location lastLocation;
    private LocationEngineCallback<LocationEngineResult> lastLocationEngineListener;
    private long lastUpdateTime;
    private LocationAnimatorCoordinator locationAnimatorCoordinator;
    private LocationCameraController locationCameraController;
    private LocationEngine locationEngine;
    private LocationEngineRequest locationEngineRequest;
    private LocationLayerController locationLayerController;
    private final MapLibreMap maplibreMap;
    private MapLibreMap.OnCameraIdleListener onCameraIdleListener;
    private OnCameraMoveInvalidateListener onCameraMoveInvalidateListener;
    private MapLibreMap.OnCameraMoveListener onCameraMoveListener;
    private final CopyOnWriteArrayList<OnCameraTrackingChangedListener> onCameraTrackingChangedListeners;
    private final CopyOnWriteArrayList<OnLocationClickListener> onLocationClickListeners;
    private final CopyOnWriteArrayList<OnLocationLongClickListener> onLocationLongClickListeners;
    private OnLocationStaleListener onLocationStaleListener;
    private final CopyOnWriteArrayList<OnLocationStaleListener> onLocationStaleListeners;
    private MapLibreMap.OnMapClickListener onMapClickListener;
    private MapLibreMap.OnMapLongClickListener onMapLongClickListener;
    private final CopyOnWriteArrayList<OnRenderModeChangedListener> onRenderModeChangedListeners;
    private LocationComponentOptions options;
    OnRenderModeChangedListener renderModeChangedListener;
    private StaleStateManager staleStateManager;
    private Style style;
    private final Transform transform;
    private boolean useSpecializedLocationLayer;

    public void onDestroy() {
    }

    public LocationComponent(MapLibreMap mapLibreMap, Transform transform, List<MapLibreMap.OnDeveloperAnimationListener> list) {
        this.locationEngineRequest = new LocationEngineRequest.Builder(1000L).setFastestInterval(1000L).setPriority(0).build();
        this.currentLocationEngineListener = new CurrentLocationEngineCallback(this);
        this.lastLocationEngineListener = new LastLocationEngineCallback(this);
        this.onLocationStaleListeners = new CopyOnWriteArrayList<>();
        this.onLocationClickListeners = new CopyOnWriteArrayList<>();
        this.onLocationLongClickListeners = new CopyOnWriteArrayList<>();
        this.onCameraTrackingChangedListeners = new CopyOnWriteArrayList<>();
        this.onRenderModeChangedListeners = new CopyOnWriteArrayList<>();
        this.onCameraMoveListener = new MapLibreMap.OnCameraMoveListener() { // from class: org.maplibre.android.location.LocationComponent.1
            @Override // org.maplibre.android.maps.MapLibreMap.OnCameraMoveListener
            public void onCameraMove() {
                LocationComponent.this.updateLayerOffsets(false);
            }
        };
        this.onCameraIdleListener = new MapLibreMap.OnCameraIdleListener() { // from class: org.maplibre.android.location.LocationComponent.2
            @Override // org.maplibre.android.maps.MapLibreMap.OnCameraIdleListener
            public void onCameraIdle() {
                LocationComponent.this.updateLayerOffsets(false);
            }
        };
        this.onMapClickListener = new MapLibreMap.OnMapClickListener() { // from class: org.maplibre.android.location.LocationComponent.3
            @Override // org.maplibre.android.maps.MapLibreMap.OnMapClickListener
            public boolean onMapClick(LatLng latLng) {
                if (LocationComponent.this.onLocationClickListeners.isEmpty() || !LocationComponent.this.locationLayerController.onMapClick(latLng)) {
                    return false;
                }
                Iterator it = LocationComponent.this.onLocationClickListeners.iterator();
                while (it.hasNext()) {
                    ((OnLocationClickListener) it.next()).onLocationComponentClick();
                }
                return true;
            }
        };
        this.onMapLongClickListener = new MapLibreMap.OnMapLongClickListener() { // from class: org.maplibre.android.location.LocationComponent.4
            @Override // org.maplibre.android.maps.MapLibreMap.OnMapLongClickListener
            public boolean onMapLongClick(LatLng latLng) {
                if (LocationComponent.this.onLocationLongClickListeners.isEmpty() || !LocationComponent.this.locationLayerController.onMapClick(latLng)) {
                    return false;
                }
                Iterator it = LocationComponent.this.onLocationLongClickListeners.iterator();
                while (it.hasNext()) {
                    ((OnLocationLongClickListener) it.next()).onLocationComponentLongClick();
                }
                return true;
            }
        };
        this.onLocationStaleListener = new OnLocationStaleListener() { // from class: org.maplibre.android.location.LocationComponent.5
            @Override // org.maplibre.android.location.OnLocationStaleListener
            public void onStaleStateChange(boolean z) {
                LocationComponent.this.locationLayerController.setLocationsStale(z);
                Iterator it = LocationComponent.this.onLocationStaleListeners.iterator();
                while (it.hasNext()) {
                    ((OnLocationStaleListener) it.next()).onStaleStateChange(z);
                }
            }
        };
        this.onCameraMoveInvalidateListener = new OnCameraMoveInvalidateListener() { // from class: org.maplibre.android.location.LocationComponent.6
            @Override // org.maplibre.android.location.OnCameraMoveInvalidateListener
            public void onInvalidateCameraMove() {
                LocationComponent.this.onCameraMoveListener.onCameraMove();
            }
        };
        this.compassListener = new CompassListener() { // from class: org.maplibre.android.location.LocationComponent.7
            @Override // org.maplibre.android.location.CompassListener
            public void onCompassAccuracyChange(int i) {
            }

            @Override // org.maplibre.android.location.CompassListener
            public void onCompassChanged(float f) {
                LocationComponent.this.updateCompassHeading(f);
            }
        };
        this.cameraTrackingChangedListener = new OnCameraTrackingChangedListener() { // from class: org.maplibre.android.location.LocationComponent.8
            @Override // org.maplibre.android.location.OnCameraTrackingChangedListener
            public void onCameraTrackingDismissed() {
                Iterator it = LocationComponent.this.onCameraTrackingChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnCameraTrackingChangedListener) it.next()).onCameraTrackingDismissed();
                }
            }

            @Override // org.maplibre.android.location.OnCameraTrackingChangedListener
            public void onCameraTrackingChanged(int i) {
                LocationComponent.this.locationAnimatorCoordinator.cancelZoomAnimation();
                LocationComponent.this.locationAnimatorCoordinator.cancelTiltAnimation();
                LocationComponent.this.updateAnimatorListenerHolders();
                Iterator it = LocationComponent.this.onCameraTrackingChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnCameraTrackingChangedListener) it.next()).onCameraTrackingChanged(i);
                }
            }
        };
        this.renderModeChangedListener = new OnRenderModeChangedListener() { // from class: org.maplibre.android.location.LocationComponent.9
            @Override // org.maplibre.android.location.OnRenderModeChangedListener
            public void onRenderModeChanged(int i) {
                LocationComponent.this.updateAnimatorListenerHolders();
                Iterator it = LocationComponent.this.onRenderModeChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnRenderModeChangedListener) it.next()).onRenderModeChanged(i);
                }
            }
        };
        MapLibreMap.OnDeveloperAnimationListener onDeveloperAnimationListener = new MapLibreMap.OnDeveloperAnimationListener() { // from class: org.maplibre.android.location.LocationComponent.10
            @Override // org.maplibre.android.maps.MapLibreMap.OnDeveloperAnimationListener
            public void onDeveloperAnimationStarted() {
                if (LocationComponent.this.isComponentInitialized && LocationComponent.this.isEnabled) {
                    LocationComponent.this.setCameraMode(8);
                }
            }
        };
        this.developerAnimationListener = onDeveloperAnimationListener;
        this.maplibreMap = mapLibreMap;
        this.transform = transform;
        list.add(onDeveloperAnimationListener);
    }

    LocationComponent() {
        this.locationEngineRequest = new LocationEngineRequest.Builder(1000L).setFastestInterval(1000L).setPriority(0).build();
        this.currentLocationEngineListener = new CurrentLocationEngineCallback(this);
        this.lastLocationEngineListener = new LastLocationEngineCallback(this);
        this.onLocationStaleListeners = new CopyOnWriteArrayList<>();
        this.onLocationClickListeners = new CopyOnWriteArrayList<>();
        this.onLocationLongClickListeners = new CopyOnWriteArrayList<>();
        this.onCameraTrackingChangedListeners = new CopyOnWriteArrayList<>();
        this.onRenderModeChangedListeners = new CopyOnWriteArrayList<>();
        this.onCameraMoveListener = new MapLibreMap.OnCameraMoveListener() { // from class: org.maplibre.android.location.LocationComponent.1
            @Override // org.maplibre.android.maps.MapLibreMap.OnCameraMoveListener
            public void onCameraMove() {
                LocationComponent.this.updateLayerOffsets(false);
            }
        };
        this.onCameraIdleListener = new MapLibreMap.OnCameraIdleListener() { // from class: org.maplibre.android.location.LocationComponent.2
            @Override // org.maplibre.android.maps.MapLibreMap.OnCameraIdleListener
            public void onCameraIdle() {
                LocationComponent.this.updateLayerOffsets(false);
            }
        };
        this.onMapClickListener = new MapLibreMap.OnMapClickListener() { // from class: org.maplibre.android.location.LocationComponent.3
            @Override // org.maplibre.android.maps.MapLibreMap.OnMapClickListener
            public boolean onMapClick(LatLng latLng) {
                if (LocationComponent.this.onLocationClickListeners.isEmpty() || !LocationComponent.this.locationLayerController.onMapClick(latLng)) {
                    return false;
                }
                Iterator it = LocationComponent.this.onLocationClickListeners.iterator();
                while (it.hasNext()) {
                    ((OnLocationClickListener) it.next()).onLocationComponentClick();
                }
                return true;
            }
        };
        this.onMapLongClickListener = new MapLibreMap.OnMapLongClickListener() { // from class: org.maplibre.android.location.LocationComponent.4
            @Override // org.maplibre.android.maps.MapLibreMap.OnMapLongClickListener
            public boolean onMapLongClick(LatLng latLng) {
                if (LocationComponent.this.onLocationLongClickListeners.isEmpty() || !LocationComponent.this.locationLayerController.onMapClick(latLng)) {
                    return false;
                }
                Iterator it = LocationComponent.this.onLocationLongClickListeners.iterator();
                while (it.hasNext()) {
                    ((OnLocationLongClickListener) it.next()).onLocationComponentLongClick();
                }
                return true;
            }
        };
        this.onLocationStaleListener = new OnLocationStaleListener() { // from class: org.maplibre.android.location.LocationComponent.5
            @Override // org.maplibre.android.location.OnLocationStaleListener
            public void onStaleStateChange(boolean z) {
                LocationComponent.this.locationLayerController.setLocationsStale(z);
                Iterator it = LocationComponent.this.onLocationStaleListeners.iterator();
                while (it.hasNext()) {
                    ((OnLocationStaleListener) it.next()).onStaleStateChange(z);
                }
            }
        };
        this.onCameraMoveInvalidateListener = new OnCameraMoveInvalidateListener() { // from class: org.maplibre.android.location.LocationComponent.6
            @Override // org.maplibre.android.location.OnCameraMoveInvalidateListener
            public void onInvalidateCameraMove() {
                LocationComponent.this.onCameraMoveListener.onCameraMove();
            }
        };
        this.compassListener = new CompassListener() { // from class: org.maplibre.android.location.LocationComponent.7
            @Override // org.maplibre.android.location.CompassListener
            public void onCompassAccuracyChange(int i) {
            }

            @Override // org.maplibre.android.location.CompassListener
            public void onCompassChanged(float f) {
                LocationComponent.this.updateCompassHeading(f);
            }
        };
        this.cameraTrackingChangedListener = new OnCameraTrackingChangedListener() { // from class: org.maplibre.android.location.LocationComponent.8
            @Override // org.maplibre.android.location.OnCameraTrackingChangedListener
            public void onCameraTrackingDismissed() {
                Iterator it = LocationComponent.this.onCameraTrackingChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnCameraTrackingChangedListener) it.next()).onCameraTrackingDismissed();
                }
            }

            @Override // org.maplibre.android.location.OnCameraTrackingChangedListener
            public void onCameraTrackingChanged(int i) {
                LocationComponent.this.locationAnimatorCoordinator.cancelZoomAnimation();
                LocationComponent.this.locationAnimatorCoordinator.cancelTiltAnimation();
                LocationComponent.this.updateAnimatorListenerHolders();
                Iterator it = LocationComponent.this.onCameraTrackingChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnCameraTrackingChangedListener) it.next()).onCameraTrackingChanged(i);
                }
            }
        };
        this.renderModeChangedListener = new OnRenderModeChangedListener() { // from class: org.maplibre.android.location.LocationComponent.9
            @Override // org.maplibre.android.location.OnRenderModeChangedListener
            public void onRenderModeChanged(int i) {
                LocationComponent.this.updateAnimatorListenerHolders();
                Iterator it = LocationComponent.this.onRenderModeChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnRenderModeChangedListener) it.next()).onRenderModeChanged(i);
                }
            }
        };
        this.developerAnimationListener = new MapLibreMap.OnDeveloperAnimationListener() { // from class: org.maplibre.android.location.LocationComponent.10
            @Override // org.maplibre.android.maps.MapLibreMap.OnDeveloperAnimationListener
            public void onDeveloperAnimationStarted() {
                if (LocationComponent.this.isComponentInitialized && LocationComponent.this.isEnabled) {
                    LocationComponent.this.setCameraMode(8);
                }
            }
        };
        this.maplibreMap = null;
        this.transform = null;
    }

    LocationComponent(MapLibreMap mapLibreMap, Transform transform, List<MapLibreMap.OnDeveloperAnimationListener> list, LocationEngineCallback<LocationEngineResult> locationEngineCallback, LocationEngineCallback<LocationEngineResult> locationEngineCallback2, LocationLayerController locationLayerController, LocationCameraController locationCameraController, LocationAnimatorCoordinator locationAnimatorCoordinator, StaleStateManager staleStateManager, CompassEngine compassEngine, boolean z) {
        this.locationEngineRequest = new LocationEngineRequest.Builder(1000L).setFastestInterval(1000L).setPriority(0).build();
        this.currentLocationEngineListener = new CurrentLocationEngineCallback(this);
        this.lastLocationEngineListener = new LastLocationEngineCallback(this);
        this.onLocationStaleListeners = new CopyOnWriteArrayList<>();
        this.onLocationClickListeners = new CopyOnWriteArrayList<>();
        this.onLocationLongClickListeners = new CopyOnWriteArrayList<>();
        this.onCameraTrackingChangedListeners = new CopyOnWriteArrayList<>();
        this.onRenderModeChangedListeners = new CopyOnWriteArrayList<>();
        this.onCameraMoveListener = new MapLibreMap.OnCameraMoveListener() { // from class: org.maplibre.android.location.LocationComponent.1
            @Override // org.maplibre.android.maps.MapLibreMap.OnCameraMoveListener
            public void onCameraMove() {
                LocationComponent.this.updateLayerOffsets(false);
            }
        };
        this.onCameraIdleListener = new MapLibreMap.OnCameraIdleListener() { // from class: org.maplibre.android.location.LocationComponent.2
            @Override // org.maplibre.android.maps.MapLibreMap.OnCameraIdleListener
            public void onCameraIdle() {
                LocationComponent.this.updateLayerOffsets(false);
            }
        };
        this.onMapClickListener = new MapLibreMap.OnMapClickListener() { // from class: org.maplibre.android.location.LocationComponent.3
            @Override // org.maplibre.android.maps.MapLibreMap.OnMapClickListener
            public boolean onMapClick(LatLng latLng) {
                if (LocationComponent.this.onLocationClickListeners.isEmpty() || !LocationComponent.this.locationLayerController.onMapClick(latLng)) {
                    return false;
                }
                Iterator it = LocationComponent.this.onLocationClickListeners.iterator();
                while (it.hasNext()) {
                    ((OnLocationClickListener) it.next()).onLocationComponentClick();
                }
                return true;
            }
        };
        this.onMapLongClickListener = new MapLibreMap.OnMapLongClickListener() { // from class: org.maplibre.android.location.LocationComponent.4
            @Override // org.maplibre.android.maps.MapLibreMap.OnMapLongClickListener
            public boolean onMapLongClick(LatLng latLng) {
                if (LocationComponent.this.onLocationLongClickListeners.isEmpty() || !LocationComponent.this.locationLayerController.onMapClick(latLng)) {
                    return false;
                }
                Iterator it = LocationComponent.this.onLocationLongClickListeners.iterator();
                while (it.hasNext()) {
                    ((OnLocationLongClickListener) it.next()).onLocationComponentLongClick();
                }
                return true;
            }
        };
        this.onLocationStaleListener = new OnLocationStaleListener() { // from class: org.maplibre.android.location.LocationComponent.5
            @Override // org.maplibre.android.location.OnLocationStaleListener
            public void onStaleStateChange(boolean z2) {
                LocationComponent.this.locationLayerController.setLocationsStale(z2);
                Iterator it = LocationComponent.this.onLocationStaleListeners.iterator();
                while (it.hasNext()) {
                    ((OnLocationStaleListener) it.next()).onStaleStateChange(z2);
                }
            }
        };
        this.onCameraMoveInvalidateListener = new OnCameraMoveInvalidateListener() { // from class: org.maplibre.android.location.LocationComponent.6
            @Override // org.maplibre.android.location.OnCameraMoveInvalidateListener
            public void onInvalidateCameraMove() {
                LocationComponent.this.onCameraMoveListener.onCameraMove();
            }
        };
        this.compassListener = new CompassListener() { // from class: org.maplibre.android.location.LocationComponent.7
            @Override // org.maplibre.android.location.CompassListener
            public void onCompassAccuracyChange(int i) {
            }

            @Override // org.maplibre.android.location.CompassListener
            public void onCompassChanged(float f) {
                LocationComponent.this.updateCompassHeading(f);
            }
        };
        this.cameraTrackingChangedListener = new OnCameraTrackingChangedListener() { // from class: org.maplibre.android.location.LocationComponent.8
            @Override // org.maplibre.android.location.OnCameraTrackingChangedListener
            public void onCameraTrackingDismissed() {
                Iterator it = LocationComponent.this.onCameraTrackingChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnCameraTrackingChangedListener) it.next()).onCameraTrackingDismissed();
                }
            }

            @Override // org.maplibre.android.location.OnCameraTrackingChangedListener
            public void onCameraTrackingChanged(int i) {
                LocationComponent.this.locationAnimatorCoordinator.cancelZoomAnimation();
                LocationComponent.this.locationAnimatorCoordinator.cancelTiltAnimation();
                LocationComponent.this.updateAnimatorListenerHolders();
                Iterator it = LocationComponent.this.onCameraTrackingChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnCameraTrackingChangedListener) it.next()).onCameraTrackingChanged(i);
                }
            }
        };
        this.renderModeChangedListener = new OnRenderModeChangedListener() { // from class: org.maplibre.android.location.LocationComponent.9
            @Override // org.maplibre.android.location.OnRenderModeChangedListener
            public void onRenderModeChanged(int i) {
                LocationComponent.this.updateAnimatorListenerHolders();
                Iterator it = LocationComponent.this.onRenderModeChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnRenderModeChangedListener) it.next()).onRenderModeChanged(i);
                }
            }
        };
        MapLibreMap.OnDeveloperAnimationListener onDeveloperAnimationListener = new MapLibreMap.OnDeveloperAnimationListener() { // from class: org.maplibre.android.location.LocationComponent.10
            @Override // org.maplibre.android.maps.MapLibreMap.OnDeveloperAnimationListener
            public void onDeveloperAnimationStarted() {
                if (LocationComponent.this.isComponentInitialized && LocationComponent.this.isEnabled) {
                    LocationComponent.this.setCameraMode(8);
                }
            }
        };
        this.developerAnimationListener = onDeveloperAnimationListener;
        this.maplibreMap = mapLibreMap;
        this.transform = transform;
        list.add(onDeveloperAnimationListener);
        this.currentLocationEngineListener = locationEngineCallback;
        this.lastLocationEngineListener = locationEngineCallback2;
        this.locationLayerController = locationLayerController;
        this.locationCameraController = locationCameraController;
        this.locationAnimatorCoordinator = locationAnimatorCoordinator;
        this.staleStateManager = staleStateManager;
        this.compassEngine = compassEngine;
        this.useSpecializedLocationLayer = z;
        this.isComponentInitialized = true;
    }

    public void activateLocationComponent(LocationComponentActivationOptions locationComponentActivationOptions) {
        LocationComponentOptions locationComponentOptions = locationComponentActivationOptions.locationComponentOptions();
        if (locationComponentOptions == null) {
            int iStyleRes = locationComponentActivationOptions.styleRes();
            if (iStyleRes == 0) {
                iStyleRes = R.style.maplibre_LocationComponent;
            }
            locationComponentOptions = LocationComponentOptions.createFromAttributes(locationComponentActivationOptions.context(), iStyleRes);
        }
        initialize(locationComponentActivationOptions.context(), locationComponentActivationOptions.style(), locationComponentActivationOptions.useSpecializedLocationLayer(), locationComponentOptions);
        applyStyle(locationComponentOptions);
        LocationEngineRequest locationEngineRequest = locationComponentActivationOptions.locationEngineRequest();
        if (locationEngineRequest != null) {
            setLocationEngineRequest(locationEngineRequest);
        }
        LocationEngine locationEngine = locationComponentActivationOptions.locationEngine();
        if (locationEngine != null) {
            setLocationEngine(locationEngine);
        } else if (locationComponentActivationOptions.useDefaultLocationEngine()) {
            setLocationEngine(LocationEngineDefault.INSTANCE.getDefaultLocationEngine(locationComponentActivationOptions.context()));
        } else {
            setLocationEngine(null);
        }
    }

    public void setLocationComponentEnabled(boolean z) {
        checkActivationState();
        if (z) {
            enableLocationComponent();
        } else {
            disableLocationComponent();
        }
        this.locationCameraController.setEnabled(z);
    }

    public boolean isLocationComponentEnabled() {
        checkActivationState();
        return this.isEnabled;
    }

    public void setCameraMode(int i) {
        setCameraMode(i, null);
    }

    public void setCameraMode(int i, OnLocationCameraTransitionListener onLocationCameraTransitionListener) {
        setCameraMode(i, 750L, null, null, null, onLocationCameraTransitionListener);
    }

    public void setCameraMode(int i, long j, Double d, Double d2, Double d3, OnLocationCameraTransitionListener onLocationCameraTransitionListener) {
        checkActivationState();
        this.locationCameraController.setCameraMode(i, this.lastLocation, j, d, d2, d3, new CameraTransitionListener(onLocationCameraTransitionListener));
        updateCompassListenerState(true);
    }

    private class CameraTransitionListener implements OnLocationCameraTransitionListener {
        private final OnLocationCameraTransitionListener externalListener;

        private CameraTransitionListener(OnLocationCameraTransitionListener onLocationCameraTransitionListener) {
            this.externalListener = onLocationCameraTransitionListener;
        }

        @Override // org.maplibre.android.location.OnLocationCameraTransitionListener
        public void onLocationCameraTransitionFinished(int i) {
            OnLocationCameraTransitionListener onLocationCameraTransitionListener = this.externalListener;
            if (onLocationCameraTransitionListener != null) {
                onLocationCameraTransitionListener.onLocationCameraTransitionFinished(i);
            }
            reset(i);
        }

        @Override // org.maplibre.android.location.OnLocationCameraTransitionListener
        public void onLocationCameraTransitionCanceled(int i) {
            OnLocationCameraTransitionListener onLocationCameraTransitionListener = this.externalListener;
            if (onLocationCameraTransitionListener != null) {
                onLocationCameraTransitionListener.onLocationCameraTransitionCanceled(i);
            }
            reset(i);
        }

        private void reset(int i) {
            LocationComponent.this.locationAnimatorCoordinator.resetAllCameraAnimations(LocationComponent.this.maplibreMap.getCameraPosition(), i == 36);
        }
    }

    public int getCameraMode() {
        checkActivationState();
        return this.locationCameraController.getCameraMode();
    }

    public void setRenderMode(int i) {
        checkActivationState();
        if (this.lastLocation != null && i == 8) {
            this.locationAnimatorCoordinator.cancelAndRemoveGpsBearingAnimation();
            this.locationLayerController.setGpsBearing(this.lastLocation.getBearing());
        }
        this.locationLayerController.setRenderMode(i);
        updateLayerOffsets(true);
        updateCompassListenerState(true);
    }

    public int getRenderMode() {
        checkActivationState();
        return this.locationLayerController.getRenderMode();
    }

    public LocationComponentOptions getLocationComponentOptions() {
        checkActivationState();
        return this.options;
    }

    public void applyStyle(Context context, int i) {
        checkActivationState();
        applyStyle(LocationComponentOptions.createFromAttributes(context, i));
    }

    public void applyStyle(LocationComponentOptions locationComponentOptions) {
        checkActivationState();
        this.options = locationComponentOptions;
        if (this.maplibreMap.getStyle() != null) {
            this.locationLayerController.applyStyle(locationComponentOptions);
            this.locationCameraController.initializeOptions(locationComponentOptions);
            this.staleStateManager.setEnabled(locationComponentOptions.enableStaleState());
            this.staleStateManager.setDelayTime(locationComponentOptions.staleStateTimeout());
            this.locationAnimatorCoordinator.setTrackingAnimationDurationMultiplier(locationComponentOptions.trackingAnimationDurationMultiplier());
            this.locationAnimatorCoordinator.setCompassAnimationEnabled(locationComponentOptions.compassAnimationEnabled());
            this.locationAnimatorCoordinator.setAccuracyAnimationEnabled(locationComponentOptions.accuracyAnimationEnabled());
            if (locationComponentOptions.pulseEnabled().booleanValue()) {
                startPulsingLocationCircle();
            } else {
                stopPulsingLocationCircle();
            }
            updateMapWithOptions(locationComponentOptions);
        }
    }

    private void startPulsingLocationCircle() {
        if (this.isEnabled && this.isLayerReady) {
            this.locationAnimatorCoordinator.startLocationComponentCirclePulsing(this.options);
            this.locationLayerController.adjustPulsingCircleLayerVisibility(true);
        }
    }

    public void zoomWhileTracking(double d, long j, MapLibreMap.CancelableCallback cancelableCallback) {
        checkActivationState();
        if (!this.isLayerReady) {
            notifyUnsuccessfulCameraOperation(cancelableCallback, null);
            return;
        }
        if (getCameraMode() == 8) {
            notifyUnsuccessfulCameraOperation(cancelableCallback, String.format("%s%s", "LocationComponent#zoomWhileTracking method can only be used", " when a camera mode other than CameraMode#NONE is engaged."));
        } else if (this.locationCameraController.isTransitioning()) {
            notifyUnsuccessfulCameraOperation(cancelableCallback, "LocationComponent#zoomWhileTracking method call is ignored because the camera mode is transitioning");
        } else {
            this.locationAnimatorCoordinator.feedNewZoomLevel(d, this.maplibreMap.getCameraPosition(), j, cancelableCallback);
        }
    }

    public void zoomWhileTracking(double d, long j) {
        checkActivationState();
        zoomWhileTracking(d, j, null);
    }

    public void zoomWhileTracking(double d) {
        checkActivationState();
        zoomWhileTracking(d, 750L, null);
    }

    public void cancelZoomWhileTrackingAnimation() {
        checkActivationState();
        this.locationAnimatorCoordinator.cancelZoomAnimation();
    }

    public void paddingWhileTracking(double[] dArr) {
        paddingWhileTracking(dArr, 750L, null);
    }

    public void paddingWhileTracking(double[] dArr, long j) {
        paddingWhileTracking(dArr, j, null);
    }

    public void paddingWhileTracking(double[] dArr, long j, MapLibreMap.CancelableCallback cancelableCallback) {
        checkActivationState();
        if (!this.isLayerReady) {
            notifyUnsuccessfulCameraOperation(cancelableCallback, null);
            return;
        }
        if (getCameraMode() == 8) {
            notifyUnsuccessfulCameraOperation(cancelableCallback, String.format("%s%s", "LocationComponent#paddingWhileTracking method can only be used", " when a camera mode other than CameraMode#NONE is engaged."));
        } else if (this.locationCameraController.isTransitioning()) {
            notifyUnsuccessfulCameraOperation(cancelableCallback, "LocationComponent#paddingWhileTracking method call is ignored because the camera mode is transitioning");
        } else {
            this.locationAnimatorCoordinator.feedNewPadding(dArr, this.maplibreMap.getCameraPosition(), j, cancelableCallback);
        }
    }

    public void cancelPaddingWhileTrackingAnimation() {
        checkActivationState();
        this.locationAnimatorCoordinator.cancelPaddingAnimation();
    }

    public void tiltWhileTracking(double d, long j, MapLibreMap.CancelableCallback cancelableCallback) {
        checkActivationState();
        if (!this.isLayerReady) {
            notifyUnsuccessfulCameraOperation(cancelableCallback, null);
            return;
        }
        if (getCameraMode() == 8) {
            notifyUnsuccessfulCameraOperation(cancelableCallback, String.format("%s%s", "LocationComponent#tiltWhileTracking method can only be used", " when a camera mode other than CameraMode#NONE is engaged."));
        } else if (this.locationCameraController.isTransitioning()) {
            notifyUnsuccessfulCameraOperation(cancelableCallback, "LocationComponent#tiltWhileTracking method call is ignored because the camera mode is transitioning");
        } else {
            this.locationAnimatorCoordinator.feedNewTilt(d, this.maplibreMap.getCameraPosition(), j, cancelableCallback);
        }
    }

    public void tiltWhileTracking(double d, long j) {
        checkActivationState();
        tiltWhileTracking(d, j, null);
    }

    public void tiltWhileTracking(double d) {
        checkActivationState();
        tiltWhileTracking(d, 1250L, null);
    }

    public void cancelTiltWhileTrackingAnimation() {
        checkActivationState();
        this.locationAnimatorCoordinator.cancelTiltAnimation();
    }

    public void forceLocationUpdate(Location location) {
        checkActivationState();
        updateLocation(location, false);
    }

    public void forceLocationUpdate(List<Location> list, boolean z) {
        checkActivationState();
        if (list != null && list.size() >= 1) {
            updateLocation(list.get(list.size() - 1), list.subList(0, list.size() - 1), false, z);
        } else {
            updateLocation(null, false);
        }
    }

    public void setMaxAnimationFps(int i) {
        checkActivationState();
        this.locationAnimatorCoordinator.setMaxAnimationFps(i);
    }

    public void setLocationEngine(LocationEngine locationEngine) {
        checkActivationState();
        LocationEngine locationEngine2 = this.locationEngine;
        if (locationEngine2 != null) {
            locationEngine2.removeLocationUpdates(this.currentLocationEngineListener);
            this.locationEngine = null;
        }
        if (locationEngine != null) {
            this.fastestInterval = this.locationEngineRequest.getFastestInterval();
            this.locationEngine = locationEngine;
            if (this.isLayerReady && this.isEnabled) {
                setLastLocation();
                locationEngine.requestLocationUpdates(this.locationEngineRequest, this.currentLocationEngineListener, Looper.getMainLooper());
                return;
            }
            return;
        }
        this.fastestInterval = 0L;
    }

    public void setLocationEngineRequest(LocationEngineRequest locationEngineRequest) {
        checkActivationState();
        this.locationEngineRequest = locationEngineRequest;
        setLocationEngine(this.locationEngine);
    }

    public LocationEngineRequest getLocationEngineRequest() {
        checkActivationState();
        return this.locationEngineRequest;
    }

    public LocationEngine getLocationEngine() {
        checkActivationState();
        return this.locationEngine;
    }

    public void setCompassEngine(CompassEngine compassEngine) {
        checkActivationState();
        if (this.compassEngine != null) {
            updateCompassListenerState(false);
        }
        this.compassEngine = compassEngine;
        updateCompassListenerState(true);
    }

    public CompassEngine getCompassEngine() {
        checkActivationState();
        return this.compassEngine;
    }

    public Location getLastKnownLocation() {
        checkActivationState();
        return this.lastLocation;
    }

    public void addOnLocationClickListener(OnLocationClickListener onLocationClickListener) {
        this.onLocationClickListeners.add(onLocationClickListener);
    }

    public void removeOnLocationClickListener(OnLocationClickListener onLocationClickListener) {
        this.onLocationClickListeners.remove(onLocationClickListener);
    }

    public void addOnLocationLongClickListener(OnLocationLongClickListener onLocationLongClickListener) {
        this.onLocationLongClickListeners.add(onLocationLongClickListener);
    }

    public void removeOnLocationLongClickListener(OnLocationLongClickListener onLocationLongClickListener) {
        this.onLocationLongClickListeners.remove(onLocationLongClickListener);
    }

    public void addOnCameraTrackingChangedListener(OnCameraTrackingChangedListener onCameraTrackingChangedListener) {
        this.onCameraTrackingChangedListeners.add(onCameraTrackingChangedListener);
    }

    public void removeOnCameraTrackingChangedListener(OnCameraTrackingChangedListener onCameraTrackingChangedListener) {
        this.onCameraTrackingChangedListeners.remove(onCameraTrackingChangedListener);
    }

    public void addOnRenderModeChangedListener(OnRenderModeChangedListener onRenderModeChangedListener) {
        this.onRenderModeChangedListeners.add(onRenderModeChangedListener);
    }

    public void removeRenderModeChangedListener(OnRenderModeChangedListener onRenderModeChangedListener) {
        this.onRenderModeChangedListeners.remove(onRenderModeChangedListener);
    }

    public void addOnLocationStaleListener(OnLocationStaleListener onLocationStaleListener) {
        this.onLocationStaleListeners.add(onLocationStaleListener);
    }

    public void removeOnLocationStaleListener(OnLocationStaleListener onLocationStaleListener) {
        this.onLocationStaleListeners.remove(onLocationStaleListener);
    }

    public void onStart() {
        this.isComponentStarted = true;
        onLocationLayerStart();
    }

    public void onStop() {
        onLocationLayerStop();
        this.isComponentStarted = false;
    }

    public void onStartLoadingMap() {
        onLocationLayerStop();
    }

    public void onFinishLoadingStyle() {
        if (this.isComponentInitialized) {
            Style style = this.maplibreMap.getStyle();
            this.style = style;
            this.locationLayerController.initializeComponents(style, this.options);
            this.locationCameraController.initializeOptions(this.options);
            onLocationLayerStart();
        }
    }

    private void stopPulsingLocationCircle() {
        this.locationAnimatorCoordinator.stopPulsingCircleAnimation();
        this.locationLayerController.adjustPulsingCircleLayerVisibility(false);
    }

    private void onLocationLayerStart() throws SecurityException {
        if (this.isComponentInitialized && this.isComponentStarted && this.maplibreMap.getStyle() != null) {
            if (!this.isLayerReady) {
                this.isLayerReady = true;
                this.maplibreMap.addOnCameraMoveListener(this.onCameraMoveListener);
                this.maplibreMap.addOnCameraIdleListener(this.onCameraIdleListener);
                if (this.options.enableStaleState()) {
                    this.staleStateManager.onStart();
                }
            }
            if (this.isEnabled) {
                LocationEngine locationEngine = this.locationEngine;
                if (locationEngine != null) {
                    try {
                        locationEngine.requestLocationUpdates(this.locationEngineRequest, this.currentLocationEngineListener, Looper.getMainLooper());
                    } catch (SecurityException e) {
                        Logger.e(TAG, "Unable to request location updates", e);
                    }
                }
                setCameraMode(this.locationCameraController.getCameraMode());
                if (this.options.pulseEnabled().booleanValue()) {
                    startPulsingLocationCircle();
                } else {
                    stopPulsingLocationCircle();
                }
                setLastLocation();
                updateCompassListenerState(true);
                setLastCompassHeading();
            }
        }
    }

    private void onLocationLayerStop() {
        if (this.isComponentInitialized && this.isLayerReady && this.isComponentStarted) {
            this.isLayerReady = false;
            this.staleStateManager.onStop();
            if (this.compassEngine != null) {
                updateCompassListenerState(false);
            }
            stopPulsingLocationCircle();
            this.locationAnimatorCoordinator.cancelAllAnimations();
            LocationEngine locationEngine = this.locationEngine;
            if (locationEngine != null) {
                locationEngine.removeLocationUpdates(this.currentLocationEngineListener);
            }
            this.maplibreMap.removeOnCameraMoveListener(this.onCameraMoveListener);
            this.maplibreMap.removeOnCameraIdleListener(this.onCameraIdleListener);
        }
    }

    private void initialize(Context context, Style style, boolean z, LocationComponentOptions locationComponentOptions) throws SecurityException {
        if (this.isComponentInitialized) {
            return;
        }
        this.isComponentInitialized = true;
        if (!style.isFullyLoaded()) {
            throw new IllegalStateException("Style is invalid, provide the most recently loaded one.");
        }
        this.style = style;
        this.options = locationComponentOptions;
        this.useSpecializedLocationLayer = z;
        this.maplibreMap.addOnMapClickListener(this.onMapClickListener);
        this.maplibreMap.addOnMapLongClickListener(this.onMapLongClickListener);
        this.locationLayerController = new LocationLayerController(this.maplibreMap, style, new LayerSourceProvider(), new LayerFeatureProvider(), new LayerBitmapProvider(context), locationComponentOptions, this.renderModeChangedListener, z);
        this.locationCameraController = new LocationCameraController(context, this.maplibreMap, this.transform, this.cameraTrackingChangedListener, locationComponentOptions, this.onCameraMoveInvalidateListener);
        LocationAnimatorCoordinator locationAnimatorCoordinator = new LocationAnimatorCoordinator(this.maplibreMap.getProjection(), MapLibreAnimatorSetProvider.getInstance(), MapLibreAnimatorProvider.getInstance());
        this.locationAnimatorCoordinator = locationAnimatorCoordinator;
        locationAnimatorCoordinator.setTrackingAnimationDurationMultiplier(locationComponentOptions.trackingAnimationDurationMultiplier());
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        if (windowManager != null && sensorManager != null) {
            this.compassEngine = new LocationComponentCompassEngine(windowManager, sensorManager);
        }
        this.staleStateManager = new StaleStateManager(this.onLocationStaleListener, locationComponentOptions);
        updateMapWithOptions(locationComponentOptions);
        setRenderMode(18);
        setCameraMode(8);
        onLocationLayerStart();
    }

    private void updateCompassListenerState(boolean z) {
        CompassEngine compassEngine = this.compassEngine;
        if (compassEngine != null) {
            if (!z) {
                removeCompassListener(compassEngine);
                return;
            }
            if (this.isComponentInitialized && this.isComponentStarted && this.isEnabled && this.isLayerReady) {
                if (this.locationCameraController.isConsumingCompass() || this.locationLayerController.isConsumingCompass()) {
                    if (this.isListeningToCompass) {
                        return;
                    }
                    this.isListeningToCompass = true;
                    this.compassEngine.addCompassListener(this.compassListener);
                    return;
                }
                removeCompassListener(this.compassEngine);
            }
        }
    }

    private void removeCompassListener(CompassEngine compassEngine) {
        if (this.isListeningToCompass) {
            this.isListeningToCompass = false;
            compassEngine.removeCompassListener(this.compassListener);
        }
    }

    private void enableLocationComponent() throws SecurityException {
        this.isEnabled = true;
        onLocationLayerStart();
    }

    private void disableLocationComponent() {
        this.isEnabled = false;
        this.locationLayerController.hide();
        onLocationLayerStop();
    }

    private void updateMapWithOptions(LocationComponentOptions locationComponentOptions) {
        int[] iArrPadding = locationComponentOptions.padding();
        if (iArrPadding != null) {
            this.maplibreMap.setPadding(iArrPadding[0], iArrPadding[1], iArrPadding[2], iArrPadding[3]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateLocation(Location location, boolean z) {
        updateLocation(location, null, z, false);
    }

    private void updateLocation(Location location, List<Location> list, boolean z, boolean z2) {
        if (location == null) {
            return;
        }
        if (!this.isLayerReady) {
            this.lastLocation = location;
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime - this.lastUpdateTime < this.fastestInterval) {
            return;
        }
        this.lastUpdateTime = jElapsedRealtime;
        showLocationLayerIfHidden();
        if (!z) {
            this.staleStateManager.updateLatestLocationTime();
        }
        CameraPosition cameraPosition = this.maplibreMap.getCameraPosition();
        boolean z3 = getCameraMode() == 36;
        if (list != null) {
            this.locationAnimatorCoordinator.feedNewLocation(getTargetLocationWithIntermediates(location, list), cameraPosition, z3, z2);
        } else {
            this.locationAnimatorCoordinator.feedNewLocation(location, cameraPosition, z3);
        }
        updateAccuracyRadius(location, false);
        this.lastLocation = location;
    }

    private Location[] getTargetLocationWithIntermediates(Location location, List<Location> list) {
        int size = list.size() + 1;
        Location[] locationArr = new Location[size];
        locationArr[size - 1] = location;
        for (int i = 0; i < list.size(); i++) {
            locationArr[i] = list.get(i);
        }
        return locationArr;
    }

    private void showLocationLayerIfHidden() {
        boolean zIsHidden = this.locationLayerController.isHidden();
        if (this.isEnabled && this.isComponentStarted && zIsHidden) {
            this.locationLayerController.show();
            if (this.options.pulseEnabled().booleanValue()) {
                this.locationLayerController.adjustPulsingCircleLayerVisibility(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCompassHeading(float f) {
        this.locationAnimatorCoordinator.feedNewCompassBearing(f, this.maplibreMap.getCameraPosition());
    }

    private void setLastLocation() throws SecurityException {
        LocationEngine locationEngine = this.locationEngine;
        if (locationEngine != null) {
            locationEngine.getLastLocation(this.lastLocationEngineListener);
        } else {
            updateLocation(getLastKnownLocation(), true);
        }
    }

    private void setLastCompassHeading() {
        CompassEngine compassEngine = this.compassEngine;
        updateCompassHeading(compassEngine != null ? compassEngine.getLastHeading() : 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateLayerOffsets(boolean z) {
        if (this.useSpecializedLocationLayer) {
            return;
        }
        CameraPosition cameraPosition = this.maplibreMap.getCameraPosition();
        if (this.lastCameraPosition == null || z) {
            this.lastCameraPosition = cameraPosition;
            this.locationLayerController.cameraBearingUpdated(cameraPosition.bearing);
            this.locationLayerController.cameraTiltUpdated(cameraPosition.tilt);
            updateAccuracyRadius(getLastKnownLocation(), true);
            return;
        }
        if (cameraPosition.bearing != this.lastCameraPosition.bearing) {
            this.locationLayerController.cameraBearingUpdated(cameraPosition.bearing);
        }
        if (cameraPosition.tilt != this.lastCameraPosition.tilt) {
            this.locationLayerController.cameraTiltUpdated(cameraPosition.tilt);
        }
        if (cameraPosition.zoom != this.lastCameraPosition.zoom) {
            updateAccuracyRadius(getLastKnownLocation(), true);
        }
        this.lastCameraPosition = cameraPosition;
    }

    private void updateAccuracyRadius(Location location, boolean z) {
        float fCalculateZoomLevelRadius;
        if (location == null) {
            fCalculateZoomLevelRadius = 0.0f;
        } else if (this.useSpecializedLocationLayer) {
            fCalculateZoomLevelRadius = location.getAccuracy();
        } else {
            fCalculateZoomLevelRadius = Utils.calculateZoomLevelRadius(this.maplibreMap, location);
        }
        this.locationAnimatorCoordinator.feedNewAccuracyRadius(fCalculateZoomLevelRadius, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAnimatorListenerHolders() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(this.locationLayerController.getAnimationListeners());
        hashSet.addAll(this.locationCameraController.getAnimationListeners());
        this.locationAnimatorCoordinator.updateAnimatorListenerHolders(hashSet);
        this.locationAnimatorCoordinator.resetAllCameraAnimations(this.maplibreMap.getCameraPosition(), this.locationCameraController.getCameraMode() == 36);
        this.locationAnimatorCoordinator.resetAllLayerAnimations();
    }

    static final class CurrentLocationEngineCallback implements LocationEngineCallback<LocationEngineResult> {
        private final WeakReference<LocationComponent> componentWeakReference;

        CurrentLocationEngineCallback(LocationComponent locationComponent) {
            this.componentWeakReference = new WeakReference<>(locationComponent);
        }

        @Override // org.maplibre.android.location.engine.LocationEngineCallback
        public void onSuccess(LocationEngineResult locationEngineResult) {
            LocationComponent locationComponent = this.componentWeakReference.get();
            if (locationComponent != null) {
                locationComponent.updateLocation(locationEngineResult.getLastLocation(), false);
            }
        }

        @Override // org.maplibre.android.location.engine.LocationEngineCallback
        public void onFailure(Exception exc) {
            Logger.e(LocationComponent.TAG, "Failed to obtain location update", exc);
        }
    }

    static final class LastLocationEngineCallback implements LocationEngineCallback<LocationEngineResult> {
        private final WeakReference<LocationComponent> componentWeakReference;

        LastLocationEngineCallback(LocationComponent locationComponent) {
            this.componentWeakReference = new WeakReference<>(locationComponent);
        }

        @Override // org.maplibre.android.location.engine.LocationEngineCallback
        public void onSuccess(LocationEngineResult locationEngineResult) {
            LocationComponent locationComponent = this.componentWeakReference.get();
            if (locationComponent != null) {
                locationComponent.updateLocation(locationEngineResult.getLastLocation(), true);
            }
        }

        @Override // org.maplibre.android.location.engine.LocationEngineCallback
        public void onFailure(Exception exc) {
            Logger.e(LocationComponent.TAG, "Failed to obtain last location update", exc);
        }
    }

    private void checkActivationState() {
        if (!this.isComponentInitialized) {
            throw new LocationComponentNotInitializedException();
        }
    }

    private void notifyUnsuccessfulCameraOperation(MapLibreMap.CancelableCallback cancelableCallback, String str) {
        if (str != null) {
            Logger.e(TAG, str);
        }
        if (cancelableCallback != null) {
            cancelableCallback.onCancel();
        }
    }

    public boolean isLocationComponentActivated() {
        return this.isComponentInitialized;
    }
}
