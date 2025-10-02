package org.maplibre.android.maps.renderer.textureview;

import android.graphics.SurfaceTexture;
import android.view.TextureView;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class TextureViewRenderThread extends Thread implements TextureView.SurfaceTextureListener {
    protected static final String TAG = "Mbgl-TextureViewRenderThread";
    protected boolean destroySurface;
    protected boolean exited;
    protected boolean hasNativeSurface;
    protected int height;
    protected final TextureViewMapRenderer mapRenderer;
    protected boolean paused;
    protected boolean requestRender;
    protected boolean shouldExit;
    protected boolean sizeChanged;
    protected SurfaceTexture surfaceTexture;
    protected int width;
    protected final Object lock = new Object();
    protected final ArrayList<Runnable> eventQueue = new ArrayList<>();

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    TextureViewRenderThread(TextureView textureView, TextureViewMapRenderer textureViewMapRenderer) {
        textureView.setOpaque(!textureViewMapRenderer.isTranslucentSurface());
        textureView.setSurfaceTextureListener(this);
        this.mapRenderer = textureViewMapRenderer;
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        synchronized (this.lock) {
            this.surfaceTexture = surfaceTexture;
            this.width = i;
            this.height = i2;
            this.requestRender = true;
            this.lock.notifyAll();
        }
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        synchronized (this.lock) {
            this.width = i;
            this.height = i2;
            this.sizeChanged = true;
            this.requestRender = true;
            this.lock.notifyAll();
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        synchronized (this.lock) {
            this.surfaceTexture = null;
            this.destroySurface = true;
            this.requestRender = false;
            this.lock.notifyAll();
        }
        return true;
    }

    void requestRender() {
        synchronized (this.lock) {
            this.requestRender = true;
            this.lock.notifyAll();
        }
    }

    void queueEvent(Runnable runnable) {
        if (runnable == null) {
            throw new IllegalArgumentException("runnable must not be null");
        }
        synchronized (this.lock) {
            this.eventQueue.add(runnable);
            this.lock.notifyAll();
        }
    }

    void waitForEmpty() {
        synchronized (this.lock) {
            while (!this.eventQueue.isEmpty()) {
                try {
                    this.lock.wait(0L);
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    void onPause() {
        synchronized (this.lock) {
            this.paused = true;
            this.lock.notifyAll();
        }
    }

    void onResume() {
        synchronized (this.lock) {
            this.paused = false;
            this.lock.notifyAll();
        }
    }

    void onDestroy() {
        synchronized (this.lock) {
            this.shouldExit = true;
            this.lock.notifyAll();
            while (!this.exited) {
                try {
                    this.lock.wait();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
