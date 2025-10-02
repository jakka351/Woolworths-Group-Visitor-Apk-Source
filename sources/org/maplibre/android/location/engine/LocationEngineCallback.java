package org.maplibre.android.location.engine;

/* loaded from: classes2.dex */
public interface LocationEngineCallback<T> {
    void onFailure(Exception exc);

    void onSuccess(T t);
}
