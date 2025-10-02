package org.maplibre.android.maps.renderer.surfaceview;

import android.content.Context;
import android.view.Surface;
import android.view.View;
import org.maplibre.android.maps.renderer.MapRenderer;
import org.maplibre.android.maps.renderer.surfaceview.MapLibreSurfaceView;

/* loaded from: classes2.dex */
public class SurfaceViewMapRenderer extends MapRenderer {
    protected final MapLibreSurfaceView surfaceView;

    public SurfaceViewMapRenderer(Context context, MapLibreSurfaceView mapLibreSurfaceView, String str) {
        super(context, str);
        this.surfaceView = mapLibreSurfaceView;
        mapLibreSurfaceView.setDetachedListener(new MapLibreSurfaceView.OnSurfaceViewDetachedListener() { // from class: org.maplibre.android.maps.renderer.surfaceview.SurfaceViewMapRenderer.1
            @Override // org.maplibre.android.maps.renderer.surfaceview.MapLibreSurfaceView.OnSurfaceViewDetachedListener
            public void onSurfaceViewDetached() {
                SurfaceViewMapRenderer.this.nativeReset();
            }
        });
    }

    @Override // org.maplibre.android.maps.renderer.MapRenderer
    public View getView() {
        return this.surfaceView;
    }

    @Override // org.maplibre.android.maps.renderer.MapRenderer
    public void onStop() {
        this.surfaceView.onPause();
    }

    @Override // org.maplibre.android.maps.renderer.MapRenderer
    public void onPause() {
        super.onPause();
    }

    @Override // org.maplibre.android.maps.renderer.MapRenderer
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // org.maplibre.android.maps.renderer.MapRenderer
    public void onStart() {
        this.surfaceView.onResume();
    }

    @Override // org.maplibre.android.maps.renderer.MapRenderer
    public void onResume() {
        super.onResume();
    }

    @Override // org.maplibre.android.maps.renderer.MapRenderer
    public void onSurfaceCreated(Surface surface) {
        super.onSurfaceCreated(surface);
    }

    @Override // org.maplibre.android.maps.renderer.MapRenderer
    public void onSurfaceDestroyed() {
        super.onSurfaceDestroyed();
    }

    @Override // org.maplibre.android.maps.renderer.MapRenderer
    public void onSurfaceChanged(int i, int i2) {
        super.onSurfaceChanged(i, i2);
    }

    @Override // org.maplibre.android.maps.renderer.MapRenderer
    public void onDrawFrame() throws InterruptedException {
        super.onDrawFrame();
    }

    @Override // org.maplibre.android.maps.renderer.MapRendererScheduler
    public void requestRender() {
        this.surfaceView.requestRender();
    }

    @Override // org.maplibre.android.maps.renderer.MapRendererScheduler
    public void queueEvent(Runnable runnable) {
        this.surfaceView.queueEvent(runnable);
    }

    @Override // org.maplibre.android.maps.renderer.MapRendererScheduler
    public void waitForEmpty() {
        this.surfaceView.waitForEmpty();
    }

    @Override // org.maplibre.android.maps.renderer.MapRenderer
    public void setRenderingRefreshMode(MapRenderer.RenderingRefreshMode renderingRefreshMode) {
        this.surfaceView.setRenderingRefreshMode(renderingRefreshMode);
    }

    @Override // org.maplibre.android.maps.renderer.MapRenderer
    public MapRenderer.RenderingRefreshMode getRenderingRefreshMode() {
        return this.surfaceView.getRenderingRefreshMode();
    }
}
