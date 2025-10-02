package org.maplibre.android.maps;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.maplibre.android.log.Logger;
import org.maplibre.android.maps.MapView;
import org.maplibre.android.maps.NativeMapView;
import org.maplibre.android.tile.TileOperation;

/* loaded from: classes2.dex */
class MapChangeReceiver implements NativeMapView.StateCallback {
    private static final String TAG = "Mbgl-MapChangeReceiver";
    private final List<MapView.OnCameraWillChangeListener> onCameraWillChangeListenerList = new CopyOnWriteArrayList();
    private final List<MapView.OnCameraIsChangingListener> onCameraIsChangingListenerList = new CopyOnWriteArrayList();
    private final List<MapView.OnCameraDidChangeListener> onCameraDidChangeListenerList = new CopyOnWriteArrayList();
    private final List<MapView.OnWillStartLoadingMapListener> onWillStartLoadingMapListenerList = new CopyOnWriteArrayList();
    private final List<MapView.OnDidFinishLoadingMapListener> onDidFinishLoadingMapListenerList = new CopyOnWriteArrayList();
    private final List<MapView.OnDidFailLoadingMapListener> onDidFailLoadingMapListenerList = new CopyOnWriteArrayList();
    private final List<MapView.OnWillStartRenderingFrameListener> onWillStartRenderingFrameList = new CopyOnWriteArrayList();
    private final List<MapView.OnDidFinishRenderingFrameListener> onDidFinishRenderingFrameList = new CopyOnWriteArrayList();
    private final List<MapView.OnWillStartRenderingMapListener> onWillStartRenderingMapListenerList = new CopyOnWriteArrayList();
    private final List<MapView.OnDidFinishRenderingMapListener> onDidFinishRenderingMapListenerList = new CopyOnWriteArrayList();
    private final List<MapView.OnDidBecomeIdleListener> onDidBecomeIdleListenerList = new CopyOnWriteArrayList();
    private final List<MapView.OnDidFinishLoadingStyleListener> onDidFinishLoadingStyleListenerList = new CopyOnWriteArrayList();
    private final List<MapView.OnSourceChangedListener> onSourceChangedListenerList = new CopyOnWriteArrayList();
    private final List<MapView.OnStyleImageMissingListener> onStyleImageMissingListenerList = new CopyOnWriteArrayList();
    private final List<MapView.OnCanRemoveUnusedStyleImageListener> onCanRemoveUnusedStyleImageListenerList = new CopyOnWriteArrayList();
    private final List<MapView.OnPreCompileShaderListener> onPreCompileShaderList = new CopyOnWriteArrayList();
    private final List<MapView.OnPostCompileShaderListener> onPostCompileShaderList = new CopyOnWriteArrayList();
    private final List<MapView.OnShaderCompileFailedListener> onShaderCompileFailedList = new CopyOnWriteArrayList();
    private final List<MapView.OnGlyphsLoadedListener> onGlyphsLoadedList = new CopyOnWriteArrayList();
    private final List<MapView.OnGlyphsErrorListener> onGlyphsErrorList = new CopyOnWriteArrayList();
    private final List<MapView.OnGlyphsRequestedListener> onGlyphsRequestedList = new CopyOnWriteArrayList();
    private final List<MapView.OnTileActionListener> onTileActionList = new CopyOnWriteArrayList();
    private final List<MapView.OnSpriteLoadedListener> onSpriteLoadedList = new CopyOnWriteArrayList();
    private final List<MapView.OnSpriteErrorListener> onSpriteErrorList = new CopyOnWriteArrayList();
    private final List<MapView.OnSpriteRequestedListener> onSpriteRequestedList = new CopyOnWriteArrayList();

    MapChangeReceiver() {
    }

    @Override // org.maplibre.android.maps.NativeMapView.StateCallback
    public void onCameraWillChange(boolean z) {
        try {
            if (this.onCameraWillChangeListenerList.isEmpty()) {
                return;
            }
            Iterator<MapView.OnCameraWillChangeListener> it = this.onCameraWillChangeListenerList.iterator();
            while (it.hasNext()) {
                it.next().onCameraWillChange(z);
            }
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onCameraWillChange", th);
            throw th;
        }
    }

