package org.maplibre.android.location;

import android.content.Context;
import android.graphics.RectF;
import android.location.Location;
import android.view.MotionEvent;
import java.util.HashSet;
import java.util.Set;
import org.maplibre.android.camera.CameraPosition;
import org.maplibre.android.camera.CameraUpdate;
import org.maplibre.android.camera.CameraUpdateFactory;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.gestures.AndroidGesturesManager;
import org.maplibre.android.gestures.MoveGestureDetector;
import org.maplibre.android.gestures.RotateGestureDetector;
import org.maplibre.android.location.MapLibreAnimator;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.Transform;

/* loaded from: classes2.dex */
final class LocationCameraController {
    private int cameraMode;
    private final AndroidGesturesManager initialGesturesManager;
    private final OnCameraTrackingChangedListener internalCameraTrackingChangedListener;
    private final AndroidGesturesManager internalGesturesManager;
    private boolean isEnabled;
    private boolean isTransitioning;
    private LatLng lastLocation;
    private final MapLibreMap maplibreMap;
    private final MoveGestureDetector moveGestureDetector;
    private final OnCameraMoveInvalidateListener onCameraMoveInvalidateListener;
    private LocationComponentOptions options;
    private final Transform transform;
    private final MapLibreAnimator.AnimationsValueChangeListener<LatLng> latLngValueListener = new MapLibreAnimator.AnimationsValueChangeListener<LatLng>() { // from class: org.maplibre.android.location.LocationCameraController.2
        @Override // org.maplibre.android.location.MapLibreAnimator.AnimationsValueChangeListener
        public void onNewAnimationValue(LatLng latLng) {
            LocationCameraController.this.setLatLng(latLng);
        }
    };
    private final MapLibreAnimator.AnimationsValueChangeListener<Float> gpsBearingValueListener = new MapLibreAnimator.AnimationsValueChangeListener<Float>() { // from class: org.maplibre.android.location.LocationCameraController.3
        @Override // org.maplibre.android.location.MapLibreAnimator.AnimationsValueChangeListener
        public void onNewAnimationValue(Float f) {
            if (LocationCameraController.this.cameraMode == 36 && LocationCameraController.this.maplibreMap.getCameraPosition().bearing == 0.0d) {
                return;
            }
            LocationCameraController.this.setBearing(f.floatValue());
        }
    };
    private final MapLibreAnimator.AnimationsValueChangeListener<Float> compassBearingValueListener = new MapLibreAnimator.AnimationsValueChangeListener<Float>() { // from class: org.maplibre.android.location.LocationCameraController.4
        @Override // org.maplibre.android.location.MapLibreAnimator.AnimationsValueChangeListener
        public void onNewAnimationValue(Float f) {
            if (LocationCameraController.this.cameraMode == 32 || LocationCameraController.this.cameraMode == 16) {
                LocationCameraController.this.setBearing(f.floatValue());
            }
        }
    };
    private final MapLibreAnimator.AnimationsValueChangeListener<Float> zoomValueListener = new MapLibreAnimator.AnimationsValueChangeListener() { // from class: org.maplibre.android.location.LocationCameraController$$ExternalSyntheticLambda0
        @Override // org.maplibre.android.location.MapLibreAnimator.AnimationsValueChangeListener
        public final void onNewAnimationValue(Object obj) {
            this.f$0.m2875x38c9679d((Float) obj);
        }
    };
    private final MapLibreAnimator.AnimationsValueChangeListener<double[]> paddingValueListener = new MapLibreAnimator.AnimationsValueChangeListener() { // from class: org.maplibre.android.location.LocationCameraController$$ExternalSyntheticLambda1
        @Override // org.maplibre.android.location.MapLibreAnimator.AnimationsValueChangeListener
        public final void onNewAnimationValue(Object obj) {
            this.f$0.m2876x39ffba7c((double[]) obj);
        }
    };
    private final MapLibreAnimator.AnimationsValueChangeListener<Float> tiltValueListener = new MapLibreAnimator.AnimationsValueChangeListener() { // from class: org.maplibre.android.location.LocationCameraController$$ExternalSyntheticLambda2
        @Override // org.maplibre.android.location.MapLibreAnimator.AnimationsValueChangeListener
        public final void onNewAnimationValue(Object obj) {
            this.f$0.m2877x3b360d5b((Float) obj);
        }
    };
    private MapLibreMap.OnCameraMoveListener onCameraMoveListener = new MapLibreMap.OnCameraMoveListener() { // from class: org.maplibre.android.location.LocationCameraController.5
        @Override // org.maplibre.android.maps.MapLibreMap.OnCameraMoveListener
        public void onCameraMove() {
            if (LocationCameraController.this.isLocationTracking() && LocationCameraController.this.lastLocation != null && LocationCameraController.this.options.trackingGesturesManagement()) {
                LocationCameraController.this.maplibreMap.getUiSettings().setFocalPoint(LocationCameraController.this.maplibreMap.getProjection().toScreenLocation(LocationCameraController.this.lastLocation));
            }
        }
    };
    MapLibreMap.OnMoveListener onMoveListener = new MapLibreMap.OnMoveListener() { // from class: org.maplibre.android.location.LocationCameraController.6
        private boolean interrupt;

        @Override // org.maplibre.android.maps.MapLibreMap.OnMoveListener
        public void onMoveBegin(MoveGestureDetector moveGestureDetector) {
            if (LocationCameraController.this.options.trackingGesturesManagement() && LocationCameraController.this.isLocationTracking()) {
                if (moveGestureDetector.getPointersCount() > 1) {
                    applyMultiFingerThresholdArea(moveGestureDetector);
                    applyMultiFingerMoveThreshold(moveGestureDetector);
                    return;
                } else {
                    applySingleFingerMoveThreshold(moveGestureDetector);
                    return;
                }
            }
            LocationCameraController.this.setCameraMode(8);
        }

        private void applyMultiFingerThresholdArea(MoveGestureDetector moveGestureDetector) {
            RectF moveThresholdRect = moveGestureDetector.getMoveThresholdRect();
            if (moveThresholdRect != null && !moveThresholdRect.equals(LocationCameraController.this.options.trackingMultiFingerProtectedMoveArea())) {
                moveGestureDetector.setMoveThresholdRect(LocationCameraController.this.options.trackingMultiFingerProtectedMoveArea());
                this.interrupt = true;
            } else {
                if (moveThresholdRect != null || LocationCameraController.this.options.trackingMultiFingerProtectedMoveArea() == null) {
                    return;
                }
                moveGestureDetector.setMoveThresholdRect(LocationCameraController.this.options.trackingMultiFingerProtectedMoveArea());
                this.interrupt = true;
            }
        }

        private void applyMultiFingerMoveThreshold(MoveGestureDetector moveGestureDetector) {
            if (moveGestureDetector.getMoveThreshold() != LocationCameraController.this.options.trackingMultiFingerMoveThreshold()) {
                moveGestureDetector.setMoveThreshold(LocationCameraController.this.options.trackingMultiFingerMoveThreshold());
                this.interrupt = true;
            }
        }

        private void applySingleFingerMoveThreshold(MoveGestureDetector moveGestureDetector) {
            if (moveGestureDetector.getMoveThreshold() != LocationCameraController.this.options.trackingInitialMoveThreshold()) {
                moveGestureDetector.setMoveThreshold(LocationCameraController.this.options.trackingInitialMoveThreshold());
                this.interrupt = true;
            }
        }

        @Override // org.maplibre.android.maps.MapLibreMap.OnMoveListener
        public void onMove(MoveGestureDetector moveGestureDetector) {
            if (this.interrupt) {
                moveGestureDetector.interrupt();
            } else if (LocationCameraController.this.isLocationTracking() || LocationCameraController.this.isBearingTracking()) {
                LocationCameraController.this.setCameraMode(8);
                moveGestureDetector.interrupt();
            }
        }

        @Override // org.maplibre.android.maps.MapLibreMap.OnMoveListener
        public void onMoveEnd(MoveGestureDetector moveGestureDetector) {
            if (LocationCameraController.this.options.trackingGesturesManagement() && !this.interrupt && LocationCameraController.this.isLocationTracking()) {
                moveGestureDetector.setMoveThreshold(LocationCameraController.this.options.trackingInitialMoveThreshold());
                moveGestureDetector.setMoveThresholdRect(null);
            }
            this.interrupt = false;
        }
    };
    private MapLibreMap.OnRotateListener onRotateListener = new MapLibreMap.OnRotateListener() { // from class: org.maplibre.android.location.LocationCameraController.7
        @Override // org.maplibre.android.maps.MapLibreMap.OnRotateListener
        public void onRotate(RotateGestureDetector rotateGestureDetector) {
        }

        @Override // org.maplibre.android.maps.MapLibreMap.OnRotateListener
        public void onRotateEnd(RotateGestureDetector rotateGestureDetector) {
        }

        @Override // org.maplibre.android.maps.MapLibreMap.OnRotateListener
        public void onRotateBegin(RotateGestureDetector rotateGestureDetector) {
            if (LocationCameraController.this.isBearingTracking()) {
                LocationCameraController.this.setCameraMode(8);
            }
        }
    };
    private MapLibreMap.OnFlingListener onFlingListener = new MapLibreMap.OnFlingListener() { // from class: org.maplibre.android.location.LocationCameraController.8
        @Override // org.maplibre.android.maps.MapLibreMap.OnFlingListener
        public void onFling() {
            LocationCameraController.this.setCameraMode(8);
        }
    };

