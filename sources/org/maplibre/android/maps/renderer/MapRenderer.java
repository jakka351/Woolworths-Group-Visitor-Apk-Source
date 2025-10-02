package org.maplibre.android.maps.renderer;

import android.content.Context;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import org.maplibre.android.LibraryLoader;
import org.maplibre.android.log.Logger;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.MapLibreMapOptions;

/* loaded from: classes2.dex */
public abstract class MapRenderer implements MapRendererScheduler {
    private static final String TAG = "Mbgl-MapRenderer";
    private MapLibreMap.OnFpsChangedListener onFpsChangedListener;
    private long timeElapsed;
    private long nativePtr = 0;
    private double expectedRenderTime = 0.0d;

    public enum RenderingRefreshMode {
        WHEN_DIRTY,
        CONTINUOUS
    }

    private native void nativeInitialize(MapRenderer mapRenderer, float f, String str);

    private native void nativeOnSurfaceChanged(int i, int i2);

    private native void nativeOnSurfaceCreated(Surface surface);

    private native void nativeOnSurfaceDestroyed();

    private native void nativeRender();

    private native void nativeSetSwapBehaviorFlush(boolean z);

    protected native void finalize() throws Throwable;

    public abstract RenderingRefreshMode getRenderingRefreshMode();

    public abstract View getView();

    /* JADX INFO: Access modifiers changed from: protected */
    public native void nativeReset();

    public void onDestroy() {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public abstract void setRenderingRefreshMode(RenderingRefreshMode renderingRefreshMode);

    static {
        LibraryLoader.load();
    }

    public static MapRenderer create(MapLibreMapOptions mapLibreMapOptions, Context context, Runnable runnable) {
        String localIdeographFontFamily = mapLibreMapOptions.getLocalIdeographFontFamily();
        if (mapLibreMapOptions.getTextureMode()) {
            return MapRendererFactory.newTextureViewMapRenderer(context, new TextureView(context), localIdeographFontFamily, mapLibreMapOptions.getTranslucentTextureSurface(), runnable);
        }
        return MapRendererFactory.newSurfaceViewMapRenderer(context, localIdeographFontFamily, mapLibreMapOptions.getRenderSurfaceOnTop(), runnable);
    }

    public MapRenderer(Context context, String str) {
        nativeInitialize(this, context.getResources().getDisplayMetrics().density, str);
    }

    public void setOnFpsChangedListener(MapLibreMap.OnFpsChangedListener onFpsChangedListener) {
        this.onFpsChangedListener = onFpsChangedListener;
    }

    protected void onSurfaceCreated(Surface surface) {
        nativeOnSurfaceCreated(surface);
    }

    protected void onSurfaceChanged(int i, int i2) {
        nativeOnSurfaceChanged(i, i2);
    }

    protected void onSurfaceDestroyed() {
        nativeOnSurfaceDestroyed();
    }

    protected void onDrawFrame() throws InterruptedException {
        long jNanoTime = System.nanoTime();
        try {
            nativeRender();
        } catch (Error e) {
            Logger.e(TAG, e.getMessage());
        }
        double dNanoTime = System.nanoTime() - jNanoTime;
        double d = this.expectedRenderTime;
        if (dNanoTime < d) {
            try {
                Thread.sleep((long) ((d - dNanoTime) / 1000000.0d));
            } catch (InterruptedException e2) {
                Logger.e(TAG, e2.getMessage());
            }
        }
        if (this.onFpsChangedListener != null) {
            updateFps();
        }
    }

    public void setSwapBehaviorFlush(boolean z) {
        nativeSetSwapBehaviorFlush(z);
    }

    void queueEvent(MapRendererRunnable mapRendererRunnable) {
        queueEvent((Runnable) mapRendererRunnable);
    }

    private void updateFps() {
        long jNanoTime = System.nanoTime();
        if (this.timeElapsed > 0) {
            this.onFpsChangedListener.onFpsChanged(1.0E9d / (jNanoTime - r2));
        }
        this.timeElapsed = jNanoTime;
    }

    public void setMaximumFps(int i) {
        if (i <= 0) {
            return;
        }
        this.expectedRenderTime = 1.0E9d / i;
    }
}