    @Override // org.maplibre.android.maps.NativeMapView.StateCallback
    public void onCameraIsChanging() {
        try {
            if (this.onCameraIsChangingListenerList.isEmpty()) {
                return;
            }
            Iterator<MapView.OnCameraIsChangingListener> it = this.onCameraIsChangingListenerList.iterator();
            while (it.hasNext()) {
                it.next().onCameraIsChanging();
            }
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onCameraIsChanging", th);
            throw th;
        }
    }

    @Override // org.maplibre.android.maps.NativeMapView.StateCallback
    public void onCameraDidChange(boolean z) {
        try {
            if (this.onCameraDidChangeListenerList.isEmpty()) {
                return;
            }
            Iterator<MapView.OnCameraDidChangeListener> it = this.onCameraDidChangeListenerList.iterator();
            while (it.hasNext()) {
                it.next().onCameraDidChange(z);
            }
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onCameraDidChange", th);
            throw th;
        }
    }

    @Override // org.maplibre.android.maps.NativeMapView.StyleCallback
    public void onWillStartLoadingMap() {
        try {
            if (this.onWillStartLoadingMapListenerList.isEmpty()) {
                return;
            }
            Iterator<MapView.OnWillStartLoadingMapListener> it = this.onWillStartLoadingMapListenerList.iterator();
            while (it.hasNext()) {
                it.next().onWillStartLoadingMap();
            }
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onWillStartLoadingMap", th);
            throw th;
        }
    }

    @Override // org.maplibre.android.maps.NativeMapView.StateCallback
    public void onDidFinishLoadingMap() {
        try {
            if (this.onDidFinishLoadingMapListenerList.isEmpty()) {
                return;
            }
            Iterator<MapView.OnDidFinishLoadingMapListener> it = this.onDidFinishLoadingMapListenerList.iterator();
            while (it.hasNext()) {
                it.next().onDidFinishLoadingMap();
            }
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onDidFinishLoadingMap", th);
            throw th;
        }
    }

    @Override // org.maplibre.android.maps.NativeMapView.StateCallback
    public void onDidFailLoadingMap(String str) {
        try {
            if (this.onDidFailLoadingMapListenerList.isEmpty()) {
                return;
            }
            Iterator<MapView.OnDidFailLoadingMapListener> it = this.onDidFailLoadingMapListenerList.iterator();
            while (it.hasNext()) {
                it.next().onDidFailLoadingMap(str);
            }
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onDidFailLoadingMap", th);
            throw th;
        }
    }

    @Override // org.maplibre.android.maps.NativeMapView.StateCallback
    public void onWillStartRenderingFrame() {
        try {
            if (this.onWillStartRenderingFrameList.isEmpty()) {
                return;
            }
            Iterator<MapView.OnWillStartRenderingFrameListener> it = this.onWillStartRenderingFrameList.iterator();
            while (it.hasNext()) {
                it.next().onWillStartRenderingFrame();
            }
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onWillStartRenderingFrame", th);
            throw th;
        }
    }

    @Override // org.maplibre.android.maps.NativeMapView.StateCallback
    public void onDidFinishRenderingFrame(boolean z, double d, double d2) {
        try {
            if (this.onDidFinishRenderingFrameList.isEmpty()) {
                return;
            }
            Iterator<MapView.OnDidFinishRenderingFrameListener> it = this.onDidFinishRenderingFrameList.iterator();
            while (it.hasNext()) {
                it.next().onDidFinishRenderingFrame(z, d, d2);
            }
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onDidFinishRenderingFrame", th);
            throw th;
        }
    }

    @Override // org.maplibre.android.maps.NativeMapView.StateCallback
    public void onWillStartRenderingMap() {
        try {
            if (this.onWillStartRenderingMapListenerList.isEmpty()) {
                return;
            }
            Iterator<MapView.OnWillStartRenderingMapListener> it = this.onWillStartRenderingMapListenerList.iterator();
            while (it.hasNext()) {
                it.next().onWillStartRenderingMap();
            }
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onWillStartRenderingMap", th);
            throw th;
        }
    }

