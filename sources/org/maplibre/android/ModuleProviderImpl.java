package org.maplibre.android;

import org.maplibre.android.http.HttpRequest;
import org.maplibre.android.module.http.HttpRequestImpl;
import org.maplibre.android.module.loader.LibraryLoaderProviderImpl;

/* loaded from: classes2.dex */
public class ModuleProviderImpl implements ModuleProvider {
    @Override // org.maplibre.android.ModuleProvider
    public HttpRequest createHttpRequest() {
        return new HttpRequestImpl();
    }

    @Override // org.maplibre.android.ModuleProvider
    public LibraryLoaderProvider createLibraryLoaderProvider() {
        return new LibraryLoaderProviderImpl();
    }
}
