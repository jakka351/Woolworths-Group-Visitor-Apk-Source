package org.maplibre.android.location;

import android.location.Location;
import android.os.SystemClock;
import android.util.SparseArray;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import java.util.ArrayList;
import java.util.Set;
import org.maplibre.android.camera.CameraPosition;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.location.MapLibreAnimator;
import org.maplibre.android.log.Logger;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.Projection;

/* loaded from: classes2.dex */
final class LocationAnimatorCoordinator {
    private static final String TAG = "Mbgl-LocationAnimatorCoordinator";
    private boolean accuracyAnimationEnabled;
    private final MapLibreAnimatorProvider animatorProvider;
    private final MapLibreAnimatorSetProvider animatorSetProvider;
    private boolean compassAnimationEnabled;
    private float durationMultiplier;
    private Location previousLocation;
    private final Projection projection;
    final SparseArray<MapLibreAnimator> animatorArray = new SparseArray<>();
    private float previousAccuracyRadius = -1.0f;
    private float previousCompassBearing = -1.0f;
    private long locationUpdateTimestamp = -1;
    int maxAnimationFps = Integer.MAX_VALUE;
    final SparseArray<MapLibreAnimator.AnimationsValueChangeListener> listeners = new SparseArray<>();

    private float checkGpsNorth(boolean z, float f) {
        if (z) {
            return 0.0f;
        }
        return f;
    }

    LocationAnimatorCoordinator(Projection projection, MapLibreAnimatorSetProvider mapLibreAnimatorSetProvider, MapLibreAnimatorProvider mapLibreAnimatorProvider) {
        this.projection = projection;
        this.animatorProvider = mapLibreAnimatorProvider;
        this.animatorSetProvider = mapLibreAnimatorSetProvider;
    }

    void updateAnimatorListenerHolders(Set<AnimatorListenerHolder> set) {
        MapLibreAnimator mapLibreAnimator;
        this.listeners.clear();
        for (AnimatorListenerHolder animatorListenerHolder : set) {
            this.listeners.append(animatorListenerHolder.getAnimatorType(), animatorListenerHolder.getListener());
        }
        for (int i = 0; i < this.animatorArray.size(); i++) {
            int iKeyAt = this.animatorArray.keyAt(i);
            if (this.listeners.get(iKeyAt) == null && (mapLibreAnimator = this.animatorArray.get(iKeyAt)) != null) {
                mapLibreAnimator.makeInvalid();
            }
        }
    }

    void feedNewLocation(Location location, CameraPosition cameraPosition, boolean z) {
        feedNewLocation(new Location[]{location}, cameraPosition, z, false);
    }