    @Override // org.maplibre.android.maps.NativeMapView.StateCallback
    public void onDidFinishRenderingMap(boolean z) {
        try {
            if (this.onDidFinishRenderingMapListenerList.isEmpty()) {
                return;
            }
            Iterator<MapView.OnDidFinishRenderingMapListener> it = this.onDidFinishRenderingMapListenerList.iterator();
            while (it.hasNext()) {
                it.next().onDidFinishRenderingMap(z);
            }
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onDidFinishRenderingMap", th);
            throw th;
        }
    }

    @Override // org.maplibre.android.maps.NativeMapView.StateCallback
    public void onDidBecomeIdle() {
        try {
            if (this.onDidBecomeIdleListenerList.isEmpty()) {
                return;
            }
            Iterator<MapView.OnDidBecomeIdleListener> it = this.onDidBecomeIdleListenerList.iterator();
            while (it.hasNext()) {
                it.next().onDidBecomeIdle();
            }
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onDidBecomeIdle", th);
            throw th;
        }
    }

    @Override // org.maplibre.android.maps.NativeMapView.StyleCallback
    public void onDidFinishLoadingStyle() {
        try {
            if (this.onDidFinishLoadingStyleListenerList.isEmpty()) {
                return;
            }
            Iterator<MapView.OnDidFinishLoadingStyleListener> it = this.onDidFinishLoadingStyleListenerList.iterator();
            while (it.hasNext()) {
                it.next().onDidFinishLoadingStyle();
            }
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onDidFinishLoadingStyle", th);
            throw th;
        }
    }

    @Override // org.maplibre.android.maps.NativeMapView.StateCallback
    public void onSourceChanged(String str) {
        try {
            if (this.onSourceChangedListenerList.isEmpty()) {
                return;
            }
            Iterator<MapView.OnSourceChangedListener> it = this.onSourceChangedListenerList.iterator();
            while (it.hasNext()) {
                it.next().onSourceChangedListener(str);
            }
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onSourceChanged", th);
            throw th;
        }
    }

    @Override // org.maplibre.android.maps.NativeMapView.StateCallback
    public void onStyleImageMissing(String str) {
        try {
            if (this.onStyleImageMissingListenerList.isEmpty()) {
                return;
            }
            Iterator<MapView.OnStyleImageMissingListener> it = this.onStyleImageMissingListenerList.iterator();
            while (it.hasNext()) {
                it.next().onStyleImageMissing(str);
            }
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onStyleImageMissing", th);
            throw th;
        }
    }

    @Override // org.maplibre.android.maps.NativeMapView.StateCallback
    public boolean onCanRemoveUnusedStyleImage(String str) {
        boolean zOnCanRemoveUnusedStyleImage = true;
        if (this.onCanRemoveUnusedStyleImageListenerList.isEmpty()) {
            return true;
        }
        try {
            if (!this.onCanRemoveUnusedStyleImageListenerList.isEmpty()) {
                Iterator<MapView.OnCanRemoveUnusedStyleImageListener> it = this.onCanRemoveUnusedStyleImageListenerList.iterator();
                while (it.hasNext()) {
                    zOnCanRemoveUnusedStyleImage &= it.next().onCanRemoveUnusedStyleImage(str);
                }
            }
            return zOnCanRemoveUnusedStyleImage;
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onCanRemoveUnusedStyleImage", th);
            throw th;
        }
    }

    @Override // org.maplibre.android.maps.NativeMapView.StateCallback
    public void onPreCompileShader(int i, int i2, String str) {
        try {
            if (this.onPreCompileShaderList.isEmpty()) {
                return;
            }
            Iterator<MapView.OnPreCompileShaderListener> it = this.onPreCompileShaderList.iterator();
            while (it.hasNext()) {
                it.next().onPreCompileShader(i, i2, str);
            }
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onPreCompileShader", th);
            throw th;
        }
    }