    LocationCameraController(Context context, MapLibreMap mapLibreMap, Transform transform, OnCameraTrackingChangedListener onCameraTrackingChangedListener, LocationComponentOptions locationComponentOptions, OnCameraMoveInvalidateListener onCameraMoveInvalidateListener) {
        this.maplibreMap = mapLibreMap;
        this.transform = transform;
        this.initialGesturesManager = mapLibreMap.getGesturesManager();
        LocationGesturesManager locationGesturesManager = new LocationGesturesManager(context);
        this.internalGesturesManager = locationGesturesManager;
        this.moveGestureDetector = locationGesturesManager.getMoveGestureDetector();
        mapLibreMap.addOnRotateListener(this.onRotateListener);
        mapLibreMap.addOnFlingListener(this.onFlingListener);
        mapLibreMap.addOnMoveListener(this.onMoveListener);
        mapLibreMap.addOnCameraMoveListener(this.onCameraMoveListener);
        this.internalCameraTrackingChangedListener = onCameraTrackingChangedListener;
        this.onCameraMoveInvalidateListener = onCameraMoveInvalidateListener;
        initializeOptions(locationComponentOptions);
    }

    LocationCameraController(MapLibreMap mapLibreMap, Transform transform, MoveGestureDetector moveGestureDetector, OnCameraTrackingChangedListener onCameraTrackingChangedListener, OnCameraMoveInvalidateListener onCameraMoveInvalidateListener, AndroidGesturesManager androidGesturesManager, AndroidGesturesManager androidGesturesManager2) {
        this.maplibreMap = mapLibreMap;
        mapLibreMap.addOnCameraMoveListener(this.onCameraMoveListener);
        this.transform = transform;
        this.moveGestureDetector = moveGestureDetector;
        this.internalCameraTrackingChangedListener = onCameraTrackingChangedListener;
        this.onCameraMoveInvalidateListener = onCameraMoveInvalidateListener;
        this.internalGesturesManager = androidGesturesManager2;
        this.initialGesturesManager = androidGesturesManager;
    }

