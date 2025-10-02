package org.maplibre.android.maps.renderer;

/* loaded from: classes2.dex */
class MapRendererRunnable implements Runnable {
    private final long nativePtr;

    private native void nativeInitialize();

    protected native void finalize() throws Throwable;

    @Override // java.lang.Runnable
    public native void run();

    MapRendererRunnable(long j) {
        this.nativePtr = j;
    }
}