    @Override // org.maplibre.android.maps.NativeMapView.StateCallback
    public void onPostCompileShader(int i, int i2, String str) {
        try {
            if (this.onPostCompileShaderList.isEmpty()) {
                return;
            }
            Iterator<MapView.OnPostCompileShaderListener> it = this.onPostCompileShaderList.iterator();
            while (it.hasNext()) {
                it.next().onPostCompileShader(i, i2, str);
            }
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onPostCompileShader", th);
            throw th;
        }
    }

    @Override // org.maplibre.android.maps.NativeMapView.StateCallback
    public void onShaderCompileFailed(int i, int i2, String str) {
        try {
            if (this.onShaderCompileFailedList.isEmpty()) {
                return;
            }
            Iterator<MapView.OnShaderCompileFailedListener> it = this.onShaderCompileFailedList.iterator();
            while (it.hasNext()) {
                it.next().onShaderCompileFailed(i, i2, str);
            }
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onShaderCompileFailed", th);
            throw th;
        }
    }

    @Override // org.maplibre.android.maps.NativeMapView.StateCallback
    public void onGlyphsLoaded(String[] strArr, int i, int i2) {
        try {
            if (this.onGlyphsLoadedList.isEmpty()) {
                return;
            }
            Iterator<MapView.OnGlyphsLoadedListener> it = this.onGlyphsLoadedList.iterator();
            while (it.hasNext()) {
                it.next().onGlyphsLoaded(strArr, i, i2);
            }
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onGlyphsLoaded", th);
            throw th;
        }
    }

    @Override // org.maplibre.android.maps.NativeMapView.StateCallback
    public void onGlyphsError(String[] strArr, int i, int i2) {
        try {
            if (this.onGlyphsErrorList.isEmpty()) {
                return;
            }
            Iterator<MapView.OnGlyphsErrorListener> it = this.onGlyphsErrorList.iterator();
            while (it.hasNext()) {
                it.next().onGlyphsError(strArr, i, i2);
            }
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onGlyphsError", th);
            throw th;
        }
    }

    @Override // org.maplibre.android.maps.NativeMapView.StateCallback
    public void onGlyphsRequested(String[] strArr, int i, int i2) {
        try {
            if (this.onGlyphsRequestedList.isEmpty()) {
                return;
            }
            Iterator<MapView.OnGlyphsRequestedListener> it = this.onGlyphsRequestedList.iterator();
            while (it.hasNext()) {
                it.next().onGlyphsRequested(strArr, i, i2);
            }
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onGlyphsRequested", th);
            throw th;
        }
    }

    @Override // org.maplibre.android.maps.NativeMapView.StateCallback
    public void onTileAction(TileOperation tileOperation, int i, int i2, int i3, int i4, int i5, String str) {
        try {
            if (this.onTileActionList.isEmpty()) {
                return;
            }
            Iterator<MapView.OnTileActionListener> it = this.onTileActionList.iterator();
            while (it.hasNext()) {
                it.next().onTileAction(tileOperation, i, i2, i3, i4, i5, str);
            }
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onTileAction", th);
            throw th;
        }
    }

    @Override // org.maplibre.android.maps.NativeMapView.StateCallback
    public void onSpriteLoaded(String str, String str2) {
        try {
            if (this.onSpriteLoadedList.isEmpty()) {
                return;
            }
            Iterator<MapView.OnSpriteLoadedListener> it = this.onSpriteLoadedList.iterator();
            while (it.hasNext()) {
                it.next().onSpriteLoaded(str, str2);
            }
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onSpriteLoaded", th);
            throw th;
        }
    }

    @Override // org.maplibre.android.maps.NativeMapView.StateCallback
    public void onSpriteError(String str, String str2) {
        try {
            if (this.onSpriteErrorList.isEmpty()) {
                return;
            }
            Iterator<MapView.OnSpriteErrorListener> it = this.onSpriteErrorList.iterator();
            while (it.hasNext()) {
                it.next().onSpriteError(str, str2);
            }
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onSpriteError", th);
            throw th;
        }
    }

    @Override // org.maplibre.android.maps.NativeMapView.StateCallback
    public void onSpriteRequested(String str, String str2) {
        try {
            if (this.onSpriteRequestedList.isEmpty()) {
                return;
            }
            Iterator<MapView.OnSpriteRequestedListener> it = this.onSpriteRequestedList.iterator();
            while (it.hasNext()) {
                it.next().onSpriteRequested(str, str2);
            }
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onSpriteRequested", th);
            throw th;
        }
    }

