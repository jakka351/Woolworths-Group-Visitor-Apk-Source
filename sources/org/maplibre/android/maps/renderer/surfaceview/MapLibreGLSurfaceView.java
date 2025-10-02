package org.maplibre.android.maps.renderer.surfaceview;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import java.lang.ref.WeakReference;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
import org.maplibre.android.maps.renderer.egl.EGLLogWrapper;
import org.maplibre.android.maps.renderer.surfaceview.MapLibreSurfaceView;

/* loaded from: classes2.dex */
public class MapLibreGLSurfaceView extends MapLibreSurfaceView {
    private GLSurfaceView.EGLConfigChooser eglConfigChooser;
    private GLSurfaceView.EGLContextFactory eglContextFactory;
    private GLSurfaceView.EGLWindowSurfaceFactory eglWindowSurfaceFactory;
    private boolean preserveEGLContextOnPause;
    protected final WeakReference<MapLibreGLSurfaceView> viewWeakReference;

    public MapLibreGLSurfaceView(Context context) {
        super(context);
        this.viewWeakReference = new WeakReference<>(this);
    }

    public MapLibreGLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.viewWeakReference = new WeakReference<>(this);
    }

    public void setPreserveEGLContextOnPause(boolean z) {
        this.preserveEGLContextOnPause = z;
    }

    public boolean getPreserveEGLContextOnPause() {
        return this.preserveEGLContextOnPause;
    }

    @Override // org.maplibre.android.maps.renderer.surfaceview.MapLibreSurfaceView
    public void setRenderer(SurfaceViewMapRenderer surfaceViewMapRenderer) {
        if (this.eglConfigChooser == null) {
            throw new IllegalStateException("No eglConfigChooser provided");
        }
        if (this.eglContextFactory == null) {
            throw new IllegalStateException("No eglContextFactory provided");
        }
        if (this.eglWindowSurfaceFactory == null) {
            throw new IllegalStateException("No eglWindowSurfaceFactory provided");
        }
        super.setRenderer(surfaceViewMapRenderer);
    }

    public void setEGLContextFactory(GLSurfaceView.EGLContextFactory eGLContextFactory) {
        checkRenderThreadState();
        this.eglContextFactory = eGLContextFactory;
    }

    public void setEGLWindowSurfaceFactory(GLSurfaceView.EGLWindowSurfaceFactory eGLWindowSurfaceFactory) {
        checkRenderThreadState();
        this.eglWindowSurfaceFactory = eGLWindowSurfaceFactory;
    }

    public void setEGLConfigChooser(GLSurfaceView.EGLConfigChooser eGLConfigChooser) {
        checkRenderThreadState();
        this.eglConfigChooser = eGLConfigChooser;
    }

    @Override // org.maplibre.android.maps.renderer.surfaceview.MapLibreSurfaceView
    protected void createRenderThread() {
        this.renderThread = new GLThread(this.viewWeakReference);
    }

    private static class EglHelper {
        EGL10 mEgl;
        EGLConfig mEglConfig;
        EGLContext mEglContext;
        EGLDisplay mEglDisplay;
        EGLSurface mEglSurface;
        private WeakReference<MapLibreGLSurfaceView> mGLSurfaceViewWeakRef;

        private EglHelper(WeakReference<MapLibreGLSurfaceView> weakReference) {
            this.mGLSurfaceViewWeakRef = weakReference;
        }

        public void start() {
            EGLDisplay eGLDisplayEglGetDisplay;
            try {
                EGL10 egl10 = (EGL10) EGLContext.getEGL();
                this.mEgl = egl10;
                eGLDisplayEglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
                this.mEglDisplay = eGLDisplayEglGetDisplay;
            } catch (Exception e) {
                Log.e("MapLibreSurfaceView", "createContext failed: ", e);
            }
            if (eGLDisplayEglGetDisplay == EGL10.EGL_NO_DISPLAY) {
                Log.e("MapLibreSurfaceView", "eglGetDisplay failed");
                return;
            }
            if (!this.mEgl.eglInitialize(this.mEglDisplay, new int[2])) {
                Log.e("MapLibreSurfaceView", "eglInitialize failed");
                return;
            }
            MapLibreGLSurfaceView mapLibreGLSurfaceView = this.mGLSurfaceViewWeakRef.get();
            if (mapLibreGLSurfaceView == null) {
                this.mEglConfig = null;
                this.mEglContext = null;
            } else {
                EGLConfig eGLConfigChooseConfig = mapLibreGLSurfaceView.eglConfigChooser.chooseConfig(this.mEgl, this.mEglDisplay);
                this.mEglConfig = eGLConfigChooseConfig;
                if (eGLConfigChooseConfig == null) {
                    Log.e("MapLibreSurfaceView", "failed to select an EGL configuration");
                    return;
                }
                this.mEglContext = mapLibreGLSurfaceView.eglContextFactory.createContext(this.mEgl, this.mEglDisplay, this.mEglConfig);
            }
            EGLContext eGLContext = this.mEglContext;
            if (eGLContext == null || eGLContext == EGL10.EGL_NO_CONTEXT) {
                this.mEglContext = null;
                Log.e("MapLibreSurfaceView", "createContext failed");
                return;
            }
            this.mEglSurface = null;
        }

        boolean createSurface() {
            if (this.mEgl == null) {
                Log.e("MapLibreSurfaceView", "egl not initialized");
                return false;
            }
            if (this.mEglDisplay == null) {
                Log.e("MapLibreSurfaceView", "eglDisplay not initialized");
                return false;
            }
            if (this.mEglConfig == null) {
                Log.e("MapLibreSurfaceView", "mEglConfig not initialized");
                return false;
            }
            destroySurfaceImp();
            MapLibreGLSurfaceView mapLibreGLSurfaceView = this.mGLSurfaceViewWeakRef.get();
            if (mapLibreGLSurfaceView != null) {
                this.mEglSurface = mapLibreGLSurfaceView.eglWindowSurfaceFactory.createWindowSurface(this.mEgl, this.mEglDisplay, this.mEglConfig, mapLibreGLSurfaceView.getHolder());
            } else {
                this.mEglSurface = null;
            }
            EGLSurface eGLSurface = this.mEglSurface;
            if (eGLSurface == null || eGLSurface == EGL10.EGL_NO_SURFACE) {
                if (this.mEgl.eglGetError() == 12299) {
                    Log.e("MapLibreSurfaceView", "createWindowSurface returned EGL_BAD_NATIVE_WINDOW.");
                }
                return false;
            }
            EGL10 egl10 = this.mEgl;
            EGLDisplay eGLDisplay = this.mEglDisplay;
            EGLSurface eGLSurface2 = this.mEglSurface;
            if (egl10.eglMakeCurrent(eGLDisplay, eGLSurface2, eGLSurface2, this.mEglContext)) {
                return true;
            }
            logEglErrorAsWarning("MapLibreSurfaceView", "eglMakeCurrent", this.mEgl.eglGetError());
            return false;
        }

        GL createGL() {
            return this.mEglContext.getGL();
        }

        public int swap() {
            if (this.mEgl.eglSwapBuffers(this.mEglDisplay, this.mEglSurface)) {
                return 12288;
            }
            return this.mEgl.eglGetError();
        }

        void destroySurface() {
            destroySurfaceImp();
        }

        private void destroySurfaceImp() {
            EGLSurface eGLSurface = this.mEglSurface;
            if (eGLSurface == null || eGLSurface == EGL10.EGL_NO_SURFACE) {
                return;
            }
            this.mEgl.eglMakeCurrent(this.mEglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
            MapLibreGLSurfaceView mapLibreGLSurfaceView = this.mGLSurfaceViewWeakRef.get();
            if (mapLibreGLSurfaceView != null) {
                mapLibreGLSurfaceView.eglWindowSurfaceFactory.destroySurface(this.mEgl, this.mEglDisplay, this.mEglSurface);
            }
            this.mEglSurface = null;
        }

        public void finish() {
            if (this.mEglContext != null) {
                MapLibreGLSurfaceView mapLibreGLSurfaceView = this.mGLSurfaceViewWeakRef.get();
                if (mapLibreGLSurfaceView != null) {
                    mapLibreGLSurfaceView.eglContextFactory.destroyContext(this.mEgl, this.mEglDisplay, this.mEglContext);
                }
                this.mEglContext = null;
            }
            EGLDisplay eGLDisplay = this.mEglDisplay;
            if (eGLDisplay != null) {
                this.mEgl.eglTerminate(eGLDisplay);
                this.mEglDisplay = null;
            }
        }

        static void logEglErrorAsWarning(String str, String str2, int i) {
            Log.w(str, formatEglError(str2, i));
        }

        static String formatEglError(String str, int i) {
            return str + " failed: " + EGLLogWrapper.getErrorString(i);
        }
    }

    static class GLThread extends MapLibreSurfaceView.RenderThread {
        private EglHelper eglHelper;
        private boolean finishedCreatingEglSurface;
        private boolean haveEglContext;
        private boolean haveEglSurface;
        protected WeakReference<MapLibreGLSurfaceView> mSurfaceViewWeakRef;
        private boolean shouldReleaseEglContext;
        private boolean surfaceIsBad;

        GLThread(WeakReference<MapLibreGLSurfaceView> weakReference) {
            this.mSurfaceViewWeakRef = weakReference;
        }

        private void stopEglSurfaceLocked() {
            if (this.haveEglSurface) {
                this.haveEglSurface = false;
                this.eglHelper.destroySurface();
            }
        }

        private void stopEglContextLocked() {
            if (this.haveEglContext) {
                this.eglHelper.finish();
                this.haveEglContext = false;
                MapLibreSurfaceView.renderThreadManager.notifyAll();
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:119:0x0179 A[Catch: all -> 0x0208, TryCatch #6 {all -> 0x0208, blocks: (B:3:0x001f, B:4:0x0021, B:96:0x0141, B:98:0x014a, B:100:0x0152, B:101:0x0154, B:108:0x0162, B:109:0x0163, B:110:0x0165, B:117:0x0176, B:119:0x0179, B:121:0x0184, B:123:0x018e, B:128:0x019b, B:130:0x01a5, B:132:0x01ab, B:134:0x01b5, B:136:0x01bc, B:137:0x01c0, B:141:0x01ce, B:142:0x01d7, B:149:0x01e4, B:161:0x0207, B:103:0x0156, B:104:0x015d, B:144:0x01d9, B:145:0x01e0, B:112:0x0167, B:113:0x0170, B:5:0x0022, B:7:0x0026, B:16:0x0035, B:18:0x003d, B:94:0x013e, B:19:0x004a, B:21:0x0050, B:23:0x005d, B:25:0x0061, B:27:0x006d, B:29:0x0076, B:31:0x007a, B:33:0x007f, B:35:0x0083, B:37:0x008d, B:42:0x0098, B:43:0x009b, B:45:0x009f, B:47:0x00a3, B:49:0x00a7, B:50:0x00aa, B:51:0x00b5, B:53:0x00b9, B:55:0x00bd, B:57:0x00c7, B:58:0x00d3, B:60:0x00d7, B:61:0x00dc, B:63:0x00e2, B:67:0x00ea, B:69:0x00f0, B:70:0x00f9, B:71:0x00fe, B:80:0x010d, B:82:0x0111, B:84:0x0115, B:85:0x011b, B:87:0x011f, B:89:0x0123, B:91:0x0132, B:158:0x01fd, B:157:0x01f2), top: B:182:0x001f, inners: #0, #1, #4, #5 }] */
        /* JADX WARN: Removed duplicated region for block: B:121:0x0184 A[Catch: all -> 0x0208, TryCatch #6 {all -> 0x0208, blocks: (B:3:0x001f, B:4:0x0021, B:96:0x0141, B:98:0x014a, B:100:0x0152, B:101:0x0154, B:108:0x0162, B:109:0x0163, B:110:0x0165, B:117:0x0176, B:119:0x0179, B:121:0x0184, B:123:0x018e, B:128:0x019b, B:130:0x01a5, B:132:0x01ab, B:134:0x01b5, B:136:0x01bc, B:137:0x01c0, B:141:0x01ce, B:142:0x01d7, B:149:0x01e4, B:161:0x0207, B:103:0x0156, B:104:0x015d, B:144:0x01d9, B:145:0x01e0, B:112:0x0167, B:113:0x0170, B:5:0x0022, B:7:0x0026, B:16:0x0035, B:18:0x003d, B:94:0x013e, B:19:0x004a, B:21:0x0050, B:23:0x005d, B:25:0x0061, B:27:0x006d, B:29:0x0076, B:31:0x007a, B:33:0x007f, B:35:0x0083, B:37:0x008d, B:42:0x0098, B:43:0x009b, B:45:0x009f, B:47:0x00a3, B:49:0x00a7, B:50:0x00aa, B:51:0x00b5, B:53:0x00b9, B:55:0x00bd, B:57:0x00c7, B:58:0x00d3, B:60:0x00d7, B:61:0x00dc, B:63:0x00e2, B:67:0x00ea, B:69:0x00f0, B:70:0x00f9, B:71:0x00fe, B:80:0x010d, B:82:0x0111, B:84:0x0115, B:85:0x011b, B:87:0x011f, B:89:0x0123, B:91:0x0132, B:158:0x01fd, B:157:0x01f2), top: B:182:0x001f, inners: #0, #1, #4, #5 }] */
        /* JADX WARN: Removed duplicated region for block: B:126:0x0198  */
        /* JADX WARN: Removed duplicated region for block: B:128:0x019b A[Catch: all -> 0x0208, TryCatch #6 {all -> 0x0208, blocks: (B:3:0x001f, B:4:0x0021, B:96:0x0141, B:98:0x014a, B:100:0x0152, B:101:0x0154, B:108:0x0162, B:109:0x0163, B:110:0x0165, B:117:0x0176, B:119:0x0179, B:121:0x0184, B:123:0x018e, B:128:0x019b, B:130:0x01a5, B:132:0x01ab, B:134:0x01b5, B:136:0x01bc, B:137:0x01c0, B:141:0x01ce, B:142:0x01d7, B:149:0x01e4, B:161:0x0207, B:103:0x0156, B:104:0x015d, B:144:0x01d9, B:145:0x01e0, B:112:0x0167, B:113:0x0170, B:5:0x0022, B:7:0x0026, B:16:0x0035, B:18:0x003d, B:94:0x013e, B:19:0x004a, B:21:0x0050, B:23:0x005d, B:25:0x0061, B:27:0x006d, B:29:0x0076, B:31:0x007a, B:33:0x007f, B:35:0x0083, B:37:0x008d, B:42:0x0098, B:43:0x009b, B:45:0x009f, B:47:0x00a3, B:49:0x00a7, B:50:0x00aa, B:51:0x00b5, B:53:0x00b9, B:55:0x00bd, B:57:0x00c7, B:58:0x00d3, B:60:0x00d7, B:61:0x00dc, B:63:0x00e2, B:67:0x00ea, B:69:0x00f0, B:70:0x00f9, B:71:0x00fe, B:80:0x010d, B:82:0x0111, B:84:0x0115, B:85:0x011b, B:87:0x011f, B:89:0x0123, B:91:0x0132, B:158:0x01fd, B:157:0x01f2), top: B:182:0x001f, inners: #0, #1, #4, #5 }] */
        /* JADX WARN: Removed duplicated region for block: B:134:0x01b5 A[Catch: all -> 0x0208, TryCatch #6 {all -> 0x0208, blocks: (B:3:0x001f, B:4:0x0021, B:96:0x0141, B:98:0x014a, B:100:0x0152, B:101:0x0154, B:108:0x0162, B:109:0x0163, B:110:0x0165, B:117:0x0176, B:119:0x0179, B:121:0x0184, B:123:0x018e, B:128:0x019b, B:130:0x01a5, B:132:0x01ab, B:134:0x01b5, B:136:0x01bc, B:137:0x01c0, B:141:0x01ce, B:142:0x01d7, B:149:0x01e4, B:161:0x0207, B:103:0x0156, B:104:0x015d, B:144:0x01d9, B:145:0x01e0, B:112:0x0167, B:113:0x0170, B:5:0x0022, B:7:0x0026, B:16:0x0035, B:18:0x003d, B:94:0x013e, B:19:0x004a, B:21:0x0050, B:23:0x005d, B:25:0x0061, B:27:0x006d, B:29:0x0076, B:31:0x007a, B:33:0x007f, B:35:0x0083, B:37:0x008d, B:42:0x0098, B:43:0x009b, B:45:0x009f, B:47:0x00a3, B:49:0x00a7, B:50:0x00aa, B:51:0x00b5, B:53:0x00b9, B:55:0x00bd, B:57:0x00c7, B:58:0x00d3, B:60:0x00d7, B:61:0x00dc, B:63:0x00e2, B:67:0x00ea, B:69:0x00f0, B:70:0x00f9, B:71:0x00fe, B:80:0x010d, B:82:0x0111, B:84:0x0115, B:85:0x011b, B:87:0x011f, B:89:0x0123, B:91:0x0132, B:158:0x01fd, B:157:0x01f2), top: B:182:0x001f, inners: #0, #1, #4, #5 }] */
        /* JADX WARN: Removed duplicated region for block: B:139:0x01ca  */
        /* JADX WARN: Removed duplicated region for block: B:151:0x01e8  */
        /* JADX WARN: Removed duplicated region for block: B:153:0x01eb  */
        /* JADX WARN: Removed duplicated region for block: B:177:0x020c A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // org.maplibre.android.maps.renderer.surfaceview.MapLibreSurfaceView.RenderThread
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        protected void guardedRun() throws java.lang.InterruptedException {
            /*
                Method dump skipped, instructions count: 535
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.maplibre.android.maps.renderer.surfaceview.MapLibreGLSurfaceView.GLThread.guardedRun():void");
        }

        @Override // org.maplibre.android.maps.renderer.surfaceview.MapLibreSurfaceView.RenderThread
        protected boolean readyToDraw() {
            return super.readyToDraw() && !this.surfaceIsBad;
        }

        @Override // org.maplibre.android.maps.renderer.surfaceview.MapLibreSurfaceView.RenderThread
        public boolean ableToDraw() {
            return this.haveEglContext && this.haveEglSurface && readyToDraw();
        }

        @Override // org.maplibre.android.maps.renderer.surfaceview.MapLibreSurfaceView.RenderThread
        public void surfaceCreated() {
            synchronized (MapLibreSurfaceView.renderThreadManager) {
                this.hasSurface = true;
                this.finishedCreatingEglSurface = false;
                MapLibreSurfaceView.renderThreadManager.notifyAll();
                while (this.waitingForSurface && !this.finishedCreatingEglSurface && !this.exited) {
                    try {
                        MapLibreSurfaceView.renderThreadManager.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void requestReleaseEglContextLocked() {
            this.shouldReleaseEglContext = true;
            MapLibreSurfaceView.renderThreadManager.notifyAll();
        }
    }
}
