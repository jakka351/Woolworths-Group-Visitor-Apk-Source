package org.maplibre.android.maps.renderer.textureview;

import android.content.Context;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import org.maplibre.android.maps.renderer.MapRenderer;

/* loaded from: classes2.dex */
public class TextureViewMapRenderer extends MapRenderer {
    private TextureViewRenderThread renderThread;
    private TextureView textureView;
    private boolean translucentSurface;

    public TextureViewMapRenderer(Context context, TextureView textureView, String str, boolean z) {
        super(context, str);
        this.textureView = textureView;
        this.translucentSurface = z;
    }

    public void setRenderThread(TextureViewRenderThread textureViewRenderThread) {
        this.renderThread = textureViewRenderThread;
        textureViewRenderThread.setName("TextureViewRenderer");
        this.renderThread.start();
    }

    @Override // org.maplibre.android.maps.renderer.MapRenderer
    public View getView() {
        return this.textureView;
    }

    @Override // org.maplibre.android.maps.renderer.MapRenderer
    protected void onSurfaceCreated(Surface surface) {
        super.onSurfaceCreated(surface);
    }

    @Override // org.maplibre.android.maps.renderer.MapRenderer
    protected void onSurfaceChanged(int i, int i2) {
        super.onSurfaceChanged(i, i2);
    }

    @Override // org.maplibre.android.maps.renderer.MapRenderer
    protected void onSurfaceDestroyed() {
        super.onSurfaceDestroyed();
    }

    @Override // org.maplibre.android.maps.renderer.MapRenderer
    protected void onDrawFrame() throws InterruptedException {
        super.onDrawFrame();
    }

    @Override // org.maplibre.android.maps.renderer.MapRendererScheduler
    public void requestRender() {
        this.renderThread.requestRender();
    }

    @Override // org.maplibre.android.maps.renderer.MapRendererScheduler
    public void queueEvent(Runnable runnable) {
        this.renderThread.queueEvent(runnable);
    }

    @Override // org.maplibre.android.maps.renderer.MapRendererScheduler
    public void waitForEmpty() {
        this.renderThread.waitForEmpty();
    }

    @Override // org.maplibre.android.maps.renderer.MapRenderer
    public void onStop() {
        this.renderThread.onPause();
    }

    @Override // org.maplibre.android.maps.renderer.MapRenderer
    public void onStart() {
        this.renderThread.onResume();
    }

    @Override // org.maplibre.android.maps.renderer.MapRenderer
    public void onDestroy() {
        this.renderThread.onDestroy();
    }

    public boolean isTranslucentSurface() {
        return this.translucentSurface;
    }

    @Override // org.maplibre.android.maps.renderer.MapRenderer
    public void setRenderingRefreshMode(MapRenderer.RenderingRefreshMode renderingRefreshMode) {
        throw new RuntimeException("setRenderingRefreshMode is not supported for TextureViewMapRenderer. Use SurfaceViewMapRenderer to set the rendering refresh mode.");
    }

    @Override // org.maplibre.android.maps.renderer.MapRenderer
    public MapRenderer.RenderingRefreshMode getRenderingRefreshMode() {
        throw new RuntimeException("getRenderingRefreshMode is not supported for TextureViewMapRenderer. Use SurfaceViewMapRenderer to set the rendering refresh mode.");
    }
}