    void addOnCameraWillChangeListener(MapView.OnCameraWillChangeListener onCameraWillChangeListener) {
        this.onCameraWillChangeListenerList.add(onCameraWillChangeListener);
    }

    void removeOnCameraWillChangeListener(MapView.OnCameraWillChangeListener onCameraWillChangeListener) {
        this.onCameraWillChangeListenerList.remove(onCameraWillChangeListener);
    }

    void addOnCameraIsChangingListener(MapView.OnCameraIsChangingListener onCameraIsChangingListener) {
        this.onCameraIsChangingListenerList.add(onCameraIsChangingListener);
    }

    void removeOnCameraIsChangingListener(MapView.OnCameraIsChangingListener onCameraIsChangingListener) {
        this.onCameraIsChangingListenerList.remove(onCameraIsChangingListener);
    }

    void addOnCameraDidChangeListener(MapView.OnCameraDidChangeListener onCameraDidChangeListener) {
        this.onCameraDidChangeListenerList.add(onCameraDidChangeListener);
    }

    void removeOnCameraDidChangeListener(MapView.OnCameraDidChangeListener onCameraDidChangeListener) {
        this.onCameraDidChangeListenerList.remove(onCameraDidChangeListener);
    }

    void addOnWillStartLoadingMapListener(MapView.OnWillStartLoadingMapListener onWillStartLoadingMapListener) {
        this.onWillStartLoadingMapListenerList.add(onWillStartLoadingMapListener);
    }

    void removeOnWillStartLoadingMapListener(MapView.OnWillStartLoadingMapListener onWillStartLoadingMapListener) {
        this.onWillStartLoadingMapListenerList.remove(onWillStartLoadingMapListener);
    }

    void addOnDidFinishLoadingMapListener(MapView.OnDidFinishLoadingMapListener onDidFinishLoadingMapListener) {
        this.onDidFinishLoadingMapListenerList.add(onDidFinishLoadingMapListener);
    }

    void removeOnDidFinishLoadingMapListener(MapView.OnDidFinishLoadingMapListener onDidFinishLoadingMapListener) {
        this.onDidFinishLoadingMapListenerList.remove(onDidFinishLoadingMapListener);
    }

    void addOnDidFailLoadingMapListener(MapView.OnDidFailLoadingMapListener onDidFailLoadingMapListener) {
        this.onDidFailLoadingMapListenerList.add(onDidFailLoadingMapListener);
    }

    void removeOnDidFailLoadingMapListener(MapView.OnDidFailLoadingMapListener onDidFailLoadingMapListener) {
        this.onDidFailLoadingMapListenerList.remove(onDidFailLoadingMapListener);
    }

    void addOnWillStartRenderingFrameListener(MapView.OnWillStartRenderingFrameListener onWillStartRenderingFrameListener) {
        this.onWillStartRenderingFrameList.add(onWillStartRenderingFrameListener);
    }

    void removeOnWillStartRenderingFrameListener(MapView.OnWillStartRenderingFrameListener onWillStartRenderingFrameListener) {
        this.onWillStartRenderingFrameList.remove(onWillStartRenderingFrameListener);
    }

    void addOnDidFinishRenderingFrameListener(MapView.OnDidFinishRenderingFrameListener onDidFinishRenderingFrameListener) {
        this.onDidFinishRenderingFrameList.add(onDidFinishRenderingFrameListener);
    }

    void removeOnDidFinishRenderingFrameListener(MapView.OnDidFinishRenderingFrameListener onDidFinishRenderingFrameListener) {
        this.onDidFinishRenderingFrameList.remove(onDidFinishRenderingFrameListener);
    }

    void addOnWillStartRenderingMapListener(MapView.OnWillStartRenderingMapListener onWillStartRenderingMapListener) {
        this.onWillStartRenderingMapListenerList.add(onWillStartRenderingMapListener);
    }

