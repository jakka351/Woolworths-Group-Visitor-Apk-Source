package org.maplibre.android.net;

import org.maplibre.android.LibraryLoader;

/* loaded from: classes2.dex */
class NativeConnectivityListener implements ConnectivityListener {
    private boolean invalidated;
    private long nativePtr;

    protected native void finalize() throws Throwable;

    protected native void initialize();

    protected native void nativeOnConnectivityStateChanged(boolean z);

    static {
        LibraryLoader.load();
    }

    NativeConnectivityListener(long j) {
        this.nativePtr = j;
    }

    NativeConnectivityListener() {
        initialize();
    }

    @Override // org.maplibre.android.net.ConnectivityListener
    public void onNetworkStateChanged(boolean z) {
        nativeOnConnectivityStateChanged(z);
    }
}
