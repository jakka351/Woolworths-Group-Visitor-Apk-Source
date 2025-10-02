package org.maplibre.android.maps.renderer;

import android.content.Context;
import android.view.Surface;
import android.view.TextureView;
import org.maplibre.android.maps.renderer.surfaceview.GLSurfaceViewMapRenderer;
import org.maplibre.android.maps.renderer.surfaceview.MapLibreGLSurfaceView;
import org.maplibre.android.maps.renderer.surfaceview.SurfaceViewMapRenderer;
import org.maplibre.android.maps.renderer.textureview.GLTextureViewRenderThread;
import org.maplibre.android.maps.renderer.textureview.TextureViewMapRenderer;

/* loaded from: classes2.dex */
public class MapRendererFactory {
    public static TextureViewMapRenderer newTextureViewMapRenderer(Context context, TextureView textureView, String str, boolean z, final Runnable runnable) {
        TextureViewMapRenderer textureViewMapRenderer = new TextureViewMapRenderer(context, textureView, str, z) { // from class: org.maplibre.android.maps.renderer.MapRendererFactory.1
            @Override // org.maplibre.android.maps.renderer.textureview.TextureViewMapRenderer, org.maplibre.android.maps.renderer.MapRenderer
            protected void onSurfaceCreated(Surface surface) {
                runnable.run();
                super.onSurfaceCreated(surface);
            }
        };
        textureViewMapRenderer.setRenderThread(new GLTextureViewRenderThread(textureView, textureViewMapRenderer));
        return textureViewMapRenderer;
    }

    public static SurfaceViewMapRenderer newSurfaceViewMapRenderer(Context context, String str, boolean z, final Runnable runnable) {
        MapLibreGLSurfaceView mapLibreGLSurfaceView = new MapLibreGLSurfaceView(context);
        mapLibreGLSurfaceView.setZOrderMediaOverlay(z);
        return new GLSurfaceViewMapRenderer(context, mapLibreGLSurfaceView, str) { // from class: org.maplibre.android.maps.renderer.MapRendererFactory.2
            @Override // org.maplibre.android.maps.renderer.surfaceview.SurfaceViewMapRenderer, org.maplibre.android.maps.renderer.MapRenderer
            public void onSurfaceCreated(Surface surface) {
                runnable.run();
                super.onSurfaceCreated(surface);
            }
        };
    }
}
