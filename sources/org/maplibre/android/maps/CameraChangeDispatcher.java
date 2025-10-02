package org.maplibre.android.maps;

import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.maplibre.android.maps.MapLibreMap;

/* loaded from: classes2.dex */
class CameraChangeDispatcher implements MapLibreMap.OnCameraMoveStartedListener, MapLibreMap.OnCameraMoveListener, MapLibreMap.OnCameraMoveCanceledListener, MapLibreMap.OnCameraIdleListener {
    private static final int IDLE = 3;
    private static final int MOVE = 1;
    private static final int MOVE_CANCELED = 2;
    private static final int MOVE_STARTED = 0;
    private int moveStartedReason;
    private final CameraChangeHandler handler = new CameraChangeHandler(this);
    private boolean idle = true;
    private final CopyOnWriteArrayList<MapLibreMap.OnCameraMoveStartedListener> onCameraMoveStarted = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<MapLibreMap.OnCameraMoveCanceledListener> onCameraMoveCanceled = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<MapLibreMap.OnCameraMoveListener> onCameraMove = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<MapLibreMap.OnCameraIdleListener> onCameraIdle = new CopyOnWriteArrayList<>();

    CameraChangeDispatcher() {
    }

    @Override // org.maplibre.android.maps.MapLibreMap.OnCameraMoveStartedListener
    public void onCameraMoveStarted(int i) {
        this.moveStartedReason = i;
        this.handler.scheduleMessage(0);
    }

    @Override // org.maplibre.android.maps.MapLibreMap.OnCameraMoveListener
    public void onCameraMove() {
        this.handler.scheduleMessage(1);
    }

    @Override // org.maplibre.android.maps.MapLibreMap.OnCameraMoveCanceledListener
    public void onCameraMoveCanceled() {
        this.handler.scheduleMessage(2);
    }

    @Override // org.maplibre.android.maps.MapLibreMap.OnCameraIdleListener
    public void onCameraIdle() {
        this.handler.scheduleMessage(3);
    }

    void addOnCameraIdleListener(MapLibreMap.OnCameraIdleListener onCameraIdleListener) {
        this.onCameraIdle.add(onCameraIdleListener);
    }

    void removeOnCameraIdleListener(MapLibreMap.OnCameraIdleListener onCameraIdleListener) {
        if (this.onCameraIdle.contains(onCameraIdleListener)) {
            this.onCameraIdle.remove(onCameraIdleListener);
        }
    }

    void addOnCameraMoveCancelListener(MapLibreMap.OnCameraMoveCanceledListener onCameraMoveCanceledListener) {
        this.onCameraMoveCanceled.add(onCameraMoveCanceledListener);
    }

    void removeOnCameraMoveCancelListener(MapLibreMap.OnCameraMoveCanceledListener onCameraMoveCanceledListener) {
        if (this.onCameraMoveCanceled.contains(onCameraMoveCanceledListener)) {
            this.onCameraMoveCanceled.remove(onCameraMoveCanceledListener);
        }
    }

    void addOnCameraMoveStartedListener(MapLibreMap.OnCameraMoveStartedListener onCameraMoveStartedListener) {
        this.onCameraMoveStarted.add(onCameraMoveStartedListener);
    }

    void removeOnCameraMoveStartedListener(MapLibreMap.OnCameraMoveStartedListener onCameraMoveStartedListener) {
        if (this.onCameraMoveStarted.contains(onCameraMoveStartedListener)) {
            this.onCameraMoveStarted.remove(onCameraMoveStartedListener);
        }
    }

    void addOnCameraMoveListener(MapLibreMap.OnCameraMoveListener onCameraMoveListener) {
        this.onCameraMove.add(onCameraMoveListener);
    }

    void removeOnCameraMoveListener(MapLibreMap.OnCameraMoveListener onCameraMoveListener) {
        if (this.onCameraMove.contains(onCameraMoveListener)) {
            this.onCameraMove.remove(onCameraMoveListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeOnCameraMoveStarted() {
        if (this.idle) {
            this.idle = false;
            if (this.onCameraMoveStarted.isEmpty()) {
                return;
            }
            Iterator<MapLibreMap.OnCameraMoveStartedListener> it = this.onCameraMoveStarted.iterator();
            while (it.hasNext()) {
                it.next().onCameraMoveStarted(this.moveStartedReason);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeOnCameraMove() {
        if (this.onCameraMove.isEmpty() || this.idle) {
            return;
        }
        Iterator<MapLibreMap.OnCameraMoveListener> it = this.onCameraMove.iterator();
        while (it.hasNext()) {
            it.next().onCameraMove();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeOnCameraMoveCancelled() {
        if (this.onCameraMoveCanceled.isEmpty() || this.idle) {
            return;
        }
        Iterator<MapLibreMap.OnCameraMoveCanceledListener> it = this.onCameraMoveCanceled.iterator();
        while (it.hasNext()) {
            it.next().onCameraMoveCanceled();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeOnCameraIdle() {
        if (this.idle) {
            return;
        }
        this.idle = true;
        if (this.onCameraIdle.isEmpty()) {
            return;
        }
        Iterator<MapLibreMap.OnCameraIdleListener> it = this.onCameraIdle.iterator();
        while (it.hasNext()) {
            it.next().onCameraIdle();
        }
    }

    void onDestroy() {
        this.handler.removeCallbacksAndMessages(null);
        this.onCameraMoveStarted.clear();
        this.onCameraMoveCanceled.clear();
        this.onCameraMove.clear();
        this.onCameraIdle.clear();
    }

    private static class CameraChangeHandler extends Handler {
        private WeakReference<CameraChangeDispatcher> dispatcherWeakReference;

        CameraChangeHandler(CameraChangeDispatcher cameraChangeDispatcher) {
            this.dispatcherWeakReference = new WeakReference<>(cameraChangeDispatcher);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            CameraChangeDispatcher cameraChangeDispatcher = this.dispatcherWeakReference.get();
            if (cameraChangeDispatcher != null) {
                int i = message.what;
                if (i == 0) {
                    cameraChangeDispatcher.executeOnCameraMoveStarted();
                    return;
                }
                if (i == 1) {
                    cameraChangeDispatcher.executeOnCameraMove();
                } else if (i == 2) {
                    cameraChangeDispatcher.executeOnCameraMoveCancelled();
                } else {
                    if (i != 3) {
                        return;
                    }
                    cameraChangeDispatcher.executeOnCameraIdle();
                }
            }
        }

        void scheduleMessage(int i) {
            CameraChangeDispatcher cameraChangeDispatcher = this.dispatcherWeakReference.get();
            if (cameraChangeDispatcher != null) {
                if (i == 0) {
                    boolean z = !cameraChangeDispatcher.idle && (hasMessages(3) || hasMessages(2));
                    removeMessages(3);
                    removeMessages(2);
                    if (z) {
                        return;
                    }
                }
                Message message = new Message();
                message.what = i;
                sendMessage(message);
            }
        }
    }
}