    void feedNewLocation(Location[] locationArr, CameraPosition cameraPosition, boolean z, boolean z2) {
        Location location = locationArr[locationArr.length - 1];
        if (this.previousLocation == null) {
            this.previousLocation = location;
            this.locationUpdateTimestamp = SystemClock.elapsedRealtime() - 750;
        }
        LatLng previousLayerLatLng = getPreviousLayerLatLng();
        float previousLayerGpsBearing = getPreviousLayerGpsBearing();
        LatLng latLng = cameraPosition.target;
        float fNormalize = Utils.normalize((float) cameraPosition.bearing);
        LatLng[] latLngValues = getLatLngValues(previousLayerLatLng, locationArr);
        updateLayerAnimators(latLngValues, getBearingValues(Float.valueOf(previousLayerGpsBearing), locationArr));
        latLngValues[0] = latLng;
        updateCameraAnimators(latLngValues, z ? new Float[]{Float.valueOf(fNormalize), Float.valueOf(Utils.shortestRotation(0.0f, fNormalize))} : getBearingValues(Float.valueOf(fNormalize), locationArr));
        LatLng latLng2 = new LatLng(location);
        long jMin = 0;
        if (!(Utils.immediateAnimation(this.projection, latLng, latLng2) || Utils.immediateAnimation(this.projection, previousLayerLatLng, latLng2))) {
            long j = this.locationUpdateTimestamp;
            this.locationUpdateTimestamp = SystemClock.elapsedRealtime();
            if (j != 0) {
                if (z2) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis > location.getTime()) {
                        Logger.e("LocationAnimatorCoordinator", "Lookahead enabled, but the target location's timestamp is smaller than current timestamp");
                    } else {
                        jMin = location.getTime() - jCurrentTimeMillis;
                    }
                } else {
                    jMin = (long) ((r7 - j) * this.durationMultiplier);
                }
            }
            jMin = Math.min(jMin, 2000L);
        }
        playAnimators(jMin, 0, 2, 1, 4);
        this.previousLocation = location;
    }

    void feedNewCompassBearing(float f, CameraPosition cameraPosition) {
        if (this.previousCompassBearing < 0.0f) {
            this.previousCompassBearing = f;
        }
        updateCompassAnimators(f, getPreviousLayerCompassBearing(), (float) cameraPosition.bearing);
        playAnimators(this.compassAnimationEnabled ? 500L : 0L, 3, 5);
        this.previousCompassBearing = f;
    }

    void feedNewAccuracyRadius(float f, boolean z) {
        if (this.previousAccuracyRadius < 0.0f) {
            this.previousAccuracyRadius = f;
        }
        updateAccuracyAnimators(f, getPreviousAccuracyRadius());
        playAnimators((z || !this.accuracyAnimationEnabled) ? 0L : 250L, 6);
        this.previousAccuracyRadius = f;
    }

    void startLocationComponentCirclePulsing(LocationComponentOptions locationComponentOptions) {
        cancelAnimator(9);
        MapLibreAnimator.AnimationsValueChangeListener animationsValueChangeListener = this.listeners.get(9);
        if (animationsValueChangeListener != null) {
            this.animatorArray.put(9, this.animatorProvider.pulsingCircleAnimator(animationsValueChangeListener, this.maxAnimationFps, locationComponentOptions.pulseSingleDuration(), locationComponentOptions.pulseMaxRadius(), locationComponentOptions.pulseInterpolator() == null ? new DecelerateInterpolator() : locationComponentOptions.pulseInterpolator()));
            playPulsingAnimator();
        }
    }

    void feedNewZoomLevel(double d, CameraPosition cameraPosition, long j, MapLibreMap.CancelableCallback cancelableCallback) {
        updateZoomAnimator((float) d, (float) cameraPosition.zoom, cancelableCallback);
        playAnimators(j, 7);
    }

    void feedNewPadding(double[] dArr, CameraPosition cameraPosition, long j, MapLibreMap.CancelableCallback cancelableCallback) {
        updatePaddingAnimator(dArr, cameraPosition.padding, cancelableCallback);
        playAnimators(j, 10);
    }

    void feedNewTilt(double d, CameraPosition cameraPosition, long j, MapLibreMap.CancelableCallback cancelableCallback) {
        updateTiltAnimator((float) d, (float) cameraPosition.tilt, cancelableCallback);
        playAnimators(j, 8);
    }

    private LatLng getPreviousLayerLatLng() {
        MapLibreAnimator mapLibreAnimator = this.animatorArray.get(0);
        if (mapLibreAnimator != null) {
            return (LatLng) mapLibreAnimator.getAnimatedValue();
        }
        return new LatLng(this.previousLocation);
    }

    private float getPreviousLayerGpsBearing() {
        MapLibreFloatAnimator mapLibreFloatAnimator = (MapLibreFloatAnimator) this.animatorArray.get(2);
        if (mapLibreFloatAnimator != null) {
            return ((Float) mapLibreFloatAnimator.getAnimatedValue()).floatValue();
        }
        return this.previousLocation.getBearing();
    }

    private float getPreviousLayerCompassBearing() {
        MapLibreFloatAnimator mapLibreFloatAnimator = (MapLibreFloatAnimator) this.animatorArray.get(3);
        if (mapLibreFloatAnimator != null) {
            return ((Float) mapLibreFloatAnimator.getAnimatedValue()).floatValue();
        }
        return this.previousCompassBearing;
    }

    private float getPreviousAccuracyRadius() {
        MapLibreAnimator mapLibreAnimator = this.animatorArray.get(6);
        if (mapLibreAnimator != null) {
            return ((Float) mapLibreAnimator.getAnimatedValue()).floatValue();
        }
        return this.previousAccuracyRadius;
    }

    private LatLng[] getLatLngValues(LatLng latLng, Location[] locationArr) {
        int length = locationArr.length + 1;
        LatLng[] latLngArr = new LatLng[length];
        latLngArr[0] = latLng;
        for (int i = 1; i < length; i++) {
            latLngArr[i] = new LatLng(locationArr[i - 1]);
        }
        return latLngArr;
    }

    private Float[] getBearingValues(Float f, Location[] locationArr) {
        int length = locationArr.length + 1;
        Float[] fArr = new Float[length];
        fArr[0] = Float.valueOf(Utils.normalize(f.floatValue()));
        for (int i = 1; i < length; i++) {
            int i2 = i - 1;
            fArr[i] = Float.valueOf(Utils.shortestRotation(locationArr[i2].getBearing(), fArr[i2].floatValue()));
        }
        return fArr;
    }

    private void updateLayerAnimators(LatLng[] latLngArr, Float[] fArr) {
        createNewLatLngAnimator(0, latLngArr);
        createNewFloatAnimator(2, fArr);
    }

    private void updateCameraAnimators(LatLng[] latLngArr, Float[] fArr) {
        createNewLatLngAnimator(1, latLngArr);
        createNewFloatAnimator(4, fArr);
    }

    private void updateCompassAnimators(float f, float f2, float f3) {
        createNewFloatAnimator(3, f2, Utils.shortestRotation(f, f2));
        createNewFloatAnimator(5, f3, Utils.shortestRotation(f, f3));
    }

    private void updateAccuracyAnimators(float f, float f2) {
        createNewFloatAnimator(6, f2, f);
    }

    private void updateZoomAnimator(float f, float f2, MapLibreMap.CancelableCallback cancelableCallback) {
        createNewCameraAdapterAnimator(7, new Float[]{Float.valueOf(f2), Float.valueOf(f)}, cancelableCallback);
    }

    private void updatePaddingAnimator(double[] dArr, double[] dArr2, MapLibreMap.CancelableCallback cancelableCallback) {
        createNewPaddingAnimator(10, new double[][]{dArr2, dArr}, cancelableCallback);
    }

    private void updateTiltAnimator(float f, float f2, MapLibreMap.CancelableCallback cancelableCallback) {
        createNewCameraAdapterAnimator(8, new Float[]{Float.valueOf(f2), Float.valueOf(f)}, cancelableCallback);
    }

    private void createNewLatLngAnimator(int i, LatLng latLng, LatLng latLng2) {
        createNewLatLngAnimator(i, new LatLng[]{latLng, latLng2});
    }

    private void createNewLatLngAnimator(int i, LatLng[] latLngArr) {
        cancelAnimator(i);
        MapLibreAnimator.AnimationsValueChangeListener animationsValueChangeListener = this.listeners.get(i);
        if (animationsValueChangeListener != null) {
            this.animatorArray.put(i, this.animatorProvider.latLngAnimator(latLngArr, animationsValueChangeListener, this.maxAnimationFps));
        }
    }

    private void createNewFloatAnimator(int i, float f, float f2) {
        createNewFloatAnimator(i, new Float[]{Float.valueOf(f), Float.valueOf(f2)});
    }

    private void createNewFloatAnimator(int i, Float[] fArr) {
        cancelAnimator(i);
        MapLibreAnimator.AnimationsValueChangeListener animationsValueChangeListener = this.listeners.get(i);
        if (animationsValueChangeListener != null) {
            this.animatorArray.put(i, this.animatorProvider.floatAnimator(fArr, animationsValueChangeListener, this.maxAnimationFps));
        }
    }

    private void createNewCameraAdapterAnimator(int i, Float[] fArr, MapLibreMap.CancelableCallback cancelableCallback) {
        cancelAnimator(i);
        MapLibreAnimator.AnimationsValueChangeListener animationsValueChangeListener = this.listeners.get(i);
        if (animationsValueChangeListener != null) {
            this.animatorArray.put(i, this.animatorProvider.cameraAnimator(fArr, animationsValueChangeListener, cancelableCallback));
        }
    }

    private void createNewPaddingAnimator(int i, double[][] dArr, MapLibreMap.CancelableCallback cancelableCallback) {
        cancelAnimator(i);
        MapLibreAnimator.AnimationsValueChangeListener<double[]> animationsValueChangeListener = this.listeners.get(i);
        if (animationsValueChangeListener != null) {
            this.animatorArray.put(i, this.animatorProvider.paddingAnimator(dArr, animationsValueChangeListener, cancelableCallback));
        }
    }

    private void playAnimators(long j, int... iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            MapLibreAnimator mapLibreAnimator = this.animatorArray.get(i);
            if (mapLibreAnimator != null) {
                arrayList.add(mapLibreAnimator);
            }
        }
        this.animatorSetProvider.startAnimation(arrayList, new LinearInterpolator(), j);
    }

    private void playPulsingAnimator() {
        MapLibreAnimator mapLibreAnimator = this.animatorArray.get(9);
        if (mapLibreAnimator != null) {
            mapLibreAnimator.start();
        }
    }

    void resetAllCameraAnimations(CameraPosition cameraPosition, boolean z) {
        resetCameraCompassAnimation(cameraPosition);
        playAnimators(resetCameraLocationAnimations(cameraPosition, z) ? 0L : 750L, 1, 4);
    }

    private boolean resetCameraLocationAnimations(CameraPosition cameraPosition, boolean z) {
        resetCameraGpsBearingAnimation(cameraPosition, z);
        return resetCameraLatLngAnimation(cameraPosition);
    }

    private boolean resetCameraLatLngAnimation(CameraPosition cameraPosition) {
        MapLibreLatLngAnimator mapLibreLatLngAnimator = (MapLibreLatLngAnimator) this.animatorArray.get(1);
        if (mapLibreLatLngAnimator == null) {
            return false;
        }
        LatLng target = mapLibreLatLngAnimator.getTarget();
        LatLng latLng = cameraPosition.target;
        createNewLatLngAnimator(1, latLng, target);
        return Utils.immediateAnimation(this.projection, latLng, target);
    }

    private void resetCameraGpsBearingAnimation(CameraPosition cameraPosition, boolean z) {
        MapLibreFloatAnimator mapLibreFloatAnimator = (MapLibreFloatAnimator) this.animatorArray.get(4);
        if (mapLibreFloatAnimator == null) {
            return;
        }
        float fCheckGpsNorth = checkGpsNorth(z, mapLibreFloatAnimator.getTarget().floatValue());
        float f = (float) cameraPosition.bearing;
        createNewFloatAnimator(4, f, Utils.shortestRotation(fCheckGpsNorth, f));
    }

    private void resetCameraCompassAnimation(CameraPosition cameraPosition) {
        MapLibreFloatAnimator mapLibreFloatAnimator = (MapLibreFloatAnimator) this.animatorArray.get(5);
        if (mapLibreFloatAnimator == null) {
            return;
        }
        float fFloatValue = mapLibreFloatAnimator.getTarget().floatValue();
        float f = (float) cameraPosition.bearing;
        createNewFloatAnimator(5, f, Utils.shortestRotation(fFloatValue, f));
    }

    void resetAllLayerAnimations() {
        MapLibreLatLngAnimator mapLibreLatLngAnimator = (MapLibreLatLngAnimator) this.animatorArray.get(0);
        MapLibreFloatAnimator mapLibreFloatAnimator = (MapLibreFloatAnimator) this.animatorArray.get(2);
        MapLibreFloatAnimator mapLibreFloatAnimator2 = (MapLibreFloatAnimator) this.animatorArray.get(3);
        MapLibreFloatAnimator mapLibreFloatAnimator3 = (MapLibreFloatAnimator) this.animatorArray.get(6);
        if (mapLibreLatLngAnimator != null && mapLibreFloatAnimator != null) {
            createNewLatLngAnimator(0, (LatLng) mapLibreLatLngAnimator.getAnimatedValue(), mapLibreLatLngAnimator.getTarget());
            createNewFloatAnimator(2, ((Float) mapLibreFloatAnimator.getAnimatedValue()).floatValue(), mapLibreFloatAnimator.getTarget().floatValue());
            playAnimators(mapLibreLatLngAnimator.getDuration() - mapLibreLatLngAnimator.getCurrentPlayTime(), 0, 2);
        }
        if (mapLibreFloatAnimator2 != null) {
            createNewFloatAnimator(3, getPreviousLayerCompassBearing(), mapLibreFloatAnimator2.getTarget().floatValue());
            playAnimators(this.compassAnimationEnabled ? 500L : 0L, 3);
        }
        if (mapLibreFloatAnimator3 != null) {
            feedNewAccuracyRadius(this.previousAccuracyRadius, false);
        }
    }

    void cancelZoomAnimation() {
        cancelAnimator(7);
    }

    void cancelPaddingAnimation() {
        cancelAnimator(10);
    }

    void cancelTiltAnimation() {
        cancelAnimator(8);
    }

    void cancelAndRemoveGpsBearingAnimation() {
        cancelAnimator(2);
        this.animatorArray.remove(2);
    }

    void stopPulsingCircleAnimation() {
        cancelAnimator(9);
    }

    void cancelAllAnimations() {
        for (int i = 0; i < this.animatorArray.size(); i++) {
            cancelAnimator(this.animatorArray.keyAt(i));
        }
    }

    private void cancelAnimator(int i) {
        MapLibreAnimator mapLibreAnimator = this.animatorArray.get(i);
        if (mapLibreAnimator != null) {
            mapLibreAnimator.cancel();
            mapLibreAnimator.removeAllUpdateListeners();
            mapLibreAnimator.removeAllListeners();
        }
    }

    void setTrackingAnimationDurationMultiplier(float f) {
        this.durationMultiplier = f;
    }

    void setCompassAnimationEnabled(boolean z) {
        this.compassAnimationEnabled = z;
    }

    void setAccuracyAnimationEnabled(boolean z) {
        this.accuracyAnimationEnabled = z;
    }

    void setMaxAnimationFps(int i) {
        if (i <= 0) {
            Logger.e(TAG, "Max animation FPS cannot be less or equal to 0.");
        } else {
            this.maxAnimationFps = i;
        }
    }
}
