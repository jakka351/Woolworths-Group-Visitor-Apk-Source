package org.maplibre.android.style.layers;

/* loaded from: classes2.dex */
public class CustomLayer extends Layer {
    @Override // org.maplibre.android.style.layers.Layer
    protected native void finalize() throws Throwable;

    protected native void initialize(String str, long j);

    @Deprecated
    public void update() {
    }

    public CustomLayer(String str, long j) {
        initialize(str, j);
    }

    CustomLayer(long j) {
        super(j);
    }
}