    void initializeOptions(LocationComponentOptions locationComponentOptions) {
        this.options = locationComponentOptions;
        if (locationComponentOptions.trackingGesturesManagement()) {
            AndroidGesturesManager gesturesManager = this.maplibreMap.getGesturesManager();
            AndroidGesturesManager androidGesturesManager = this.internalGesturesManager;
            if (gesturesManager != androidGesturesManager) {
                this.maplibreMap.setGesturesManager(androidGesturesManager, true, true);
            }
            adjustGesturesThresholds();
            return;
        }
        AndroidGesturesManager gesturesManager2 = this.maplibreMap.getGesturesManager();
        AndroidGesturesManager androidGesturesManager2 = this.initialGesturesManager;
        if (gesturesManager2 != androidGesturesManager2) {
            this.maplibreMap.setGesturesManager(androidGesturesManager2, true, true);
        }
    }

    void setCameraMode(int i) {
        setCameraMode(i, null, 750L, null, null, null, null);
    }

    void setCameraMode(int i, Location location, long j, Double d, Double d2, Double d3, OnLocationCameraTransitionListener onLocationCameraTransitionListener) {
        if (this.cameraMode == i) {
            if (onLocationCameraTransitionListener != null) {
                onLocationCameraTransitionListener.onLocationCameraTransitionFinished(i);
                return;
            }
            return;
        }
        boolean zIsLocationTracking = isLocationTracking();
        this.cameraMode = i;
        if (i != 8) {
            this.maplibreMap.cancelTransitions();
        }
        adjustGesturesThresholds();
        notifyCameraTrackingChangeListener(zIsLocationTracking);
        transitionToCurrentLocation(zIsLocationTracking, location, j, d, d2, d3, onLocationCameraTransitionListener);
    }

