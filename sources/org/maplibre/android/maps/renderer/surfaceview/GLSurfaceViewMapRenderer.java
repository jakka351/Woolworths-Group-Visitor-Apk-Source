package org.maplibre.android.maps.renderer.surfaceview;

import android.content.Context;
import org.maplibre.android.maps.renderer.MapRenderer;
import org.maplibre.android.maps.renderer.egl.EGLConfigChooser;
import org.maplibre.android.maps.renderer.egl.EGLContextFactory;
import org.maplibre.android.maps.renderer.egl.EGLWindowSurfaceFactory;

/* loaded from: classes2.dex */
public class GLSurfaceViewMapRenderer extends SurfaceViewMapRenderer {
    public GLSurfaceViewMapRenderer(Context context, MapLibreGLSurfaceView mapLibreGLSurfaceView, String str) {
        super(context, mapLibreGLSurfaceView, str);
        mapLibreGLSurfaceView.setEGLContextFactory(new EGLContextFactory());
        mapLibreGLSurfaceView.setEGLWindowSurfaceFactory(new EGLWindowSurfaceFactory());
        mapLibreGLSurfaceView.setEGLConfigChooser(new EGLConfigChooser());
        mapLibreGLSurfaceView.setRenderer(this);
        mapLibreGLSurfaceView.setRenderingRefreshMode(MapRenderer.RenderingRefreshMode.WHEN_DIRTY);
        mapLibreGLSurfaceView.setPreserveEGLContextOnPause(true);
    }
}