    void removeOnWillStartRenderingMapListener(MapView.OnWillStartRenderingMapListener onWillStartRenderingMapListener) {
        this.onWillStartRenderingMapListenerList.remove(onWillStartRenderingMapListener);
    }

    void addOnDidFinishRenderingMapListener(MapView.OnDidFinishRenderingMapListener onDidFinishRenderingMapListener) {
        this.onDidFinishRenderingMapListenerList.add(onDidFinishRenderingMapListener);
    }

    void removeOnDidFinishRenderingMapListener(MapView.OnDidFinishRenderingMapListener onDidFinishRenderingMapListener) {
        this.onDidFinishRenderingMapListenerList.remove(onDidFinishRenderingMapListener);
    }

    void addOnDidBecomeIdleListener(MapView.OnDidBecomeIdleListener onDidBecomeIdleListener) {
        this.onDidBecomeIdleListenerList.add(onDidBecomeIdleListener);
    }

    void removeOnDidBecomeIdleListener(MapView.OnDidBecomeIdleListener onDidBecomeIdleListener) {
        this.onDidBecomeIdleListenerList.remove(onDidBecomeIdleListener);
    }

    void addOnDidFinishLoadingStyleListener(MapView.OnDidFinishLoadingStyleListener onDidFinishLoadingStyleListener) {
        this.onDidFinishLoadingStyleListenerList.add(onDidFinishLoadingStyleListener);
    }

    void removeOnDidFinishLoadingStyleListener(MapView.OnDidFinishLoadingStyleListener onDidFinishLoadingStyleListener) {
        this.onDidFinishLoadingStyleListenerList.remove(onDidFinishLoadingStyleListener);
    }

    void addOnSourceChangedListener(MapView.OnSourceChangedListener onSourceChangedListener) {
        this.onSourceChangedListenerList.add(onSourceChangedListener);
    }

    void removeOnSourceChangedListener(MapView.OnSourceChangedListener onSourceChangedListener) {
        this.onSourceChangedListenerList.remove(onSourceChangedListener);
    }

    void addOnStyleImageMissingListener(MapView.OnStyleImageMissingListener onStyleImageMissingListener) {
        this.onStyleImageMissingListenerList.add(onStyleImageMissingListener);
    }

    void removeOnStyleImageMissingListener(MapView.OnStyleImageMissingListener onStyleImageMissingListener) {
        this.onStyleImageMissingListenerList.remove(onStyleImageMissingListener);
    }

    void addOnCanRemoveUnusedStyleImageListener(MapView.OnCanRemoveUnusedStyleImageListener onCanRemoveUnusedStyleImageListener) {
        this.onCanRemoveUnusedStyleImageListenerList.add(onCanRemoveUnusedStyleImageListener);
    }

    void removeOnCanRemoveUnusedStyleImageListener(MapView.OnCanRemoveUnusedStyleImageListener onCanRemoveUnusedStyleImageListener) {
        this.onCanRemoveUnusedStyleImageListenerList.remove(onCanRemoveUnusedStyleImageListener);
    }

    public void addOnPreCompileShaderListener(MapView.OnPreCompileShaderListener onPreCompileShaderListener) {
        this.onPreCompileShaderList.add(onPreCompileShaderListener);
    }

    public void addOnPostCompileShaderListener(MapView.OnPostCompileShaderListener onPostCompileShaderListener) {
        this.onPostCompileShaderList.add(onPostCompileShaderListener);
    }

    public void addOnShaderCompileFailedListener(MapView.OnShaderCompileFailedListener onShaderCompileFailedListener) {
        this.onShaderCompileFailedList.add(onShaderCompileFailedListener);
    }

    public void addOnGlyphsLoadedListener(MapView.OnGlyphsLoadedListener onGlyphsLoadedListener) {
        this.onGlyphsLoadedList.add(onGlyphsLoadedListener);
    }

    public void addOnGlyphsErrorListener(MapView.OnGlyphsErrorListener onGlyphsErrorListener) {
        this.onGlyphsErrorList.add(onGlyphsErrorListener);
    }

    public void addOnGlyphsRequestedListener(MapView.OnGlyphsRequestedListener onGlyphsRequestedListener) {
        this.onGlyphsRequestedList.add(onGlyphsRequestedListener);
    }

