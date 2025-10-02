package com.maplibre.rctmln.components.camera;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.maplibre.android.camera.CameraUpdate;
import org.maplibre.android.maps.MapLibreMap;

/* loaded from: classes3.dex */
public class CameraUpdateItem implements RunnableFuture<Void> {
    private boolean isCameraActionCancelled;
    private boolean isCameraActionFinished;
    private MapLibreMap.CancelableCallback mCallback;
    private int mCameraMode;
    private CameraUpdate mCameraUpdate;
    private int mDuration;
    private WeakReference<MapLibreMap> mMap;

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return false;
    }

    @Override // java.util.concurrent.Future
    public Void get() throws ExecutionException, InterruptedException {
        return null;
    }

    @Override // java.util.concurrent.Future
    public Void get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return null;
    }

    public CameraUpdateItem(MapLibreMap mapLibreMap, CameraUpdate cameraUpdate, int i, MapLibreMap.CancelableCallback cancelableCallback, int i2) {
        this.mCameraUpdate = cameraUpdate;
        this.mDuration = i;
        this.mCallback = cancelableCallback;
        this.mCameraMode = i2;
        this.mMap = new WeakReference<>(mapLibreMap);
    }

    public int getDuration() {
        return this.mDuration;
    }

    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    public void run() {
        int i;
        MapLibreMap.CancelableCallback cancelableCallback = new MapLibreMap.CancelableCallback() { // from class: com.maplibre.rctmln.components.camera.CameraUpdateItem.1
            @Override // org.maplibre.android.maps.MapLibreMap.CancelableCallback
            public void onCancel() {
                CameraUpdateItem.this.handleCallbackResponse(true);
            }

            @Override // org.maplibre.android.maps.MapLibreMap.CancelableCallback
            public void onFinish() {
                CameraUpdateItem.this.handleCallbackResponse(false);
            }
        };
        MapLibreMap mapLibreMap = this.mMap.get();
        if (mapLibreMap == null) {
            this.isCameraActionCancelled = true;
            return;
        }
        int i2 = this.mDuration;
        if (i2 == 0 || (i = this.mCameraMode) == 4) {
            mapLibreMap.moveCamera(this.mCameraUpdate, cancelableCallback);
            return;
        }
        if (i2 < 0) {
            i2 = 300;
        }
        if (i == 1) {
            mapLibreMap.animateCamera(this.mCameraUpdate, i2, cancelableCallback);
        } else if (i == 3) {
            mapLibreMap.easeCamera(this.mCameraUpdate, i2, false, cancelableCallback);
        } else if (i == 2) {
            mapLibreMap.easeCamera(this.mCameraUpdate, i2, true, cancelableCallback);
        }
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.isCameraActionCancelled;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.isCameraActionFinished;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCallbackResponse(boolean z) {
        MapLibreMap.CancelableCallback cancelableCallback = this.mCallback;
        if (cancelableCallback == null) {
            return;
        }
        this.isCameraActionCancelled = z;
        this.isCameraActionFinished = !z;
        if (z) {
            cancelableCallback.onCancel();
        } else {
            cancelableCallback.onFinish();
        }
    }
}