    private void transitionToCurrentLocation(boolean z, Location location, long j, Double d, Double d2, Double d3, final OnLocationCameraTransitionListener onLocationCameraTransitionListener) {
        if (z || !isLocationTracking() || location == null || !this.isEnabled) {
            if (onLocationCameraTransitionListener != null) {
                onLocationCameraTransitionListener.onLocationCameraTransitionFinished(this.cameraMode);
                return;
            }
            return;
        }
        this.isTransitioning = true;
        LatLng latLng = new LatLng(location);
        CameraPosition.Builder builderTarget = new CameraPosition.Builder().target(latLng);
        if (d != null) {
            builderTarget.zoom(d.doubleValue());
        }
        if (d3 != null) {
            builderTarget.tilt(d3.doubleValue());
        }
        if (d2 != null) {
            builderTarget.bearing(d2.doubleValue());
        } else if (isLocationBearingTracking()) {
            builderTarget.bearing(this.cameraMode == 36 ? 0.0d : location.getBearing());
        }
        CameraUpdate cameraUpdateNewCameraPosition = CameraUpdateFactory.newCameraPosition(builderTarget.build());
        MapLibreMap.CancelableCallback cancelableCallback = new MapLibreMap.CancelableCallback() { // from class: org.maplibre.android.location.LocationCameraController.1
            @Override // org.maplibre.android.maps.MapLibreMap.CancelableCallback
            public void onCancel() {
                LocationCameraController.this.isTransitioning = false;
                OnLocationCameraTransitionListener onLocationCameraTransitionListener2 = onLocationCameraTransitionListener;
                if (onLocationCameraTransitionListener2 != null) {
                    onLocationCameraTransitionListener2.onLocationCameraTransitionCanceled(LocationCameraController.this.cameraMode);
                }
            }

            @Override // org.maplibre.android.maps.MapLibreMap.CancelableCallback
            public void onFinish() {
                LocationCameraController.this.isTransitioning = false;
                OnLocationCameraTransitionListener onLocationCameraTransitionListener2 = onLocationCameraTransitionListener;
                if (onLocationCameraTransitionListener2 != null) {
                    onLocationCameraTransitionListener2.onLocationCameraTransitionFinished(LocationCameraController.this.cameraMode);
                }
            }
        };
        if (Utils.immediateAnimation(this.maplibreMap.getProjection(), this.maplibreMap.getCameraPosition().target, latLng)) {
            this.transform.moveCamera(this.maplibreMap, cameraUpdateNewCameraPosition, cancelableCallback);
        } else {
            this.transform.animateCamera(this.maplibreMap, cameraUpdateNewCameraPosition, (int) j, cancelableCallback);
        }
    }