    public void addOnTileActionListener(MapView.OnTileActionListener onTileActionListener) {
        this.onTileActionList.add(onTileActionListener);
    }

    public void addOnSpriteLoadedListener(MapView.OnSpriteLoadedListener onSpriteLoadedListener) {
        this.onSpriteLoadedList.add(onSpriteLoadedListener);
    }

    public void addOnSpriteErrorListener(MapView.OnSpriteErrorListener onSpriteErrorListener) {
        this.onSpriteErrorList.add(onSpriteErrorListener);
    }

    public void addOnSpriteRequestedListener(MapView.OnSpriteRequestedListener onSpriteRequestedListener) {
        this.onSpriteRequestedList.add(onSpriteRequestedListener);
    }

    public void removeOnPreCompileShaderListener(MapView.OnPreCompileShaderListener onPreCompileShaderListener) {
        this.onPreCompileShaderList.remove(onPreCompileShaderListener);
    }

    public void removeOnPostCompileShaderListener(MapView.OnPostCompileShaderListener onPostCompileShaderListener) {
        this.onPostCompileShaderList.remove(onPostCompileShaderListener);
    }

    public void removeOnShaderCompileFailedListener(MapView.OnShaderCompileFailedListener onShaderCompileFailedListener) {
        this.onShaderCompileFailedList.remove(onShaderCompileFailedListener);
    }

    public void removeOnGlyphsLoadedListener(MapView.OnGlyphsLoadedListener onGlyphsLoadedListener) {
        this.onGlyphsLoadedList.remove(onGlyphsLoadedListener);
    }

    public void removeOnGlyphsErrorListener(MapView.OnGlyphsErrorListener onGlyphsErrorListener) {
        this.onGlyphsErrorList.remove(onGlyphsErrorListener);
    }

    public void removeOnGlyphsRequestedListener(MapView.OnGlyphsRequestedListener onGlyphsRequestedListener) {
        this.onGlyphsRequestedList.remove(onGlyphsRequestedListener);
    }

    public void removeOnTileActionListener(MapView.OnTileActionListener onTileActionListener) {
        this.onTileActionList.remove(onTileActionListener);
    }

    public void removeOnSpriteLoadedListener(MapView.OnSpriteLoadedListener onSpriteLoadedListener) {
        this.onSpriteLoadedList.remove(onSpriteLoadedListener);
    }

    public void removeOnSpriteErrorListener(MapView.OnSpriteErrorListener onSpriteErrorListener) {
        this.onSpriteErrorList.remove(onSpriteErrorListener);
    }

    public void removeOnSpriteRequestedListener(MapView.OnSpriteRequestedListener onSpriteRequestedListener) {
        this.onSpriteRequestedList.remove(onSpriteRequestedListener);
    }

    void clear() {
        this.onCameraWillChangeListenerList.clear();
        this.onCameraIsChangingListenerList.clear();
        this.onCameraDidChangeListenerList.clear();
        this.onWillStartLoadingMapListenerList.clear();
        this.onDidFinishLoadingMapListenerList.clear();
        this.onDidFailLoadingMapListenerList.clear();
        this.onWillStartRenderingFrameList.clear();
        this.onDidFinishRenderingFrameList.clear();
        this.onWillStartRenderingMapListenerList.clear();
        this.onDidFinishRenderingMapListenerList.clear();
        this.onDidBecomeIdleListenerList.clear();
        this.onDidFinishLoadingStyleListenerList.clear();
        this.onSourceChangedListenerList.clear();
        this.onStyleImageMissingListenerList.clear();
        this.onCanRemoveUnusedStyleImageListenerList.clear();
        this.onPreCompileShaderList.clear();
        this.onPostCompileShaderList.clear();
        this.onShaderCompileFailedList.clear();
        this.onGlyphsLoadedList.clear();
        this.onGlyphsErrorList.clear();
        this.onGlyphsRequestedList.clear();
        this.onTileActionList.clear();
        this.onSpriteLoadedList.clear();
        this.onSpriteErrorList.clear();
        this.onSpriteRequestedList.clear();
    }
}
