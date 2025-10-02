package org.maplibre.android.maps.renderer.textureview;

import android.graphics.SurfaceTexture;
import android.view.TextureView;
import java.lang.ref.WeakReference;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;
import org.maplibre.android.log.Logger;
import org.maplibre.android.maps.renderer.egl.EGLConfigChooser;

/* loaded from: classes2.dex */
public class GLTextureViewRenderThread extends TextureViewRenderThread {
    private boolean destroyContext;
    private final EGLHolder eglHolder;

    @Override // org.maplibre.android.maps.renderer.textureview.TextureViewRenderThread, android.view.TextureView.SurfaceTextureListener
    public /* bridge */ /* synthetic */ void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        super.onSurfaceTextureAvailable(surfaceTexture, i, i2);
    }

    @Override // org.maplibre.android.maps.renderer.textureview.TextureViewRenderThread, android.view.TextureView.SurfaceTextureListener
    public /* bridge */ /* synthetic */ boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return super.onSurfaceTextureDestroyed(surfaceTexture);
    }

    @Override // org.maplibre.android.maps.renderer.textureview.TextureViewRenderThread, android.view.TextureView.SurfaceTextureListener
    public /* bridge */ /* synthetic */ void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        super.onSurfaceTextureSizeChanged(surfaceTexture, i, i2);
    }

    @Override // org.maplibre.android.maps.renderer.textureview.TextureViewRenderThread, android.view.TextureView.SurfaceTextureListener
    public /* bridge */ /* synthetic */ void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        super.onSurfaceTextureUpdated(surfaceTexture);
    }

    public GLTextureViewRenderThread(TextureView textureView, TextureViewMapRenderer textureViewMapRenderer) {
        super(textureView, textureViewMapRenderer);
        this.eglHolder = new EGLHolder(new WeakReference(textureView), textureViewMapRenderer.isTranslucentSurface());
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        int i;
        Runnable runnableRemove;
        int i2;
        boolean z;
        boolean z2;
        while (true) {
            try {
                synchronized (this.lock) {
                    while (!this.shouldExit) {
                        i = -1;
                        if (this.eventQueue.isEmpty()) {
                            if (this.destroySurface) {
                                this.eglHolder.destroySurface();
                                this.destroySurface = false;
                            } else if (this.destroyContext) {
                                this.eglHolder.destroyContext();
                                this.destroyContext = false;
                            } else if (this.surfaceTexture == null || this.paused || !this.requestRender) {
                                this.lock.wait();
                            } else {
                                i = this.width;
                                int i3 = this.height;
                                if (this.eglHolder.eglContext == EGL10.EGL_NO_CONTEXT) {
                                    z = true;
                                    i2 = i3;
                                    runnableRemove = null;
                                    z2 = false;
                                } else if (this.eglHolder.eglSurface == EGL10.EGL_NO_SURFACE) {
                                    z2 = true;
                                    i2 = i3;
                                    runnableRemove = null;
                                    z = false;
                                } else {
                                    this.requestRender = false;
                                    i2 = i3;
                                    runnableRemove = null;
                                }
                            }
                            i2 = -1;
                            runnableRemove = null;
                        } else {
                            runnableRemove = this.eventQueue.remove(0);
                            i2 = -1;
                        }
                        z = false;
                        z2 = false;
                    }
                    this.eglHolder.cleanup();
                    synchronized (this.lock) {
                        this.exited = true;
                        this.lock.notifyAll();
                    }
                    return;
                }
                if (runnableRemove != null) {
                    runnableRemove.run();
                } else {
                    this.eglHolder.createGL();
                    if (z) {
                        this.eglHolder.prepare();
                        synchronized (this.lock) {
                            if (this.eglHolder.createSurface()) {
                                this.mapRenderer.onSurfaceCreated(null);
                                this.mapRenderer.onSurfaceChanged(i, i2);
                            } else {
                                this.destroySurface = true;
                            }
                        }
                    } else if (z2) {
                        synchronized (this.lock) {
                            this.eglHolder.createSurface();
                        }
                        this.mapRenderer.onSurfaceChanged(i, i2);
                    } else if (this.sizeChanged) {
                        this.mapRenderer.onSurfaceChanged(i, i2);
                        this.sizeChanged = false;
                    } else if (this.eglHolder.eglSurface != EGL10.EGL_NO_SURFACE) {
                        this.mapRenderer.onDrawFrame();
                        int iSwap = this.eglHolder.swap();
                        if (iSwap == 12288) {
                            continue;
                        } else if (iSwap != 12302) {
                            Logger.w("Mbgl-TextureViewRenderThread", String.format("eglSwapBuffer error: %s. Waiting or new surface", Integer.valueOf(iSwap)));
                            synchronized (this.lock) {
                                this.surfaceTexture = null;
                                this.destroySurface = true;
                            }
                        } else {
                            Logger.w("Mbgl-TextureViewRenderThread", "Context lost. Waiting for re-aquire");
                            synchronized (this.lock) {
                                this.surfaceTexture = null;
                                this.destroySurface = true;
                                this.destroyContext = true;
                            }
                        }
                    }
                }
            } catch (InterruptedException unused) {
                this.eglHolder.cleanup();
                synchronized (this.lock) {
                    this.exited = true;
                    this.lock.notifyAll();
                    return;
                }
            } catch (Throwable th) {
                this.eglHolder.cleanup();
                synchronized (this.lock) {
                    this.exited = true;
                    this.lock.notifyAll();
                    throw th;
                }
            }
        }
    }

    private static class EGLHolder {
        private static final int EGL_CONTEXT_CLIENT_VERSION = 12440;
        private EGL10 egl;
        private EGLConfig eglConfig;
        private final WeakReference<TextureView> textureViewWeakRef;
        private boolean translucentSurface;
        private EGLDisplay eglDisplay = EGL10.EGL_NO_DISPLAY;
        private EGLContext eglContext = EGL10.EGL_NO_CONTEXT;
        private EGLSurface eglSurface = EGL10.EGL_NO_SURFACE;

        EGLHolder(WeakReference<TextureView> weakReference, boolean z) {
            this.textureViewWeakRef = weakReference;
            this.translucentSurface = z;
        }

        void prepare() {
            this.egl = (EGL10) EGLContext.getEGL();
            if (this.eglDisplay == EGL10.EGL_NO_DISPLAY) {
                EGLDisplay eGLDisplayEglGetDisplay = this.egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
                this.eglDisplay = eGLDisplayEglGetDisplay;
                if (eGLDisplayEglGetDisplay == EGL10.EGL_NO_DISPLAY) {
                    throw new RuntimeException("eglGetDisplay failed");
                }
                if (!this.egl.eglInitialize(this.eglDisplay, new int[2])) {
                    throw new RuntimeException("eglInitialize failed");
                }
            }
            if (this.textureViewWeakRef == null) {
                this.eglConfig = null;
                this.eglContext = EGL10.EGL_NO_CONTEXT;
            } else if (this.eglContext == EGL10.EGL_NO_CONTEXT) {
                EGLConfig eGLConfigChooseConfig = new EGLConfigChooser(this.translucentSurface).chooseConfig(this.egl, this.eglDisplay);
                this.eglConfig = eGLConfigChooseConfig;
                this.eglContext = this.egl.eglCreateContext(this.eglDisplay, eGLConfigChooseConfig, EGL10.EGL_NO_CONTEXT, new int[]{EGL_CONTEXT_CLIENT_VERSION, 2, 12344});
            }
            if (this.eglContext == EGL10.EGL_NO_CONTEXT) {
                throw new RuntimeException("createContext");
            }
        }

        GL10 createGL() {
            return (GL10) this.eglContext.getGL();
        }

        boolean createSurface() {
            destroySurface();
            TextureView textureView = this.textureViewWeakRef.get();
            if (textureView != null && textureView.getSurfaceTexture() != null) {
                this.eglSurface = this.egl.eglCreateWindowSurface(this.eglDisplay, this.eglConfig, textureView.getSurfaceTexture(), new int[]{12344});
            } else {
                this.eglSurface = EGL10.EGL_NO_SURFACE;
            }
            EGLSurface eGLSurface = this.eglSurface;
            if (eGLSurface == null || eGLSurface == EGL10.EGL_NO_SURFACE) {
                if (this.egl.eglGetError() != 12299) {
                    return false;
                }
                Logger.e("Mbgl-TextureViewRenderThread", "createWindowSurface returned EGL_BAD_NATIVE_WINDOW.");
                return false;
            }
            return makeCurrent();
        }

        boolean makeCurrent() {
            EGL10 egl10 = this.egl;
            EGLDisplay eGLDisplay = this.eglDisplay;
            EGLSurface eGLSurface = this.eglSurface;
            if (egl10.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, this.eglContext)) {
                return true;
            }
            Logger.w("Mbgl-TextureViewRenderThread", String.format("eglMakeCurrent: %s", Integer.valueOf(this.egl.eglGetError())));
            return false;
        }

        int swap() {
            if (this.egl.eglSwapBuffers(this.eglDisplay, this.eglSurface)) {
                return 12288;
            }
            return this.egl.eglGetError();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void destroySurface() {
            if (this.eglSurface == EGL10.EGL_NO_SURFACE) {
                return;
            }
            if (!this.egl.eglDestroySurface(this.eglDisplay, this.eglSurface)) {
                Logger.w("Mbgl-TextureViewRenderThread", String.format("Could not destroy egl surface. Display %s, Surface %s", this.eglDisplay, this.eglSurface));
            }
            this.eglSurface = EGL10.EGL_NO_SURFACE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void destroyContext() {
            if (this.eglContext == EGL10.EGL_NO_CONTEXT) {
                return;
            }
            if (!this.egl.eglDestroyContext(this.eglDisplay, this.eglContext)) {
                Logger.w("Mbgl-TextureViewRenderThread", String.format("Could not destroy egl context. Display %s, Context %s", this.eglDisplay, this.eglContext));
            }
            this.eglContext = EGL10.EGL_NO_CONTEXT;
        }

        private void terminate() {
            if (this.eglDisplay == EGL10.EGL_NO_DISPLAY) {
                return;
            }
            if (!this.egl.eglTerminate(this.eglDisplay)) {
                Logger.w("Mbgl-TextureViewRenderThread", String.format("Could not terminate egl. Display %s", this.eglDisplay));
            }
            this.eglDisplay = EGL10.EGL_NO_DISPLAY;
        }

        void cleanup() {
            destroySurface();
            destroyContext();
            terminate();
        }
    }
}