    int getCameraMode() {
        return this.cameraMode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBearing(float f) {
        if (this.isTransitioning) {
            return;
        }
        this.transform.moveCamera(this.maplibreMap, CameraUpdateFactory.bearingTo(f), null);
        this.onCameraMoveInvalidateListener.onInvalidateCameraMove();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLatLng(LatLng latLng) {
        if (this.isTransitioning) {
            return;
        }
        this.lastLocation = latLng;
        this.transform.moveCamera(this.maplibreMap, CameraUpdateFactory.newLatLng(latLng), null);
        this.onCameraMoveInvalidateListener.onInvalidateCameraMove();
    }

    private void setZoom(float f) {
        if (this.isTransitioning) {
            return;
        }
        this.transform.moveCamera(this.maplibreMap, CameraUpdateFactory.zoomTo(f), null);
        this.onCameraMoveInvalidateListener.onInvalidateCameraMove();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setPadding, reason: merged with bridge method [inline-methods] */
    public void m2876x39ffba7c(double[] dArr) {
        if (this.isTransitioning) {
            return;
        }
        this.transform.moveCamera(this.maplibreMap, CameraUpdateFactory.paddingTo(dArr), null);
        this.onCameraMoveInvalidateListener.onInvalidateCameraMove();
    }

    private void setTilt(float f) {
        if (this.isTransitioning) {
            return;
        }
        this.transform.moveCamera(this.maplibreMap, CameraUpdateFactory.tiltTo(f), null);
        this.onCameraMoveInvalidateListener.onInvalidateCameraMove();
    }

    /* renamed from: lambda$new$0$org-maplibre-android-location-LocationCameraController, reason: not valid java name */
    /* synthetic */ void m2875x38c9679d(Float f) {
        setZoom(f.floatValue());
    }

    /* renamed from: lambda$new$2$org-maplibre-android-location-LocationCameraController, reason: not valid java name */
    /* synthetic */ void m2877x3b360d5b(Float f) {
        setTilt(f.floatValue());
    }

    Set<AnimatorListenerHolder> getAnimationListeners() {
        HashSet hashSet = new HashSet();
        if (isLocationTracking()) {
            hashSet.add(new AnimatorListenerHolder(1, this.latLngValueListener));
        }
        if (isLocationBearingTracking()) {
            hashSet.add(new AnimatorListenerHolder(4, this.gpsBearingValueListener));
        }
        if (isConsumingCompass()) {
            hashSet.add(new AnimatorListenerHolder(5, this.compassBearingValueListener));
        }
        hashSet.add(new AnimatorListenerHolder(7, this.zoomValueListener));
        hashSet.add(new AnimatorListenerHolder(8, this.tiltValueListener));
        hashSet.add(new AnimatorListenerHolder(10, this.paddingValueListener));
        return hashSet;
    }

    boolean isTransitioning() {
        return this.isTransitioning;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void adjustGesturesThresholds() {
        if (this.options.trackingGesturesManagement()) {
            if (isLocationTracking()) {
                this.moveGestureDetector.setMoveThreshold(this.options.trackingInitialMoveThreshold());
            } else {
                this.moveGestureDetector.setMoveThreshold(0.0f);
                this.moveGestureDetector.setMoveThresholdRect(null);
            }
        }
    }

    boolean isConsumingCompass() {
        int i = this.cameraMode;
        return i == 32 || i == 16;
    }

    void setEnabled(boolean z) {
        this.isEnabled = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isLocationTracking() {
        int i = this.cameraMode;
        return i == 24 || i == 32 || i == 34 || i == 36;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isBearingTracking() {
        int i = this.cameraMode;
        return i == 16 || i == 32 || i == 22 || i == 34 || i == 36;
    }

    private boolean isLocationBearingTracking() {
        int i = this.cameraMode;
        return i == 34 || i == 36 || i == 22;
    }

    private void notifyCameraTrackingChangeListener(boolean z) {
        this.internalCameraTrackingChangedListener.onCameraTrackingChanged(this.cameraMode);
        if (!z || isLocationTracking()) {
            return;
        }
        this.maplibreMap.getUiSettings().setFocalPoint(null);
        this.internalCameraTrackingChangedListener.onCameraTrackingDismissed();
    }

    private class LocationGesturesManager extends AndroidGesturesManager {
        LocationGesturesManager(Context context) {
            super(context);
        }

        @Override // org.maplibre.android.gestures.AndroidGesturesManager
        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (motionEvent != null && motionEvent.getActionMasked() == 1) {
                LocationCameraController.this.adjustGesturesThresholds();
            }
            return super.onTouchEvent(motionEvent);
        }
    }
}
